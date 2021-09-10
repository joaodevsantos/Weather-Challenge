package com.example.weatherchallenge.UI.Utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class Functions {
    public static void changeFragment(FragmentActivity fragmentActivity,
                                          int containerId,
                                          Fragment fragment){
        fragmentActivity.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(containerId, fragment)
                            .commit();
    }

    public static void changeFragmentToBackstack(FragmentActivity fragmentActivity,
                                          int containerId,
                                          Fragment fragment){
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(null)
                .commit();
    }
}
