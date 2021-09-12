package com.example.weatherchallenge.ui.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.weatherchallenge.R;
import com.example.weatherchallenge.ui.fragments.LocationsFragment;
import com.example.weatherchallenge.utils.Functions;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private final ActivityResultLauncher<String> requestLocationPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                // Although the permission, the user moves to Locations Fragment
                // There, the permission is always checked before present the list
                // This prevents the user going to settings and change the permission by himself
                // At this Main activity we only need to grant that the request is made
                Functions.changeFragment(MainActivity.this,
                        R.id.activity_main_frame,
                        getLocationsFragmentWithCities());
            });

    private final DialogInterface.OnClickListener onRationaleClick = (dialog, which) -> {
        // If the user wants to give the permission
        if(which == DialogInterface.BUTTON_POSITIVE)
            // Request the location permission
            requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION);
        else if(which == DialogInterface.BUTTON_NEGATIVE)
            // Move to the locations fragment without location permission
            Functions.changeFragment(MainActivity.this,
                    R.id.activity_main_frame,
                    getLocationsFragmentWithCities());
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if the location permission is granted or denied
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                                                Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_DENIED)
            // Check if it is needed to present a permission rationale
            if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION))
                // Show the rational as an UI educational
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(getString(R.string.rationale_location_title))
                        .setMessage(getString(R.string.rationale_location_message))
                        .setPositiveButton(getString(R.string.rationale_location_btn_ok), onRationaleClick)
                        .setNegativeButton(getString(R.string.rationale_location_btn_cancel), onRationaleClick)
                        .show();
            else
                // Request the location permission
                requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION);
        else
            // If the permission is already granted
            // Move to locations fragment
            Functions.changeFragment(MainActivity.this,
                    R.id.activity_main_frame,
                    getLocationsFragmentWithCities());
    }

    private LocationsFragment getLocationsFragmentWithCities(){
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("citiesList", new ArrayList<>(Arrays.asList("Lisbon,pt", "Madrid,es", "Paris,fr", "Berlin,de",
                "Copenhagen,dk", "Roma,it", "London,uk", "Dublin,ie", "Prague,cz", "Vienna,at")));

        LocationsFragment frag = new LocationsFragment();
        frag.setArguments(bundle);

        return frag;
    }
}