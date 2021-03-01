package com.penkin.weatherapp20.model.retrofit;

import com.penkin.weatherapp20.model.entities.OpenWeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRetrofitService {
    @GET("data/2.5/forecast")
    Observable<OpenWeatherResponse> getWeather(@Query("q") String city,
                                               @Query("appid") String keyApi,
                                               @Query("units") String units);
}
