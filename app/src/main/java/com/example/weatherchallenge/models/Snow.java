package com.example.weatherchallenge.models;

import com.google.gson.annotations.SerializedName;

public class Snow {
    @SerializedName("1h")
    private int ofLastHour;
    @SerializedName("3h")
    private int ofLastThreeHours;

    public int getOfLastHour() {
        return ofLastHour;
    }

    public void setOfLastHour(int ofLastHour) {
        this.ofLastHour = ofLastHour;
    }

    public int getOfLastThreeHours() {
        return ofLastThreeHours;
    }

    public void setOfLastThreeHours(int ofLastThreeHours) {
        this.ofLastThreeHours = ofLastThreeHours;
    }
}
