package com.penkin.weatherapp20.di;

import com.penkin.weatherapp20.model.retrofit.RetrofitApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    RetrofitApi provideRetrofitApi(){return new RetrofitApi();}
}
