package com.example.weatherchallenge.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityWeather {
    @SerializedName("name")
    private String city;
    @SerializedName("dt")
    private int last_update_timestamp;
    @SerializedName("coord")
    private Coordinates coordinates;
    @SerializedName("weather")
    private List<WeatherDescription> weatherDescription;
    @SerializedName("main")
    private WeatherInfo weather;
    @SerializedName("wind")
    private WindInfo wind;
    @SerializedName("sys") // Does not makes sense the sun information within this JSON object
    private SunInfo sun;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLast_update_timestamp() {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(int last_update_timestamp) {
        this.last_update_timestamp = last_update_timestamp;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<WeatherDescription> getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(List<WeatherDescription> weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public WeatherInfo getWeather() {
        return weather;
    }

    public void setWeather(WeatherInfo weather) {
        this.weather = weather;
    }

    public WindInfo getWind() {
        return wind;
    }

    public void setWind(WindInfo wind) {
        this.wind = wind;
    }

    public SunInfo getSun() {
        return sun;
    }

    public void setSun(SunInfo sun) {
        this.sun = sun;
    }
}
