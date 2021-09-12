package com.example.weatherchallenge.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.weatherchallenge.R;
import com.example.weatherchallenge.databinding.ItemCityWeatherBinding;
import com.example.weatherchallenge.models.CityWeather;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

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

            String pattern = "EEEE, d MMMM HH:mm";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());

            Date date = new java.util.Date((cityWeather.getLast_update_timestamp() + cityWeather.getTimezone() - 3600) * 1000);
            itemBinding.datetime.setText(String.format("%s UTC", dateFormat.format(date)));

            itemBinding.temperature.setText(String.format("%.2f", cityWeather.getWeather().getTemperature()));

            Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
            calendar.setTime(date);   // assigns calendar to given date
            int hour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format

            int drawableID;
            if(hour > 22 || hour < 6)
                drawableID = R.drawable.weather_night;
            else if(cityWeather.getWeatherDescription().get(0).getDescription().contains("clear"))
                drawableID = R.drawable.weather_sun;
            else if(cityWeather.getWeatherDescription().get(0).getDescription().contains("clouds"))
                drawableID = R.drawable.weather_clouds;
            else if(cityWeather.getWeatherDescription().get(0).getDescription().contains("rain"))
                drawableID = R.drawable.weather_rain;
            else if(cityWeather.getWeatherDescription().get(0).getDescription().contains("snow"))
                drawableID = R.drawable.weather_snow;
            else
                drawableID = R.drawable.weather_sun;

            Glide.with(context)
                    .load(AppCompatResources.getDrawable(context, drawableID))
                    .transform(new CenterCrop(), new RoundedCorners(64))
                    .into(itemBinding.background);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int cityId);
    }
}
