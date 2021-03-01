package com.penkin.weatherapp20.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WindInfo {

    @Expose
    @SerializedName("speed")
    private float speed;

    @Expose
    @SerializedName("deg")
    private float deg;

    public float getSpeed() {
        return speed;
    }

    public float getDeg() {
        return deg;
    }
}
