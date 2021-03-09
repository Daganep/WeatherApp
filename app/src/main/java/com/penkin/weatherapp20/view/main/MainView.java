package com.penkin.weatherapp20.view.main;

import com.penkin.weatherapp20.model.entities.CurrentResponseInfo;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void renderWeather(CurrentResponseInfo response);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showError(int msg);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void loadLastKey();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void saveLastKey(String key);
}
