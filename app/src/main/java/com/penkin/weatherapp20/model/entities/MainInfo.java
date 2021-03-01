package com.penkin.weatherapp20.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainInfo {

    @Expose
    @SerializedName("temp")
    private float temp;

    @Expose
    @SerializedName("feels_like")
    private float feelsLike;

    @Expose
    @SerializedName("pressure")
    private float pressure;

    @Expose
    @SerializedName("humidity")
    private float humidity;

    @Expose
    @SerializedName("temp_min")
    private float tempMin;

    @Expose
    @SerializedName("temp_max")
    private float tempMax;

    public float getTemp() {
        return temp;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }
}
