package com.juuh.ht;

//Throws contains data of every throw

public class Throwdata_scorecard {

    private int throw_turn;

    private String player;

    private String scoreFirst;
    private String scoreSecond;
    private String scoreThird;
    private String scoreFourth;

    public Throwdata_scorecard(int throw_turn, String player, String scoreFirst, String scoreSecond,
                               String scoreThird, String scoreFourth){
        this.throw_turn = throw_turn;
        this.player = player;
        this.scoreFirst = scoreFirst;
        this.scoreSecond= scoreSecond;
        this.scoreThird= scoreThird;
        this.scoreFourth = scoreFourth;
        
    }

    public int getThrow_turn() {
        return throw_turn;
    }

    public String getPlayer() {
        return player;
    }

    public String getScoreFirst() {
        return scoreFirst;
    }

    public String getScoreSecond() {
        return scoreSecond;
    }

    public String getScoreThird() {
        return scoreThird;
    }

    public String getScoreFourth() {
        return scoreFourth;
    }
}
