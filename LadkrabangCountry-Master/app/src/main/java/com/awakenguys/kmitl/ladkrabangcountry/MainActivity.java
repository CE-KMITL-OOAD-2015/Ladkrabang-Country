package com.awakenguys.kmitl.ladkrabangcountry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;
import android.graphics.drawable.BitmapDrawable;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import com.facebook.login.LoginManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends ActionBarActivity {
    ProfileTracker profileTracker;
    String firstName;
    String lastName;
    String fullName;
    String profileImg;
    String id;
    Uri propic = null;
    Toast popup;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        try {
            if (bundle != null) {
                firstName = bundle.getString("firstname");
                lastName = bundle.getString("lastname");
                id = bundle.getString("id");
                fullName = bundle.getString("fullName");
                profileImg = bundle.getString("profileImg");
                propic = getIntent().getParcelableExtra("propic");
            }
        }catch(Exception e){

        }
    }

/*    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.nameFB);
        if (firstName!=null) {
            menuItem.setTitle(firstName);
        }
        return super.onPrepareOptionsMenu(menu);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        boolean loggedIn = AccessToken.getCurrentAccessToken()!= null;
        Profile profile = Profile.getCurrentProfile();
        if (loggedIn && (profile != null)) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.guest_anotherpage_menu, menu);
            MenuItem menuItem = menu.findItem(R.id.nameFB);
            menuItem.setTitle(fullName);
            //menuItem.setIcon();
        } else {
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.home_icon) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        if (id == R.id.logout_icon) {
            LoginManager.getInstance().logOut();
            startActivity(new Intent(MainActivity.this, First.class));
            return true;
        }
        else if (id == R.id.login_icon) {
            Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends", "email"));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void updateUI() {
        boolean loggedIn = AccessToken.getCurrentAccessToken() != null;
        Profile profile = Profile.getCurrentProfile();
        if (loggedIn && (profile != null)) {
            firstName = profile.getFirstName();
            lastName = profile.getLastName();
            fullName = profile.getName();
            profileImg = profile.getProfilePictureUri(400,400).toString();
            id = profile.getId();

            Toast.makeText(MainActivity.this, "Hello " + profile.getName(), Toast.LENGTH_SHORT).show();
        }else {

        }
    }

    public void Menu_Building(View view) {
        Intent intent = new Intent(this, Main_building.class);
        startActivity(intent);
    }
    public void Menu_Tran(View view) {
        Intent intent = new Intent(this, Main_Tran.class);
        startActivity(intent);
    }
    public void contact(View view)
    {
        Intent intent = new Intent(this,contactUs.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}