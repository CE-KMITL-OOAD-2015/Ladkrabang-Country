package com.awakenguys.kmitl.ladkrabangcountry;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class PlaceList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        Bundle bundle = getIntent().getExtras();
        String[] places = bundle.getStringArray("places");
        final ListView  listView = (ListView) findViewById(android.R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, places);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
                new GetPlace().execute(itemValue);
            }

        });

    }

    public class GetPlace extends AsyncTask<String,Void,Object> {

        @Override
        protected Object doInBackground(String... params) {
            ObjectProvider provider = new ObjectProvider();
            try {
                List<Place> place = provider.getPlacesByNameLike(params[0].replace(" ","+"));
                return place;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object obj) {
            List<Place> list = (List<Place>) obj;
            Place place = list.get(0);
            showMap(place);
        }
    }


    public void showMap(Place place){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:" + place.getLat() + ", " + place.getLng() + "?q=" + place.getLat()
                        + ", " + place.getLng() + " (" + place.getName() + ")"));
        intent.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));
        startActivity(intent);
    }
}
