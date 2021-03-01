package com.penkin.weatherapp20.view.Main;

import com.penkin.weatherapp20.model.entities.OpenWeatherResponse;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void viewUpdate(OpenWeatherResponse response);
}
