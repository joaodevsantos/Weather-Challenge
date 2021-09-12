package com.example.weatherchallenge.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityWeather {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String city;
    @SerializedName("dt")
    private long last_update_timestamp;
    @SerializedName("timezone")
    private int timezone;
    @SerializedName("coord")
    private Coordinates coordinates;
    @SerializedName("weather")
    private List<WeatherDescription> weatherDescription;
    @SerializedName("main")
    private WeatherInfo weather;
    @SerializedName("wind")
    private WindInfo wind;
    @SerializedName("clouds")
    private Clouds clouds;
    @SerializedName("rain")
    private Rain rain;
    @SerializedName("snow")
    private Snow snow;
    @SerializedName("sys") // Does not makes sense the sun information within this JSON object
    private SunInfo sun;

    private boolean isCurrent = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getLast_update_timestamp() {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(long last_update_timestamp) {
        this.last_update_timestamp = last_update_timestamp;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
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

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public SunInfo getSun() {
        return sun;
    }

    public void setSun(SunInfo sun) {
        this.sun = sun;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }
}
