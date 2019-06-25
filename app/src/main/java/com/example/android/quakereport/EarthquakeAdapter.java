package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.Array;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    public EarthquakeAdapter(@NonNull Context context, int resource, @NonNull List<Earthquake> earthquakes) {
        super(context, resource, earthquakes);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("LLL dd, yyyy", Locale.getDefault());
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude from the double gotten by the response
     */
    private String formatDecimal(double magnitude) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }

    /**
     * Returns the array of split strings
     */
    private String getOffsetLocation(String string) {

        String offsetLocation;

        if (string.contains(" of ")) {
            String[] parts = string.split("(?<=of)");
            offsetLocation = parts[0];
        } else {
            offsetLocation = "Near the ";
        }

        return offsetLocation;
    }

    /**
     * Returns the array of split strings
     */
    private String getPrimaryLocation(String string) {

        String primaryLocation;

        if (string.contains(" of ")) {
            String[] parts = string.split("(?<=of)");
            primaryLocation = parts[1];
        } else {
            primaryLocation = string;
        }

        return primaryLocation;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,
                    parent, false);
        }

        //Gets the earthquake object at the position in the list
        Earthquake currentEarthquake = getItem(position);

        /*
        Finds the TextView in the earthquake_list_item.xml with the id magnitude
        Gets the magnitude and sets it to the TextView
         */
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        String magnitudeOutput = formatDecimal(currentEarthquake.getMagnitude());
        magnitudeTextView.setText(magnitudeOutput);
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        /*
        Finds the TextView in the earthquake_list_item.xml with the id location_offset
        Gets the location and sets it to the TextView
         */
        TextView offsetLocationTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        offsetLocationTextView.setText(getOffsetLocation(currentEarthquake.getLocation()));

        /*
        Finds the TextView in the earthquake_list_item.xml with the id primary_location
        Gets the location and sets it to the TextView
         */
        TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationTextView.setText(getPrimaryLocation(currentEarthquake.getLocation()));


        //Create a new date object in milliseconds
        Date dateObject = new Date(currentEarthquake.getDate());

        /*
        Finds the TextView in the earthquake_list_item.xml with the id date
        Gets the date and sets it to the TextView
         */
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

         /*
        Finds the TextView in the earthquake_list_item.xml with the id time
        Gets the date and sets it to the TextView
         */
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeTextView.setText(formattedTime);

        //This returns the whole list item layout containing one integer and two strings
        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
