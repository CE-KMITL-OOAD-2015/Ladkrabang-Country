package com.awakenguys.kmitl.ladkrabangcountry;


import android.app.ProgressDialog;
import android.content.Context;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.awakenguys.kmitl.ladkrabangcountry.model.Review;
import com.awakenguys.kmitl.ladkrabangcountry.model.User;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Review_Create extends AppCompatActivity {
    private static final int SELECT_PHOTO = 1;
    private TextView text;
    private ImageButton imageButton;
    private Context context = this;
    Bitmap bitmap = null;
    String filePath = "";
    String topic;
    String content;
    String encodedImg = null;
    RestTemplate rest = new RestTemplate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);
        if (Profile.getUser().getLevel() == User.GUEST) {
            Toast.makeText(Review_Create.this, "You must login before post.", Toast.LENGTH_SHORT).show();
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
                EditText txtDescription = (EditText) findViewById(R.id.edit_text);
                EditText txtDescription2 = (EditText) findViewById(R.id.edit_text_2);
                topic = txtDescription.getText().toString();
                content = txtDescription2.getText().toString();
                //new ImageUploadTask().execute();
                post();

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

    private void post() {
        //magePost();


        EditText txtDescription = (EditText) findViewById(R.id.edit_text);
        EditText txtDescription2 = (EditText) findViewById(R.id.edit_text_2);
        topic = txtDescription.getText().toString();
        content = txtDescription2.getText().toString();

        //new PostTask().execute();


        ;
        HTTPRequest rq = new HTTPRequest();
        try {
            if (encodedImg == null) {
                rq.execute("http://203.151.92.199:8888/addreview?topic=" + topic + "&content=" +
                        content + "&authorId=" + Profile.getUser().getId());
            } else {
                rq.execute("http://203.151.92.199:8888/addreview?topic=" + topic + "&content=" +
                        content + "&authorId=" + Profile.getUser().getId() + "&img=" + encodedImg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(Review_Create.this, "Success!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, Review_View.class));

    }

    /*private class PostTask extends AsyncTask<Void,Void,String>{
        @Override
        protected String doInBackground(Void... params) {
            try {
                MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
                map.add("topic", topic);
                map.add("content", content);
                map.add("authorId", Profile.getUser().getId());
                map.add("img", encodedImg);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

                HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
                HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
                HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();
                List<HttpMessageConverter<?>> converters = new ArrayList<>();
                converters.add(formHttpMessageConverter);
                converters.add(stringHttpMessageConverternew);
                rest.setMessageConverters(converters);

                String result = rest.postForObject("http://203.151.91.199:8888/addreviewwithimg", map, String.class);
                return result;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Review_Create.this, s, Toast.LENGTH_SHORT).show();
        }


    }
*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            decodeFile(picturePath);
            filePath = picturePath;
        }

    }


    public void decodeFile(String filePath) {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 200;

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

        bitmap = BitmapFactory.decodeFile(filePath, o2);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, baos);
        byte[] b = baos.toByteArray();
        imageButton.setImageBitmap(bitmap);
        imageButton.setBackgroundResource(0);
        encodedImg = Base64.encodeToString(b, Base64.URL_SAFE);
                /*replace("%", "%25").
                replace(" ", "%20").
                replace("$", "%24").
                replace("&", "%60").
                replace("`", "%60").
                replace(":", "%3A").
                replace("<", "%3C").
                replace(">", "%3E").
                replace("[", "%5B").
                replace("]", "%5D").
                replace("{", "%7B").
                replace("}", "%7D").
                replace("“", "%22").
                replace("+", "%2B").
                replace("#", "%23").
                replace("@", "%40").
                replace("/", "%2F").
                replace(";", "%3B").
                replace("=", "%3D").
                replace("?", "%23").
                replace("\\", "%5C").
                replace("^", "%5E").
                replace("|", "%7C").
                replace("~", "%7E").
                replace("‘", "%27").
                replace(",", "%2C");*/

    }
}
/*
    class ImageUploadTask extends AsyncTask<String, Void, String> {


        // private ProgressDialog dialog;
<<<<<<< HEAD:LadkrabangCountry-Master/app/src/main/java/com/awakenguys/kmitl/ladkrabangcountry/Review_Create.java
        //private ProgressDialog dialog = new ProgressDialog(Review_Create.this);
=======
        private ProgressDialog dialog = new ProgressDialog(Review_Create.this);
>>>>>>> origin/reviewUI:LadkrabangCountry-Master/app/src/main/java/com/awakenguys/kmitl/ladkrabangcountry/Review_Create.java

        @Override
        protected void onPreExecute() {
            //dialog.setMessage("Uploading...");
            //dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            FTPClient ftpClient = new FTPClient();
            FileInputStream fis = null;
            boolean result;
            String ftpServerAddress = "sfZZ203.151.92.199";
            String userName = "root";
            String password = "kingkikkok";

            if (bitmap != null) {
                ftpClient = new FTPClient();
                try {
                    ftpClient.connect("203.151.92.199:1");
                    result = ftpClient.login(userName, password);
                    if (result == true) {
                        System.out.println("Logged in Successfully !");
                    } else {
                        System.out.println("Login Fail!");
                        return null;
                    }
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    ftpClient.changeWorkingDirectory("/images");
                    File file = new File(filePath);
                    String fileName = file.getName();
                    fis = new FileInputStream(file);
                    result = ftpClient.storeFile(fileName, fis);
                    if (result == true) {
                        System.out.println("File is uploaded successfully");
                        filePath = file.getName();
                    } else {
                        System.out.println("File uploading failed");
                    }
                    ftpClient.logout();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        ftpClient.disconnect();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            //dialog.dismiss();
            HTTPRequest rq = new HTTPRequest();
            try {
                rq.execute("http://203.151.92.199:8888/addreview?topic=" + topic + "&content=" +
                        content + "&authorId=" + Profile.getUser().getId() + "&img_path=" + filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            finish();
        }

    }
}*/



