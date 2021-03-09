package com.penkin.weatherapp20.view.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.penkin.weatherapp20.R;
import com.penkin.weatherapp20.application.Constants;
import com.penkin.weatherapp20.databinding.FragmentMainBinding;
import com.penkin.weatherapp20.model.entities.CurrentResponseInfo;
import com.penkin.weatherapp20.presenter.MainPresenter;
import com.penkin.weatherapp20.utils.ImageSetter;

import java.util.Objects;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class MainFragment extends MvpAppCompatFragment implements MainView {

    @InjectPresenter
    MainPresenter presenter;
    private FragmentMainBinding mainBinding;
    private ImageSetter imageSetter;
    private NavController navController;
    private String lastKey;
    private String units;
    private String theme;
    private boolean isNotification;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainBinding = FragmentMainBinding.inflate(inflater, container, false);
        return mainBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setHasOptionsMenu(true);
        presenter.getData(lastKey, units, theme, isNotification);
    }

    private void init(View view){
        imageSetter = new ImageSetter();
        initToolbar();
        navController = Navigation.findNavController(view);
        loadSettings();
    }

    private void initToolbar(){
        ((AppCompatActivity) requireActivity()).setSupportActionBar(mainBinding.mainToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_toolbar_main, menu);

        MenuItem changeCityItem = menu.findItem(R.id.menu_change_city);
        changeCityItem.setOnMenuItemClickListener(item -> {
            navController.navigate(R.id.changeCityFragment);
            return false;
        });

        MenuItem settingsItem = menu.findItem(R.id.menu_settings);
        settingsItem.setOnMenuItemClickListener(item -> {
            navController.navigate(R.id.settingsFragment);
            return false;
        });
    }

    @Override
    public void renderWeather(CurrentResponseInfo response){
        mainBinding.curCityNameMF.setText(response.getCityName());
        mainBinding.curTempMF.setText(response.getCurrentTemp());
        imageSetter.setImage(response.getCurrentImagePath(), mainBinding.curTempImageMF);
        mainBinding.curTempDescriptMF.setText(response.getCurrentTempDescription());
        mainBinding.curTempSensMF.setText(response.getGetCurrentTempSens());
        mainBinding.windSpeedMeanMF.setText(response.getWindSpeed());
        mainBinding.pressureMeanMF.setText(response.getPressure());
        mainBinding.humidityMeanMF.setText(response.getHumidity());

        //later code below will be delete (view will be initialize in RecyclerView)
        mainBinding.tomorrowDateMF.setText(response.getOtherDayDate(1));
        mainBinding.tomorrowMF.setText(response.getOtherDay(1));
        imageSetter.setImage(response.getOtherDayImagePath(1), mainBinding.tomorrowTempImageMF);
        mainBinding.tomorrowDayTempMF.setText(response.getOtherDayTemp(1));
        mainBinding.tomorrowNightTempMF.setText(response.getOtherNightTemp(1));

        mainBinding.day3DateMF.setText(response.getOtherDayDate(2));
        mainBinding.day3MF.setText(response.getOtherDay(2));
        imageSetter.setImage(response.getOtherDayImagePath(2), mainBinding.day3TempImageMF);
        mainBinding.day3DayTempMF.setText(response.getOtherDayTemp(2));
        mainBinding.day3NightTempMF.setText(response.getOtherNightTemp(2));

        mainBinding.day4DateMF.setText(response.getOtherDayDate(3));
        mainBinding.day4MF.setText(response.getOtherDay(3));
        imageSetter.setImage(response.getOtherDayImagePath(3), mainBinding.day4TempImageMF);
        mainBinding.day4DayTempMF.setText(response.getOtherDayTemp(3));
        mainBinding.day4NightTempMF.setText(response.getOtherNightTemp(3));

        mainBinding.day5DateMF.setText(response.getOtherDayDate(4));
        mainBinding.day5MF.setText(response.getOtherDay(4));
        imageSetter.setImage(response.getOtherDayImagePath(4), mainBinding.day5TempImageMF);
        mainBinding.day5DayTempMF.setText(response.getOtherDayTemp(4));
        mainBinding.day5NightTempMF.setText(response.getOtherNightTemp(4));
    }

    @Override
    public void showError(int msg) {
        mainBinding.mainScreen.setVisibility(View.GONE);
        mainBinding.emptyResult.setText(msg);
        mainBinding.emptyResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void saveLastKey(String key){
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(getString(R.string.last_key), key);
        editor.apply();
    }

    @Override
    public void loadSettings(){
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        lastKey = prefs.getString(getString(R.string.last_key), "");
        units = prefs.getString(getString(R.string.units_in_settings_fragment), Constants.METRIC);
        theme = prefs.getString(getString(R.string.theme_in_settings_fragment), Constants.SPRING);
        isNotification = prefs.getBoolean(getString(R.string.not_in_settings_fragment), true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainBinding = null;
    }
}
