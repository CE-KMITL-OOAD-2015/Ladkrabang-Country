package com.awakenguys.kmitl.ladkrabangcountry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Announce_Create extends AppCompatActivity {
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce__create);
        //--------------send text to server-----------------
        Button postButton = (Button) findViewById(R.id.announceButton);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                announce();
                finish();
            }
        });
    }
    private void announce(){
        EditText txtDescription = (EditText) findViewById(R.id.topic_Ann);
        EditText txtDescription2 = (EditText) findViewById(R.id.content_Ann);
        String topic = txtDescription.getText().toString();
        String content = txtDescription2.getText().toString();
        HTTPRequest rq = new HTTPRequest();
        try {
            rq.execute("http://203.151.92.199:8888/addannounce?topic=" + topic + "&content=" +
                    content + "&authorId=" + Profile.getUser().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(Announce_Create.this, "Success!", Toast.LENGTH_SHORT).show();
    }
    //---------------------------------
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
    public void Menu_Post(View view)
    {
        Intent intent = new Intent(this,Review_Create.class);
        startActivity(intent);
    }
    //------------------------------------------------------
    public void Announce_list(View view) {
        Intent intent = new Intent(this, Announce_View.class);
        startActivity(intent);
    }
}
