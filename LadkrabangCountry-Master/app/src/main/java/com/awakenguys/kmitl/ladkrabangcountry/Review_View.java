package com.awakenguys.kmitl.ladkrabangcountry;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.awakenguys.kmitl.ladkrabangcountry.model.Review;

import java.util.ArrayList;
import java.util.List;

public class Review_View extends AppCompatActivity {
    private List<Review> reviewList = new ArrayList<Review>();
    private ListView listview;
    private ReviewListAdapter adapter;
    private TextView emptyView;
    private UpdateReviewTask updateReviewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        listview = (ListView) findViewById(android.R.id.list);
        adapter = new ReviewListAdapter(this, reviewList);
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
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view_review(position);
                //finish();
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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reviewList.clear();
        adapter.notifyDataSetChanged();
        updateReviewTask = new UpdateReviewTask();
        updateReviewTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(updateReviewTask !=null) updateReviewTask.cancel(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(updateReviewTask !=null) updateReviewTask.cancel(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(updateReviewTask !=null) updateReviewTask.cancel(true);
    }

    private void view_review(int position){
        Intent intent = new Intent(this, Review_Info.class);
        intent.putExtra("id", ((Review) adapter.getItem(position)).getId());
        intent.putExtra("topic", ((Review) adapter.getItem(position)).getTopic());
        intent.putExtra("content", ((Review) adapter.getItem(position)).getContent());
        //intent.putExtra("other", ((Review) adapter.getItem(position)).getImg_path());
        startActivity(intent);
    }

    private void startPost() {
        startActivity(new Intent(this, Review_Create.class));
    }

    public class UpdateReviewTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            ContentProvider provider = new  ContentProvider();
            int i = provider.getReviewSize();
            for(;i>0;i--) {
                if (isCancelled()) break;
                try {
                    publishProgress();
                    reviewList.add(provider.getReviewByIndex(i - 1));

                } catch (Exception e) {
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
