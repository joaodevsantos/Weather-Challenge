package com.example.weatherchallenge.ui.fragments;

import static com.example.weatherchallenge.utils.Constants.API_KEY;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherchallenge.R;
import com.example.weatherchallenge.models.CityWeather;
import com.example.weatherchallenge.networking.webservices.weather.APIInterface;
import com.example.weatherchallenge.networking.webservices.weather.ServiceGenerator;
import com.example.weatherchallenge.ui.adapters.LocationsAdapter;
import com.example.weatherchallenge.ui.adapters.LocationsAdapter.OnItemClickListener;
import com.example.weatherchallenge.utils.Functions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationsFragment extends Fragment {

    private final String TAG = LocationsFragment.class.getSimpleName();

    @BindView(R.id.fragment_locations_recyclerview)
    RecyclerView recyclerView;
    private LocationsAdapter locationsAdapter;
    private List<CityWeather> citiesWeather = new ArrayList<>();

    private Unbinder unbinder;

    private final OnItemClickListener onItemClickListener = cityId -> {
        Bundle bundle = new Bundle();
        bundle.putInt("cityId", cityId);

        LocationFragment locationFragment = new LocationFragment();
        locationFragment.setArguments(bundle);

        Functions.changeFragment(getActivity(), R.id.activity_main_frame, locationFragment);
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_locations, container, false);
        unbinder = ButterKnife.bind(view);

        // Setup the Recycler View
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        locationsAdapter = new LocationsAdapter(getContext(), citiesWeather, onItemClickListener);
        recyclerView.setAdapter(locationsAdapter);

        // TEMPORARY
        List<String> cities = Arrays.asList("Lisboa", "Madrid", "Paris", "Berlim", "Copenhaga",
                "Roma", "Londres", "Dublin", "Praga", "Viena");
        getWeatherForLocations(cities);
        //getCurrentLocation();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void getWeatherForLocations(List<String> cities){
        for(String city : cities)
            getWeatherForLocation(city);
    }

    private void getWeatherForLocation(String city){
        APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);
        Call<CityWeather> getCurrentWeatherAt = apiInterface.getCurrentWeatherAt(city, API_KEY);

        // Consume the web API to get the weather at a specific location
        getCurrentWeatherAt.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if(response.isSuccessful()){
                    citiesWeather.add(response.body());
                    locationsAdapter.notifyDataSetChanged();
                } else
                    Log.d(TAG, response.message());
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    private void getCurrentLocation(){
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
            fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, null)
                    .addOnSuccessListener(this::getCurrentWeatherAtMyLocation);
        }
    }

    private void getCurrentWeatherAtMyLocation(Location location){
        APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);
        Call<CityWeather> getCurrentWeatherAtMyLocation = apiInterface.getCurrentWeatherBy(location.getLatitude(),
                                                                                            location.getLongitude(),
                                                                                            API_KEY);

        // Consume the web API to get the weather at user's current location
        getCurrentWeatherAtMyLocation.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if(response.isSuccessful()){
                    CityWeather cityWeather = response.body();
                    citiesWeather.add(cityWeather);
                    locationsAdapter.notifyDataSetChanged();
                } else
                    Log.d(TAG, response.message());
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }
}
