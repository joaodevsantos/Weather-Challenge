package com.example.weatherchallenge.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.weatherchallenge.databinding.FragmentLocationBinding;
import com.example.weatherchallenge.models.CityWeather;
import com.example.weatherchallenge.utils.Constants;
import com.example.weatherchallenge.utils.Functions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class LocationFragment extends Fragment {

    private CityWeather cityWeather;

    private FragmentLocationBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("city"))
            cityWeather = bundle.getParcelable("city");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.city.setText(cityWeather.getCity());
        binding.description.setText(cityWeather.getWeatherDescription().get(0).getDescription());
        binding.temperature.setText(String.format("%.1fºC", cityWeather.getWeather().getTemperature()));
        binding.temperatureMax.setText(String.format("%.1fºC", cityWeather.getWeather().getTemperature_maximum()));
        binding.temperatureMin.setText(String.format("%.1fºC", cityWeather.getWeather().getTemperature_minimum()));

        // Sets the date time according to a specific pattern
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_TIME_PATTERN, Locale.getDefault());
        Date date = new java.util.Date((cityWeather.getLast_update_timestamp() + cityWeather.getTimezone() - 3600) * 1000);
        // Gets the hour of the day in 24 hours format
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);

        // Gets the drawable to fit the Image View of the item
        int drawableID = Functions.getWeatherDrawable(calendar.get(Calendar.HOUR_OF_DAY),
                cityWeather.getWeatherDescription().get(0).getDescription());
        // Sets/Loads the image within the image view
        Glide.with(getContext())
                .load(AppCompatResources.getDrawable(getContext(), drawableID))
                .into(binding.background);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
