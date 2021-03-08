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
import com.penkin.weatherapp20.presenter.SettingsPresenter;

import java.util.Objects;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class SettingsFragment extends MvpAppCompatFragment implements SettingsView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @InjectPresenter
    SettingsPresenter presenter;
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
                settingsBinding.celButtonSetFrag.setBackgroundColor(getResources()
                        .getColor(R.color.design_default_color_error, null));
                settingsBinding.farButtonSetFrag.setBackgroundColor(getResources()
                        .getColor(R.color.design_default_color_primary, null));
                presenter.setUnits(true);
                break;
            }
            case R.id.farButtonSetFrag:{
                Log.d(Constants.TAG, "Fahrenheit button clicked!");
                settingsBinding.celButtonSetFrag.setBackgroundColor(getResources()
                        .getColor(R.color.design_default_color_primary, null));
                settingsBinding.farButtonSetFrag.setBackgroundColor(getResources()
                        .getColor(R.color.design_default_color_error, null));
                presenter.setUnits(false);
                break;
            }
            case R.id.winterThemeSetFrag:{
                Log.d(Constants.TAG, "Violet button clicked!");
                settingsBinding.winterThemeSetFrag.setBackgroundColor(getResources()
                        .getColor(R.color.design_default_color_error, null));
                settingsBinding.springThemeSetFrag.setBackgroundColor(getResources()
                        .getColor(R.color.design_default_color_primary, null));
                //if(getActivity() != null)getActivity().recreate();
                break;
            }
            case R.id.springThemeSetFrag:{
                Log.d(Constants.TAG, "Green button clicked!");
                settingsBinding.winterThemeSetFrag.setBackgroundColor(getResources()
                        .getColor(R.color.design_default_color_primary, null));
                settingsBinding.springThemeSetFrag.setBackgroundColor(getResources()
                        .getColor(R.color.design_default_color_error, null));
                //if(getActivity() != null)getActivity().recreate();
                break;
            }
            case R.id.acceptButtonSetFrag:{
                Log.d(Constants.TAG, "Accept button clicked!");
                break;
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        presenter.setNotification(isChecked);
    }
}
