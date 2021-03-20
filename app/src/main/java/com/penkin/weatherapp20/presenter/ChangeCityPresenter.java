package com.penkin.weatherapp20.presenter;

import android.util.Log;

import com.penkin.weatherapp20.application.Constants;
import com.penkin.weatherapp20.application.WeatherApp;
import com.penkin.weatherapp20.model.SettingsSingleton;
import com.penkin.weatherapp20.model.database.CityDao;
import com.penkin.weatherapp20.model.database.CityHistoryDatabase;
import com.penkin.weatherapp20.model.entities.City;
import com.penkin.weatherapp20.view.changecity.ChangeCityView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class ChangeCityPresenter extends MvpPresenter<ChangeCityView> {

    @Inject
    CityHistoryDatabase appDatabase;
    private final CityDao cityDao;
    private final CompositeDisposable subscriptions;
    private List<City> cities;

    public ChangeCityPresenter(){
        WeatherApp.getAppComponent().inject(this);
        cityDao = appDatabase.cityDao();
        subscriptions = new CompositeDisposable();
    }

    public void getCityHistory(){
        subscriptions.add(cityDao.getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(emitter -> {
                    cities = emitter;
                    if(cities.size() != 0)getViewState().setHistory(historyToString(cities));
                }, throwable ->
                        Log.e(Constants.TAG, Constants.ERROR_MESSAGE + throwable)));
    }

    private String historyToString(List<City> cities){
        StringBuilder builder = new StringBuilder();
        for(City city : cities){
            builder.append(city.getName());
            builder.append("  ");
        }
        return builder.toString();
    }

    public void saveHistory(String city){
        cities.add(new City(city));
        subscriptions.add(cityDao.insertList(cities)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(throwable -> Log.e(Constants.TAG, Constants.ERROR_MESSAGE + throwable)));
    }

    public void clearHistory(){
        cities.clear();
        getViewState().setHistory("");
        subscriptions.add(cityDao.deleteAll()
                .subscribeOn(Schedulers.io())
                .subscribe(throwable -> Log.e(Constants.TAG, Constants.ERROR_MESSAGE + throwable)));
    }

    public void setCurrentCity(String cityName){
        SettingsSingleton.setCurrentCity(cityName);
    }

    public void getCurrentCity(){
        getViewState().setCurrentCity(SettingsSingleton.getCurrentCity());
    }

    public void getLocationCity(){
        String city = SettingsSingleton.getLocationCity();
        if(city.isEmpty())city = Constants.LOCATION_ERROR;
        getViewState().setCurrentCity(city);
    }
}
