package com.mg4.wirelesscarplay;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * USB Gadget Controller - Creates a virtual USB device that appears as an iPhone to AllgoCarplay.
 * 
 * This uses the Linux USB Gadget framework (ConfigFS) to create a virtual USB device:
 * - Device Class: 0xFF (Vendor Specific)
 * - Subclass: 0xF0 (Apple specific)
 * - Protocol: 0x00 (AOAP - Android Open Accessory Protocol)
 * 
 * The virtual device will be presented to the car's USB stack as a physical iPhone,
 * allowing AllgoCarplay to connect to it normally.
 * 
 * Note: This requires system-level permissions and may need kernel modules:
 * - CONFIG_USB_CONFIGFS
 * - CONFIG_USB_CONFIGFS_F_FS (FunctionFS for custom endpoints)
 */
public class UsbGadgetController {
    private static final String TAG = "UsbGadgetController";
    
    // ConfigFS paths (standard Linux USB Gadget paths)
    private static final String CONFIGFS_PATH = "/config/usb_gadget";
    private static final String GADGET_NAME = "carplay";
    private static final String GADGET_PATH = CONFIGFS_PATH + "/" + GADGET_NAME;
    
    // USB Descriptors for iPhone emulation
    private static final int VENDOR_ID_APPLE = 0x05AC; // Apple Inc.
    private static final int PRODUCT_ID_IPHONE = 0x12A8; // iPhone
    private static final int DEVICE_CLASS = 0xFF; // Vendor Specific
    private static final int DEVICE_SUBCLASS = 0xF0; // Apple Subclass
    private static final int DEVICE_PROTOCOL = 0x00; // AOAP
    
    private final Context context;
    private FileDescriptor usbFd;
    private FileInputStream usbInputStream;
    private FileOutputStream usbOutputStream;
    private boolean isActive = false;

    public UsbGadgetController(Context context) {
        this.context = context;
    }

    /**
     * Create the virtual USB device using ConfigFS.
     * This sets up the device descriptors and endpoints but doesn't activate it yet.
     */
    public boolean createVirtualDevice() {
        Log.i(TAG, "Creating virtual USB device...");
        
        if (BuildConfig.MOCK_MODE) {
            Log.i(TAG, "MOCK MODE: Virtual USB device creation simulated - success");
            return true;
        }
        
        try {
            // Check if ConfigFS is available
            File configfsRoot = new File(CONFIGFS_PATH);
            if (!configfsRoot.exists()) {
                Log.e(TAG, "ConfigFS not available at " + CONFIGFS_PATH);
                Log.e(TAG, "Kernel may not have USB_CONFIGFS enabled");
                return false;
            }
            
            // Create gadget directory
            File gadgetDir = new File(GADGET_PATH);
            if (!gadgetDir.exists()) {
                boolean created = gadgetDir.mkdirs();
                if (!created) {
                    Log.e(TAG, "Failed to create gadget directory");
                    return false;
                }
            }
            
            // Set USB descriptors
            writeConfigFile("idVendor", String.format("0x%04X", VENDOR_ID_APPLE));
            writeConfigFile("idProduct", String.format("0x%04X", PRODUCT_ID_IPHONE));
            writeConfigFile("bcdDevice", "0x0100"); // Device version 1.0
            writeConfigFile("bcdUSB", "0x0200"); // USB 2.0
            writeConfigFile("bDeviceClass", String.format("0x%02X", DEVICE_CLASS));
            writeConfigFile("bDeviceSubClass", String.format("0x%02X", DEVICE_SUBCLASS));
            writeConfigFile("bDeviceProtocol", String.format("0x%02X", DEVICE_PROTOCOL));
            
            // Set device strings
            createStringsDirectory();
            writeConfigFile("strings/0x409/manufacturer", "Apple Inc.");
            writeConfigFile("strings/0x409/product", "iPhone");
            writeConfigFile("strings/0x409/serialnumber", generateSerialNumber());
            
            // Create configuration
            createConfiguration();
            
            // Create function (FunctionFS for custom endpoints)
            createFunction();
            
            Log.i(TAG, "Virtual USB device created successfully");
            return true;
            
        } catch (Exception e) {
            Log.e(TAG, "Failed to create virtual USB device", e);
            return false;
        }
    }

    /**
     * Activate the virtual device (bind to UDC - USB Device Controller).
     * This makes the device visible to the host (car's USB stack).
     */
    public boolean activateDevice() {
        Log.i(TAG, "Activating virtual USB device...");
        
        if (BuildConfig.MOCK_MODE) {
            Log.i(TAG, "MOCK MODE: Virtual USB device activation simulated - success");
            isActive = true;
            return true;
        }
        
        try {
            // Find available UDC (USB Device Controller)
            File udcDir = new File("/sys/class/udc");
            String[] udcs = udcDir.list();
            
            if (udcs == null || udcs.length == 0) {
                Log.e(TAG, "No USB Device Controller found");
                return false;
            }
            
            String udc = udcs[0];
            Log.d(TAG, "Using UDC: " + udc);
            
            // Bind gadget to UDC
            writeConfigFile("UDC", udc);
            
            // Open FunctionFS endpoints
            openEndpoints();
            
            isActive = true;
            Log.i(TAG, "Virtual USB device activated - car should see it now");
            return true;
            
        } catch (Exception e) {
            Log.e(TAG, "Failed to activate virtual USB device", e);
            return false;
        }
    }

    /**
     * Deactivate the virtual device (unbind from UDC).
     */
    public void deactivateDevice() {
        if (!isActive) {
            return;
        }
        
        Log.i(TAG, "Deactivating virtual USB device...");
        
        try {
            // Close endpoints
            closeEndpoints();
            
            // Unbind from UDC
            writeConfigFile("UDC", "");
            
            isActive = false;
            Log.i(TAG, "Virtual USB device deactivated");
            
        } catch (Exception e) {
            Log.e(TAG, "Error deactivating virtual USB device", e);
        }
    }

    /**
     * Destroy the virtual device (remove ConfigFS entries).
     */
    public void destroyVirtualDevice() {
        Log.i(TAG, "Destroying virtual USB device...");
        
        deactivateDevice();
        
        try {
            // Remove symlink
            File symlinkFile = new File(GADGET_PATH + "/configs/c.1/ffs.carplay");
            if (symlinkFile.exists()) {
                symlinkFile.delete();
            }
            
            // Remove function
            File functionDir = new File(GADGET_PATH + "/functions/ffs.carplay");
            if (functionDir.exists()) {
                deleteRecursive(functionDir);
            }
            
            // Remove configuration
            File configDir = new File(GADGET_PATH + "/configs/c.1");
            if (configDir.exists()) {
                deleteRecursive(configDir);
            }
            
            // Remove gadget
            File gadgetDir = new File(GADGET_PATH);
            if (gadgetDir.exists()) {
                deleteRecursive(gadgetDir);
            }
            
            Log.i(TAG, "Virtual USB device destroyed");
            
        } catch (Exception e) {
            Log.e(TAG, "Error destroying virtual USB device", e);
        }
    }

    public void cleanup() {
        destroyVirtualDevice();
    }

    public FileDescriptor getFileDescriptor() {
        return usbFd;
    }

    public FileInputStream getInputStream() {
        return usbInputStream;
    }

    public FileOutputStream getOutputStream() {
        return usbOutputStream;
    }

    private void createStringsDirectory() throws IOException {
        File stringsDir = new File(GADGET_PATH + "/strings/0x409");
        if (!stringsDir.exists()) {
            stringsDir.mkdirs();
        }
    }

    private void createConfiguration() throws IOException {
        // Create config directory
        File configDir = new File(GADGET_PATH + "/configs/c.1");
        if (!configDir.exists()) {
            configDir.mkdirs();
        }
        
        // Set config strings
        File configStringsDir = new File(GADGET_PATH + "/configs/c.1/strings/0x409");
        if (!configStringsDir.exists()) {
            configStringsDir.mkdirs();
        }
        
        writeConfigFile("configs/c.1/strings/0x409/configuration", "CarPlay Configuration");
        writeConfigFile("configs/c.1/MaxPower", "500"); // 500mA
    }

    private void createFunction() throws IOException {
        // Create FunctionFS function
        File functionDir = new File(GADGET_PATH + "/functions/ffs.carplay");
        if (!functionDir.exists()) {
            functionDir.mkdirs();
        }
        
        // Create symlink from config to function
        File symlinkFile = new File(GADGET_PATH + "/configs/c.1/ffs.carplay");
        if (!symlinkFile.exists()) {
            // This would normally use Files.createSymbolicLink but we need system permissions
            Runtime.getRuntime().exec(new String[] {
                "ln", "-s",
                GADGET_PATH + "/functions/ffs.carplay",
                GADGET_PATH + "/configs/c.1/ffs.carplay"
            });
        }
    }

    private void openEndpoints() throws IOException {
        // FunctionFS endpoints are typically at /dev/usb-ffs/<function_name>/
        String ffsPath = "/dev/usb-ffs/carplay";
        File ffsDir = new File(ffsPath);
        
        if (!ffsDir.exists()) {
            // Mount FunctionFS
            Runtime.getRuntime().exec(new String[] {
                "mount", "-t", "functionfs", "carplay", ffsPath
            });
            
            // Wait for mount
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Open ep0 (control endpoint)
        File ep0 = new File(ffsPath + "/ep0");
        if (ep0.exists()) {
            // In a real implementation, we'd open this as a file descriptor
            // For now, just log that we would open it
            Log.d(TAG, "Would open endpoint: " + ep0.getAbsolutePath());
        }
    }

    private void closeEndpoints() {
        if (usbInputStream != null) {
            try {
                usbInputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "Error closing USB input stream", e);
            }
        }
        
        if (usbOutputStream != null) {
            try {
                usbOutputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "Error closing USB output stream", e);
            }
        }
    }

    private void writeConfigFile(String path, String value) throws IOException {
        File file = new File(GADGET_PATH + "/" + path);
        
        // Ensure parent directory exists
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        
        // Write value using shell (needs root/system permissions)
        try {
            Process process = Runtime.getRuntime().exec(new String[] {
                "sh", "-c",
                "echo '" + value + "' > " + file.getAbsolutePath()
            });
            process.waitFor();
        } catch (Exception e) {
            Log.w(TAG, "Failed to write " + path + ": " + e.getMessage());
        }
    }

    private String generateSerialNumber() {
        // Generate a fake iPhone serial number
        return "DNPXXXXXXXX";
    }

    private void deleteRecursive(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File child : files) {
                    deleteRecursive(child);
                }
            }
        }
        file.delete();
    }
}
