package com.example.weatherchallenge.ui.fragments;

import static com.example.weatherchallenge.utils.Constants.API_KEY;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.weatherchallenge.R;
import com.example.weatherchallenge.models.CityWeather;
import com.example.weatherchallenge.networking.webservices.weather.APIInterface;
import com.example.weatherchallenge.networking.webservices.weather.ServiceGenerator;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationsFragment extends Fragment {

    TextView tv;

    private final String TAG = LocationsFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_locations, container, false);

        tv = view.findViewById(R.id.tv_locations);

        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){

            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
            fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, null)
                                                                .addOnSuccessListener(location -> {

                                                                    APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);
                Call<CityWeather> getCurrentWeatherAtMyLocation = apiInterface.getCurrentWeatherBy(location.getLatitude(),
                                                                                                    location.getLongitude(),
                                                                                                    API_KEY);
                getCurrentWeatherAtMyLocation.enqueue(new Callback<CityWeather>() {
                    @Override
                    public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                        if(response.isSuccessful()){
                            CityWeather cityWeather = response.body();
                            tv.setText(cityWeather.getCity());
                        } else
                            Log.d(TAG, response.message());
                    }

                    @Override
                    public void onFailure(Call<CityWeather> call, Throwable t) {
                        Log.d(TAG, t.getMessage());
                    }
                });
            });
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
