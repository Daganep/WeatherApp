package com.penkin.weatherapp20.presenter;

import com.penkin.weatherapp20.R;
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
        if(SettingsSingleton.getTheme().equals(Constants.SPRING)){
            getViewState().setButtonsColor(R.color.design_default_color_error, R.color.colorGreenPrimary);
            getViewState().setSpring();
        } else{
            getViewState().setButtonsColor(R.color.design_default_color_error, R.color.colorDarkGrey);
            getViewState().setWinter();
        }
        if(SettingsSingleton.isNotificationOn())
            getViewState().setNotification();
    }

    public void setTheme(String theme){
        SettingsSingleton.setTheme(theme);
        if(theme.equals(Constants.SPRING))
            getViewState().saveCurrentTheme(R.style.Theme_WeatherApp20_Spring, R.color.colorGreenPrimaryDark);
        else getViewState().saveCurrentTheme(R.style.Theme_WeatherApp20_Winter, R.color.colorLightGrey);
    }

    public void saveSettings(){
        getViewState().saveCurrentSettings(
                SettingsSingleton.getUnits(),
                SettingsSingleton.getTheme(),
                SettingsSingleton.isNotificationOn());
    }
}
