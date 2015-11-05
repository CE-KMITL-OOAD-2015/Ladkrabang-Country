package com.awakenguys.kmitl.ladkrabangcountry;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class First extends AppCompatActivity {
    CallbackManager callbackManager;
    ProfileTracker profileTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.awakenguys.kmitl.ladkrabangcountry",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_first);
        android.support.v7.app.ActionBar aBar = getSupportActionBar();
        aBar.hide();
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Toast.makeText(First.this,"Success",Toast.LENGTH_SHORT).show();
                        updateUI();
                        //startActivity(new Intent(First.this, MainActivity.class));
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        //updateUI();
                        Toast.makeText(First.this,"Canceled",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        //updateUI();
                        Toast.makeText(First.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                });
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile profile, Profile profile1) {
                updateUI();
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Home(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void updateUI() {
        boolean loggedIn = AccessToken.getCurrentAccessToken() != null;
        Profile profile = Profile.getCurrentProfile();
        if (loggedIn && (profile != null)) {
            Intent main = new Intent(this, MainActivity.class);
            main.putExtra("firstName", profile.getFirstName());
            main.putExtra("midName", profile.getMiddleName());
            main.putExtra("lastName", profile.getLastName());
            main.putExtra("fullName", profile.getName());
            main.putExtra("profileImg", profile.getProfilePictureUri(400,400).toString());
            main.putExtra("ID", profile.getId());
            Toast.makeText(First.this, "Hello " + profile.getName(), Toast.LENGTH_SHORT).show();

            startActivity(main);
        }else {

        }
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
