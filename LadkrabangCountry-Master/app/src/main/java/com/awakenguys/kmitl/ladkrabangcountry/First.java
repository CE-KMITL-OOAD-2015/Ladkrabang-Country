package com.awakenguys.kmitl.ladkrabangcountry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.ProfilePictureView;

public class First extends AppCompatActivity {

    CallbackManager callbackManager;
    ProfileTracker profileTracker;

    private TextView userName;
    private ProfilePictureView profilePicture;
    private Button postLinkButton;
    private Button postPictureButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        updateUI();
                    }

                    @Override
                    public void onCancel() {
                        updateUI();
                    }

                    @Override
                    public void onError(FacebookException e) {
                        updateUI();
                    }
                });

        setContentView(R.layout.activity_first);

        //userName = (TextView) findViewById(R.id.user_name);
        //profilePicture = (ProfilePictureView)
        //        findViewById(R.id.profile_picture);
        //postLinkButton = (Button)
        //       findViewById(R.id.post_link_button);
        //postPictureButton = (Button)
        //       findViewById(R.id.post_picture_button);

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile profile, Profile profile1) {
                updateUI();
            }
        };
    }

    private void updateUI(){
        boolean loggedIn = AccessToken.getCurrentAccessToken()!= null;
        Profile profile = Profile.getCurrentProfile();

        if(loggedIn && (profile != null)){
            profilePicture.setProfileId(profile.getId());
            userName.setText(profile.getName());
            postLinkButton.setEnabled(true);
            postPictureButton.setEnabled(true);
        }
        else {
            profilePicture.setProfileId(null);
            userName.setText(null);
            postLinkButton.setEnabled(false);
            postPictureButton.setEnabled(false);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        profileTracker.stopTracking();
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
