package com.penkin.weatherapp20.di;

import com.penkin.weatherapp20.presenter.ChangeCityPresenter;
import com.penkin.weatherapp20.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainPresenter mainPresenter);

    void inject(ChangeCityPresenter changeCityPresenter);
}
