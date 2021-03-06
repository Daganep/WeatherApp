package com.penkin.weatherapp20.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.penkin.weatherapp20.R;
import com.penkin.weatherapp20.application.Constants;
import com.penkin.weatherapp20.model.SettingsSingleton;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /** The link to a {@link LocationManager} instance for quick access. */
    public LocationManager mLocManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLocation();
    }

    @Override public boolean onSupportNavigateUp () {
        onBackPressed() ;
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(requestCode == 100) {
            boolean permissionsGranted = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    permissionsGranted = false;
                    break;
                }
            }
            if(permissionsGranted) recreate();
        }
    }

    private void getLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        } else {
            // Location manager
            mLocManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // Current Location
            Location loc;

            // Receive information from GPS provider
            assert mLocManager != null;
            loc = mLocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            //mGpsInfo.setText(locToString(loc));

            // Receive information from Passive (virtual) provider
            loc = mLocManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            //mPassiveInfo.setText(locToString(loc));
            if (loc != null) {
                SettingsSingleton.setLocationCity(getCityLoc(loc));
            }
        }
    }

    private String getCityLoc(Location loc){
        // Create geocoder
        final Geocoder geo = new Geocoder(this);

        // Try to get addresses list
        List<Address> list;
        try {
            list = geo.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }

        // If list is empty, return "No data" string
        if (list.isEmpty()) return Constants.MSG_NO_DATA;

        // Get first element from List
        Address a = list.get(0);

        // Get a Postal Code
        final int index = a.getMaxAddressLineIndex();
        String postal = null;
        if (index >= 0) {
            postal = a.getAddressLine(index);
        }
        return a.getAdminArea();
    }
}