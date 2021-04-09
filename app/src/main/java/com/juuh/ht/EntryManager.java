package com.juuh.ht;

import java.util.ArrayList;

public class EntryManager {
    private ArrayList<TeamEntry> TeamsList;
    private static EntryManager em_instance = null;
    private EntryManager(){
        TeamsList = new ArrayList<TeamEntry>();
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
}
