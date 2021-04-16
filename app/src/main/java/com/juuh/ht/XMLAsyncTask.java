package com.juuh.ht;

import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class XMLAsyncTask extends AsyncTask<String, Void, ArrayList<WeatherEntry>> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<WeatherEntry> doInBackground(String... urls) {
        EntryManager entryManager = EntryManager.getInstance();
        ArrayList<WeatherEntry> weatherList = new ArrayList<>();
        //https://opendata.fmi.fi/wfs/fin?service=WFS&version=2.0.0&request=getFeature&storedquery_id=fmi::observations::weather::timevaluepair&place=Lappeenranta&parameters=ws_10min,t2m&starttime=2021-01-01T12:00:00Z&endtime=2021-01-05T12:00:00&
        String stringURL = urls[0];
        String[] result = null;
        XmlPullParser parser = null;
        try{
            URL url = new URL(stringURL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();
            WeatherEntry currentEntry = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String elementName;
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        elementName = parser.getName();
                        if (elementName.equals("wml2:MeasurementTVP")) {
                            currentEntry = new WeatherEntry();
                            weatherList.add(currentEntry);
                        } else if (currentEntry != null) {
                            if (elementName.equals("wml2:time")) {
                                currentEntry.setTime(parser.nextText());
                            } else if (elementName.equals("wml2:value")) {
                                currentEntry.setTemp(Float.parseFloat(parser.nextText()));
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (IOException e){
            e.printStackTrace();
            parser = null;
        } catch (XmlPullParserException e){
            e.printStackTrace();
        }
        return weatherList;

    }

}

