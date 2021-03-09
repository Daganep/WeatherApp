package com.penkin.weatherapp20.presenter;

import com.penkin.weatherapp20.application.Constants;
import com.penkin.weatherapp20.model.SettingsSingleton;
import com.penkin.weatherapp20.view.settings.SettingsView;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> {

    public void setNotification(boolean isNotificationOn){
        SettingsSingleton.setIsNotificationOn(isNotificationOn);
    }

    public void setUnits(boolean units){
        if(units) SettingsSingleton.setUnits(Constants.METRIC);
        else SettingsSingleton.setUnits(Constants.IMPERIAL);
    }

    public void setButtons(){
        if(SettingsSingleton.getUnits().equals(Constants.METRIC))
            getViewState().setCelsius();
        else getViewState().setFahrenheit();
        if(SettingsSingleton.getTheme().equals(Constants.SPRING))
            getViewState().setSpring();
        else getViewState().setWinter();
        if(SettingsSingleton.isNotificationOn())
            getViewState().setNotification();
    }
}
