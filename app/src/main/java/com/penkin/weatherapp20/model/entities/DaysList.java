package com.penkin.weatherapp20.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DaysList {

    @Expose
    @SerializedName("dt")
    private long dt;

    @Expose
    @SerializedName("main")
    private MainInfo main;

    @Expose
    @SerializedName("weather")
    private WeatherInfo[] weather;

    @Expose
    @SerializedName("clouds")
    private CloudsInfo clouds;

    @Expose
    @SerializedName("wind")
    private WindInfo wind;

    @Expose
    @SerializedName("dt_txt")
    private  String dtTxt;

    public long getDt() {
        return dt;
    }

    public MainInfo getMain() {
        return main;
    }

    public WeatherInfo[] getWeather() {
        return weather;
    }

    public CloudsInfo getClouds() {
        return clouds;
    }

    public WindInfo getWind() {
        return wind;
    }

    public String getDtTxt() {
        return dtTxt;
    }
}
