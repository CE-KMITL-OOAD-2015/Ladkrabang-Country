package com.awakenguys.kmitl.ladkrabangcountry;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.awakenguys.kmitl.ladkrabangcountry.model.Review;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class Review_View extends AppCompatActivity {
    private List<Review> reviewList = new ArrayList<Review>();
    private ListView listview;
    private CustomListAdapter adapter;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        listview = (ListView) findViewById(android.R.id.list);
        adapter = new CustomListAdapter(this, reviewList);
        listview.setAdapter(adapter);
        emptyView = (TextView) findViewById(R.id.emptyList);
        emptyView.setVisibility(View.INVISIBLE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPost();
                finish();
            }
        });
        new UpdateTask().execute();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view_review(position);
            }
        });

    }

    private void view_review(int position){
        Intent intent = new Intent(this, Review_Info.class);
        intent.putExtra("topic", ((Review) adapter.getItem(position)).getTopic());
        intent.putExtra("content", ((Review) adapter.getItem(position)).getContent());
        intent.putExtra("other", ((Review) adapter.getItem(position)).getImg_path());
        startActivity(intent);
    }

    private void startPost() {
        startActivity(new Intent(this, Review_Create.class));
    }
    public class UpdateTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            ContentProvider provider = new  ContentProvider();
            int i = provider.getReviewSize();
            for(;i>0;i--){
                try{
                    reviewList.add(provider.getReviewByIndex(i-1));
                    publishProgress();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            adapter.notifyDataSetChanged();
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(reviewList.size()==0)emptyView.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
        }
    }

}
