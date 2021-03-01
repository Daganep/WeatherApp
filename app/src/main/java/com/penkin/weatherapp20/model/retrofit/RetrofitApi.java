package com.penkin.weatherapp20.model.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.penkin.weatherapp20.model.entities.OpenWeatherResponse;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    private final String baseUrl = "https://api.openweathermap.org/";
    private final IRetrofitService api;

    public RetrofitApi(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ErrorInterceptor())
                .build();

        api = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(IRetrofitService.class);
    }

    public Observable<OpenWeatherResponse> requestServer(String city, String apiKey, String units){
        return api.getWeather(city, apiKey, units).subscribeOn(Schedulers.io());
    }
}
