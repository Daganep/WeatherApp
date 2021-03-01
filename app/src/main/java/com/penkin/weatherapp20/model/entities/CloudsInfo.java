package com.penkin.weatherapp20.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CloudsInfo {

    @Expose
    @SerializedName("all")
    private int all;

    public int getAll() {
        return all;
    }
}
