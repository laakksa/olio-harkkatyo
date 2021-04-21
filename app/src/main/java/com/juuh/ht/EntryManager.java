package com.juuh.ht;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class EntryManager {
    private ArrayList<TeamEntry> TeamsList;
    private ArrayList<MatchEntry> matchesList;
    private static EntryManager em_instance = null;
    private EntryManager(){
        //Initialize teamsList and matchesList and populate them with data from Kyykka.com API
        TeamsList = new ArrayList<TeamEntry>();
        matchesList = new ArrayList<MatchEntry>();
        getTeams();
        getMatches();
    }
    //Singleton pattern for EntryManager
    public static EntryManager getInstance(){
        if (em_instance == null){
            em_instance = new EntryManager();
        }
        return em_instance;
    }

    public void addMatchEntry(MatchEntry entry){
        matchesList.add(entry);
    }

    public void addTeamEntry(TeamEntry entry){
        TeamsList.add(entry);
    }

    public ArrayList<TeamEntry> getTeamsList(){
        return TeamsList;
    }
    public ArrayList<MatchEntry> getMatchesList(){return matchesList;}


    public void getTeams(){
        //Get team data from kyykkacom API and store it in TeamsList
        String myurl = "https://kyykka.com/api/teams/?format=json&season=1";
        String result;
        JSONAsyncTask get = new JSONAsyncTask();
        try {
            result = get.execute(myurl).get();
            if (!(result == null)) {
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
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<WeatherEntry> getWeather(String startdate, String enddate){
        //Get 10min temperature data between startdate and enddate
        ArrayList<WeatherEntry> entries = null;
        String weatherUrl = "https://opendata.fmi.fi/wfs/fin?service=WFS&" +
                "version=2.0.0&request=getFeature&storedquery_id=fmi::observations::weather::" +
                "timevaluepair&place=Lappeenranta&parameters=t2m&starttime=" +
                startdate + "&endtime=" + enddate + "&timestep=30&";
        XMLAsyncTask xmltask = new XMLAsyncTask();
        try {
            entries = xmltask.execute(weatherUrl).get();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        return entries;
    }

    //Get match data from Kyykka.com API and store it in matchesList
    public void getMatches(){
        String matchUrl = "https://kyykka.com/api/matches/?format=json&season=1";
        String result;
        JSONAsyncTask get = new JSONAsyncTask();
        try {
            result = get.execute(matchUrl).get();
            if (!(result == null)) {
                Integer home_score, away_score;
                JSONArray ja = new JSONArray(result);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject j = (JSONObject) ja.get(i);
                    String datetime = j.getString("match_time");
                    int id = j.getInt("id");
                    if (!j.isNull("home_score_total")) {
                        home_score = j.getInt("home_score_total");
                    } else {
                        home_score = null;
                    }
                    if (!j.isNull("away_score_total")) {
                        away_score = j.getInt("away_score_total");
                    } else {
                        away_score = null;
                    }
                    JSONObject home_team = j.getJSONObject("home_team");
                    JSONObject away_team = j.getJSONObject("away_team");
                    int home_id = home_team.getInt("id");
                    int away_id = away_team.getInt("id");
                    String home_abbreviation = home_team.getString("abbreviation");
                    String away_abbreviation = away_team.getString("abbreviation");
                    MatchEntry matchEntry = new MatchEntry(id, away_score, home_score, datetime,
                            home_id, away_id, home_abbreviation, away_abbreviation);
                    addMatchEntry(matchEntry);
                }
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
