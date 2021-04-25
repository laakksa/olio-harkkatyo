package com.juuh.ht;

//Contains data of everymatvh

public class Match {

    private String id;
    private String date;
    private String homeTeam;
    private String awayTeam;
    private String roundOneHomeScore;
    private String roundTwoHomeScore;
    private String homeFinalScore;
    private String roundOneAwayScore;
    private String roundTwoAwayScore;
    private String awayFinalScore;


    public Match(String id, String homeTeam, String awayTeam, String roundOneHomeScore
            , String roundTwoHomeScore, String homeFinalScore, String roundOneAwayScore
            ,String roundTwoAwayScore, String awayFinalScore, String date){
        this.id = id;
        this.date = date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.roundOneHomeScore = roundOneHomeScore;
        this.roundTwoHomeScore = roundTwoHomeScore;
        this.homeFinalScore = homeFinalScore;
        this.roundOneAwayScore= roundOneAwayScore;
        this.roundTwoAwayScore = roundTwoAwayScore;
        this.awayFinalScore = awayFinalScore;
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

    public String getHomeFinalScore() {
        return homeFinalScore;
    }

    public String getRoundOneHomeScore() {
        return roundOneHomeScore;
    }

    public String getRoundTwoHomeScore() {
        return roundTwoHomeScore;
    }

    public String getAwayFinalScore() {
        return awayFinalScore;
    }

    public String getDate() {
        return date;
    }

    public String getRoundOneAwayScore() {
        return roundOneAwayScore;
    }

    public String getRoundTwoAwayScore() {
        return roundTwoAwayScore;
    }

    public String toString(){
        return homeTeam+""+awayTeam+" "+date;
    }


}
