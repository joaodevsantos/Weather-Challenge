package com.example.weatherchallenge.networking.webservices.weather;

import com.example.weatherchallenge.models.CityWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("weather")
    public Call<CityWeather> getCurrentWeatherAt(@Query("q") String city,
                                                   @Query("appid") String APIkey);

    @GET("weather")
    public Call<CityWeather> getCurrentWeatherBy(@Query("lat") double latitude,
                                                   @Query("lon") double longitude,
                                                   @Query("appid") String APIkey);
}
