package com.custom.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;

public class UsbDebugActivity extends Activity {
    private static final String TAG = "UsbDebugActivity";
    
    private TableLayout debugTable;
    private Handler refreshHandler;
    private Runnable refreshRunnable;
    private boolean isRefreshing = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usb_debug);
        
        debugTable = findViewById(R.id.debugTable);
        ImageView backButton = findViewById(R.id.iv_back);
        
        backButton.setOnClickListener(v -> finish());
        
        // Initial load
        loadDebugInfo();
        
        // Auto-refresh every 2 seconds
        refreshHandler = new Handler(Looper.getMainLooper());
        refreshRunnable = new Runnable() {
            @Override
            public void run() {
                if (isRefreshing) {
                    loadDebugInfo();
                    refreshHandler.postDelayed(this, 2000);
                }
            }
        };
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        isRefreshing = true;
        refreshHandler.post(refreshRunnable);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        isRefreshing = false;
        refreshHandler.removeCallbacks(refreshRunnable);
    }
    
    private void loadDebugInfo() {
        debugTable.removeAllViews();
        
        // Add header
        addHeaderRow("Property", "Value");
        
        // System Properties (via reflection)
        addPropertyRow("sys.usb.config", formatUsbConfig(getSystemProperty("sys.usb.config")));
        addPropertyRow("persist.sys.usb.config", getSystemProperty("persist.sys.usb.config"));
        addPropertyRow("sys.usb.state", getSystemProperty("sys.usb.state"));
        addPropertyRow("sys.usb.controller", getSystemProperty("sys.usb.controller"));
        addPropertyRow("sys.usb.configfs", formatBooleanProperty(getSystemProperty("sys.usb.configfs")));
        addPropertyRow("sys.usb.ffs.ready", formatBooleanProperty(getSystemProperty("sys.usb.ffs.ready")));
        addPropertyRow("persist.sys.service.otg_gadget", formatOtgGadget(getSystemProperty("persist.sys.service.otg_gadget")));
        addPropertyRow("service.adb.root", formatBooleanProperty(getSystemProperty("service.adb.root")));
        addPropertyRow("service.adb.tcp.port", getSystemProperty("service.adb.tcp.port"));
        addPropertyRow("ro.adb.secure", formatSecure(getSystemProperty("ro.adb.secure")));
        addPropertyRow("ro.debuggable", formatBooleanProperty(getSystemProperty("ro.debuggable")));
        addPropertyRow("ro.secure", formatSecure(getSystemProperty("ro.secure")));
        
        // Android Settings
        addSeparatorRow("Android Settings");
        addPropertyRow("adb_enabled", formatEnabled(getGlobalSetting("adb_enabled")));
        addPropertyRow("development_settings_enabled", formatEnabled(getGlobalSetting("development_settings_enabled")));
        
        // USB Hardware State
        addSeparatorRow("USB Hardware");
        addPropertyRow("USB Mode", readFile("/sys/kernel/debug/usb/11271000.usb/mode"));
        addPropertyRow("USB VBUS", readFile("/sys/kernel/debug/usb/11271000.usb/vbus"));
        addPropertyRow("USB State", readFile("/sys/class/android_usb/android0/state"));
        
        // USB Gadget ConfigFS
        addSeparatorRow("USB ConfigFS");
        addPropertyRow("UDC", readFile("/config/usb_gadget/g1/UDC"));
        addPropertyRow("idVendor", readFile("/config/usb_gadget/g1/idVendor"));
        addPropertyRow("idProduct", readFile("/config/usb_gadget/g1/idProduct"));
        
        // Network interfaces
        addSeparatorRow("Network");
        addPropertyRow("usb0 status", getInterfaceStatus("usb0"));
        addPropertyRow("eth0 status", getInterfaceStatus("eth0"));
        
        // ADB Process Check
        addSeparatorRow("Process Status");
        addPropertyRow("adbd running", isProcessRunning("adbd") ? "Yes" : "No");
        addPropertyRow("usbd running", isProcessRunning("usbd") ? "Yes" : "No");
    }
    
    private void addHeaderRow(String col1, String col2) {
        TableRow row = new TableRow(this);
        row.setBackgroundColor(0xFF2A2A2A);
        row.setPadding(20, 20, 20, 20);
        
        TextView tv1 = new TextView(this);
        tv1.setText(col1);
        tv1.setTextColor(0xFFFFFFFF);
        tv1.setTextSize(18);
        tv1.setPadding(10, 10, 10, 10);
        tv1.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        
        TextView tv2 = new TextView(this);
        tv2.setText(col2);
        tv2.setTextColor(0xFFFFFFFF);
        tv2.setTextSize(18);
        tv2.setPadding(10, 10, 10, 10);
        tv2.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2f));
        
        row.addView(tv1);
        row.addView(tv2);
        debugTable.addView(row);
    }
    
    private void addPropertyRow(String name, String value) {
        TableRow row = new TableRow(this);
        row.setPadding(20, 15, 20, 15);
        
        TextView nameView = new TextView(this);
        nameView.setText(name);
        nameView.setTextColor(0xFFCCCCCC);
        nameView.setTextSize(16);
        nameView.setPadding(10, 5, 10, 5);
        nameView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        
        TextView valueView = new TextView(this);
        valueView.setText(value != null && !value.isEmpty() ? value : "(empty)");
        valueView.setTextColor(value != null && !value.isEmpty() ? 0xFF00FF00 : 0xFF888888);
        valueView.setTextSize(16);
        valueView.setPadding(10, 5, 10, 5);
        valueView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2f));
        
        row.addView(nameView);
        row.addView(valueView);
        debugTable.addView(row);
    }
    
    private void addSeparatorRow(String title) {
        TableRow row = new TableRow(this);
        row.setBackgroundColor(0xFF1A1A1A);
        row.setPadding(20, 25, 20, 10);
        
        TextView titleView = new TextView(this);
        titleView.setText(title);
        titleView.setTextColor(0xFFFFAA00);
        titleView.setTextSize(20);
        titleView.setPadding(10, 5, 10, 5);
        TableRow.LayoutParams params = new TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT, 
            TableRow.LayoutParams.WRAP_CONTENT
        );
        params.span = 2;
        titleView.setLayoutParams(params);
        
        row.addView(titleView);
        debugTable.addView(row);
    }
    
    private String getSystemProperty(String key) {
        try {
            Class<?> systemProperties = Class.forName("android.os.SystemProperties");
            Method get = systemProperties.getMethod("get", String.class);
            return (String) get.invoke(null, key);
        } catch (Exception e) {
            Log.e(TAG, "Error getting system property: " + key, e);
            return "(error)";
        }
    }
    
    private String getGlobalSetting(String key) {
        try {
            String value = android.provider.Settings.Global.getString(
                getContentResolver(), key
            );
            return value != null ? value : "(not set)";
        } catch (Exception e) {
            Log.e(TAG, "Error getting global setting: " + key, e);
            return "(error)";
        }
    }
    
    private String readFile(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                return "(file not found)";
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            reader.close();
            return line != null ? line.trim() : "(empty)";
        } catch (Exception e) {
            return "(permission denied)";
        }
    }
    
    private String getInterfaceStatus(String iface) {
        String operstate = readFile("/sys/class/net/" + iface + "/operstate");
        String carrier = readFile("/sys/class/net/" + iface + "/carrier");
        
        if (operstate.equals("(file not found)")) {
            return "not present";
        }
        
        return operstate + " (carrier: " + carrier + ")";
    }
    
    private boolean isProcessRunning(String processName) {
        // Android security prevents apps from seeing system processes via ps
        // Instead, infer from system properties and USB config
        
        if (processName.equals("adbd")) {
            // ADB is running if:
            // 1. sys.usb.config contains "adb" OR
            // 2. init.svc.adbd property is "running"
            String usbConfig = getSystemProperty("sys.usb.config");
            String adbService = getSystemProperty("init.svc.adbd");
            
            Log.d(TAG, "ADB check - sys.usb.config=" + usbConfig + ", init.svc.adbd=" + adbService);
            
            return (usbConfig != null && usbConfig.contains("adb")) || 
                   (adbService != null && adbService.equals("running"));
        } else if (processName.equals("usbd")) {
            // USB daemon check - look for usb service property
            String usbdService = getSystemProperty("init.svc.usbd");
            Log.d(TAG, "USBD check - init.svc.usbd=" + usbdService);
            return usbdService != null && usbdService.equals("running");
        }
        
        return false;
    }
    
    // Formatting methods to make values more readable
    private String formatOtgGadget(String value) {
        if (value == null || value.equals("(not set)")) return value;
        if (value.equals("0")) return "0 (Host mode - CarPlay)";
        if (value.equals("1")) return "1 (Device mode - ADB)";
        return value;
    }
    
    private String formatBooleanProperty(String value) {
        if (value == null || value.equals("(not set)")) return value;
        if (value.equals("0")) return "0 (No)";
        if (value.equals("1")) return "1 (Yes)";
        return value;
    }
    
    private String formatEnabled(String value) {
        if (value == null || value.equals("(not set)")) return value;
        if (value.equals("0")) return "0 (Disabled)";
        if (value.equals("1")) return "1 (Enabled)";
        return value;
    }
    
    private String formatSecure(String value) {
        if (value == null || value.equals("(not set)")) return value;
        if (value.equals("0")) return "0 (Insecure)";
        if (value.equals("1")) return "1 (Secure)";
        return value;
    }
    
    private String formatUsbConfig(String value) {
        if (value == null || value.equals("(not set)")) return value;
        
        // Add explanation for common configs
        if (value.contains("adb") && value.contains("mtp")) {
            return value + " (File Transfer + Debug)";
        } else if (value.contains("adb")) {
            return value + " (Debug mode)";
        } else if (value.equals("host")) {
            return value + " (Host mode - CarPlay)";
        } else if (value.contains("mtp")) {
            return value + " (File Transfer)";
        }
        
        return value;
    }
}

