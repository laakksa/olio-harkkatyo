package com.juuh.ht;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class JSONAsyncTask extends AsyncTask<String, Void, String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    //Gets json from url and returns it as a string
    protected String doInBackground(String... urls) {
        String stringURL = urls[0];
        String result;
        String inputline;
        try{
            URL url = new URL(stringURL);
            HttpsURLConnection connection =(HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.connect();
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                while ((inputline = br.readLine()) != null) {
                    sb.append(inputline);
                }
            }
            br.close();
            isr.close();
            result = sb.toString();
        } catch (IOException e){
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
