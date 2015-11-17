package com.awakenguys.kmitl.ladkrabangcountry;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.internal.http.multipart.MultipartEntity;
import com.awakenguys.kmitl.ladkrabangcountry.model.User;


import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class PostActivity extends AppCompatActivity {
    private static final int SELECT_PHOTO = 1;
    private TextView text;
    private ImageButton imageButton;
    private Context context = this;
    Bitmap bitmap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        if(Profile.getUser().getLevel()== User.GUEST){
            Toast.makeText(PostActivity.this, "You must login before post.", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this, Profile.class));
        }

        imageButton = (ImageButton) findViewById(R.id.image_button);
        imageButton.setBackgroundResource(R.drawable.camera);
        text = (TextView) findViewById(R.id.text);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
        //----------------------------------------------------------
        Button postButton = (Button) findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();

                finish();
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
    //Edittext to String and send to server
    private void post(){
        imagePost();
        EditText txtDescription = (EditText) findViewById(R.id.edit_text);
        EditText txtDescription2 = (EditText) findViewById(R.id.edit_text_2);
        String topic = txtDescription.getText().toString();
        String content = txtDescription2.getText().toString();
        HTTPRequest rq = new HTTPRequest();
        try {
            rq.execute("http://203.151.92.199:8888/addreview?topic=" + topic + "&content=" +
                    content + "&authorId=" + Profile.getUser().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(PostActivity.this, "Success!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ReviewList.class));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            decodeFile(picturePath);

        }

    }

    public String imagePost()
    {
        if(bitmap!=null){
            URL url;
            HttpURLConnection connection = null;
            try {
                //Create connection
                url = new URL("http://203.151.92.199:8888/uploadimage");
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setDoOutput(true);
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "image/jpeg");
                connection.setRequestMethod("POST");


                OutputStream outputStream = connection.getOutputStream();


                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

                outputStream.close();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while((inputLine = in.readLine())!= null){
                    response.append(inputLine);
                }
                in.close();
                connection.disconnect();
                return response.toString();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return null;
    }

    public void decodeFile(String filePath) {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 300;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        bitmap = BitmapFactory.decodeFile(filePath, o2);
        imageButton.setImageBitmap(bitmap);
        imageButton.setBackgroundResource(0);

    }

    class ImageUploadTask extends AsyncTask<String, Void, String> {


        // private ProgressDialog dialog;
        private ProgressDialog dialog = new ProgressDialog(PostActivity.this);

        @Override
        protected void onPreExecute() {
            //dialog.setMessage("Uploading...");
            //dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            Toast.makeText(getApplicationContext(), "file uploaded",Toast.LENGTH_LONG).show();
        }
    }



}