package com.example.weatherchallenge.networking.webservices.weather;

import static com.example.weatherchallenge.utils.Constants.API_URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit retrofit = null;
    private static Gson gson = new GsonBuilder().create();

    public static <T> T createService(Class <T> serviceClass){
        if(retrofit == null)
            retrofit = new retrofit2.Retrofit.Builder()
                                                .baseUrl(API_URL)
                                                .addConverterFactory(GsonConverterFactory.create(gson))
                                                .build();

        return retrofit.create(serviceClass);
    }
}
