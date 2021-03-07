package com.penkin.weatherapp20.application;

import android.app.Application;

import com.penkin.weatherapp20.di.AppComponent;
import com.penkin.weatherapp20.di.AppModule;
import com.penkin.weatherapp20.di.DaggerAppComponent;

public class WeatherApp extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        appComponent = generateAppComponent();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }

    private AppComponent generateAppComponent(){
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
