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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AppCompatActivity {
    private List<String> placeNames;
    private ArrayAdapter<String> adapter;
    private AsyncTask searchTask;
    private TextView emptyView;
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
        emptyView = (TextView) findViewById(R.id.emptyList);
        emptyView.setVisibility(View.INVISIBLE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                placeNames.clear();
                adapter.clear();
                if(searchTask!=null)
                {
                    //stop old keyword search
                    searchTask.cancel(true);
                }
                searchTask = new SearchTask().execute(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (searchTask != null) {
                    searchTask.cancel(true);
                }
                placeNames.clear();
                adapter.clear();
                if(newText.length()!=0) {
                    if (searchTask != null) {
                        searchTask.cancel(true);
                    }
                    searchTask = new SearchTask().execute(newText);
                }
                return true;
            }
        });
        placeNames = new ArrayList<String>();

        //moved from show place list
        final ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, placeNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) listView.getItemAtPosition(position);
                new GetPlace().execute(itemValue);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stop asynctask when exit from this activity
        if(searchTask!=null)
        {
            searchTask.cancel(true);
        }
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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class SearchTask extends AsyncTask<String,String,Object> {
        
        @Override
        protected Object doInBackground(String... params) {
            ContentProvider provider = new ContentProvider();
            String name;
            try {
                int index=0;
                while(!isCancelled())
                {
                    name = provider.getPlaceNameByNameLike(params[0],index);
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
        protected void onPreExecute() {
            super.onPreExecute();
            emptyView.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            placeNames.add(values[0]);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Object obj) {
            //List<String> list = (List<String>) obj;
            //showPlacesList(list);
            if(placeNames.size()==0) emptyView.setVisibility(View.VISIBLE);
        }
    }

//    public void showPlacesList(List<String> names){
//        final ListView listView = (ListView) findViewById(R.id.listView);
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, names);
//
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
//                String itemValue = (String) listView.getItemAtPosition(position);
//                new GetPlace().execute(itemValue);
//            }
//        });
//    }

    public class GetPlace extends AsyncTask<String,Void,Object> {

        @Override
        protected Object doInBackground(String... params) {
            ContentProvider provider = new ContentProvider();
                List<Place> place = provider.getPlacesByNameLike(params[0].replace(" ","+"));
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
