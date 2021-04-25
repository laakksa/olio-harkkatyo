package com.juuh.ht;

import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;


import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Scorecard_fragment extends Fragment {

    //Creating variables

    JSONWriteAndRead jwr = JSONWriteAndRead.getInstance();
    String ID = "-";
    SharedPreferences preferences;
    String currentUser;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        preferences = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        currentUser = preferences.getString("currentUser", null);
        return inflater.inflate(R.layout.fragment_scorecard,container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        ArrayList<Match> matches = jwr.readIndex(currentUser);
        System.out.println(currentUser);
        jwr.writefile0(currentUser);

        //Creating spinner for matches

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner2);

        ArrayAdapter<Match> adapter =
                new ArrayAdapter<>(view.getContext(),  android.R.layout.simple_spinner_item, matches);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        for (int i = 0; i < matches.size(); i++) {
            ID = matches.get(i).getId();}
        ID += "1";

        Button button = (Button)view.findViewById(R.id.button2);
        Button button2 = (Button)view.findViewById(R.id.button);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Match match = (Match) spinner.getSelectedItem();
                //If selected match is 0, shows empty match

                if (match.getId().equals("0")){
                    readGame("0","","",""
                            ,"","",""
                            ,"","","");
                    for (int i = 0; i < matches.size(); i++) {
                        ID = matches.get(i).getId();}
                    ID += "1";
                    System.out.println(ID);
                    System.out.println("MatchID = 0");
                    button.setOnClickListener(view1 -> {saveGame(matches,ID);
                        readGame("0","","",""
                                ,"",""
                                ,"","",""
                                ,"");});
                }


                //If selected match something else showning that match
                else {
                    readGame(match.getId(),match.getHomeTeam(),match.getAwayTeam(),match.getRoundOneHomeScore()
                    ,match.getRoundTwoHomeScore(),match.getHomeFinalScore()
                    ,match.getRoundOneAwayScore(),match.getRoundTwoAwayScore()
                    ,match.getAwayFinalScore(),match.getDate());
                    button.setOnClickListener(view12 -> {saveGame(matches,match.getId());
                        readGame("0","","",""
                                ,"",""
                                ,"","",""
                                ,"");});

                    button2.setOnClickListener(v -> {
                        removeMatch(matches,match.getId());
                        readGame("0","","",""
                                ,"",""
                                ,"","",""
                                ,"");
                    });
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        super.onViewCreated(view, savedInstanceState);
    }



    public void saveGame(ArrayList<Match> matches, String id) {

        //Creating variables

        EditText gameDate = getView().findViewById(R.id.editGameDate);

        EditText homeTeam = getView().findViewById(R.id.editTextTextPersonName);
        EditText roundOneScoreHome = getView().findViewById(R.id.setFirstRoundHomeScore);
        EditText roundTwoScoreHome = getView().findViewById(R.id.setSecondRoundHomeScore);
        EditText homeScore = getView().findViewById(R.id.setHomeFinalScore);

        EditText awayTeam = getView().findViewById(R.id.editTextTextPersonName2);
        EditText roundOneScoreAway = getView().findViewById(R.id.setFirstRoundAwayScore);
        EditText roundTwoScoreAway = getView().findViewById(R.id.setSecondRoundAwayScore);
        EditText awayScore = getView().findViewById(R.id.setAwayScore);

        EditText homePlayer1 = getView().findViewById(R.id.setfirstPlayer);

        EditText homePlayer1Throw1 = getView().findViewById(R.id.player1Throw1);
        EditText homePlayer1Throw2 = getView().findViewById(R.id.player1Throw2);
        EditText homePlayer1Throw3 = getView().findViewById(R.id.player1Throw3);
        EditText homePlayer1Throw4 = getView().findViewById(R.id.player1Throw4);

        EditText homePlayer2 = getView().findViewById(R.id.setsecondPlayer);

        EditText homePlayer2Throw1 = getView().findViewById(R.id.player2Throw5);
        EditText homePlayer2Throw2 = getView().findViewById(R.id.player2Throw2);
        EditText homePlayer2Throw3 = getView().findViewById(R.id.player2Throw3);
        EditText homePlayer2Throw4 = getView().findViewById(R.id.player2Throw4);

        EditText homePlayer3 = getView().findViewById(R.id.setthirdPlayer);

        EditText homePlayer3Throw1 = getView().findViewById(R.id.player3Throw1);
        EditText homePlayer3Throw2 = getView().findViewById(R.id.player3Throw2);
        EditText homePlayer3Throw3 = getView().findViewById(R.id.player3Throw3);
        EditText homePlayer3Throw4 = getView().findViewById(R.id.player3Throw4);

        EditText homePlayer4 = getView().findViewById(R.id.setFourthPlayer);

        EditText homePlayer4Throw1 = getView().findViewById(R.id.player4Throw1);
        EditText homePlayer4Throw2 = getView().findViewById(R.id.player4Throw2);
        EditText homePlayer4Throw3 = getView().findViewById(R.id.player4Throw3);
        EditText homePlayer4Throw4 = getView().findViewById(R.id.player4Throw4);

        EditText homePlayer5 = getView().findViewById(R.id.setfirstPlayer2);

        EditText homePlayer5Throw1 = getView().findViewById(R.id.player1Throw5);
        EditText homePlayer5Throw2 = getView().findViewById(R.id.player1Throw6);
        EditText homePlayer5Throw3 = getView().findViewById(R.id.player1Throw7);
        EditText homePlayer5Throw4 = getView().findViewById(R.id.player1Throw8);

        EditText homePlayer6 = getView().findViewById(R.id.setsecondPlayer2);

        EditText homePlayer6Throw1 = getView().findViewById(R.id.player2Throw6);
        EditText homePlayer6Throw2 = getView().findViewById(R.id.player2Throw7);
        EditText homePlayer6Throw3 = getView().findViewById(R.id.player2Throw8);
        EditText homePlayer6Throw4 = getView().findViewById(R.id.player2Throw);

        EditText homePlayer7 = getView().findViewById(R.id.setthirdPlayer2);

        EditText homePlayer7Throw1 = getView().findViewById(R.id.player3Throw5);
        EditText homePlayer7Throw2 = getView().findViewById(R.id.player3Throw6);
        EditText homePlayer7Throw3 = getView().findViewById(R.id.player3Throw7);
        EditText homePlayer7Throw4 = getView().findViewById(R.id.player3Throw8);

        EditText homePlayer8 = getView().findViewById(R.id.setFourthPlayer2);

        EditText homePlayer8Throw1 = getView().findViewById(R.id.player4Throw5);
        EditText homePlayer8Throw2 = getView().findViewById(R.id.player4Throw6);
        EditText homePlayer8Throw3 = getView().findViewById(R.id.player4Throw7);
        EditText homePlayer8Throw4 = getView().findViewById(R.id.player4Throw8);

        EditText awayPlayer1 = getView().findViewById(R.id.setAwayPlayer);

        EditText awayPlayer1Throw1 = getView().findViewById(R.id.awayPlayer1Throw1);
        EditText awayPlayer1Throw2 = getView().findViewById(R.id.awayPlayer1Throw2);
        EditText awayPlayer1Throw3 = getView().findViewById(R.id.awayPlayer1Throw3);
        EditText awayPlayer1Throw4 = getView().findViewById(R.id.awayPlayer1Throw4);

        EditText awayPlayer2 = getView().findViewById(R.id.setAwayPlayer2);

        EditText awayPlayer2Throw1 = getView().findViewById(R.id.awayPlayer2Throw1);
        EditText awayPlayer2Throw2 = getView().findViewById(R.id.awayPlayer2Throw2);
        EditText awayPlayer2Throw3 = getView().findViewById(R.id.awayPlayer2Throw3);
        EditText awayPlayer2Throw4 = getView().findViewById(R.id.awayPlayer2Throw4);

        EditText awayPlayer3 = getView().findViewById(R.id.setAwayPlayer3);

        EditText awayPlayer3Throw1 = getView().findViewById(R.id.awayPlayer3Throw1);
        EditText awayPlayer3Throw2 = getView().findViewById(R.id.awayPlayer3Throw2);
        EditText awayPlayer3Throw3 = getView().findViewById(R.id.awayPlayer3Throw3);
        EditText awayPlayer3Throw4 = getView().findViewById(R.id.awayPlayer3Throw4);

        EditText awayPlayer4 = getView().findViewById(R.id.setAwayPlayer4);

        EditText awayPlayer4Throw1 = getView().findViewById(R.id.awayPlayer4Throw1);
        EditText awayPlayer4Throw2 = getView().findViewById(R.id.awayPlayer4Throw2);
        EditText awayPlayer4Throw3 = getView().findViewById(R.id.awayPlayer4Throw3);
        EditText awayPlayer4Throw4 = getView().findViewById(R.id.awayPlayer4Throw4);


        EditText awayPlayer5 = getView().findViewById(R.id.setAwayPlayer5);

        EditText awayPlayer5Throw1 = getView().findViewById(R.id.awayPlayer5Throw1);
        EditText awayPlayer5Throw2 = getView().findViewById(R.id.awayPlayer5Throw2);
        EditText awayPlayer5Throw3 = getView().findViewById(R.id.awayPlayer5Throw3);
        EditText awayPlayer5Throw4 = getView().findViewById(R.id.awayPlayer5Throw4);

        EditText awayPlayer6 = getView().findViewById(R.id.setAwayPlayer6);

        EditText awayPlayer6Throw1 = getView().findViewById(R.id.awayPlayer6Throw1);
        EditText awayPlayer6Throw2 = getView().findViewById(R.id.awayPlayer6Throw2);
        EditText awayPlayer6Throw3 = getView().findViewById(R.id.awayPlayer6Throw3);
        EditText awayPlayer6Throw4 = getView().findViewById(R.id.awayPlayer6Throw4);

        EditText awayPlayer7 = getView().findViewById(R.id.setAwayPlayer7);

        EditText awayPlayer7Throw1 = getView().findViewById(R.id.awayPlayer7Throw1);
        EditText awayPlayer7Throw2 = getView().findViewById(R.id.awayPlayer7Throw2);
        EditText awayPlayer7Throw3 = getView().findViewById(R.id.awayPlayer7Throw3);
        EditText awayPlayer7Throw4 = getView().findViewById(R.id.awayPlayer7Throw4);

        EditText awayPlayer8 = getView().findViewById(R.id.setAwayPlayer8);

        EditText awayPlayer8Throw1 = getView().findViewById(R.id.awayPlayer8Throw1);
        EditText awayPlayer8Throw2 = getView().findViewById(R.id.awayPlayer8Throw2);
        EditText awayPlayer8Throw3 = getView().findViewById(R.id.awayPlayer8Throw3);
        EditText awayPlayer8Throw4 = getView().findViewById(R.id.awayPlayer8Throw4);


        //Adding scores to arraylist and throws object

        ArrayList<Throws> th = new ArrayList<>();
        System.out.println("Testi");
        th.add(new Throws(1, homePlayer1.getText().toString()
                , homePlayer1Throw1.getText().toString()
                , homePlayer1Throw2.getText().toString()
                , homePlayer1Throw3.getText().toString()
                , homePlayer1Throw4.getText().toString()));
        th.add(new Throws(2, homePlayer2.getText().toString()
                , homePlayer2Throw1.getText().toString()
                , homePlayer2Throw2.getText().toString()
                , homePlayer2Throw3.getText().toString()
                , homePlayer2Throw4.getText().toString()));
        th.add(new Throws(3, homePlayer3.getText().toString()
                , homePlayer3Throw1.getText().toString()
                , homePlayer3Throw2.getText().toString()
                , homePlayer3Throw3.getText().toString()
                , homePlayer3Throw4.getText().toString()));
        th.add(new Throws(4, homePlayer4.getText().toString()
                , homePlayer4Throw1.getText().toString()
                , homePlayer4Throw2.getText().toString()
                , homePlayer4Throw3.getText().toString()
                , homePlayer4Throw4.getText().toString()));
        th.add(new Throws(5, homePlayer5.getText().toString()
                , homePlayer5Throw1.getText().toString()
                , homePlayer5Throw2.getText().toString()
                , homePlayer5Throw3.getText().toString()
                , homePlayer5Throw4.getText().toString()));
        th.add(new Throws(6, homePlayer6.getText().toString()
                , homePlayer6Throw1.getText().toString()
                , homePlayer6Throw2.getText().toString()
                , homePlayer6Throw3.getText().toString()
                , homePlayer6Throw4.getText().toString()));
        th.add(new Throws(7, homePlayer7.getText().toString()
                , homePlayer7Throw1.getText().toString()
                , homePlayer7Throw2.getText().toString()
                , homePlayer7Throw3.getText().toString()
                , homePlayer7Throw4.getText().toString()));
        th.add(new Throws(8, homePlayer8.getText().toString()
                , homePlayer8Throw1.getText().toString()
                , homePlayer8Throw2.getText().toString()
                , homePlayer8Throw3.getText().toString()
                , homePlayer8Throw4.getText().toString()));

        th.add(new Throws(9, awayPlayer1.getText().toString()
                , awayPlayer1Throw1.getText().toString()
                , awayPlayer1Throw2.getText().toString()
                , awayPlayer1Throw3.getText().toString()
                , awayPlayer1Throw4.getText().toString()));
        th.add(new Throws(10, awayPlayer2.getText().toString()
                , awayPlayer2Throw1.getText().toString()
                , awayPlayer2Throw2.getText().toString()
                , awayPlayer2Throw3.getText().toString()
                , awayPlayer2Throw4.getText().toString()));
        th.add(new Throws(11, awayPlayer3.getText().toString()
                , awayPlayer3Throw1.getText().toString()
                , awayPlayer3Throw2.getText().toString()
                , awayPlayer3Throw3.getText().toString()
                , awayPlayer3Throw4.getText().toString()));
        th.add(new Throws(12, awayPlayer4.getText().toString()
                , awayPlayer4Throw1.getText().toString()
                , awayPlayer4Throw2.getText().toString()
                , awayPlayer4Throw3.getText().toString()
                , awayPlayer4Throw4.getText().toString()));
        th.add(new Throws(13, awayPlayer5.getText().toString()
                , awayPlayer5Throw1.getText().toString()
                , awayPlayer5Throw2.getText().toString()
                , awayPlayer5Throw3.getText().toString()
                , awayPlayer5Throw4.getText().toString()));
        th.add(new Throws(14, awayPlayer6.getText().toString()
                , awayPlayer6Throw1.getText().toString()
                , awayPlayer6Throw2.getText().toString()
                , awayPlayer6Throw3.getText().toString()
                , awayPlayer6Throw4.getText().toString()));
        th.add(new Throws(15, awayPlayer7.getText().toString()
                , awayPlayer7Throw1.getText().toString()
                , awayPlayer7Throw2.getText().toString()
                , awayPlayer7Throw3.getText().toString()
                , awayPlayer7Throw4.getText().toString()));
        th.add(new Throws(16, awayPlayer8.getText().toString()
                , awayPlayer8Throw1.getText().toString()
                , awayPlayer8Throw2.getText().toString()
                , awayPlayer8Throw3.getText().toString()
                , awayPlayer8Throw4.getText().toString()));


        //adding match detail to match object


        for (int i = 0; i < matches.size(); i++) {
            if (id.equals(matches.get(i).getId())) {
                matches.set(i,new Match(id,homeTeam.getText().toString()
                        ,awayTeam.getText().toString(),roundOneScoreHome.getText().toString()
                        ,roundTwoScoreHome.getText().toString()
                        ,homeScore.getText().toString()
                        ,roundOneScoreAway.getText().toString()
                        ,roundTwoScoreAway.getText().toString()
                        ,awayScore.getText().toString(), gameDate.getText().toString()));
                jwr.writeIndex(matches,currentUser);
                jwr.write(th,id,currentUser);
                return;
            }
        }
        matches.add(new Match(id,homeTeam.getText().toString()+"-"
                ,awayTeam.getText().toString(),roundOneScoreHome.getText().toString()
                ,roundTwoScoreHome.getText().toString()
                ,homeScore.getText().toString()
                ,roundOneScoreAway.getText().toString()
                ,roundTwoScoreAway.getText().toString()
                ,awayScore.getText().toString(), gameDate.getText().toString()));
        jwr.writeIndex(matches, currentUser);
        jwr.write(th,id, currentUser);



    }

    public void readGame(String id, String homeTeam2, String awayTeam2, String roundOneScoreHome2
            ,String roundTwoScoreHome2, String FinalScoreHome
            ,String roundOneScoreAway2, String roundTwoScoreAway2
            ,String finalScoreAway, String date) {

        //Adding variables

        EditText gameDate = getView().findViewById(R.id.editGameDate);

        EditText homeTeam = getView().findViewById(R.id.editTextTextPersonName);

        EditText roundOneScoreHome = getView().findViewById(R.id.setFirstRoundHomeScore);
        EditText roundTwoScoreHome = getView().findViewById(R.id.setSecondRoundHomeScore);
        EditText homeScore = getView().findViewById(R.id.setHomeFinalScore);

        EditText awayTeam = getView().findViewById(R.id.editTextTextPersonName2);
        EditText roundOneScoreAway = getView().findViewById(R.id.setFirstRoundAwayScore);
        EditText roundTwoScoreAway = getView().findViewById(R.id.setSecondRoundAwayScore);
        EditText awayScore = getView().findViewById(R.id.setAwayScore);

        EditText homePlayer1 = getView().findViewById(R.id.setfirstPlayer);

        EditText homePlayer1Throw1 = getView().findViewById(R.id.player1Throw1);
        EditText homePlayer1Throw2 = getView().findViewById(R.id.player1Throw2);
        EditText homePlayer1Throw3 = getView().findViewById(R.id.player1Throw3);
        EditText homePlayer1Throw4 = getView().findViewById(R.id.player1Throw4);

        EditText homePlayer2 = getView().findViewById(R.id.setsecondPlayer);

        EditText homePlayer2Throw1 = getView().findViewById(R.id.player2Throw5);
        EditText homePlayer2Throw2 = getView().findViewById(R.id.player2Throw2);
        EditText homePlayer2Throw3 = getView().findViewById(R.id.player2Throw3);
        EditText homePlayer2Throw4 = getView().findViewById(R.id.player2Throw4);

        EditText homePlayer3 = getView().findViewById(R.id.setthirdPlayer);

        EditText homePlayer3Throw1 = getView().findViewById(R.id.player3Throw1);
        EditText homePlayer3Throw2 = getView().findViewById(R.id.player3Throw2);
        EditText homePlayer3Throw3 = getView().findViewById(R.id.player3Throw3);
        EditText homePlayer3Throw4 = getView().findViewById(R.id.player3Throw4);

        EditText homePlayer4 = getView().findViewById(R.id.setFourthPlayer);

        EditText homePlayer4Throw1 = getView().findViewById(R.id.player4Throw1);
        EditText homePlayer4Throw2 = getView().findViewById(R.id.player4Throw2);
        EditText homePlayer4Throw3 = getView().findViewById(R.id.player4Throw3);
        EditText homePlayer4Throw4 = getView().findViewById(R.id.player4Throw4);

        EditText homePlayer5 = getView().findViewById(R.id.setfirstPlayer2);

        EditText homePlayer5Throw1 = getView().findViewById(R.id.player1Throw5);
        EditText homePlayer5Throw2 = getView().findViewById(R.id.player1Throw6);
        EditText homePlayer5Throw3 = getView().findViewById(R.id.player1Throw7);
        EditText homePlayer5Throw4 = getView().findViewById(R.id.player1Throw8);

        EditText homePlayer6 = getView().findViewById(R.id.setsecondPlayer2);

        EditText homePlayer6Throw1 = getView().findViewById(R.id.player2Throw6);
        EditText homePlayer6Throw2 = getView().findViewById(R.id.player2Throw7);
        EditText homePlayer6Throw3 = getView().findViewById(R.id.player2Throw8);
        EditText homePlayer6Throw4 = getView().findViewById(R.id.player2Throw);

        EditText homePlayer7 = getView().findViewById(R.id.setthirdPlayer2);

        EditText homePlayer7Throw1 = getView().findViewById(R.id.player3Throw5);
        EditText homePlayer7Throw2 = getView().findViewById(R.id.player3Throw6);
        EditText homePlayer7Throw3 = getView().findViewById(R.id.player3Throw7);
        EditText homePlayer7Throw4 = getView().findViewById(R.id.player3Throw8);

        EditText homePlayer8 = getView().findViewById(R.id.setFourthPlayer2);

        EditText homePlayer8Throw1 = getView().findViewById(R.id.player4Throw5);
        EditText homePlayer8Throw2 = getView().findViewById(R.id.player4Throw6);
        EditText homePlayer8Throw3 = getView().findViewById(R.id.player4Throw7);
        EditText homePlayer8Throw4 = getView().findViewById(R.id.player4Throw8);

        EditText awayPlayer1 = getView().findViewById(R.id.setAwayPlayer);

        EditText awayPlayer1Throw1 = getView().findViewById(R.id.awayPlayer1Throw1);
        EditText awayPlayer1Throw2 = getView().findViewById(R.id.awayPlayer1Throw2);
        EditText awayPlayer1Throw3 = getView().findViewById(R.id.awayPlayer1Throw3);
        EditText awayPlayer1Throw4 = getView().findViewById(R.id.awayPlayer1Throw4);

        EditText awayPlayer2 = getView().findViewById(R.id.setAwayPlayer2);

        EditText awayPlayer2Throw1 = getView().findViewById(R.id.awayPlayer2Throw1);
        EditText awayPlayer2Throw2 = getView().findViewById(R.id.awayPlayer2Throw2);
        EditText awayPlayer2Throw3 = getView().findViewById(R.id.awayPlayer2Throw3);
        EditText awayPlayer2Throw4 = getView().findViewById(R.id.awayPlayer2Throw4);

        EditText awayPlayer3 = getView().findViewById(R.id.setAwayPlayer3);

        EditText awayPlayer3Throw1 = getView().findViewById(R.id.awayPlayer3Throw1);
        EditText awayPlayer3Throw2 = getView().findViewById(R.id.awayPlayer3Throw2);
        EditText awayPlayer3Throw3 = getView().findViewById(R.id.awayPlayer3Throw3);
        EditText awayPlayer3Throw4 = getView().findViewById(R.id.awayPlayer3Throw4);

        EditText awayPlayer4 = getView().findViewById(R.id.setAwayPlayer4);

        EditText awayPlayer4Throw1 = getView().findViewById(R.id.awayPlayer4Throw1);
        EditText awayPlayer4Throw2 = getView().findViewById(R.id.awayPlayer4Throw2);
        EditText awayPlayer4Throw3 = getView().findViewById(R.id.awayPlayer4Throw3);
        EditText awayPlayer4Throw4 = getView().findViewById(R.id.awayPlayer4Throw4);


        EditText awayPlayer5 = getView().findViewById(R.id.setAwayPlayer5);

        EditText awayPlayer5Throw1 = getView().findViewById(R.id.awayPlayer5Throw1);
        EditText awayPlayer5Throw2 = getView().findViewById(R.id.awayPlayer5Throw2);
        EditText awayPlayer5Throw3 = getView().findViewById(R.id.awayPlayer5Throw3);
        EditText awayPlayer5Throw4 = getView().findViewById(R.id.awayPlayer5Throw4);

        EditText awayPlayer6 = getView().findViewById(R.id.setAwayPlayer6);

        EditText awayPlayer6Throw1 = getView().findViewById(R.id.awayPlayer6Throw1);
        EditText awayPlayer6Throw2 = getView().findViewById(R.id.awayPlayer6Throw2);
        EditText awayPlayer6Throw3 = getView().findViewById(R.id.awayPlayer6Throw3);
        EditText awayPlayer6Throw4 = getView().findViewById(R.id.awayPlayer6Throw4);

        EditText awayPlayer7 = getView().findViewById(R.id.setAwayPlayer7);

        EditText awayPlayer7Throw1 = getView().findViewById(R.id.awayPlayer7Throw1);
        EditText awayPlayer7Throw2 = getView().findViewById(R.id.awayPlayer7Throw2);
        EditText awayPlayer7Throw3 = getView().findViewById(R.id.awayPlayer7Throw3);
        EditText awayPlayer7Throw4 = getView().findViewById(R.id.awayPlayer7Throw4);

        EditText awayPlayer8 = getView().findViewById(R.id.setAwayPlayer8);

        EditText awayPlayer8Throw1 = getView().findViewById(R.id.awayPlayer8Throw1);
        EditText awayPlayer8Throw2 = getView().findViewById(R.id.awayPlayer8Throw2);
        EditText awayPlayer8Throw3 = getView().findViewById(R.id.awayPlayer8Throw3);
        EditText awayPlayer8Throw4 = getView().findViewById(R.id.awayPlayer8Throw4);
        TextView totalFirst = getView().findViewById(R.id.textViewTotalFirst);
        TextView total2 = getView().findViewById(R.id.textTotal2);
        TextView total3 = getView().findViewById(R.id.textTotal3);
        TextView total4 = getView().findViewById(R.id.textTotal4);
        TextView total5 = getView().findViewById(R.id.textTotal5);
        TextView total6 = getView().findViewById(R.id.textTotal6);
        TextView total7 = getView().findViewById(R.id.textTotal7);
        TextView total8 = getView().findViewById(R.id.textTotal8);
        TextView total9 = getView().findViewById(R.id.textTotal9);
        TextView total10 = getView().findViewById(R.id.textTotal10);
        TextView total11 = getView().findViewById(R.id.textTotal11);
        TextView total12 = getView().findViewById(R.id.textTotal12);
        TextView total13 = getView().findViewById(R.id.textTotal13);
        TextView total14 = getView().findViewById(R.id.textTotal14);
        TextView total15 = getView().findViewById(R.id.textTotal15);
        TextView total16 = getView().findViewById(R.id.textTotal16);


        Integer t1,t2,t3,t4;
        Integer total;

        //Setting game detail from index.JSON which contains match data


        ArrayList<Throws> th = jwr.read(id, currentUser);

        homeTeam.setText(homeTeam2);
        awayTeam.setText(awayTeam2);
        roundOneScoreHome.setText(roundOneScoreHome2);
        roundTwoScoreHome.setText(roundTwoScoreHome2);
        homeScore.setText(FinalScoreHome);
        roundOneScoreAway.setText(roundOneScoreAway2);
        roundTwoScoreAway.setText(roundTwoScoreAway2);
        awayScore.setText(finalScoreAway);
        gameDate.setText(date);



        homePlayer1.setText(th.get(0).getPlayer());

        homePlayer1Throw1.setText(th.get(0).getScoreFirst());
        homePlayer1Throw2.setText(th.get(0).getScoreSecond());
        homePlayer1Throw3.setText(th.get(0).getScoreThird());
        homePlayer1Throw4.setText(th.get(0).getScoreFourth());

        //Calculating total score for every player

        try{t1 = parseInt(th.get(0).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(0).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(0).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(0).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        totalFirst.setText(total.toString());

        try{t1 = parseInt(th.get(1).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(1).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(1).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(1).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total2.setText(total.toString());

        try{t1 = parseInt(th.get(2).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(2).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(2).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(2).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total3.setText(total.toString());

        try{t1 = parseInt(th.get(3).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(3).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(3).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(3).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total4.setText(total.toString());

        try{t1 = parseInt(th.get(4).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(4).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(4).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(4).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total5.setText(total.toString());

        try{t1 = parseInt(th.get(5).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(5).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(5).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(5).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total6.setText(total.toString());

        try{t1 = parseInt(th.get(6).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(6).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(6).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(6).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total7.setText(total.toString());

        try{t1 = parseInt(th.get(7).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(7).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(7).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(7).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total8.setText(total.toString());

        try{t1 = parseInt(th.get(8).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(8).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(8).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(8).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total9.setText(total.toString());

        try{t1 = parseInt(th.get(9).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(9).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(9).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(9).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total10.setText(total.toString());

        try{t1 = parseInt(th.get(10).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(10).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(10).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(10).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total11.setText(total.toString());

        try{t1 = parseInt(th.get(11).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(11).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(11).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(11).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total12.setText(total.toString());

        try{t1 = parseInt(th.get(12).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(12).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(12).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(12).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total13.setText(total.toString());

        try{t1 = parseInt(th.get(13).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(13).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(13).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(13).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total14.setText(total.toString());

        try{t1 = parseInt(th.get(14).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(14).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(14).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(14).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total15.setText(total.toString());

        try{t1 = parseInt(th.get(15).getScoreFirst());}catch (NumberFormatException e){ t1 = 0;}
        try{t2 = parseInt(th.get(15).getScoreSecond());}catch (NumberFormatException e){t2 = 0;}
        try{t3 = parseInt(th.get(15).getScoreThird());}catch (NumberFormatException e){t3 = 0;}
        try{t4 = parseInt(th.get(15).getScoreFourth());}catch (NumberFormatException e){t4 = 0;}

        total = t1+t2+t3+t4;
        total16.setText(total.toString());






        //Reading score

        homePlayer2Throw1.setText(th.get(1).getScoreFirst());
        homePlayer2Throw2.setText(th.get(1).getScoreSecond());
        homePlayer2Throw3.setText(th.get(1).getScoreThird());
        homePlayer2Throw4.setText(th.get(1).getScoreFourth());

        homePlayer3.setText(th.get(2).getPlayer());

        homePlayer3Throw1.setText(th.get(2).getScoreFirst());
        homePlayer3Throw2.setText(th.get(2).getScoreSecond());
        homePlayer3Throw3.setText(th.get(2).getScoreThird());
        homePlayer3Throw4.setText(th.get(2).getScoreFourth());

        homePlayer4.setText(th.get(3).getPlayer());

        homePlayer4Throw1.setText(th.get(3).getScoreFirst());
        homePlayer4Throw2.setText(th.get(3).getScoreSecond());
        homePlayer4Throw3.setText(th.get(3).getScoreThird());
        homePlayer4Throw4.setText(th.get(3).getScoreFourth());

        homePlayer5.setText(th.get(4).getPlayer());

        homePlayer5Throw1.setText(th.get(4).getScoreFirst());
        homePlayer5Throw2.setText(th.get(4).getScoreSecond());
        homePlayer5Throw3.setText(th.get(4).getScoreThird());
        homePlayer5Throw4.setText(th.get(4).getScoreFourth());


        homePlayer6.setText(th.get(5).getPlayer());

        homePlayer6Throw1.setText(th.get(5).getScoreFirst());
        homePlayer6Throw2.setText(th.get(5).getScoreSecond());
        homePlayer6Throw3.setText(th.get(5).getScoreThird());
        homePlayer6Throw4.setText(th.get(5).getScoreFourth());

        homePlayer7.setText(th.get(6).getPlayer());

        homePlayer7Throw1.setText(th.get(6).getScoreFirst());
        homePlayer7Throw2.setText(th.get(6).getScoreSecond());
        homePlayer7Throw3.setText(th.get(6).getScoreThird());
        homePlayer7Throw4.setText(th.get(6).getScoreFourth());

        homePlayer8.setText(th.get(7).getPlayer());

        homePlayer8Throw1.setText(th.get(7).getScoreFirst());
        homePlayer8Throw2.setText(th.get(7).getScoreSecond());
        homePlayer8Throw3.setText(th.get(7).getScoreThird());
        homePlayer8Throw4.setText(th.get(7).getScoreFourth());

        awayPlayer1.setText(th.get(8).getPlayer());

        awayPlayer1Throw1.setText(th.get(8).getScoreFirst());
        awayPlayer1Throw2.setText(th.get(8).getScoreSecond());
        awayPlayer1Throw3.setText(th.get(8).getScoreThird());
        awayPlayer1Throw4.setText(th.get(8).getScoreFourth());

        awayPlayer2.setText(th.get(9).getPlayer());

        awayPlayer2Throw1.setText(th.get(9).getScoreFirst());
        awayPlayer2Throw2.setText(th.get(9).getScoreSecond());
        awayPlayer2Throw3.setText(th.get(9).getScoreThird());
        awayPlayer2Throw4.setText(th.get(9).getScoreFourth());

        awayPlayer3.setText(th.get(10).getPlayer());

        awayPlayer3Throw1.setText(th.get(10).getScoreFirst());
        awayPlayer3Throw2.setText(th.get(10).getScoreSecond());
        awayPlayer3Throw3.setText(th.get(10).getScoreThird());
        awayPlayer3Throw4.setText(th.get(10).getScoreFourth());


        awayPlayer4.setText(th.get(11).getPlayer());

        awayPlayer4Throw1.setText(th.get(11).getScoreFirst());
        awayPlayer4Throw2.setText(th.get(11).getScoreSecond());
        awayPlayer4Throw3.setText(th.get(11).getScoreThird());
        awayPlayer4Throw4.setText(th.get(11).getScoreFourth());

        awayPlayer5.setText(th.get(12).getPlayer());

        awayPlayer5Throw1.setText(th.get(12).getScoreFirst());
        awayPlayer5Throw2.setText(th.get(12).getScoreSecond());
        awayPlayer5Throw3.setText(th.get(12).getScoreThird());
        awayPlayer5Throw4.setText(th.get(12).getScoreFourth());

        awayPlayer6.setText(th.get(13).getPlayer());

        awayPlayer6Throw1.setText(th.get(13).getScoreFirst());
        awayPlayer6Throw2.setText(th.get(13).getScoreSecond());
        awayPlayer6Throw3.setText(th.get(13).getScoreThird());
        awayPlayer6Throw4.setText(th.get(13).getScoreFourth());

        awayPlayer7.setText(th.get(14).getPlayer());

        awayPlayer7Throw1.setText(th.get(14).getScoreFirst());
        awayPlayer7Throw2.setText(th.get(14).getScoreSecond());
        awayPlayer7Throw3.setText(th.get(14).getScoreThird());
        awayPlayer7Throw4.setText(th.get(14).getScoreFourth());

        awayPlayer8.setText(th.get(15).getPlayer());

        awayPlayer8Throw1.setText(th.get(15).getScoreFirst());
        awayPlayer8Throw2.setText(th.get(15).getScoreSecond());
        awayPlayer8Throw3.setText(th.get(15).getScoreThird());
        awayPlayer8Throw4.setText(th.get(15).getScoreFourth());
    }

    public void removeMatch(ArrayList<Match> matches, String id){
        for (int i = 0; i < matches.size(); i++) {
            if (id.equals(matches.get(i).getId())) {
                matches.remove(i);
                jwr.writeIndex(matches,currentUser);
                jwr.fileDetele(id, currentUser);
                return;
            }
        }

    }


}


