package com.example.weatherchallenge.models;

import com.google.gson.annotations.SerializedName;

public class WeatherInfo {
    @SerializedName("temp")
    private float temperature;
    @SerializedName("feels_like")
    private float temperature_feeling;
    @SerializedName("temp_min")
    private float temperature_minimum;
    @SerializedName("temp_max")
    private float temperature_maximum;
    @SerializedName("pressure")
    private int pressure;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("sea_level")
    private int sea_level;
    @SerializedName("grnd_level")
    private int ground_level;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getTemperature_feeling() {
        return temperature_feeling;
    }

    public void setTemperature_feeling(float temperature_feeling) {
        this.temperature_feeling = temperature_feeling;
    }

    public float getTemperature_minimum() {
        return temperature_minimum;
    }

    public void setTemperature_minimum(float temperature_minimum) {
        this.temperature_minimum = temperature_minimum;
    }

    public float getTemperature_maximum() {
        return temperature_maximum;
    }

    public void setTemperature_maximum(float temperature_maximum) {
        this.temperature_maximum = temperature_maximum;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getSea_level() {
        return sea_level;
    }

    public void setSea_level(int sea_level) {
        this.sea_level = sea_level;
    }

    public int getGround_level() {
        return ground_level;
    }

    public void setGround_level(int ground_level) {
        this.ground_level = ground_level;
    }
}
