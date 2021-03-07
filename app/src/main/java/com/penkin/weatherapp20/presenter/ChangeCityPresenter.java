package com.penkin.weatherapp20.presenter;

import android.util.Log;

import com.penkin.weatherapp20.application.WeatherApp;
import com.penkin.weatherapp20.model.SettingsSingleton;
import com.penkin.weatherapp20.model.database.CityDao;
import com.penkin.weatherapp20.model.database.CityHistoryDatabase;
import com.penkin.weatherapp20.model.entities.City;
import com.penkin.weatherapp20.view.changecity.ChangeCityView;

import java.util.List;
import java.util.Set;

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
    private final String TAG = "Error";
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
                        Log.e(TAG, "onError" + throwable)));
    }

    private String historyToString(List<City> cities){
        StringBuilder builder = new StringBuilder();
        for(City city : cities){
            builder.append(city.getName());
            builder.append(" ");
        }
        return builder.toString();
    }

    public void saveHistory(String city){
        cities.add(new City(city));
        subscriptions.add(cityDao.insertList(cities)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(throwable -> Log.e(TAG, "onError" + throwable)));
    }

    public void setCurrentCity(String cityName){
        SettingsSingleton.setCurrentCity(cityName);
    }
}
