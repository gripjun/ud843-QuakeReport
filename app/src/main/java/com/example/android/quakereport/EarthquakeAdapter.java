package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.android.quakereport.R.id.offsetFromPlace;
import static com.example.android.quakereport.R.id.place;

/**
 * Created by lim on 2017. 1. 1..
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPERATOR = " of ";

    public EarthquakeAdapter (Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        final Earthquake currentEarthquake = getItem(position);


        // Find the TextView in the list_item.xml layout with the ID magnitude
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Get the version name from the current Earthquake object and
        // set this text on the name TextView

        magnitudeTextView.setText(formatMagnitude(currentEarthquake.getMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        String originalPlace = currentEarthquake.getPlace();
        String primaryLocation;
        String locationOffset;


        if(originalPlace.contains(" of ")) {
            String[] parts = originalPlace.split(LOCATION_SEPERATOR);
            locationOffset = parts[0] + LOCATION_SEPERATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalPlace;
        }

        TextView offSetFromPlaceTextView = (TextView) listItemView.findViewById(offsetFromPlace);
        offSetFromPlaceTextView.setText(locationOffset);

        // Find the TextView in the list_item.xml layout with the ID place
        TextView placeTextView = (TextView) listItemView.findViewById(place);
        // Get the version number from the current Earthquake object and
        // set this text on the number TextView
        placeTextView.setText(primaryLocation);

        // Find the TextView in the list_item.xml layout with the ID date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        // Get the version number from the current Earthquake object and
        // set this text on the number TextView

        Date dateObject = new Date(currentEarthquake.getTime());

        dateTextView.setText(miliSecondToDateFormat(dateObject));

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);

        timeTextView.setText(miliSecondToTimeFormat(dateObject));

        // Return the whole list item layout (containing 3 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }

    private String miliSecondToDateFormat(Date dateObject) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormatter.format(dateObject);
    }

    private String miliSecondToTimeFormat(Date dateObject) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        return timeFormatter.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(magnitude);
    }

    private int getMagnitudeColor(double curMagnitude) {
        int mag = (int) Math.floor(curMagnitude);
        int magnitudeColorResourceId;
        switch(mag) {
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
