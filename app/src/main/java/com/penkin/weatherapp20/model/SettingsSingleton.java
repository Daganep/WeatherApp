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
}
