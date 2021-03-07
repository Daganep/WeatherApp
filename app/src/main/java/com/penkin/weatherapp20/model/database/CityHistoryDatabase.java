package com.penkin.weatherapp20.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.penkin.weatherapp20.model.entities.City;

@Database(entities = {City.class}, version = 1, exportSchema = false)
public abstract class CityHistoryDatabase extends RoomDatabase {
    public abstract CityDao cityDao();
}
