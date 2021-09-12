package com.example.weatherchallenge.ui.fragments;

import static com.example.weatherchallenge.utils.Constants.API_KEY;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weatherchallenge.R;
import com.example.weatherchallenge.databinding.FragmentLocationsBinding;
import com.example.weatherchallenge.models.CityWeather;
import com.example.weatherchallenge.networking.webservices.weather.APIInterface;
import com.example.weatherchallenge.networking.webservices.weather.ServiceGenerator;
import com.example.weatherchallenge.ui.adapters.LocationsAdapter;
import com.example.weatherchallenge.ui.adapters.LocationsAdapter.OnItemClickListener;
import com.example.weatherchallenge.utils.Functions;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.FusedLocationProviderClient;
import com.huawei.hms.location.FusedLocationProviderClient;
import com.huawei.hms.location.LocationServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationsFragment extends Fragment {

    private LocationsAdapter locationsAdapter;
    private List<CityWeather> citiesWeather = new ArrayList<>();

    private FragmentLocationsBinding binding;

    // TEMPORARY
    private final List<String> cities = Arrays.asList("Lisbon,pt", "Madrid,es", "Paris,fr", "Berlin,de",
            "Copenhagen,dk", "Roma,it", "London,uk", "Dublin,ie", "Prague,cz", "Vienna,at");

    private final OnItemClickListener onItemClickListener = cityId -> {
        Bundle bundle = new Bundle();
        bundle.putInt("cityId", cityId);

        LocationFragment locationFragment = new LocationFragment();
        locationFragment.setArguments(bundle);

        Functions.changeFragmentToBackstack(getActivity(), R.id.activity_main_frame, locationFragment);
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Binding with View Binding of Android Jetpack
        binding = FragmentLocationsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Setup the Recycler View
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerview.setLayoutManager(linearLayoutManager);

        locationsAdapter = new LocationsAdapter(getActivity(), citiesWeather, onItemClickListener);
        binding.recyclerview.setAdapter(locationsAdapter);

        // TEMPORARY
        if(citiesWeather.size() == 0)
            getCurrentLocation();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getWeatherForLocations(List<String> cities){
        for(String city : cities)
            getWeatherForLocation(city);
    }

    private void getWeatherForLocation(String city){
        APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);
        Call<CityWeather> getCurrentWeatherAt = apiInterface.getCurrentWeatherAt(city, API_KEY, "metric");

        // Consume the web API to get the weather at a specific location
        getCurrentWeatherAt.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if(response.isSuccessful()){
                    citiesWeather.add(response.body());
                    locationsAdapter.notifyDataSetChanged();
                } else {
                    // Do something
                }
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                // Do something
            }
        });
    }

    private void getCurrentLocation(){
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

            fusedLocationProviderClient.getLastLocation()
                                        .addOnSuccessListener(this::getCurrentWeatherAtMyLocation)
                                        .addOnFailureListener(e -> getWeatherForLocations(cities));

            //fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, null)
        } else
            getWeatherForLocations(cities);
    }

    private void getCurrentWeatherAtMyLocation(Location location){
        APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);
        Call<CityWeather> getCurrentWeatherAtMyLocation = apiInterface.getCurrentWeatherBy(location.getLatitude(),
                                                                                            location.getLongitude(),
                                                                                            API_KEY, "metric");

        // Consume the web API to get the weather at user's current location
        getCurrentWeatherAtMyLocation.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if(response.isSuccessful()){
                    citiesWeather.add(response.body());
                    locationsAdapter.notifyDataSetChanged();
                }

                getWeatherForLocations(cities);
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                getWeatherForLocations(cities);
            }
        });
    }
}
