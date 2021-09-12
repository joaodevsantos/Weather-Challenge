package com.example.weatherchallenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SunInfo implements Parcelable {

    public static final Creator<SunInfo> CREATOR = new Creator<SunInfo>() {
        @Override
        public SunInfo createFromParcel(Parcel source) {
            return new SunInfo(source);
        }

        @Override
        public SunInfo[] newArray(int size) {
            return new SunInfo[size];
        }
    };

    @SerializedName("sunrise")
    private int sunrise_timestamp;
    @SerializedName("sunset")
    private int sunset_timestamp;

    private SunInfo(Parcel in) {
        this.sunrise_timestamp = in.readInt();
        this.sunset_timestamp = in.readInt();
    }

    public int getSunrise_timestamp() {
        return sunrise_timestamp;
    }

    public void setSunrise_timestamp(int sunrise_timestamp) {
        this.sunrise_timestamp = sunrise_timestamp;
    }

    public int getSunset_timestamp() {
        return sunset_timestamp;
    }

    public void setSunset_timestamp(int sunset_timestamp) {
        this.sunset_timestamp = sunset_timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.sunrise_timestamp);
        dest.writeInt(this.sunset_timestamp);
    }
}
