package com.awakenguys.kmitl.ladkrabangcountry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.awakenguys.kmitl.ladkrabangcountry.model.Train;

import java.util.Calendar;

public class TrainArrivalTimeActivity extends AppCompatActivity {

    private Train train;
    private Calendar calendar;
    private AsyncTask getTrainTask;
    private ArrayAdapter<String> adapter;

    Context context=null;
    //broadcast class is used as nested class
    private BroadcastReceiver mtimeInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context c, Intent i) {
            //This is where the magic begins!!
            calendar = Calendar.getInstance();
            int minutesLeft = train.minutesUntilNextTrain("" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
            ((TextView)findViewById(R.id.Hours_Left)).setText(""+minutesLeft/60);
            if(minutesLeft%60 < 10) {
                ((TextView) findViewById(R.id.Minutes_Left)).setText("0" + minutesLeft % 60);
            }
            else{
                ((TextView) findViewById(R.id.Minutes_Left)).setText("" + minutesLeft % 60);
            }
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_arrival_time);

        //use action time tick for getting update every minute
        IntentFilter mTime = new IntentFilter(Intent.ACTION_TIME_TICK);
        //register broadcast receiver
        registerReceiver(mtimeInfoReceiver, mTime);


        TextView textView = (TextView)findViewById(R.id.from_or_to_bangkok);
        Bundle bundle = getIntent().getExtras();
        String trainName = bundle.getString("name");
        switch(trainName)
        {
            case "go": textView.setText("รถไฟจากกรุงเทพฯ จะมาถึงในอีก");
                break;
            case "re": textView.setText("รถไฟไปกรุงเทพฯ จะมาถึงในอีก");
                break;
        }
        getTrainTask = new GetTrainTask().execute(trainName);
        while(train==null);
        //This is where the magic begins!!
        calendar = Calendar.getInstance();
        int minutesLeft = train.minutesUntilNextTrain("" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
        ((TextView)findViewById(R.id.Hours_Left)).setText(""+minutesLeft/60);
        if(minutesLeft%60 < 10) {
            ((TextView) findViewById(R.id.Minutes_Left)).setText("0" + minutesLeft % 60);
        }
        else{
            ((TextView) findViewById(R.id.Minutes_Left)).setText("" + minutesLeft % 60);
        }

        ListView listView = (ListView) findViewById(R.id.trainArrivalTimeListView);
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.header_train_time_table_listview, listView, false);
        listView.addHeaderView(header, null, false);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,train.getTrainArrivalTimes().split(",")) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View itemView = super.getView(position, convertView, parent);

                // play with itemView
                if(((TextView)itemView).getText().toString().contentEquals(
                        train.getNextTrainArrivalTime("" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE))))
                {
                    ((TextView)itemView).setTextColor(Color.RED);
                    ((TextView)itemView).setTextSize(
                            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));//set font size to 20dp
                }
                else
                {
                    ((TextView)itemView).setTextColor(Color.BLACK);
                    ((TextView)itemView).setTextSize(
                            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));//set font size to 10dp
                }

                return itemView;
            }
        };

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        adapter.getView(adapter.getPosition("07:00"),listView,null).setBackgroundColor(Color.RED);

    }

    @Override
    protected void onStop() {
        super.onStop();
        getTrainTask.cancel(true);
    }

    class GetTrainTask extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... params) {
            while(train==null && !isCancelled()) {
                train = new ContentProvider().getTrainByName(params[0]);
            }
            return null;
        }
    }

}
