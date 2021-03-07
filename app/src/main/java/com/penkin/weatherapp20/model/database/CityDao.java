package com.penkin.weatherapp20.model.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.penkin.weatherapp20.model.entities.City;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface CityDao {
    @Query("SELECT * FROM table_cities")
    Single<List<City>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertList(List<City> elements);

    @Query("DELETE FROM table_cities")
    Single<Integer> deleteAll();
}
