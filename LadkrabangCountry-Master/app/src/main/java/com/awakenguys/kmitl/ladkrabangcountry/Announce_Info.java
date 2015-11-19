package com.awakenguys.kmitl.ladkrabangcountry;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.awakenguys.kmitl.ladkrabangcountry.model.User;


public class Announce_Info extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce__info);

        final Bundle bundle = getIntent().getExtras();
        TextView topicView = (TextView) findViewById(R.id.topic_ann_show);
        TextView contentView = (TextView) findViewById(R.id.content_ann_show);
        Button delButton = (Button) findViewById(R.id.delete_announce);
        if(Profile.getUser().getLevel()== User.GUEST) delButton.setVisibility(View.INVISIBLE);
        topicView.setText(bundle.getString("topic"));
        contentView.setText(bundle.getString("content"));
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAnnounce(bundle.getString("id"));
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

    public void deleteAnnounce(String id)
    {
        HTTPRequest rq = new HTTPRequest();
        rq.execute("http://203.151.92.199:8888/deleteannounce?id="+id);
        Toast.makeText(Announce_Info.this,"Delete successful.",Toast.LENGTH_SHORT).show();
        finish();
    }



}
