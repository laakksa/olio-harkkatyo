package com.juuh.ht;


import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONWriteAndRead {


        public void write(ArrayList<Throws> th,String id) {

            String username = "testi1";

            //Creating a JSONObject object
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonarray = new JSONArray();
            //Inserting key-value pairs into the json object
            try {
                File root = new File("/data/user/0/com.juuh.ht/files/"+username+"/");
                root.mkdir();
                FileOutputStream fos = new FileOutputStream(new File("/data/user/0/com.juuh.ht/files/"+username+"/"+id+".json"));
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
        public ArrayList<Throws> read(String id){
            String json = null;
            try {
                json = readFileAsString("testi1",id+".json");
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
        public String readFileAsString(String username, String fileName) throws IOException {



            return new String(Files.readAllBytes(Paths.get("/data/user/0/com.juuh.ht/files/"+username+"/"+fileName)));


        }

        public ArrayList<Match> readIndex(){
            String username = "testi1";
            String json = null;
            try {
                json = readFileAsString("testi1","index.json");
                System.out.println(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ArrayList<Match> matches = new ArrayList<>();

            if (json == null){
                matches.add(new Match("0", "New Game", ""));
                File root = new File("/data/user/0/com.juuh.ht/files/"+username+"/");
                root.mkdir();

                return matches;
            }

            else {


                try {
                    JSONArray jarray = new JSONArray(json);
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject jobtect = jarray.getJSONObject(i);
                        matches.add(new Match(jobtect.getString("id"), jobtect.getString("hometeam"), jobtect.getString("awayteam")));

                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                    System.out.println("Testi");
                }

                return matches;
            }

        }

        public void writeIndex(ArrayList<Match> matches){

            String username = "testi1";

            //Creating a JSONObject object
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonarray = new JSONArray();
            //Inserting key-value pairs into the json object
            try {

                FileOutputStream fos = new FileOutputStream(new File("/data/user/0/com.juuh.ht/files/"+username+"/"+"index.json"));

                for (int i = 0; i < matches.size(); i++) {
                    jsonObject.put("id",matches.get(i).getId());
                    jsonObject.put("hometeam",matches.get(i).getHomeTeam());
                    jsonObject.put("awayteam",matches.get(i).getAwayTeam());
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
        public void writefile0() {
            String username = "testi1";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("/data/user/0/com.juuh.ht/files/"+username+"/"+"0.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write("[{\"ID\":1,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":2,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":3,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":4,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":5,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":6,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":7,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":8,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":9,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":10,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":11,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":12,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":13,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":14,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":15,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"},{\"ID\":16,\"Name\":\"\",\"score_first\":\"\",\"score_second\":\"\",\"score_third\":\"\",\"score_fourth\":\"\"}]".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileDetele(String id){
            String username  ="testi1";
        File fdelete = new File("/data/user/0/com.juuh.ht/files/"+username+"/"+id+".json");
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                System.out.println("file Deleted");
            } else {
                System.out.println("file not Deleted");
            }
        }
    }


        }






