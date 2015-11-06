package com.awakenguys.kmitl.ladkrabangcountry;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;

import java.net.URISyntaxException;
import java.util.List;

public class CatList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        final ListView  listView = (ListView) findViewById(android.R.id.list);
        String[] catlist = new String[]{
                "คณะวิศวกรรมศาสตร์",
                "คณะวิทยาศาสตร์",
                "คณะสถาปัตยกรรมศาสตร์",
                "คณะครุศาสตร์อุตสาหกรรม",
                "คณะอุตสาหกรรมการเกษตร",
                "คณะเทคโนโลยีการเกษตร",
                "คณะเทคโนโลยีสารสนเทศ",
                "วิทยาลัยนานาชาติ",
                "วิทยาลัยนาโนเทคโนโลยี",
                "วิทยาลัยการบริหารและการจัดการ",
                "วิทยาลัยนวัตกรรมการจัดการข้อมูล",
                "ทั่วไป",
                "ทั้งหมด"};

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, catlist);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
                new GetPlacesName().execute(itemValue);
            }

        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_default, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showPlacesList(String[] places){
        Intent intent = new Intent(this,PlaceList.class);
        intent.putExtra("places", places);
        startActivity(intent);
    }


    private class GetPlacesName extends AsyncTask<String,Void,Object> {

        @Override
        protected Object doInBackground(String... params) {
            ObjectProvider provider = new ObjectProvider();
            try {
                List<String> names = provider.getPlacesNameByCategory(params[0]);
                return names;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object obj) {
            List<String> list = (List<String>) obj;
            String[] placeslist = new String[list.size()];
            list.toArray(placeslist);
            showPlacesList(placeslist);
        }
    }

}











