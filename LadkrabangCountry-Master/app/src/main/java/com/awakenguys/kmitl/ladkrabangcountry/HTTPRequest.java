package com.awakenguys.kmitl.ladkrabangcountry;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPRequest extends AsyncTask<String,Void,Void> {
    @Override
    protected Void doInBackground(String... params) {
        try {
            executeGet(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String executeGet(String targetURL) throws Exception{
        URL url;
        HttpURLConnection urlcon;
        url = new URL(targetURL);
        urlcon = (HttpURLConnection) url.openConnection();
        urlcon.setRequestMethod("GET");
        urlcon.setRequestProperty("User-Agent", "Mozilla 5.0");
        urlcon.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while((inputLine = in.readLine())!= null){
            response.append(inputLine);
        }
        in.close();
        urlcon.disconnect();
        return response.toString();
    }
}