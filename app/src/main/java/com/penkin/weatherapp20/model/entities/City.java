package com.penkin.weatherapp20.model.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_cities")
public class City implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String country;

    public City(){}

    public City(CurrentCity city){
        if(city.getName() != null)
            name = city.getName();
        if(city.getCountry() != null){
            country = city.getCountry();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
