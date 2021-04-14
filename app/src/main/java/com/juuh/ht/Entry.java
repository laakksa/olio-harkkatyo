package com.juuh.ht;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Entry {
    int id;
    public Entry(int id){
        this.id = id;
    }
}


class TeamEntry extends Entry{
    String name, abbreviation;
    int wins, losses, ties, matches_played, points;
    ArrayList<PlayerEntry> playerList;
    public TeamEntry(int id, String name, int wins, int losses, int ties, int matches_played,
                     int points, String abbreviation){
        super(id);
        playerList = new ArrayList<PlayerEntry>();
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
        this.matches_played = matches_played;
        this.points = points;
        this.abbreviation = abbreviation;
    }
    public void addPlayer(PlayerEntry p){
        playerList.add(p);
    }

}

class MatchEntry extends Entry{
    Date datetime;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    Integer away_score, home_score;
    int home_id, away_id;
    public MatchEntry(int id, Integer away_score, Integer home_score, String datetime, int home_id, int away_id){
        super(id);
        this.away_score = away_score;
        this.home_score = home_score;
        this.away_id = away_id;
        this.home_id = home_id;
        try {
            this.datetime = format.parse(datetime);
        } catch (ParseException e){
            e.printStackTrace();
            this.datetime = null;
        }
    }
    public Date getDatetime(){
        return this.datetime;
    }
}

class PlayerEntry extends  Entry{
    String name;
    int team_id;
    public PlayerEntry(int player_id, int team_id, String name){
        super(player_id);
        this.name = name;
        this.team_id = team_id;
    }
}

