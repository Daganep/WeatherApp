package com.penkin.weatherapp20.presenter;

import android.util.Log;

import com.penkin.weatherapp20.R;
import com.penkin.weatherapp20.application.Constants;
import com.penkin.weatherapp20.application.WeatherApp;
import com.penkin.weatherapp20.model.SettingsSingleton;
import com.penkin.weatherapp20.model.database.CityDao;
import com.penkin.weatherapp20.model.database.CityHistoryDatabase;
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
    @Inject
    CityHistoryDatabase appDatabase;
    private final CityDao cityDao;
    private final CompositeDisposable subscriptions;
    private final String TAG = "Error";
    private CurrentResponseInfo responseInfo;

    public MainPresenter(){
        WeatherApp.getAppComponent().inject(this);
        cityDao = appDatabase.cityDao();
        subscriptions = new CompositeDisposable();
    }

    public void getData(String lastKey){
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
        Observable<OpenWeatherResponse> observable = retrofitApi.requestServer(cityName, Constants.APIKEY, "metric");
        subscriptions.add(observable.observeOn(AndroidSchedulers.mainThread()).subscribe( emitter -> {
            responseInfo = new CurrentResponseInfo(emitter);
            if(!responseInfo.getCityName().equals("")){
                getViewState().saveLastKey(cityName);
                SettingsSingleton.setCurrentCity(cityName);
            }
            getViewState().renderWeather(new CurrentResponseInfo(emitter));
            checkResponse();
        },  throwable -> {
            if (throwable instanceof IOException) {
                getViewState().showError(R.string.load_info_network_error);
            } else {
                getViewState().showError(R.string.load_info_server_error);
            }
            Log.e(TAG, "onError" + throwable);
        }));
    }

    private void checkResponse(){
        Log.d(TAG, "Server response code: " + ErrorInterceptor.code);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!subscriptions.isDisposed())subscriptions.dispose();
    }
}
