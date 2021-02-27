package com.penkin.weatherapp20.presentation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.penkin.weatherapp20.R;
import com.penkin.weatherapp20.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private FragmentMainBinding mainBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
