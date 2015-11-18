package com.awakenguys.kmitl.ladkrabangcountry;


import android.os.AsyncTask;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HTTPRequest extends AsyncTask<String,Void,Void> {
    private String value = null;

    @Override
    protected Void doInBackground(String... params) {
        try {
            value = executeGet(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String executeGet(String targetURL) throws Exception{
        URL url;
        HttpURLConnection connection;
        url = new URL(targetURL.replace(" ","+"));
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla 5.0");
        connection.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while((inputLine = in.readLine())!= null){
            response.append(inputLine);
        }
        in.close();
        connection.disconnect();
        return response.toString();
    }

    public String getValue() {
        return value;
    }
}

