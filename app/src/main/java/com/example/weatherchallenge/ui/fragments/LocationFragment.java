package com.example.weatherchallenge.ui.fragments;

import static com.example.weatherchallenge.utils.Constants.API_KEY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weatherchallenge.databinding.FragmentLocationBinding;
import com.example.weatherchallenge.models.CityWeather;
import com.example.weatherchallenge.networking.webservices.weather.APIInterface;
import com.example.weatherchallenge.networking.webservices.weather.ServiceGenerator;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationFragment extends Fragment {

    private FragmentLocationBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("cityId"))
            getCurrentWeatherByCityId(bundle.getInt("cityId"));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getCurrentWeatherByCityId(int cityId){
        APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);
        Call<CityWeather> getCurrentWeatherByCityId = apiInterface.getCurrentWeatherBy(cityId, API_KEY, "metric");

        // Consume the web API to get the weather at user's current location
        getCurrentWeatherByCityId.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if(response.isSuccessful()){
                    CityWeather cityWeather = response.body();

                    // Populate the view
                    binding.city.setText(cityWeather.getCity());

                    Date time = new java.util.Date((long)cityWeather.getLast_update_timestamp()*1000);
                    binding.datetime.setText(time.toString());

                    binding.temperature.setText(String.format("%f", cityWeather.getWeather().getTemperature()));
                    binding.description.setText(cityWeather.getWeatherDescription().get(0).getDescription());

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
}
