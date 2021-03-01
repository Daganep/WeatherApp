package com.penkin.weatherapp20.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentCity {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("coord")
    private Coordinates coordinates;

    @Expose
    @SerializedName("country")
    private String country;

    @Expose
    @SerializedName("timezone")
    private int timezone;

    @Expose
    @SerializedName("sunrise")
    private long sunrise;

    @Expose
    @SerializedName("sunset")
    private long sunset;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCountry() {
        return country;
    }

    public int getTimezone() {
        return timezone;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }
}
