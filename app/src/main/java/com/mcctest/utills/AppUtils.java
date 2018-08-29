package com.mcctest.utills;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.TypedValue;

import com.mcctest.R;
import com.mcctest.dialog.MessageDialog;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class AppUtils {

    public static int getDarkerShadeColor(int c) {
        float[] hsv = new float[3];
        int color = c;
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.80f;
        color = Color.HSVToColor(hsv);
        return color;
    }

    public static int getLighterShadeColor(int c) {
        float[] hsv = new float[3];
        int color = c;
        Color.colorToHSV(color, hsv);
//        hsv[2] *= 1.35f;
        hsv[2] = 0.2f + 0.8f * hsv[2];
        color = Color.HSVToColor(hsv);
        return color;
    }

    public static boolean isAppRunning(final Context context, final String packageName) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        if (procInfos != null) {
            for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
                if (processInfo.processName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }


    public static int fetchPrimaryColor(Context mContext) {
        TypedValue typedValue = new TypedValue();

        TypedArray a = mContext.obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorPrimary});
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }

    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;

        }
        return isAvailable;
    }

    public static String getAppPackageName(Context context) {

        return context.getApplicationContext().getPackageName();
    }

    public static String getAppVersionNumber(Context context) {

        String version = "1.0";
        try {
            version = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "app-" + version;
    }

    public static String getAppPlatfrom() {
        return "android";
    }

    public static String getDeviceModel() {
        String OS_VERSION = "1.0.0";
        String deviceName = Build.MODEL;
        String deviceMan = Build.MANUFACTURER;

        OS_VERSION = Build.VERSION.RELEASE;

        return deviceName + " " + deviceMan + "-" + OS_VERSION;
    }

    public static String getAndroidID(Context context) {
        String android_id = Secure.getString(context.getContentResolver(),
                Secure.ANDROID_ID);
        return android_id;
    }

    public static String getUID(Context context) {
        final String macAddr, androidId;

        WifiManager wifiMan = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInf = wifiMan.getConnectionInfo();

        macAddr = wifiInf.getMacAddress();
        androidId = "" + Secure.getString(context.getContentResolver(),
                Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), macAddr.hashCode());

        return deviceUuid.toString();

    }

    public static String getGUID(Context context) {
        String GUID = UUID.randomUUID().toString();
        return GUID;
    }

    public static String getimeiID(Context context) {

        // <uses-permission android:name="android.permission.READ_PHONE_STATE"/>  required

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imeiID = telephonyManager.getDeviceId().toString();
        return imeiID;
    }

    public static String getLocalCountryName() {
        return Locale.getDefault().getCountry();
    }

    public static String getLocalLanguage() {

        return Locale.getDefault().getLanguage();
    }

    public static boolean isMobileOrWifiConnectivityAvailable(Context ctx) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;


        try {
            ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
            for (NetworkInfo ni : netInfo) {
                if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                    if (ni.isConnected()) {
                        haveConnectedWifi = true;
                    }
                if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                    if (ni.isConnected()) {
                        haveConnectedMobile = true;
                    }
            }
        } catch (Exception e) {
            System.out.print("[ConnectionVerifier] inside isInternetOn() Exception is : " + e.toString());
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            MessageDialog messageDialog = new MessageDialog(context, "Internet connection not available.", true);
            messageDialog.show();
        }
        return false;
    }


}