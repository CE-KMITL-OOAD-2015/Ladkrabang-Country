package com.awakenguys.kmitl.ladkrabangcountry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.awakenguys.kmitl.ladkrabangcountry.model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        //if (accessToken == null) {

        startActivity(new Intent(this, Authentication.class));
        invalidateOptionsMenu();

            /*else
            {
                Toast.makeText(MainActivity.this,"Welcome Guest"
                        ,Toast.LENGTH_SHORT).show();
            }*/


        // }
        //FacebookSdk.sdkInitialize(getApplicationContext());

        //startActivityForResult(new Intent(this, LogInActivity.class), 1);
        //Bundle bundle = getIntent().getExtras();
    }
//
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        if(auth.isLoggedIn()) {
//            savedInstanceState.putString("id", auth.getUser().getId());
//            savedInstanceState.putString("fbId", auth.getUser().getFbId());
//            savedInstanceState.putString("name", auth.getUser().getName());
//            savedInstanceState.putInt("level", auth.getUser().getLevel());
//            savedInstanceState.putStringArrayList("ratedReview", auth.getUser().getRatedReviews());
//        }
//    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        String id = savedInstanceState.getString("id");
//        String fbId = savedInstanceState.getString("fbId");
//        String name = savedInstanceState.getString("name");
//        int level = savedInstanceState.getInt("level");
//        ArrayList<String> ratedReview = savedInstanceState.getStringArrayList("ratedReview");
//        auth.setUser(new User(id, fbId, name, level, false, ratedReview));
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (Authentication.isLoggedIn()&&Authentication.getUser().getLevel()!=2) { //logged in
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.logged_in_menu, menu);
            MenuItem menuItem = menu.findItem(R.id.nameFB);
        } else { //guest
            getMenuInflater().inflate(R.menu.guest_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        invalidateOptionsMenu();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout_icon) {
            LoginManager.getInstance().logOut();
            Authentication.logout();
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return true;
        }
        else if (id == R.id.login_icon) {
            Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Authentication.class));
            invalidateOptionsMenu();
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
        Intent intent = new Intent(this,Main_Review.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onResume(){
        super.onResume();

    }

}