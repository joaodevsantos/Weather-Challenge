package com.example.weatherchallenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityWeather implements Parcelable {

    public static final Creator<CityWeather> CREATOR = new Creator<CityWeather>() {
        public CityWeather createFromParcel(Parcel source) {
            return new CityWeather(source);
        }

        public CityWeather[] newArray(int size) {
            return new CityWeather[size];
        }
    };

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

    private CityWeather(Parcel in){
        this.id = in.readInt();
        this.city = in.readString();
        this.last_update_timestamp =  in.readLong();
        this.timezone =  in.readInt();
        this.coordinates = in.readParcelable(Coordinates.class.getClassLoader());
        in.readTypedList(this.weatherDescription, WeatherDescription.CREATOR);
        this.weather = in.readParcelable(WeatherInfo.class.getClassLoader());
        this.wind = in.readParcelable(WindInfo.class.getClassLoader());
        this.clouds = in.readParcelable(Clouds.class.getClassLoader());
        this.rain = in.readParcelable(Rain.class.getClassLoader());
        this.snow = in.readParcelable(Snow.class.getClassLoader());
        this.sun = in.readParcelable(SunInfo.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.city);
        dest.writeLong(this.last_update_timestamp);
        dest.writeInt(this.timezone);
        dest.writeParcelable(this.coordinates, flags);
        dest.writeTypedList(this.weatherDescription);
        dest.writeParcelable(this.weather, flags);
        dest.writeParcelable(this.wind, flags);
        dest.writeParcelable(this.clouds, flags);
        dest.writeParcelable(this.rain, flags);
        dest.writeParcelable(this.snow, flags);
        dest.writeParcelable(this.sun, flags);

    }
}
