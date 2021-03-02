package com.penkin.weatherapp20.view.changecity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.penkin.weatherapp20.databinding.FragmentChangecityBinding;

import java.util.Objects;

public class ChangeCityFragment extends Fragment {

    private FragmentChangecityBinding changeCityBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        changeCityBinding = FragmentChangecityBinding.inflate(inflater, container, false);
        return changeCityBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
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
}
