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
import android.widget.TextView;

import java.net.URISyntaxException;
import java.util.List;

public class CatList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        final ListView  listView = (ListView) findViewById(android.R.id.list);
        String[] catList = new String[]{
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
                android.R.layout.simple_list_item_1, android.R.id.text1, catList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
                showPlacesList(itemValue);
            }

        });
        TextView emptyText = (TextView) findViewById(R.id.emptyList);
        emptyText.setVisibility(View.INVISIBLE);


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

    public void showPlacesList(String catName){
        Intent intent = new Intent(this,PlaceList.class);
        intent.putExtra("catName", catName);
        startActivity(intent);
    }


//    private class GetPlacesName extends AsyncTask<String,Void,Object> {
//
//        @Override
//        protected Object doInBackground(String... params) {
//            ContentProvider provider = new bjectProvider();
//            try {
//                List<String> names = provider.getPlacesNameByCategory(params[0]);
//                return names;
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Object obj) {
//            List<String> list = (List<String>) obj;
//            String[] placeslist = new String[list.size()];
//            list.toArray(placeslist);
//            showPlacesList(placeslist);
//        }
//    }
}











