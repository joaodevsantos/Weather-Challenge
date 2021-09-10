package com.example.weatherchallenge.models;

import com.google.gson.annotations.SerializedName;

public class SunInfo {
    @SerializedName("sunrise")
    private int sunrise_timestamp;
    @SerializedName("sunset")
    private int sunset_timestamp;

    public int getSunrise_timestamp() {
        return sunrise_timestamp;
    }

    public void setSunrise_timestamp(int sunrise_timestamp) {
        this.sunrise_timestamp = sunrise_timestamp;
    }

    public int getSunset_timestamp() {
        return sunset_timestamp;
    }

    public void setSunset_timestamp(int sunset_timestamp) {
        this.sunset_timestamp = sunset_timestamp;
    }
}
