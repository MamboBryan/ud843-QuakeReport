package com.example.android.quakereport;

public class Earthquake {

    //This is the variable name for the earthquake magnitude
    private double mMagnitude;

    //This is the variable name for the earthquake location
    private String mLocation;

    //This is the variable name for the earthquake date
    private long mTimeInMilliseconds;

    //This is a string for the URl of the earthquake
    private String mEarthquakeURL;

    public Earthquake(double magnitude, String location, long date, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = date;
        mEarthquakeURL = url;
    }

    public String getEarthquakeURL() {
        return mEarthquakeURL;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public long getDate() {
        return mTimeInMilliseconds;
    }

    public String getLocation() {
        return mLocation;
    }
}
