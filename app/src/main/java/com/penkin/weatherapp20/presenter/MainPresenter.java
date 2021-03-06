package com.penkin.weatherapp20.presenter;

import android.util.Log;

import com.penkin.weatherapp20.R;
import com.penkin.weatherapp20.application.Constants;
import com.penkin.weatherapp20.application.WeatherApp;
import com.penkin.weatherapp20.model.SettingsSingleton;
import com.penkin.weatherapp20.model.entities.CurrentResponseInfo;
import com.penkin.weatherapp20.model.entities.OpenWeatherResponse;
import com.penkin.weatherapp20.model.retrofit.ErrorInterceptor;
import com.penkin.weatherapp20.model.retrofit.RetrofitApi;
import com.penkin.weatherapp20.view.main.MainView;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    RetrofitApi retrofitApi;
    private final CompositeDisposable subscriptions;
    private CurrentResponseInfo responseInfo;

    public MainPresenter(){
        WeatherApp.getAppComponent().inject(this);
        subscriptions = new CompositeDisposable();
    }

    public void getData(String lastKey, String units, String theme, boolean isNotification){
        SettingsSingleton.setUnits(units);
        SettingsSingleton.setTheme(theme);
        SettingsSingleton.setIsNotificationOn(isNotification);
        requestData(getCityName(lastKey));
    }

    private String getCityName(String lastKey){
        String cityName;
        if(SettingsSingleton.getCurrentCity().isEmpty()){
            if(lastKey.isEmpty())cityName = SettingsSingleton.getDefaultCity();
            else cityName = lastKey;
        } else cityName = SettingsSingleton.getCurrentCity();
        return cityName;
    }

    public void requestData(String cityName){
        Observable<OpenWeatherResponse> observable = retrofitApi
                .requestServer(cityName, Constants.APIKEY, SettingsSingleton.getUnits());
        subscriptions.add(observable.observeOn(AndroidSchedulers.mainThread()).subscribe( emitter -> {
            responseInfo = new CurrentResponseInfo(emitter, SettingsSingleton.getUnits());
            if(!responseInfo.getCityName().equals("")){
                getViewState().saveLastKey(cityName);
                SettingsSingleton.setCurrentCity(cityName);
            }
            getViewState().renderWeather(responseInfo);
            checkResponse();
        },  throwable -> {
            if (throwable instanceof IOException) {
                getViewState().showError(R.string.load_info_network_error);
            } else {
                getViewState().showError(R.string.load_info_server_error);
            }
            Log.e(Constants.TAG, Constants.ERROR_MESSAGE + throwable);
        }));
    }

    private void checkResponse(){
        Log.d(Constants.TAG, "Server response code: " + ErrorInterceptor.code);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!subscriptions.isDisposed())subscriptions.dispose();
    }
}
