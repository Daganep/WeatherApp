package com.penkin.weatherapp20.view.changecity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.penkin.weatherapp20.R;
import com.penkin.weatherapp20.databinding.FragmentChangecityBinding;
import com.penkin.weatherapp20.model.SettingsSingleton;
import com.penkin.weatherapp20.presenter.ChangeCityPresenter;

import java.util.Objects;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class ChangeCityFragment extends MvpAppCompatFragment implements ChangeCityView, View.OnClickListener, TextWatcher {

    @InjectPresenter
    ChangeCityPresenter presenter;
    private NavController navController;
    private FragmentChangecityBinding changeCityBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        changeCityBinding = FragmentChangecityBinding.inflate(inflater, container, false);
        return changeCityBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view){
        initToolbar();
        clickListenerInit();
        autoCompleteInit();
        navController =  Navigation.findNavController(view);
        presenter.getCurrentCity();
        presenter.getCityHistory();
    }

    private void autoCompleteInit(){
        ArrayAdapter<?> adapter = ArrayAdapter
                .createFromResource(requireContext(),
                R.array.cities,
                android.R.layout.select_dialog_item);
        changeCityBinding.cityNameACTV.setThreshold(1);
        changeCityBinding.cityNameACTV.setAdapter(adapter);
        changeCityBinding.cityNameACTV.addTextChangedListener(this);
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
        changeCityBinding.clearHistoryButton.setOnClickListener(this);
    }

    @Override
    public void setHistory(String cities){
        changeCityBinding.citiesInHistoryTV.setText(cities);
    }

    @Override
    public void setCurrentCity(String city){
        changeCityBinding.cityNameACTV.setText(city);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.acceptButton:{
                presenter.setCurrentCity(changeCityBinding.cityNameACTV.getText().toString());
                presenter.saveHistory(changeCityBinding.cityNameACTV.getText().toString());
                navController.navigate(R.id.mainFragment);
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
                presenter.getLocationCity();
                break;
            }
            case R.id.clearHistoryButton:{
                presenter.clearHistory();
                break;
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
}
