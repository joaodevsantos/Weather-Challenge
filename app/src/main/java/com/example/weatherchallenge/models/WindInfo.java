package com.example.weatherchallenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WindInfo implements Parcelable {

    public static final Creator<WindInfo> CREATOR = new Creator<WindInfo>() {
        @Override
        public WindInfo createFromParcel(Parcel source) {
            return new WindInfo(source);
        }

        @Override
        public WindInfo[] newArray(int size) {
            return new WindInfo[size];
        }
    };

    @SerializedName("speed")
    private float speed;
    @SerializedName("deg")
    private int direction; // in degrees
    @SerializedName("gust")
    private float gust; // Default: meter/sec, Metric: meter/sec, Imperial: miles/hour

    private WindInfo(Parcel in) {
        this.speed = in.readFloat();
        this.direction = in.readInt();
        this.gust = in.readFloat();
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public float getGust() {
        return gust;
    }

    public void setGust(float gust) {
        this.gust = gust;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.speed);
        dest.writeInt(this.direction);
        dest.writeFloat(this.gust);
    }
}
