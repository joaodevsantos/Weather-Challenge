package com.example.weatherchallenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Rain implements Parcelable {

    public static final Creator<Rain> CREATOR = new Creator<Rain>() {
        @Override
        public Rain createFromParcel(Parcel source) {
            return new Rain(source);
        }

        @Override
        public Rain[] newArray(int size) {
            return new Rain[size];
        }
    };

    @SerializedName("1h")
    private int ofLastHour;
    @SerializedName("3h")
    private int ofLastThreeHours;

    private Rain(Parcel in) {
        this.ofLastHour = in.readInt();
        this.ofLastThreeHours = in.readInt();
    }

    public int getOfLastHour() {
        return ofLastHour;
    }

    public void setOfLastHour(int ofLastHour) {
        this.ofLastHour = ofLastHour;
    }

    public int getOfLastThreeHours() {
        return ofLastThreeHours;
    }

    public void setOfLastThreeHours(int ofLastThreeHours) {
        this.ofLastThreeHours = ofLastThreeHours;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ofLastHour);
        dest.writeInt(this.ofLastThreeHours);
    }
}
