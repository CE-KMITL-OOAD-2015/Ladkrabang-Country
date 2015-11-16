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
import android.widget.TextView;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class PlaceList extends AppCompatActivity {
    private List<String> placeNames;
    private ArrayAdapter<String> adapter;
    private AsyncTask showPlaceListTask;
    private TextView emptyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        Bundle bundle = getIntent().getExtras();
        String catName = bundle.getString("catName");

        placeNames = new ArrayList<String>();
        final ListView  listView = (ListView) findViewById(android.R.id.list);
        emptyView = (TextView) findViewById(R.id.emptyList);
        emptyView.setVisibility(View.INVISIBLE);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, placeNames);
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
        showPlaceListTask = new ShowPlaceListTask().execute(catName);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stop asynctask when exit from this activity
        showPlaceListTask.cancel(true);
    }

    public class ShowPlaceListTask extends AsyncTask<String,String,Object>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            placeNames.clear();
            adapter.clear();
        }

        @Override
        protected Object doInBackground(String... params) {
            ContentProvider provider = new ContentProvider();
            String name;
            try {
                int index=0;
                while(!isCancelled()) {
                    name = provider.getPlaceNameByCategory(params[0],index);
                    if(name==null)break;
                    publishProgress(name);
                    index++;
                }
                return null;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            placeNames.add(values[0]);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(placeNames.size()==0) emptyView.setVisibility(View.VISIBLE);
        }
    }

    public class GetPlace extends AsyncTask<String,Void,Object> {

        @Override
        protected Object doInBackground(String... params) {
            ContentProvider provider = new ContentProvider();

                List<Place> place = provider.getPlacesByNameLike(params[0]);
                return place;
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
