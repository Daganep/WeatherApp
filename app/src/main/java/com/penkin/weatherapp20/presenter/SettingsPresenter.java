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
}
