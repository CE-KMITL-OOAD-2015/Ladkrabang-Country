package com.awakenguys.kmitl.ladkrabangcountry;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;

import com.awakenguys.kmitl.ladkrabangcountry.model.Review;


public class Review_Info extends AppCompatActivity {
    String id;
    int reviewRating = 0;
    int userRating = 0;
    RatingBar rating_Bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review__info);

        // textView1
        final TextView txtView1 = (TextView)findViewById(R.id.textView1);

        // seekBar1
        rating_Bar = (RatingBar)findViewById(R.id.ratingBar1);
        txtView1.setText("Your Selected :    ");
        rating_Bar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtView1.setText("Your Selected : " + String.valueOf(rating));
                userRating = (int) rating;
            }
        });

        // button1
        final Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(Review_Info.this,
                        String.valueOf("Your Selected : " + rating_Bar.getRating()),
                        Toast.LENGTH_SHORT).show();

                rate();
                //finish();
            }
        });
        //pull data
        Bundle bundle = getIntent().getExtras();
        String topic = bundle.getString("topic");
        String content = bundle.getString("content");
        id = bundle.getString("id");
        final TextView topicView = (TextView)findViewById(R.id.edittextInfo);
        topicView.setText(topic);
        final TextView topicView2 = (TextView)findViewById(R.id.textViewInfo);
        topicView2.setText(content);
        ContentProvider provider = new ContentProvider();
        //Review review = provider.getReviewById(id);
        HTTPRequest rq = new HTTPRequest();
        rq.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://203.151.92.199:8888/reviewratingbyid?userId=" + Profile.getUser().getId() +
                "&reviewId=" + id);
        while(rq.getValue()==null);

        reviewRating = Integer.parseInt(rq.getValue());
        rating_Bar.setRating(reviewRating);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.guest_anotherpage_menu, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // startActivity(new Intent(this, Review_View.class));
    }

    private void rate(){
        HTTPRequest rq = new HTTPRequest();
            rq.execute("http://203.151.92.199:8888/ratereview?userId="+Profile.getUser().getId()+
                    "&reviewId="+id+"&rating="+ userRating);
    }


   /* private class UpdateRatingTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... params) {
            ContentProvider provider = new ContentProvider();
            Review review = provider.getReviewById(id);
            HTTPRequest rq = new HTTPRequest();
            rq.execute("http://203.151.92.199:8888/reviewratingbyid?userId="+Profile.getUser().getId()+
                    "&reviewId="+id);
            if(rq.getValue()!=null) reviewRating = Integer.parseInt(rq.getValue());
            rating_Bar.setRating(reviewRating);
            return null;
        }
    }*/


}
