package com.awakenguys.kmitl.ladkrabangcountry;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.awakenguys.kmitl.ladkrabangcountry.model.Announce;
import com.awakenguys.kmitl.ladkrabangcountry.model.Review;

import java.util.ArrayList;
import java.util.List;

public class Announce_View extends AppCompatActivity {
    private List<Announce> announceList = new ArrayList<Announce>();
    private ListView listview;
    private AnnounceListAdapter adapter;
    private TextView emptyView;
    private AsyncTask updateTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce__list);

        listview = (ListView) findViewById(android.R.id.list);
        adapter = new AnnounceListAdapter(this, announceList);
        listview.setAdapter(adapter);
        emptyView = (TextView) findViewById(R.id.emptyList);
        emptyView.setVisibility(View.INVISIBLE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPost();
                //finish();
            }
        });
        updateTask = new UpdateTask();
        updateTask.execute();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view_announce(position);
            }
        });

    }

    private void view_announce(int position){
        Intent intent = new Intent(this, Review_Info.class);
        intent.putExtra("topic", ((Review) adapter.getItem(position)).getTopic());
        intent.putExtra("content", ((Review) adapter.getItem(position)).getContent());
        intent.putExtra("image", ((Review) adapter.getItem(position)).getImg_path());
        startActivity(intent);
    }


    private void startPost() {
        startActivity(new Intent(this, Announce_Create.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
            new UpdateTask().execute();
    }

    public class UpdateTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            ContentProvider provider = new  ContentProvider();
            int i = provider.getAnnounceSize();
            for(;i>0;i--){
                try{
                    announceList.add(provider.getAnnounceByIndex(i-1));
                    isCancelled();
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
            if(announceList.size()==0)emptyView.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(updateTask!=null) updateTask.cancel(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(updateTask!=null) updateTask.cancel(true);
    }
}
