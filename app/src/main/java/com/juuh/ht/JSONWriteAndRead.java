package com.juuh.ht;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONWriteAndRead {


        public void write(Context context, ArrayList<Throws> th) {

            //Creating a JSONObject object
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            JSONArray jsonarray = new JSONArray();
            //Inserting key-value pairs into the json object
            try {
                FileOutputStream fos = context.openFileOutput("testi.json", Context.MODE_PRIVATE);
                for (int i = 0; i < 16; i++) {
                    jsonObject.put("ID",th.get(i).getThrow_turn());
                    jsonObject.put("Name", th.get(i).getPlayer());
                    System.out.println(th.get(i).getPlayer());
                    jsonObject.put("score_first", th.get(i).getScoreFirst());
                    jsonObject.put("score_second", th.get(i).getScoreSecond());
                    jsonObject.put("score_third", th.get(i).getScoreThird());
                    jsonObject.put("score_fourth", th.get(i).getScoreFourth());
                    jsonarray.put(jsonObject);
                    jsonObject = new JSONObject();

                }
                fos.write(jsonarray.toString().getBytes());
                fos.close();

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            System.out.println("JSON file created: "+jsonObject);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public ArrayList<Throws> read(){
            String json = null;
            try {
                json = readFileAsString();
                System.out.println(json);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayList<Throws> th = new ArrayList<>();

                try{
                    JSONArray jarray = new JSONArray(json);
                    for (int i = 0; i < 16; i++) {
                        JSONObject jobtect = jarray.getJSONObject(i);
                        th.add(new Throws((i+1),jobtect.getString("Name"),jobtect.getString("score_first"),jobtect.getString("score_second"), jobtect.getString("score_third"),jobtect.getString("score_fourth")));

                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

            return th;






        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public String readFileAsString() throws IOException {

            return new String(Files.readAllBytes(Paths.get("/data/user/0/com.juuh.ht/files/testi.json")));
        }


}
