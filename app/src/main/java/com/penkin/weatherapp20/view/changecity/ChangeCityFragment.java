package com.penkin.weatherapp20.view.changecity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.penkin.weatherapp20.R;
import com.penkin.weatherapp20.databinding.FragmentChangecityBinding;
import com.penkin.weatherapp20.model.SettingsSingleton;

import java.util.Objects;

import moxy.MvpAppCompatFragment;

public class ChangeCityFragment extends MvpAppCompatFragment implements ChangeCityView, View.OnClickListener {

    private FragmentChangecityBinding changeCityBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        changeCityBinding = FragmentChangecityBinding.inflate(inflater, container, false);
        return changeCityBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        initToolbar();
        clickListenerInit();
        changeCityBinding.curCityNameTV.setText(SettingsSingleton.getCurrentCity());
    }

    private void initToolbar(){
        ((AppCompatActivity) requireActivity()).setSupportActionBar(changeCityBinding.changeCityToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        changeCityBinding = null;
    }

    private void clickListenerInit(){
        changeCityBinding.determineButton.setOnClickListener(this);
        changeCityBinding.moscowButton.setOnClickListener(this);
        changeCityBinding.spbButton.setOnClickListener(this);
        changeCityBinding.vladimirButton.setOnClickListener(this);
        changeCityBinding.bishkekButton.setOnClickListener(this);
        changeCityBinding.novosibirskButton.setOnClickListener(this);
        changeCityBinding.almatyButton.setOnClickListener(this);
        changeCityBinding.acceptButton.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.acceptButton:{
                break;
            }
            case R.id.moscowButton:{
                changeCityBinding.cityNameACTV.setText(R.string.moscow);
                break;
            }
            case R.id.spbButton:{
                changeCityBinding.cityNameACTV.setText(R.string.saint_petersburg);
                break;
            }
            case R.id.vladimirButton:{
                changeCityBinding.cityNameACTV.setText(R.string.vladimir);
                break;
            }
            case R.id.bishkekButton:{
                changeCityBinding.cityNameACTV.setText(R.string.bishkek);
                break;
            }
            case R.id.novosibirskButton:{
                changeCityBinding.cityNameACTV.setText(R.string.novosibirsk);
                break;
            }
            case R.id.almatyButton:{
                changeCityBinding.cityNameACTV.setText(R.string.almaty);
                break;
            }
            case R.id.determineButton:{
                changeCityBinding.cityNameACTV.setText(SettingsSingleton.getLocationCity());
                break;
            }
            /*case R.id.clearHistoryButton:{
                CityTable.deleteAll(MainActivity.getDatabase());
                DatabaseHelper.clearHistoryList();
                history.setText("");
                break;
            }*/
        }
    }
}
