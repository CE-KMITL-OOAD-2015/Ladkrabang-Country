package com.awakenguys.kmitl.ladkrabangcountry;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.awakenguys.kmitl.ladkrabangcountry.model.User;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class PostActivity extends AppCompatActivity {
    private static final int SELECT_PHOTO = 1;
    private TextView text;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        if(Profile.getUser().getLevel()== User.GUEST){
            Toast.makeText(PostActivity.this, "You must login before post.", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this, Profile.class));
        }

        Button button = (Button) findViewById(R.id.pick_image_button);
        text = (TextView) findViewById(R.id.text);
        imageView = (ImageView) findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {
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
        EditText txtDescription = (EditText) findViewById(R.id.edittext);
        EditText txtDescription2 = (EditText) findViewById(R.id.edittext2);
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
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Here we need to check if the activity that was triggers was the Image Gallery.
        // If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
        // If the resultCode is RESULT_OK and there is some data we know that an image was picked.
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null) {
            // Let's read picked image data - its URI
            Uri pickedImage = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
            imageView.setImageBitmap(bitmap);
            // Do something with the bitmap
            // At the end remember to close the cursor or you will end with the RuntimeException!
            cursor.close();
        }
    }
}