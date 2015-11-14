package com.awakenguys.kmitl.ladkrabangcountry;

import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class NearbyPlace extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener {
    protected static final String TAG = "NearByActivity";

    /**
     * Provides the entry point to Google Play services.
     */
    protected GoogleApiClient mGoogleApiClient;

    /**
     * Represents a geographical location.
     */
    protected Location mLastLocation;

    protected String mLatitudeLabel;
    protected String mLongitudeLabel;
    protected TextView mLatitudeText;
    protected TextView mLongitudeText;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_place);

        /*mLatitudeLabel = getResources().getString(R.string.latitude_label);
        mLongitudeLabel = getResources().getString(R.string.longitude_label);
        mLatitudeText = (TextView) findViewById((R.id.latitude_text));
        mLongitudeText = (TextView) findViewById((R.id.longitude_text));
*/
        buildGoogleApiClient();




    }

    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            /*mLatitudeText.setText(String.format("%s: %f", mLatitudeLabel,
                    mLastLocation.getLatitude()));
            mLongitudeText.setText(String.format("%s: %f", mLongitudeLabel,
                    mLastLocation.getLongitude()));*/
            new GetPlacesTask().execute(mLastLocation);
        } else {
            Toast.makeText(this, R.string.no_location_detected, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    public void showPlacesOnMap(List<Place> places){
        Intent intent = new Intent(this, MapsActivity.class);
        String[] names = new String[100];
        double[] lat = new double[100], lng = new double[100];
        int i = 0;
        for(Place place : places){
            lat[i] = place.getLat();
            lng[i] = place.getLng();
            i++;
        }
        if(lat.length>0) {
            intent.putExtra("names", names);
            intent.putExtra("lat", lat);
            intent.putExtra("lng", lng);
            intent.putExtra("size",i);
        }
        startActivity(intent);
    }

    private class GetPlacesTask extends AsyncTask<Location,Void,List<Place>>{
        @Override
        protected List<Place> doInBackground(Location... params) {
            ObjectProvider provider = new ObjectProvider();
            /*List<String> latAxis = provider.getNearbyPlacesByLat(mLastLocation.getLatitude(),
                    params[0].getLatitude());
            List<String> lngAxis = provider.getNearbyPlacesByLng(mLastLocation.getLatitude(),
                    params[0].getLongitude());*/
            List<String> latAxis = provider.getNearbyPlacesByLat(13.729549, 100.775311);
            List<String> lngAxis = provider.getNearbyPlacesByLng(13.729549, 100.775311);

            latAxis.retainAll(lngAxis);
            List<Place> places = new ArrayList<Place>();
            for(String name :latAxis) {
                    Place place = provider.getPlacesByNameLike(name).get(0);
                    places.add(place);

                }

            return places;
        }

        @Override
        protected void onPostExecute(List<Place> places) {
            super.onPostExecute(places);
            showPlacesOnMap(places);
        }
    }

}



