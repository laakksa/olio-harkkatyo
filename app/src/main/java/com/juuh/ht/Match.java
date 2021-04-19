package com.juuh.ht;

public class Match {

    private String id;
    private String homeTeam;
    private String awayTeam;

    public Match(String id, String homeTeam, String awayTeam){
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }


    public String getId() {
        return id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String toString(){
        return homeTeam+"-"+awayTeam;
    }
}
