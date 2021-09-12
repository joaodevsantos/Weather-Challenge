package com.example.weatherchallenge.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.weatherchallenge.R;

public class Functions {
    public static void changeFragment(FragmentActivity fragmentActivity,
                                      int containerId,
                                      Fragment fragment) {
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment)
                .commit();
    }

    public static void changeFragmentToBackstack(FragmentActivity fragmentActivity,
                                                 int containerId,
                                                 Fragment fragment) {
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(null)
                .commit();
    }

    public static int getWeatherDrawable(int hour, String description) {
        if (hour > 22 || hour < 6)
            return R.drawable.weather_night;
        else if (description.contains("clear"))
            return R.drawable.weather_sun;
        else if (description.contains("clouds"))
            return R.drawable.weather_clouds;
        else if (description.contains("rain"))
            return R.drawable.weather_rain;
        else if (description.contains("snow"))
            return R.drawable.weather_snow;

        return R.drawable.weather_sun;
    }
}
