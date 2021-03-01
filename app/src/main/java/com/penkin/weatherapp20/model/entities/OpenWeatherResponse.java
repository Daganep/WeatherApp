package com.penkin.weatherapp20.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenWeatherResponse {

    @Expose
    @SerializedName("cod")
    private String cod;

    @Expose
    @SerializedName("message")
    private int message;

    @Expose
    @SerializedName("cnt")
    private int cnt;

    @Expose
    @SerializedName("list")
    private DaysList[] list;

    @Expose
    @SerializedName("city")
    private CurrentCity city;

    public String getCod() {
        return cod;
    }

    public int getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public DaysList[] getList() {
        return list;
    }

    public CurrentCity getCity() {
        return city;
    }
}
