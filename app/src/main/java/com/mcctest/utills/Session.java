package com.mcctest.utills;


import android.support.v4.app.Fragment;



import java.util.Set;

/**
 * Created by sanjoy on 5/21/17.
 */

public class Session {
    private static String token;
    private static String username;
    private static String password;
    private static String terminal;

    private static boolean isexpired;
    private static Set<String> rights;
    private static Set<String> roles;
    private static boolean isfingerexpired;



    private static boolean isThemeChange=false;
    private static boolean isHomeThemeChange=false;


    private static String fcmToken = "";

    private static String pathToStoredVideo = "";

    private static String pathToStoredImage = "";


    private static boolean isChild = false;


    public static boolean isHomeThemeChange() {
        return isHomeThemeChange;
    }

    public static void setIsHomeThemeChange(boolean isHomeThemeChange) {
        Session.isHomeThemeChange = isHomeThemeChange;
    }

    public static boolean isThemeChange() {
        return isThemeChange;
    }

    public static void setIsThemeChange(boolean isThemeChange) {
        Session.isThemeChange = isThemeChange;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Session.token = token;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Session.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Session.password = password;
    }

    public static String getFcmToken() {
        return fcmToken;
    }

    public static void setFcmToken(String fcmToken) {
        Session.fcmToken = fcmToken;
    }

    public static boolean isexpired() {
        return isexpired;
    }

    public static void setIsexpired(boolean isexpired) {
        Session.isexpired = isexpired;
    }

    public static Set<String> getRights() {
        return rights;
    }

    public static void setRights(Set<String> rights) {
        Session.rights = rights;
    }

    public static Set<String> getRoles() {
        return roles;
    }

    public static void setRoles(Set<String> roles) {
        Session.roles = roles;
    }

    public static boolean isfingerexpired() {
        return isfingerexpired;
    }

    public static void setIsfingerexpired(boolean isfingerexpired) {
        Session.isfingerexpired = isfingerexpired;
    }

    public static String getTerminal() {
        return terminal;
    }

    public static void setTerminal(String terminal) {
        Session.terminal = terminal;
    }

    public static boolean isIsexpired() {
        return isexpired;
    }

    public static boolean isIsfingerexpired() {
        return isfingerexpired;
    }

    public static boolean isIsThemeChange() {
        return isThemeChange;
    }

    public static boolean isIsHomeThemeChange() {
        return isHomeThemeChange;
    }

    public static String getPathToStoredVideo() {
        return pathToStoredVideo;
    }

    public static void setPathToStoredVideo(String pathToStoredVideo) {
        Session.pathToStoredVideo = pathToStoredVideo;
        AppConvertions.allPath.add(pathToStoredVideo);
    }

    public static String getPathToStoredImage() {
        return pathToStoredImage;
    }

    public static void setPathToStoredImage(String pathToStoredImage) {
        Session.pathToStoredImage = pathToStoredImage;
        AppConvertions.allPath.add(pathToStoredImage);
    }
    public static boolean isChild() {
        return isChild;
    }

    public static void setIsChild(boolean isChild) {
        Session.isChild = isChild;
    }
}
