package com.awakenguys.kmitl.ladkrabangcountry;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Main_Tran extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__tran);
        Button btnTrain = (Button) findViewById(R.id.train);//สร้างตัวแปรมาเก็ยปุ่ม Train
        btnTrain.setOnClickListener(new View.OnClickListener() { // เมื่อกดปุ่มแล้ให้ทำ
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main_Tran.this);// สร้างAlert Dialog ในหน้า Main_Train
                LayoutInflater inflater = getLayoutInflater();// สร้าง Inflater

                View view = inflater.inflate(R.layout.dialog_tran, null);// ทำหน้าที่ในการย่อหน้าของ Dialog_tran เป็น Dialog pop-up
                builder.setView(view);
                builder.show();
            }
        });

        Button btnBus = (Button) findViewById(R.id.bus);//สร้างตัวแปรมาเก็ยปุ่ม Train
        btnBus.setOnClickListener(new View.OnClickListener() { // เมื่อกดปุ่มแล้ให้ทำ
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main_Tran.this);// สร้างAlert Dialog ในหน้า Main_Train
                LayoutInflater inflater = getLayoutInflater();// สร้าง Inflater

                View view = inflater.inflate(R.layout.dialog_bus, null);// ทำหน้าที่ในการย่อหน้าของ Dialog_tran เป็น Dialog pop-up
                builder.setView(view);
                builder.show();
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
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.train:
                Intent i = new Intent(this,Tran_Train.class);
                startActivity(i);
                break;

            case R.id.bus:
                Intent j = new Intent(this,Tran_Bus.class);
                startActivity(j);
                break;
        }
    }
    //========================================================================
    public void Menu_Train_Re(View view) {
        Intent intent = new Intent(this, TrainArrivalTimeActivity.class);
        intent.putExtra("name","re");
        startActivity(intent);
    }

    public void Menu_Train_Go(View view) {
        Intent intent = new Intent(this, TrainArrivalTimeActivity.class);
        intent.putExtra("name","go");
        startActivity(intent);
    }

    public void Menu_Van(View view) {
        Intent intent = new Intent(this, Tran_Van.class);
        startActivity(intent);
    }

    public void Menu_Motorcycle(View view) {
        Intent intent = new Intent(this, MotorcycleMap.class);
        startActivity(intent);
    }


    public void Menu_Bus1(View view) {
        Intent intent = new Intent(this, Tran_Bus.class);
        startActivity(intent);
    }

    public void Menu_Bus2(View view) {
        Intent intent = new Intent(this, Tran_Bus_Second.class);
        startActivity(intent);
    }

}