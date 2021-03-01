package com.penkin.weatherapp20.view.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.penkin.weatherapp20.R;
import com.penkin.weatherapp20.databinding.FragmentMainBinding;

import moxy.MvpAppCompatFragment;

public class MainFragment extends MvpAppCompatFragment implements MainView {

    private FragmentMainBinding mainBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
