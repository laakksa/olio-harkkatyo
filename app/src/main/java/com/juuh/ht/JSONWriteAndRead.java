package com.juuh.ht;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

public class JSONWriteAndRead {

    private static JSONWriteAndRead jwr = new JSONWriteAndRead();
    public static JSONWriteAndRead getInstance(){
        return jwr;
    }

        //Takes list of throws, match id and users name
        //Writes throw data to json file that is located in users own folder


        public void write(ArrayList<Throwdata_scorecard> th, String id, String username) {


            //Creating a JSONObject object
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonarray = new JSONArray();

            //Creating folder and throw data files to user
            try {
                File root = new File("/data/user/0/com.juuh.ht/files/"+username+"/");
                root.mkdir();
                FileOutputStream fos = new FileOutputStream(new File(
                        "/data/user/0/com.juuh.ht/files/"+username+"/"+id+".json"));
                for (int i = 0; i < 16; i++) {
                    jsonObject.put("ID",th.get(i).getThrow_turn());
                    jsonObject.put("Name", th.get(i).getPlayer());
                    jsonObject.put("score_first", th.get(i).getScoreFirst());
                    jsonObject.put("score_second", th.get(i).getScoreSecond());
                    jsonObject.put("score_third", th.get(i).getScoreThird());
                    jsonObject.put("score_fourth", th.get(i).getScoreFourth());
                    jsonarray.put(jsonObject);
                    jsonObject = new JSONObject();

                }
                //Writing scores to json file
                fos.write(jsonarray.toString().getBytes());
                fos.close();

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            System.out.println("JSON file created: "+jsonObject);
        }


        //Takes match id and username. Reads file from users own folder and saves data to ArrayList
        //Returns Arraylist that contains throws data

        public ArrayList<Throwdata_scorecard> read(String id, String username){
            String json = null;
            try {
                json = readFileAsString(username,id+".json");
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayList<Throwdata_scorecard> th = new ArrayList<>();

            //Reading throws data from file

                try{
                    JSONArray jarray = new JSONArray(json);
                    for (int i = 0; i < 16; i++) {
                        JSONObject jobtect = jarray.getJSONObject(i);
                        th.add(new Throwdata_scorecard((i+1),jobtect.getString("Name")
                                ,jobtect.getString("score_first")
                                ,jobtect.getString("score_second")
                                ,jobtect.getString("score_third")
                                ,jobtect.getString("score_fourth")));

                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

            return th;






        }

        //Takes username and filename. Reads file to string and returns that

        public String readFileAsString(String username, String fileName) throws IOException {

            BufferedReader bf = new BufferedReader(
                    new FileReader(
                            "/data/user/0/com.juuh.ht/files/"+username+"/"+fileName));
            String line = bf.readLine();

            return line;



        }

        //Taking username and returns arraylist of all matches and main values of match like date
        // and teams.

        public ArrayList<Match> readIndex(String username){
            String json = null;
            try {
                json = readFileAsString(username,"index.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
            ArrayList<Match> matches = new ArrayList<>();

            //Creating match 0 what is empty match

            if (json == null){
                matches.add(new Match("0", "New Game", ""
                        ,"","",""
                        ,"","","",""));

                //Making folder to user
                File root1 = new File("/data/user/0/com.juuh.ht/files/");
                root1.mkdir();
                File root2 = new File("/data/user/0/com.juuh.ht/files/"+username+"/");
                root2.mkdir();
                return matches;
            }

            else {

            //Readind match main data from index file and saving it to matches object
                try {
                    JSONArray jarray = new JSONArray(json);
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject jobtect = jarray.getJSONObject(i);
                        matches.add(new Match(jobtect.getString("id")
                                ,jobtect.getString("hometeam")
                                ,jobtect.getString("awayteam")
                                ,jobtect.getString("homeRoundOneScore")
                                ,jobtect.getString("homeRoundTwoScore")
                                ,jobtect.getString("homeScore")
                                ,jobtect.getString("awayRoundOneScore")
                                ,jobtect.getString("awayRoundTwoScore")
                                ,jobtect.getString("awayScore")
                                ,jobtect.getString("date")));

                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

                return matches;
            }

        }

        //Writing index file that contains main data of matches like name and date.
        //Takes list of matches and username.

        public void writeIndex(ArrayList<Match> matches, String username){


            //Creating a JSONObject object
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonarray = new JSONArray();


            // Creating index file
            try {

                FileOutputStream fos = new FileOutputStream(new File(
                        "/data/user/0/com.juuh.ht/files/"+username+"/"+"index.json"));

                //Writing main details of all matches to index file

                for (int i = 0; i < matches.size(); i++) {
                    jsonObject.put("id",matches.get(i).getId());
                    jsonObject.put("date",matches.get(i).getDate());
                    jsonObject.put("hometeam",matches.get(i).getHomeTeam());
                    jsonObject.put("homeRoundOneScore",matches.get(i).getRoundOneHomeScore());
                    jsonObject.put("homeRoundTwoScore",matches.get(i).getRoundTwoHomeScore());
                    jsonObject.put("homeScore",matches.get(i).getHomeFinalScore());
                    jsonObject.put("awayteam",matches.get(i).getAwayTeam());
                    jsonObject.put("awayRoundOneScore",matches.get(i).getRoundOneAwayScore());
                    jsonObject.put("awayRoundTwoScore",matches.get(i).getRoundTwoAwayScore());
                    jsonObject.put("awayScore",matches.get(i).getAwayFinalScore());
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

        //Takes username. Writes throw data file0 that is empty. Can be used show empty scorecard.
        public void writefile0(String username) {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(
                    "/data/user/0/com.juuh.ht/files/"+username+"/"+"0.json"));
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

    //Takes match id and username. Deletes selected match.

    public void fileDetele(String id, String username){
        File fdelete = new File(
                "/data/user/0/com.juuh.ht/files/"+username+"/"+id+".json");
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                System.out.println("file Deleted");
            } else {
                System.out.println("file not Deleted");
            }
        }
    }


        }






