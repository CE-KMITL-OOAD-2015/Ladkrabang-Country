package com.awakenguys.kmitl.ladkrabangcountry;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class NearbyMap extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    LocationManager locationManager;
    Location myLocation;
    AsyncTask getPlacesTask;
    Marker blueMarker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(final GoogleMap map) {
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
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 16));
            blueMarker = map.addMarker(new MarkerOptions().position(mapCenter).title("Your last known location")
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            getPlacesTask = new GetPlacesTask().execute(myLocation);
        }

        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 1000, 0, new android.location.LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        myLocation = location;
                        blueMarker.remove();
                        LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                        blueMarker = map.addMarker(new MarkerOptions().position(latLng).title("Your last known location")
                                .icon(BitmapDescriptorFactory
                                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                });

            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (getPlacesTask != null) {
                    getPlacesTask.cancel(true);
                }


                map.clear();
                Location location = new Location("clickEvent");
                location.setLatitude(latLng.latitude);
                location.setLongitude(latLng.longitude);
                map.addMarker(new MarkerOptions().position(latLng).title("Selected location").icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

                getPlacesTask = new GetPlacesTask().execute(location);
            }
        });

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getTitle().equals("Your last known location")) {
                    if(blueMarker.isInfoWindowShown()){
                        if (getPlacesTask != null) {
                            getPlacesTask.cancel(true);
                        }
                        map.clear();

                        LatLng mapCenter = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                        blueMarker = map.addMarker(new MarkerOptions().position(mapCenter).title("Your last known location")
                                .icon(BitmapDescriptorFactory
                                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                        blueMarker.hideInfoWindow();
                        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 16));
                        getPlacesTask = new GetPlacesTask().execute(myLocation);
                    }
                    else {
                        if (getPlacesTask != null) {
                            getPlacesTask.cancel(true);
                        }
                        map.clear();

                        LatLng mapCenter = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                        blueMarker = map.addMarker(new MarkerOptions().position(mapCenter).title("Your last known location")
                                .icon(BitmapDescriptorFactory
                                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                        blueMarker.showInfoWindow();
                        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 16));
                        getPlacesTask = new GetPlacesTask().execute(myLocation);
                    }
                }
                return false;
            }

        });
    }

    private class GetPlacesTask extends AsyncTask<Location,Place,List<Place>> {
        Toast progress;
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
                if(isCancelled()) break;
                Place place = provider.getPlacesByNameLike(name).get(0);
                places.add(place);
                publishProgress(place);
            }
            return places;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = Toast.makeText(NearbyMap.this ,"     Searching for nearby places ...\n" +
                    "Touch to select location for search."
                    , Toast.LENGTH_SHORT);
            progress.show();
        }

        @Override
        protected void onProgressUpdate(Place... values) {
            super.onProgressUpdate(values);
            LatLng latLng = new LatLng(values[0].getLat(), values[0].getLng());
            map.addMarker(new MarkerOptions().position(latLng).title(values[0].getName()));

        }

        @Override
        protected void onPostExecute(List<Place> places) {
            super.onPostExecute(places);
            progress.cancel();
            if(places.size()==0) Toast.makeText(NearbyMap.this ,"No nearby place found."
                    , Toast.LENGTH_SHORT).show();
            else Toast.makeText(NearbyMap.this ,"Search finished"
                    , Toast.LENGTH_SHORT).show();

        }
    }
}



