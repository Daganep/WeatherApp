package com.penkin.weatherapp20.model;

public final class SettingsSingleton {

    private static SettingsSingleton settings;
    private SettingsSingleton() {
    }
    public static SettingsSingleton getSettingsSingleton(){
        if(settings == null){
            settings = new SettingsSingleton();
        }
        return settings;
    }

    private final static String defaultCity = "Moscow"; //if have no lastKey, no location
    private static String currentCity = ""; //current city from choose
    private static String locationCity = ""; //current location city
    private static String units = "metric";
    private static String theme = "spring";
    private static boolean isNotificationOn = false;

    public static String getCurrentCity() {
        return currentCity;
    }

    public static void setCurrentCity(String currentCity) {
        SettingsSingleton.currentCity = currentCity;
    }

    public static String getLocationCity() {
        return locationCity;
    }

    public static void setLocationCity(String locationCity) {
        SettingsSingleton.locationCity = locationCity;
    }

    public static String getDefaultCity(){
        return defaultCity;
    }

    public static boolean isNotificationOn() {
        return isNotificationOn;
    }

    public static void setIsNotificationOn(boolean isNotificationOn) {
        SettingsSingleton.isNotificationOn = isNotificationOn;
    }

    public static String getUnits() {
        return units;
    }

    public static void setUnits(String units) {
        SettingsSingleton.units = units;
    }

    public static String getTheme() {
        return theme;
    }

    public static void setTheme(String theme) {
        SettingsSingleton.theme = theme;
    }
}
