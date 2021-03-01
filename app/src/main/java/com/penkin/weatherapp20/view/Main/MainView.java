package com.penkin.weatherapp20.view.Main;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {
    /*@StateStrategyType(value = AddToEndSingleStrategy.class)
    void updateRecyclerView(List<Element> elements);*/
}
