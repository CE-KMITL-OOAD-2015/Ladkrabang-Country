package com.awakenguys.kmitl.ladkrabangcountry;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class NearbyMap extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    LocationManager locationManager;
    Location myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



    @Override
    public void onMapReady(GoogleMap map) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        } else {
            Toast.makeText(NearbyMap.this, "Permission denied.", Toast.LENGTH_SHORT).show();
        }


        this.map = map;
        locationManager = (LocationManager) getSystemService
                (Context.LOCATION_SERVICE);
        myLocation = locationManager.getLastKnownLocation
                (LocationManager.PASSIVE_PROVIDER);

        if (myLocation == null) {
            Toast.makeText(NearbyMap.this, "Cannot get current location." +
                    " Make sure location is enabled on the device and staying outdoor.", Toast.LENGTH_SHORT).show();
            LatLng mapCenter = new LatLng(13.728137, 100.777847);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 15.2f));

        } else {
            LatLng mapCenter = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            //map.addMarker(new MarkerOptions().position(mapCenter).title("Marker in Sydney"));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 16));
            new GetPlacesTask().execute(myLocation);
        }
    }


    private class GetPlacesTask extends AsyncTask<Location,Place,List<Place>> {
        @Override
        protected List<Place> doInBackground(Location... params) {
            ContentProvider provider = new ContentProvider();
            List<String> latAxis = provider.getNearbyPlacesByLat(params[0].getLatitude(),
                    params[0].getLongitude());
            List<String> lngAxis = provider.getNearbyPlacesByLng(params[0].getLatitude(),
                    params[0].getLongitude());

            latAxis.retainAll(lngAxis);
            List<Place> places = new ArrayList<Place>();
            for(String name :latAxis) {
                Place place = provider.getPlacesByNameLike(name).get(0);
                places.add(place);
                publishProgress(place);
            }


            return places;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(NearbyMap.this ,"Loading nearby places ...", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Place... values) {
            super.onProgressUpdate(values);
            LatLng latLng = new LatLng(values[0].getLat(), values[0].getLng());
            map.addMarker(new MarkerOptions().position(latLng).title(values[0].getName()));
        }

    }
}



