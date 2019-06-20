package com.example.android.quakereport;

public class Earthquake {

    //This is the variable name for the earthquake magnitude
    private double mMagnitude;

    //This is the variable name for the earthquake location
    private String mLocation;

    //This is the variable name for the earthquake date
    private long mTimeInMilliseconds;

    public Earthquake(double magnitude, String location, long date) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = date;
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
