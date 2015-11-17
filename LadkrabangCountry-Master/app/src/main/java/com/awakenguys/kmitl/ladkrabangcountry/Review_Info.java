package com.awakenguys.kmitl.ladkrabangcountry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;



public class Review_Info extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review__info);

        // textView1
        final TextView txtView1 = (TextView)findViewById(R.id.textView1);

        // seekBar1
        final RatingBar rating_Bar = (RatingBar)findViewById(R.id.ratingBar1);
        rating_Bar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtView1.setText("Your Selected : " + String.valueOf(rating));

            }
        });

        // button1
        final Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(Review_Info.this,
                        String.valueOf("Your Selected : " + rating_Bar.getRating()),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main__review, menu);
        return true;
    }


}
