package com.custom.launcher.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.custom.launcher.R;
import com.custom.launcher.UsbDebugActivity;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Enumeration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Background proxy service:
 * Listens on IPv4 0.0.0.0:35555 and proxies traffic to localhost IPv6 ::1:5555.
 */
public class AdbV4ProxyService extends Service {
    private static final String TAG = "AdbV4ProxyService";

    public static final String ACTION_START = "com.custom.launcher.action.START_ADB_V4_PROXY";
    public static final String ACTION_STOP = "com.custom.launcher.action.STOP_ADB_V4_PROXY";

    public static final int LISTEN_PORT_V4 = 35555;
    public static final int TARGET_PORT_V6 = 5555;

    private static final String CHANNEL_ID = "adb_v4_proxy_channel";
    private static final int NOTIFICATION_ID = 35555;

    private volatile boolean running = false;
    private volatile ServerSocket serverSocket;
    private Thread acceptThread;

    private final Set<Socket> clientSockets = Collections.synchronizedSet(new HashSet<>());

    private static volatile boolean proxyRunning = false;

    // Diagnostics (readable from UI)
    private static volatile String lastStatus = "(not started)";
    private static volatile String lastError = "";
    private static volatile String boundAddress = "";
    private static final AtomicInteger acceptedClients = new AtomicInteger(0);
    private static final AtomicInteger activeBridges = new AtomicInteger(0);
    private static final AtomicInteger targetConnectFailures = new AtomicInteger(0);

    public static boolean isProxyRunning() {
        return proxyRunning;
    }

    public static String getLastStatus() {
        return lastStatus;
    }

    public static String getLastError() {
        return lastError;
    }

    public static String getBoundAddress() {
        return boundAddress;
    }

    public static int getAcceptedClients() {
        return acceptedClients.get();
    }

    public static int getActiveBridges() {
        return activeBridges.get();
    }

    public static int getTargetConnectFailures() {
        return targetConnectFailures.get();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent != null ? intent.getAction() : ACTION_START;

        if (ACTION_STOP.equals(action)) {
            stopProxy();
            stopForeground(true);
            stopSelf();
            return START_NOT_STICKY;
        }

        startForeground(NOTIFICATION_ID, buildNotification("Starting IPv4 ADB proxy..."));

        if (!running) {
            startProxy();
        } else {
            updateNotification("IPv4 ADB proxy active on port " + LISTEN_PORT_V4);
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopProxy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private synchronized void startProxy() {
        if (running) {
            return;
        }

        running = true;
        proxyRunning = true;

        acceptThread = new Thread(() -> {
            try {
                // Hint Java networking to prefer IPv4 sockets.
                System.setProperty("java.net.preferIPv4Stack", "true");

                // Bind explicitly to IPv4 wildcard address.
                serverSocket = new ServerSocket();
                serverSocket.setReuseAddress(true);

                InetAddress bindAddress = findBestIpv4BindAddress();
                serverSocket.bind(new InetSocketAddress(bindAddress, LISTEN_PORT_V4));

                boundAddress = bindAddress.getHostAddress();
                lastStatus = "LISTENING " + boundAddress + ":" + LISTEN_PORT_V4;
                lastError = "";

                Log.i(TAG, "Listening on " + bindAddress.getHostAddress() + ":" + LISTEN_PORT_V4
                        + " -> ::1:" + TARGET_PORT_V6);
                updateNotification("IPv4 ADB proxy active on port " + LISTEN_PORT_V4);

                while (running && !serverSocket.isClosed()) {
                    Socket client = serverSocket.accept();
                    acceptedClients.incrementAndGet();
                    clientSockets.add(client);
                    handleClient(client);
                }
            } catch (Exception e) {
                if (running) {
                    Log.e(TAG, "Proxy accept loop failed", e);
                    lastStatus = "FAILED";
                    lastError = e.getClass().getSimpleName() + ": " + e.getMessage();
                    updateNotification("Proxy failed: " + e.getClass().getSimpleName());
                }
            } finally {
                running = false;
                proxyRunning = false;
                activeBridges.set(0);
            }
        }, "adb-v4-proxy-accept");

        acceptThread.start();
    }

    private void handleClient(Socket client) {
        Thread t = new Thread(() -> {
            Socket target = null;
            try {
                client.setTcpNoDelay(true);
                target = connectToAdbdTarget();
                target.setTcpNoDelay(true);

                activeBridges.incrementAndGet();
                lastStatus = "BRIDGING (active=" + activeBridges.get() + ")";

                Socket finalTarget = target;
                Thread cToT = new Thread(() -> pipe(client, finalTarget), "adb-v4-c2t");
                Thread tToC = new Thread(() -> pipe(finalTarget, client), "adb-v4-t2c");
                cToT.start();
                tToC.start();
                cToT.join();
                tToC.join();
            } catch (Exception e) {
                Log.w(TAG, "Client bridge failed: " + e.getMessage());
                lastError = e.getClass().getSimpleName() + ": " + e.getMessage();
            } finally {
                closeQuietly(client);
                closeQuietly(target);
                clientSockets.remove(client);
                if (activeBridges.get() > 0) {
                    activeBridges.decrementAndGet();
                }
            }
        }, "adb-v4-client");
        t.start();
    }

    private Socket connectToAdbdTarget() throws Exception {
        // Try IPv6 loopback first (common when adbd shows tcp6).
        Exception last = null;
        for (InetAddress addr : new InetAddress[] {
                InetAddress.getByName("::1"),
                InetAddress.getByName("127.0.0.1"),
        }) {
            try {
                Socket s = new Socket();
                s.connect(new InetSocketAddress(addr, TARGET_PORT_V6), 1500);
                return s;
            } catch (Exception e) {
                last = e;
            }
        }

        targetConnectFailures.incrementAndGet();
        lastStatus = "TARGET CONNECT FAILED";
        if (last != null) {
            lastError = last.getClass().getSimpleName() + ": " + last.getMessage();
        }
        throw last != null ? last : new SocketTimeoutException("connect failed");
    }

    private void pipe(Socket inSocket, Socket outSocket) {
        try {
            InputStream in = inSocket.getInputStream();
            OutputStream out = outSocket.getOutputStream();
            byte[] buffer = new byte[8192];
            int read;
            while (running && (read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
                out.flush();
            }

            // Propagate EOF to the other side so ADB can complete its handshake/teardown
            // cleanly.
            try {
                outSocket.shutdownOutput();
            } catch (Exception ignored) {
            }
        } catch (Exception ignored) {
            // Normal when peer disconnects.
        }
    }

    private synchronized void stopProxy() {
        running = false;
        proxyRunning = false;

        closeQuietly(serverSocket);
        serverSocket = null;

        synchronized (clientSockets) {
            for (Socket socket : clientSockets) {
                closeQuietly(socket);
            }
            clientSockets.clear();
        }

        if (acceptThread != null) {
            acceptThread.interrupt();
            acceptThread = null;
        }

        Log.i(TAG, "ADB IPv4 proxy stopped");
    }

    private Notification buildNotification(String content) {
        createNotificationChannelIfNeeded();

        Intent openIntent = new Intent(this, UsbDebugActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                openIntent,
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        ? PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
                        : PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("ADB IPv4 Proxy")
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
    }

    private void updateNotification(String content) {
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (nm != null) {
            nm.notify(NOTIFICATION_ID, buildNotification(content));
        }
    }

    private void createNotificationChannelIfNeeded() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (nm == null) {
            return;
        }

        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "ADB IPv4 Proxy",
                NotificationManager.IMPORTANCE_LOW);
        channel.setDescription("Foreground service for IPv4 to IPv6 ADB proxy");
        nm.createNotificationChannel(channel);
    }

    private void closeQuietly(ServerSocket socket) {
        if (socket == null) {
            return;
        }
        try {
            socket.close();
        } catch (Exception ignored) {
        }
    }

    private void closeQuietly(Socket socket) {
        if (socket == null) {
            return;
        }
        try {
            socket.close();
        } catch (Exception ignored) {
        }
    }

    private InetAddress findBestIpv4BindAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces != null && interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                        // Prefer site-local address (e.g. 192.168.x.x) for client connectivity.
                        if (addr.isSiteLocalAddress()) {
                            return addr;
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.w(TAG, "Failed to enumerate interfaces for IPv4 bind, falling back to 0.0.0.0", e);
        }

        try {
            return Inet4Address.getByAddress(new byte[] { 0, 0, 0, 0 });
        } catch (Exception e) {
            return InetAddress.getLoopbackAddress();
        }
    }

}
