package com.mg4.wirelesscarplay;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.net.ConnectivityManager;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * WiFi Direct (P2P) manager for establishing WiFi connection with iPhone.
 * 
 * Flow:
 * 1. iPhone discovers car via BLE advertisement
 * 2. iPhone initiates WiFi Direct connection request
 * 3. This manager accepts the connection
 * 4. WiFi Direct creates ad-hoc network (5 GHz if supported)
 * 5. Socket connection established for CarPlay data
 */
public class WiFiDirectManager {
    private static final String TAG = "WiFiDirectManager";
    
    // CarPlay uses port 8080 for HTTP-based communication
    private static final int CARPLAY_PORT = 8080;
    
    private final Context context;
    private final ConnectionCallback callback;
    
    private WifiP2pManager p2pManager;
    private Channel p2pChannel;
    private BroadcastReceiver p2pReceiver;
    
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private boolean isListening = false;

    public interface ConnectionCallback {
        void onDeviceConnected(String deviceAddress);
        void onDeviceDisconnected();
        void onConnectionFailed(String reason);
    }

    public WiFiDirectManager(Context context, ConnectionCallback callback) {
        this.context = context;
        this.callback = callback;
        if (!BuildConfig.MOCK_MODE) {
            initializeWiFiDirect();
        } else {
            Log.i(TAG, "MOCK MODE: WiFi Direct initialization skipped");
        }
    }

    private void initializeWiFiDirect() {
        try {
            p2pManager = (WifiP2pManager) context.getSystemService(Context.WIFI_P2P_SERVICE);
            
            if (p2pManager == null) {
                Log.e(TAG, "WiFi P2P not supported on this device");
                return;
            }
            
            p2pChannel = p2pManager.initialize(context, context.getMainLooper(), null);
            
            if (p2pChannel == null) {
                Log.e(TAG, "Failed to initialize WiFi P2P channel");
                return;
            }
            
            Log.i(TAG, "WiFi Direct initialized successfully");
            
        } catch (Exception e) {
            Log.e(TAG, "Error initializing WiFi Direct", e);
        }
    }

    public void startListening() {
        if (BuildConfig.MOCK_MODE) {
            Log.i(TAG, "MOCK MODE: WiFi Direct listening simulated - success");
            isListening = true;
            return;
        }
        
        if (isListening) {
            Log.w(TAG, "Already listening for WiFi Direct connections");
            return;
        }
        
        try {
            // Register broadcast receiver for WiFi P2P events
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
            intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
            intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
            intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
            
            p2pReceiver = new WiFiDirectBroadcastReceiver();
            context.registerReceiver(p2pReceiver, intentFilter);
            
            // Start peer discovery
            p2pManager.discoverPeers(p2pChannel, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                    Log.i(TAG, "WiFi Direct peer discovery started");
                    isListening = true;
                }

                @Override
                public void onFailure(int reason) {
                    Log.e(TAG, "WiFi Direct peer discovery failed: " + getFailureReason(reason));
                    callback.onConnectionFailed("Peer discovery failed: " + getFailureReason(reason));
                }
            });
            
            // Create server socket for incoming CarPlay connections
            createServerSocket();
            
        } catch (Exception e) {
            Log.e(TAG, "Error starting WiFi Direct listener", e);
            callback.onConnectionFailed("Exception: " + e.getMessage());
        }
    }

    public void stopListening() {
        isListening = false;
        
        try {
            if (p2pReceiver != null) {
                context.unregisterReceiver(p2pReceiver);
                p2pReceiver = null;
            }
            
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            
            if (p2pManager != null && p2pChannel != null) {
                p2pManager.removeGroup(p2pChannel, null);
            }
            
            Log.i(TAG, "Stopped WiFi Direct listening");
            
        } catch (Exception e) {
            Log.e(TAG, "Error stopping WiFi Direct", e);
        }
    }

    public void cleanup() {
        stopListening();
        
        if (clientSocket != null) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Error closing client socket", e);
            }
        }
    }

    public Socket getSocket() {
        return clientSocket;
    }

    private void createServerSocket() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(CARPLAY_PORT);
                Log.i(TAG, "Server socket listening on port " + CARPLAY_PORT);
                
                while (isListening && !serverSocket.isClosed()) {
                    try {
                        Log.d(TAG, "Waiting for iPhone connection...");
                        clientSocket = serverSocket.accept();
                        
                        String clientAddress = clientSocket.getInetAddress().getHostAddress();
                        Log.i(TAG, "iPhone connected from: " + clientAddress);
                        
                        callback.onDeviceConnected(clientAddress);
                        
                    } catch (IOException e) {
                        if (isListening) {
                            Log.e(TAG, "Error accepting connection", e);
                        }
                    }
                }
                
            } catch (IOException e) {
                Log.e(TAG, "Failed to create server socket", e);
                callback.onConnectionFailed("Server socket error: " + e.getMessage());
            }
        }).start();
    }

    private String getFailureReason(int reason) {
        switch (reason) {
            case WifiP2pManager.ERROR:
                return "Internal error";
            case WifiP2pManager.P2P_UNSUPPORTED:
                return "WiFi P2P not supported";
            case WifiP2pManager.BUSY:
                return "System busy";
            default:
                return "Unknown error: " + reason;
        }
    }

    /**
     * Broadcast receiver for WiFi Direct events
     */
    private class WiFiDirectBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            
            if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
                int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
                if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                    Log.d(TAG, "WiFi P2P is enabled");
                } else {
                    Log.w(TAG, "WiFi P2P is disabled");
                }
                
            } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
                // Request updated peer list
                if (p2pManager != null && p2pChannel != null) {
                    p2pManager.requestPeers(p2pChannel, peerListListener);
                }
                
            } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
                // Connection state changed
                if (p2pManager != null && p2pChannel != null) {
                    p2pManager.requestConnectionInfo(p2pChannel, connectionInfoListener);
                }
                
            } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
                WifiP2pDevice device = intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_DEVICE);
                if (device != null) {
                    Log.d(TAG, "This device: " + device.deviceName + " [" + device.deviceAddress + "]");
                }
            }
        }
    }

    private final PeerListListener peerListListener = peers -> {
        if (peers == null || peers.getDeviceList().isEmpty()) {
            Log.d(TAG, "No peers found");
            return;
        }
        
        Log.i(TAG, "Found " + peers.getDeviceList().size() + " peer(s)");
        
        for (WifiP2pDevice device : peers.getDeviceList()) {
            Log.d(TAG, "Peer: " + device.deviceName + " [" + device.deviceAddress + "]");
            
            // Check if this is an iPhone requesting CarPlay connection
            if (isIPhoneDevice(device)) {
                Log.i(TAG, "Detected iPhone, accepting connection...");
                acceptConnection(device);
            }
        }
    };

    private final ConnectionInfoListener connectionInfoListener = new ConnectionInfoListener() {
        @Override
        public void onConnectionInfoAvailable(WifiP2pInfo info) {
            if (info == null) {
                return;
            }
            
            if (info.groupFormed) {
                Log.i(TAG, "WiFi P2P group formed");
                Log.d(TAG, "Group owner: " + (info.isGroupOwner ? "This device" : "Peer"));
                
                InetAddress groupOwnerAddress = info.groupOwnerAddress;
                if (groupOwnerAddress != null) {
                    Log.d(TAG, "Group owner address: " + groupOwnerAddress.getHostAddress());
                }
                
                if (!info.isGroupOwner && groupOwnerAddress != null) {
                    // We are the client, iPhone is the group owner
                    // Connect to iPhone's server
                    connectToGroupOwner(groupOwnerAddress);
                }
            } else {
                Log.d(TAG, "WiFi P2P group not formed");
                callback.onDeviceDisconnected();
            }
        }
    };

    private boolean isIPhoneDevice(WifiP2pDevice device) {
        // iPhones typically have device names like "iPhone", "John's iPhone", etc.
        String deviceName = device.deviceName != null ? device.deviceName.toLowerCase() : "";
        return deviceName.contains("iphone") || deviceName.contains("ipad");
    }

    private void acceptConnection(WifiP2pDevice device) {
        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = device.deviceAddress;
        
        p2pManager.connect(p2pChannel, config, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "Connection request sent successfully");
            }

            @Override
            public void onFailure(int reason) {
                Log.e(TAG, "Connection request failed: " + getFailureReason(reason));
                callback.onConnectionFailed("Connection failed: " + getFailureReason(reason));
            }
        });
    }

    private void connectToGroupOwner(InetAddress groupOwnerAddress) {
        new Thread(() -> {
            try {
                Log.i(TAG, "Connecting to group owner at " + groupOwnerAddress.getHostAddress());
                Socket socket = new Socket(groupOwnerAddress, CARPLAY_PORT);
                
                clientSocket = socket;
                Log.i(TAG, "Connected to iPhone CarPlay service");
                
                callback.onDeviceConnected(groupOwnerAddress.getHostAddress());
                
            } catch (IOException e) {
                Log.e(TAG, "Failed to connect to group owner", e);
                callback.onConnectionFailed("Connection error: " + e.getMessage());
            }
        }).start();
    }
}
