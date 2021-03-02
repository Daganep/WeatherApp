package com.penkin.weatherapp20.model.entities;

import com.penkin.weatherapp20.application.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class CurrentResponseInfo {

    private SimpleDateFormat sdf;
    private SimpleDateFormat sdd;
    private Calendar calendar;

    private OpenWeatherResponse response;

    public CurrentResponseInfo(OpenWeatherResponse response){
        this.response = response;
        makeDate();
    }

    private void makeDate(){
        Date currentDate = new Date();
        String dt = currentDate.toString();  // Start date
        sdf = new SimpleDateFormat("dd MMMM", Locale.ENGLISH);
        sdd = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        calendar = Calendar.getInstance();
        try {
            calendar.setTime(Objects.requireNonNull(sdf.parse(dt)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getCityName() {
        return response.getCity().getName();
    }

    public String getCurrentTemp() {
        return String.format(Locale.getDefault(), "%.0f", response.getList()[0].getMain().getTemp()) + "°C";
    }

    public String getCurrentImagePath() {
        return Constants.IMAGE_PATH + response.getList()[0].getWeather()[0].getIcon() + Constants.IMAGE_FORMAT;
    }

    public String getCurrentTempDescription() {
        return response.getList()[0].getWeather()[0].getDescription();
    }

    public String getGetCurrentTempSens() {
        return "Feels like: " + String.format(Locale.getDefault(), "%.0f", response.getList()[0].getMain().getFeelsLike()) + "°C";
    }

    public String getWindSpeed() {
        return String.format(Locale.getDefault(), "%.0f", response.getList()[0].getWind().getSpeed()) + " meter/sec";
    }

    public String getPressure() {
        return String.format(Locale.getDefault(), "%.0f", response.getList()[0].getMain().getPressure()) + " hPa";
    }

    public String getHumidity() {
        return String.format(Locale.getDefault(), "%.0f", response.getList()[0].getMain().getHumidity()) + " %";
    }

    public String getOtherDayDate(int i) {
        calendar.add(Calendar.DATE, i+1);  // number of days to add
        return sdf.format(calendar.getTime());
    }

    public String getOtherDay(int i) {
        calendar.add(Calendar.DATE, i+1);  // number of days to add
        return sdd.format(calendar.getTime());
    }

    public String getOtherDayImagePath(int i) {
        return Constants.IMAGE_PATH + response.getList()[i].getWeather()[0].getIcon() + Constants.IMAGE_FORMAT;
    }

    public String getOtherDayTemp(int i) {
        return String.format(Locale.getDefault(), "%.0f", response.getList()[1].getMain().getTempMax()) + "°C";
    }

    public String getOtherNightTemp(int i) {
        return String.format(Locale.getDefault(), "%.0f", response.getList()[1].getMain().getTempMin()) + "°C";
    }
}
