package com.example.weatherchallenge.ui.fragments;

import static com.example.weatherchallenge.utils.Constants.API_KEY;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weatherchallenge.R;
import com.example.weatherchallenge.models.CityWeather;
import com.example.weatherchallenge.networking.webservices.weather.APIInterface;
import com.example.weatherchallenge.networking.webservices.weather.ServiceGenerator;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationFragment extends Fragment {

    private final String TAG = LocationFragment.class.getSimpleName();

    @BindView(R.id.item_city_weather_city)
    TextView city;
    @BindView(R.id.item_city_weather_datetime)
    TextView timestamp;
    @BindView(R.id.item_city_weather_temperature)
    TextView temperature;
    @BindView(R.id.item_city_weather_description)
    TextView description;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        unbinder = ButterKnife.bind(view);

        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("cityId"))
            getCurrentWeatherByCityId(bundle.getInt("cityId"));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void getCurrentWeatherByCityId(int cityId){
        APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);
        Call<CityWeather> getCurrentWeatherByCityId = apiInterface.getCurrentWeatherBy(cityId, API_KEY);

        // Consume the web API to get the weather at user's current location
        getCurrentWeatherByCityId.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if(response.isSuccessful()){
                    CityWeather cityWeather = response.body();

                    // Populate the view
                    city.setText(cityWeather.getCity());

                    Date time = new java.util.Date((long)cityWeather.getLast_update_timestamp()*1000);
                    timestamp.setText(time.toString());

                    temperature.setText(String.format("%f", cityWeather.getWeather().getTemperature()));
                    description.setText(cityWeather.getWeatherDescription().get(0).getDescription());

                } else
                    Log.d(TAG, response.message());
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }
}
