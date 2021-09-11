package com.example.weatherchallenge.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherchallenge.R;
import com.example.weatherchallenge.databinding.ItemCityWeatherBinding;
import com.example.weatherchallenge.models.CityWeather;

import java.util.Date;
import java.util.List;

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
        ItemCityWeatherBinding binding = ItemCityWeatherBinding.inflate(LayoutInflater.from(parent.getContext()),
                                                                            parent,
                                                                            false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CityWeather cityWeather = citiesWeather.get(position);

        // Set listener to "OnItemClick"
        // Sends his identifier over Bundle to the detailed fragment
        holder.itemView.setOnClickListener(v -> listener.onItemClick(cityWeather.getId()));
        holder.bind(cityWeather);
    }

    @Override
    public int getItemCount() {
        return citiesWeather.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ItemCityWeatherBinding itemBinding;

        public ViewHolder(@NonNull ItemCityWeatherBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(CityWeather cityWeather) {
            itemBinding.city.setText(cityWeather.getCity());

            Date time = new java.util.Date((long)cityWeather.getLast_update_timestamp()*1000);
            itemBinding.datetime.setText(time.toString());

            itemBinding.temperature.setText("" + cityWeather.getWeather().getTemperature());
            itemBinding.description.setText(cityWeather.getWeatherDescription().get(0).getDescription());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int cityId);
    }
}
