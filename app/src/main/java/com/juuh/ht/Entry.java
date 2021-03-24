package com.juuh.ht;

import android.os.ParcelFormatException;

import androidx.versionedparcelable.VersionedParcel;

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
    String name;
    ArrayList<PlayerEntry> playerList;
    public TeamEntry(int id, String name){
        super(id);
        playerList = new ArrayList<PlayerEntry>();
        this.name = name;
    }
    public void addPlayer(PlayerEntry p){
        playerList.add(p);
    }
}

class MatchEntry extends Entry{
    Date datetime;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    int away_score, home_score;
    public MatchEntry(int id, int away_score, int home_score, String datetime){
        super(id);
        this.away_score = away_score;
        this.home_score = home_score;
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