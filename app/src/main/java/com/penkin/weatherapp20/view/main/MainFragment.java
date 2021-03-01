package com.penkin.weatherapp20.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.penkin.weatherapp20.databinding.FragmentMainBinding;
import com.penkin.weatherapp20.model.entities.OpenWeatherResponse;
import com.penkin.weatherapp20.presenter.MainPresenter;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class MainFragment extends MvpAppCompatFragment implements MainView {

    @InjectPresenter
    MainPresenter presenter;
    private FragmentMainBinding mainBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainBinding = FragmentMainBinding.inflate(inflater, container, false);
        return mainBinding.getRoot();
    }

    @Override
    public void viewUpdate(OpenWeatherResponse response) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainBinding = null;
    }


}
