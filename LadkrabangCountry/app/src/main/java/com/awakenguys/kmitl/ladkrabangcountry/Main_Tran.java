package com.awakenguys.kmitl.ladkrabangcountry;

import android.content.Intent;
import android.preference.DialogPreference;
import android.preference.Preference;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Button;
import android.widget.PopupMenu;


public class Main_Tran extends ActionBarActivity implements View.OnClickListener {
    private void openTrainDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.title_activity_tran__train);
        dialog.setItems(R.array.func_train, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__tran);

        //กำหนด listener ให้ปุ่ม train
        View btnTrain = findViewById(R.id.train);
        btnTrain.setOnClickListener(this);
        //pop
        /*Button cmd = (Button) findViewById(R.id.train);
        cmd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                PopupMenu pop = new PopupMenu(Main_Tran.this,v);
                pop.getMenuInflater().inflate(R.menu.menu_tran__train,pop.getMenu());
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()){
                }
            }
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main__tran, menu);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.train:
                Intent i = new Intent(this,Tran_Train.class);
                startActivity(i);
                break;
        }
    }
//========================================================================
    public void Menu_Train(View view) {
        Intent intent = new Intent(this, Tran_Train.class);
        startActivity(intent);
    }
    public void Menu_Van(View view) {
        Intent intent = new Intent(this, Tran_Van.class);
        startActivity(intent);
    }
    public void Menu_Bus(View view) {
        Intent intent = new Intent(this, Tran_Bus.class);
        startActivity(intent);
    }
    public void Menu_Motorcycle(View view) {
        Intent intent = new Intent(this, Tran_Motorcycle.class);
        startActivity(intent);
    }
}