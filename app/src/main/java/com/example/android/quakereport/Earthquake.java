package com.example.android.quakereport;

/**
 * Created by lim on 2017. 1. 1..
 */

public class Earthquake {
    private double magnitude;
    private String place;
    private long mTimeMiliSecond;
    private String mUrl;

    public Earthquake(double magnitude, String place, long mTimeMiliSecond, String url) {
        this.magnitude = magnitude;
        this.place = place;
        this.mTimeMiliSecond = mTimeMiliSecond;
        this.mUrl = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getPlace() {
        return place;
    }

    public long getTime() {
        return mTimeMiliSecond;
    }

    public String getUrl() {
        return mUrl;
    }
}
