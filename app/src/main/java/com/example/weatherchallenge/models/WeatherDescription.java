package com.example.weatherchallenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WeatherDescription implements Parcelable {

    public static final Creator<WeatherDescription> CREATOR = new Creator<WeatherDescription>() {
        @Override
        public WeatherDescription createFromParcel(Parcel source) {
            return new WeatherDescription(source);
        }

        @Override
        public WeatherDescription[] newArray(int size) {
            return new WeatherDescription[size];
        }
    };

    @SerializedName("main")
    private String title;
    @SerializedName("description")
    private String description;

    private WeatherDescription(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
    }
}
