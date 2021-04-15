package com.juuh.ht;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class EntryManager {
    private ArrayList<TeamEntry> TeamsList;
    private static EntryManager em_instance = null;
    private EntryManager(){
        TeamsList = new ArrayList<TeamEntry>();
        getTeams();
    }

    public static EntryManager getInstance(){
        if (em_instance == null){
            em_instance = new EntryManager();
        }
        return em_instance;
    }

    public ArrayList<TeamEntry> addTeamEntry(TeamEntry entry){
        TeamsList.add(entry);
        return TeamsList;
    }
    /*public ArrayList<Entry> removeEntry(Entry entry){
        EntryList.remove(entry);
        return EntryList;
    }*/

    public ArrayList<TeamEntry> getTeamsList(){
        return TeamsList;
    }


    public void getTeams(){
        //Get data from kyykkacom API
        String myurl = "https://kyykka.com/api/teams/?format=json";
        String result;
        JSONAsyncTask get = new JSONAsyncTask();
        try {
            result = get.execute(myurl).get();
            JSONArray ja = new JSONArray(result);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject j = (JSONObject) ja.get(i);
                String abbreviation = j.getString("abbreviation");
                String name = j.getString("name");
                int id = j.getInt("id");
                int wins = j.getInt("matches_won");
                int losses = j.getInt("matches_lost");
                int ties = j.getInt("matches_tie");
                int matches_played = j.getInt("matches_played");
                int points = j.getInt("points_total");
                TeamEntry teamEntry = new TeamEntry(id, name, wins, losses, ties, matches_played,
                        points, abbreviation);
                addTeamEntry(teamEntry);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
