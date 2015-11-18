package com.awakenguys.kmitl.ladkrabangcountry;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.facebook.login.LoginManager;

import java.io.InputStream;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    MenuItem menuItem;
    Drawable fbPic = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, Profile.class));
        invalidateOptionsMenu();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        if (Profile.isLoggedIn()&& Profile.getUser().getLevel()!=2) { //logged in
            String fullName = Profile.getUser().getName();
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.logged_in_menu, menu);
            menuItem = menu.findItem(R.id.profile);
            menuItem.setTitle(fullName);
            if(fbPic==null) new GetProfilePicTask().execute();
            menuItem.setIcon(fbPic);
        } else { //guest
            getMenuInflater().inflate(R.menu.guest_menu, menu);
            invalidateOptionsMenu();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.logout_icon) {
            LoginManager.getInstance().logOut();
            Profile.logout();
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return true;
        }
        else if (id == R.id.login_menu) {
            startActivity(new Intent(this, Profile.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Menu_Building(View view) {
        Intent intent = new Intent(this, Main_Place.class);
        startActivity(intent);
    }
    public void Menu_Tran(View view) {
        Intent intent = new Intent(this, Main_Tran.class);
        startActivity(intent);
    }
    public void contact(View view)
    {
        Intent intent = new Intent(this,ContactUs.class);
        startActivity(intent);
    }
    public void Menu_Reviewing(View view)
    {
        Intent intent = new Intent(this,Review_View.class);
        startActivity(intent);
    }
    public void Menu_Announce(View view)
    {
        Intent intent = new Intent(this,Announce_View.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onResume(){
        super.onResume();
        invalidateOptionsMenu();

    }
    class GetProfilePicTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            InputStream inputStream = null;

            try {
                inputStream = new URL(Profile.getPicURI()).openStream();
                fbPic = Drawable.createFromStream(inputStream, "facebook-pictures");
                invalidateOptionsMenu();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}