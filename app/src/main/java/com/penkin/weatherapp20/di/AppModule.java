package com.penkin.weatherapp20.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.penkin.weatherapp20.model.database.CityHistoryDatabase;
import com.penkin.weatherapp20.model.retrofit.RetrofitApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    CityHistoryDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context,
                CityHistoryDatabase.class, "weather_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    RetrofitApi provideRetrofitApi(){return new RetrofitApi();}

    @Singleton
    @Provides
    Context provideContext() {
        return application;
    }
}
