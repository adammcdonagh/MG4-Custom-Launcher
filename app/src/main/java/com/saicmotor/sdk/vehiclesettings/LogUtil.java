package com.saicmotor.sdk.vehiclesettings;

import android.util.Log;

/* loaded from: classes2.dex */
public class LogUtil {
    private static final boolean DEBUG = true;
    private static final boolean ERROR = true;
    private static final boolean INFO = true;
    private static final String NULL = "null";
    private static final boolean VERBOSE = true;
    private static final boolean WARN = true;

    public static void logD(String objTag, String objMsg) {
        String tag = getTag(objTag);
        String msg = (objMsg == null || objMsg.toString() == null) ? NULL : objMsg.toString();
        Log.d(tag, msg);
    }

    public static void logV(String objTag, String objMsg) {
        String tag = getTag(objTag);
        String msg = (objMsg == null || objMsg.toString() == null) ? NULL : objMsg.toString();
        Log.v(tag, msg);
    }

    public static void logI(String objTag, String objMsg) {
        String tag = getTag(objTag);
        String msg = (objMsg == null || objMsg.toString() == null) ? NULL : objMsg.toString();
        Log.i(tag, msg);
    }

    public static void logW(Object objTag, Object objMsg) {
        String tag = getTag(objTag);
        String msg = (objMsg == null || objMsg.toString() == null) ? NULL : objMsg.toString();
        Log.w(tag, msg);
    }

    public static void logE(Object objTag, Object objMsg) {
        String tag = getTag(objTag);
        String msg = (objMsg == null || objMsg.toString() == null) ? NULL : objMsg.toString();
        Log.e(tag, msg);
    }

    private static String getTag(Object objTag) {
        if (objTag instanceof String) {
            String tag = (String) objTag;
            return tag;
        }
        if (objTag instanceof Class) {
            String tag2 = ((Class) objTag).getSimpleName();
            return tag2;
        }
        String tag3 = objTag.getClass().getSimpleName();
        return tag3;
    }
}
