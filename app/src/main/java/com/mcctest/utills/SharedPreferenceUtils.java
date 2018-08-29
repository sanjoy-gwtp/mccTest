package com.mcctest.utills;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by sanjoy on 7/24/2017.
 */

public class SharedPreferenceUtils {

   private static String prefName = "SP_AgentBanking";

    public static void saveFCMtokenPref(Context context, String value) {

        if(context == null) return;

        SharedPreferences pref = context.getSharedPreferences(
                prefName, /* MODE_PRIVATE */0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("fcm_token", value);
        editor.commit();
    }


    public static String geFCMtokenPref(Context context) {
        if(context == null) return "";
        String result = null;
        SharedPreferences settings = context.getSharedPreferences(prefName, /* MODE_PRIVATE */0);
        result = settings.getString("fcm_token", "");
        return result;
    }

    public static void saveThemePref(Context context, String value) {

        if(context == null) return;

        SharedPreferences pref = context.getSharedPreferences(
                prefName, /* MODE_PRIVATE */0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("theme", value);
        editor.commit();
    }


    public static String getThemePref(Context context) {
        if(context == null) return "Default";
        String result = null;
        SharedPreferences settings = context.getSharedPreferences(prefName, /* MODE_PRIVATE */0);
        result = settings.getString("theme", "Default");
        return result;
    }

    public static void saveThemeColorPref(Context context, String value) {

        if(context == null) return;

        SharedPreferences pref = context.getSharedPreferences(
                prefName, /* MODE_PRIVATE */0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("theme_color", value);
        editor.commit();
    }


    public static String getThemeColorPref(Context context) {
        if(context == null) return "#FF264D8E";
        String result = null;
        SharedPreferences settings = context.getSharedPreferences(prefName, /* MODE_PRIVATE */0);
        result = settings.getString("theme_color", "#FF00A3E3");
        return result;
    }

    public static void saveColorPref(Context context, String value) {

        if(context == null) return;

        SharedPreferences pref = context.getSharedPreferences(
                prefName, /* MODE_PRIVATE */0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("button_color", value);
        editor.commit();
    }


    public static String getColorPref(Context context) {
        if(context == null) return "#FF264D8E";
        String result = null;
        SharedPreferences settings = context.getSharedPreferences(prefName, /* MODE_PRIVATE */0);
        result = settings.getString("button_color", "#FF00A3E3");
        return result;
    }

//    public static void saveNotificationPref(Context context, ArrayList<NotificationItem> mSelectedList) {
//
//        if(context == null) return;
//
//        SharedPreferences pref = context.getSharedPreferences(
//                prefName, /* MODE_PRIVATE */0);
//        SharedPreferences.Editor editor = pref.edit();
//        Gson gson = new Gson();
//
//        String jsonString = gson.toJson(mSelectedList);
//        editor.putString("NOTIFICATIONS", jsonString);
//        editor.commit();
//
//    }


//    public static ArrayList<NotificationItem> getNotificationPref(Context context) {
//        if(context == null) return new ArrayList<NotificationItem>();
//        String result = null;
//        Gson gson = new Gson();
//        SharedPreferences preferences = context.getSharedPreferences(prefName, /* MODE_PRIVATE */0);
//
//        String empty_list = preferences.getString("NOTIFICATIONS", gson.toJson(new ArrayList<NotificationItem>()));
//        Type type = new TypeToken<ArrayList<NotificationItem>>() {}.getType();
//        ArrayList<NotificationItem> mSelectedList = gson.fromJson(empty_list, type);
//
////        String empty_list = gson.toJson(new ArrayList<NotificationItem>());
////
////        ArrayList<NotificationItem> mSelectedList = gson.fromJson(preferences.getString("NOTIFICATIONS", empty_list),
////                new TypeToken<ArrayList<NotificationItem>>() {
////                }.getType());
////
////
//
//
//        return mSelectedList;
//    }

    public static void removePref(Context context, String key){
        if(context == null) return;

        SharedPreferences pref = context.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();
    }




}
