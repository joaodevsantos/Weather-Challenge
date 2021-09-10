package com.example.weatherchallenge.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherchallenge.R;
import com.example.weatherchallenge.models.CityWeather;

import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.ViewHolder> {

    private Context context;
    private List<CityWeather> citiesWeather;
    private final OnItemClickListener listener;

    public LocationsAdapter(Context context,
                            List<CityWeather> citiesWeather,
                            OnItemClickListener listener){
        this.context = context;
        this.citiesWeather = citiesWeather;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                                    .inflate(R.layout.item_city_weather, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CityWeather cityWeather = citiesWeather.get(position);

        // Set listener to "OnItemClick"
        // Sends his identifier over Bundle to the detailed fragment
        holder.itemView.setOnClickListener(v -> listener.onItemClick(cityWeather.getId()));

        holder.city.setText(cityWeather.getCity());

        Date time = new java.util.Date((long)cityWeather.getLast_update_timestamp()*1000);
        holder.timestamp.setText(time.toString());

        holder.temperature.setText("" + cityWeather.getWeather().getTemperature());
        holder.description.setText(cityWeather.getWeatherDescription().get(0).getDescription());
    }

    @Override
    public int getItemCount() {
        return citiesWeather.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_city_weather_city)
        TextView city;
        @BindView(R.id.item_city_weather_datetime)
        TextView timestamp;
        @BindView(R.id.item_city_weather_temperature)
        TextView temperature;
        @BindView(R.id.item_city_weather_description)
        TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int cityId);
    }
}
