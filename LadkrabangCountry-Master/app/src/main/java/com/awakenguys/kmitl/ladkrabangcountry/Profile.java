package com.awakenguys.kmitl.ladkrabangcountry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.awakenguys.kmitl.ladkrabangcountry.model.User;

public class Profile extends Activity {
    private static User user = null;
    private static Context mContext;
    private static String picURI = null;

    public static String getPicURI() {
        return picURI;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        mContext = this;
        if(user==null||user.getLevel()==User.GUEST) login();
        else finish();
    }

    public static boolean isLoggedIn() {
        return user != null;
    }

    public static User getUser() {
        return user;
    }

    public static void logout() {
        user = null;
        picURI = null;
    }


    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                User user;
                String fbId = data.getStringExtra("fbId");
                String fullName = data.getStringExtra("fullName");
                picURI = data.getStringExtra("profileImg");
                if(fullName.equals("Guest")) user = new User(null,"Guest",User.GUEST);
                else user = new User(fbId, fullName, User.MEMBER);
                new LogInTask().execute(user);
            }
        }else Toast.makeText(Profile.this, "error!"
                , Toast.LENGTH_SHORT).show();
    }

    public void login(){
        startActivityForResult(new Intent(this, LogInActivity.class), 1);
    }

    private class LogInTask extends AsyncTask<User, Void, User> {

        @Override
        protected User doInBackground(User... params) {
            User user = login(params[0]);
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            setUser(user);
            ((Activity)mContext).finish();
            Toast.makeText(Profile.this, "Welcome "+user.getName(), Toast.LENGTH_SHORT).show();
        }

        private User login(User login_user) {
            ContentProvider provider = new ContentProvider();
            User user = provider.getUserByFbId(login_user.getFbId());
            try {
                if (user == null) {
                    if (login_user.getLevel() != 2) {
                        HTTPRequest rq = new HTTPRequest();
                        rq.executeGet("http://203.151.92.199:8888/adduser?fbId=" + login_user.getFbId()
                                + "&name=" + login_user.getName());
                        user = provider.getUserByFbId(login_user.getFbId());
                    } else user = new User(null, "Guest", 2);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                return user;
            }
        }
    }
}
