package com.penkin.weatherapp20.view.changecity;

import com.penkin.weatherapp20.model.entities.City;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface ChangeCityView extends MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void setHistory(List<City> cities);
}
