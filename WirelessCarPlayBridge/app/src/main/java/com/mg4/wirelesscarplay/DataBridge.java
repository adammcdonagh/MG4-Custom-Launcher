package com.mg4.wirelesscarplay;

import android.content.Context;
import android.util.Log;

import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Data Bridge - Routes data between WiFi Direct connection and USB virtual device.
 * 
 * This is the core of the wireless bridge:
 * - Reads CarPlay data from iPhone via WiFi socket
 * - Writes data to USB virtual device (for AllgoCarplay to read)
 * - Reads responses from USB virtual device (from AllgoCarplay)
 * - Sends responses back to iPhone via WiFi
 * 
 * Essentially acts as a transparent proxy between WiFi and USB.
 */
public class DataBridge {
    private static final String TAG = "DataBridge";
    
    private static final int BUFFER_SIZE = 8192; // 8KB buffer
    
    private final Context context;
    
    private Thread wifiToUsbThread;
    private Thread usbToWifiThread;
    private volatile boolean isRunning = false;
    
    private Socket wifiSocket;
    private FileDescriptor usbFd;

    public DataBridge(Context context) {
        this.context = context;
    }

    /**
     * Start the data bridge.
     * 
     * @param wifiSocket Socket connected to iPhone via WiFi Direct
     * @param usbFd File descriptor for USB virtual device
     * @return true if bridge started successfully
     */
    public boolean start(Socket wifiSocket, FileDescriptor usbFd) {
        if (isRunning) {
            Log.w(TAG, "Data bridge already running");
            return false;
        }
        
        if (wifiSocket == null || usbFd == null) {
            Log.e(TAG, "Invalid parameters: wifiSocket=" + wifiSocket + ", usbFd=" + usbFd);
            return false;
        }
        
        this.wifiSocket = wifiSocket;
        this.usbFd = usbFd;
        
        try {
            isRunning = true;
            
            // Start WiFi → USB bridge thread
            wifiToUsbThread = new Thread(this::bridgeWiFiToUsb, "WiFi→USB");
            wifiToUsbThread.start();
            
            // Start USB → WiFi bridge thread
            usbToWifiThread = new Thread(this::bridgeUsbToWiFi, "USB→WiFi");
            usbToWifiThread.start();
            
            Log.i(TAG, "Data bridge started");
            return true;
            
        } catch (Exception e) {
            Log.e(TAG, "Failed to start data bridge", e);
            stop();
            return false;
        }
    }

    /**
     * Stop the data bridge.
     */
    public void stop() {
        if (!isRunning) {
            return;
        }
        
        Log.i(TAG, "Stopping data bridge...");
        
        isRunning = false;
        
        // Interrupt threads
        if (wifiToUsbThread != null) {
            wifiToUsbThread.interrupt();
        }
        if (usbToWifiThread != null) {
            usbToWifiThread.interrupt();
        }
        
        // Wait for threads to finish
        try {
            if (wifiToUsbThread != null) {
                wifiToUsbThread.join(1000);
            }
            if (usbToWifiThread != null) {
                usbToWifiThread.join(1000);
            }
        } catch (InterruptedException e) {
            Log.w(TAG, "Interrupted while waiting for threads to finish");
        }
        
        Log.i(TAG, "Data bridge stopped");
    }

    public void cleanup() {
        stop();
    }

    /**
     * Bridge data from WiFi (iPhone) to USB (AllgoCarplay).
     * 
     * Flow:
     * 1. Read data from iPhone via WiFi socket
     * 2. Write data to USB virtual device
     * 3. AllgoCarplay reads from USB as if it's a real iPhone
     */
    private void bridgeWiFiToUsb() {
        Log.d(TAG, "WiFi→USB bridge thread started");
        
        byte[] buffer = new byte[BUFFER_SIZE];
        
        try {
            InputStream wifiInput = wifiSocket.getInputStream();
            // In a real implementation, we'd write to the USB FileDescriptor
            // For now, we'll just log the data flow
            
            while (isRunning && !Thread.currentThread().isInterrupted()) {
                int bytesRead = wifiInput.read(buffer);
                
                if (bytesRead == -1) {
                    Log.i(TAG, "WiFi connection closed");
                    break;
                }
                
                if (bytesRead > 0) {
                    Log.v(TAG, "WiFi→USB: " + bytesRead + " bytes");
                    
                    // Write to USB virtual device
                    // usbOutputStream.write(buffer, 0, bytesRead);
                    
                    // For debugging, log first few bytes
                    if (Log.isLoggable(TAG, Log.VERBOSE)) {
                        StringBuilder hex = new StringBuilder();
                        for (int i = 0; i < Math.min(16, bytesRead); i++) {
                            hex.append(String.format("%02X ", buffer[i]));
                        }
                        Log.v(TAG, "Data: " + hex.toString());
                    }
                }
            }
            
        } catch (Exception e) {
            if (isRunning) {
                Log.e(TAG, "Error in WiFi→USB bridge", e);
            }
        } finally {
            Log.d(TAG, "WiFi→USB bridge thread stopped");
        }
    }

    /**
     * Bridge data from USB (AllgoCarplay) to WiFi (iPhone).
     * 
     * Flow:
     * 1. Read responses from AllgoCarplay via USB virtual device
     * 2. Write responses back to iPhone via WiFi socket
     * 3. iPhone receives responses as if directly connected via USB
     */
    private void bridgeUsbToWiFi() {
        Log.d(TAG, "USB→WiFi bridge thread started");
        
        byte[] buffer = new byte[BUFFER_SIZE];
        
        try {
            // In a real implementation, we'd read from the USB FileDescriptor
            OutputStream wifiOutput = wifiSocket.getOutputStream();
            
            while (isRunning && !Thread.currentThread().isInterrupted()) {
                // Read from USB virtual device
                // int bytesRead = usbInputStream.read(buffer);
                
                // For now, simulate reading (in real implementation, this would block)
                Thread.sleep(100);
                
                // When we have data:
                // if (bytesRead > 0) {
                //     Log.v(TAG, "USB→WiFi: " + bytesRead + " bytes");
                //     wifiOutput.write(buffer, 0, bytesRead);
                //     wifiOutput.flush();
                // }
            }
            
        } catch (Exception e) {
            if (isRunning) {
                Log.e(TAG, "Error in USB→WiFi bridge", e);
            }
        } finally {
            Log.d(TAG, "USB→WiFi bridge thread stopped");
        }
    }
}
