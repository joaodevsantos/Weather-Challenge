package com.example.weatherchallenge.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.weatherchallenge.R;
import com.example.weatherchallenge.databinding.ItemCityWeatherBinding;
import com.example.weatherchallenge.models.CityWeather;
import com.example.weatherchallenge.utils.Constants;
import com.example.weatherchallenge.utils.Functions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    private final Context context;
    private final List<CityWeather> citiesWeather;

    public LocationsAdapter(Context context,
                            List<CityWeather> citiesWeather,
                            OnItemClickListener listener) {
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

    public interface OnItemClickListener {
        void onItemClick(int cityId);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ItemCityWeatherBinding itemBinding;

        public ViewHolder(@NonNull ItemCityWeatherBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(CityWeather cityWeather) {
            // The first item is always the current location
            // Animate the icon to distinguish from the others
            if (cityWeather.isCurrent()) {
                itemBinding.poi.setImageDrawable(context.getDrawable(R.drawable.ic_poi_gradient_blue));
                itemBinding.poi.startAnimation(AnimationUtils.loadAnimation(context, R.anim.up_down));
            } else
                itemBinding.poi.setImageDrawable(context.getDrawable(R.drawable.ic_poi));

            itemBinding.city.setText(cityWeather.getCity());
            itemBinding.temperature.setText(String.format("%.2fºC", cityWeather.getWeather().getTemperature()));

            // Sets the date time according to a specific pattern
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_TIME_PATTERN, Locale.getDefault());
            Date date = new java.util.Date((cityWeather.getLast_update_timestamp() + cityWeather.getTimezone() - 3600) * 1000);
            itemBinding.datetime.setText(String.format("%s UTC", dateFormat.format(date)));

            // Gets the hour of the day in 24 hours format
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(date);

            // Gets the drawable to fit the Image View of the item
            int drawableID = Functions.getWeatherDrawable(calendar.get(Calendar.HOUR_OF_DAY),
                    cityWeather.getWeatherDescription().get(0).getDescription());
            // Sets/Loads the image within the image view
            Glide.with(context)
                    .load(AppCompatResources.getDrawable(context, drawableID))
                    .transform(new CenterCrop(), new RoundedCorners(64))
                    .into(itemBinding.background);
        }
    }
}
