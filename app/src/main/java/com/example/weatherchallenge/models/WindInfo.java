package com.example.weatherchallenge.models;

import com.google.gson.annotations.SerializedName;

public class WindInfo {
    @SerializedName("speed")
    private float speed;
    @SerializedName("deg")
    private int direction; // in degrees
    @SerializedName("gust")
    private float gust; // Default: meter/sec, Metric: meter/sec, Imperial: miles/hour

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public float getGust() {
        return gust;
    }

    public void setGust(float gust) {
        this.gust = gust;
    }
}
