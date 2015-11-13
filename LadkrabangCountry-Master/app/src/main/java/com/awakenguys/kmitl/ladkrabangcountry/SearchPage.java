package com.awakenguys.kmitl.ladkrabangcountry;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;

import java.net.URISyntaxException;
import java.util.List;

public class SearchPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        // Get a reference to the AutoCompleteTextView in the layout
        final SearchView searchView = (SearchView) findViewById(R.id.searchView);
        // Get the string array
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Enter keyword");
        searchView.setSubmitButtonEnabled(true);
        /*searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchView.getQuery().toString();
                new SearchTask().execute(query);
            }
        });*/
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                new SearchTask().execute(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //new SearchTask().execute(newText);
                return true;
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guest_anotherpage_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home_icon) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private class SearchTask extends AsyncTask<String,Void,Object> {

        @Override
        protected Object doInBackground(String... params) {
            ObjectProvider provider = new ObjectProvider();
            try {
                List<String> names = provider.getPlacesNameByNameLike(params[0]);
                return names;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object obj) {
            List<String> list = (List<String>) obj;
            showPlacesList(list);
        }
    }

    public void showPlacesList(List<String> names){
        final ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, names);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
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
