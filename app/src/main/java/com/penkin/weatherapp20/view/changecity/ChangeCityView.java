package com.penkin.weatherapp20.view.changecity;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface ChangeCityView extends MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void setHistory(String cities);
}
