package com.penkin.weatherapp20.view.settings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.penkin.weatherapp20.R;
import com.penkin.weatherapp20.application.Constants;
import com.penkin.weatherapp20.databinding.FragmentSettingsBinding;

import java.util.Objects;

import moxy.MvpAppCompatFragment;

public class SettingsFragment extends MvpAppCompatFragment implements SettingsView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private FragmentSettingsBinding settingsBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsBinding = FragmentSettingsBinding.inflate(inflater, container, false);
        return settingsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view){
        initToolbar();
        clickListenerInit();
    }

    private void initToolbar(){
        ((AppCompatActivity) requireActivity()).setSupportActionBar(settingsBinding.settingsToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void clickListenerInit(){
        settingsBinding.celButtonSetFrag.setOnClickListener(this);
        settingsBinding.farButtonSetFrag.setOnClickListener(this);
        settingsBinding.winterThemeSetFrag.setOnClickListener(this);
        settingsBinding.springThemeSetFrag.setOnClickListener(this);
        settingsBinding.acceptButtonSetFrag.setOnClickListener(this);
    }

    //Log.d will be deleted, there will be button action
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.celButtonSetFrag:{
                Log.d(Constants.TAG, "Celsius button clicked!");
                break;
            }
            case R.id.farButtonSetFrag:{
                Log.d(Constants.TAG, "Fahrenheit button clicked!");
                break;
            }
            case R.id.winterThemeSetFrag:{
                Log.d(Constants.TAG, "Violet button clicked!");
                break;
            }
            case R.id.springThemeSetFrag:{
                Log.d(Constants.TAG, "Green button clicked!");
                break;
            }
            case R.id.acceptButtonSetFrag:{
                Log.d(Constants.TAG, "Accept button clicked!");
                break;
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        //WeatherSettings.setNotificationON(isChecked);
    }
}
