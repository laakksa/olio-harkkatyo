package com.juuh.ht;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Scorecard_fragment extends Fragment {

    JSONWriteAndRead jwr = new JSONWriteAndRead();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_scorecard,container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Button button = (Button)view.findViewById(R.id.button2);
        Button button2 = (Button)view.findViewById(R.id.button);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readGame();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGame();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveGame() {

        EditText homePlayer1 = getView().findViewById(R.id.setfirstPlayer);

        EditText homePlayer1Throw1 = getView().findViewById(R.id.player1Throw1);
        EditText homePlayer1Throw2 = getView().findViewById(R.id.player1Throw2);
        EditText homePlayer1Throw3 = getView().findViewById(R.id.player1Throw3);
        EditText homePlayer1Throw4 = getView().findViewById(R.id.player1Throw4);

        EditText homePlayer2 = getView().findViewById(R.id.setsecondPlayer);

        EditText homePlayer2Throw1 = getView().findViewById(R.id.player2Throw);
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

        EditText homePlayer6Throw1 = getView().findViewById(R.id.player2Throw5);
        EditText homePlayer6Throw2 = getView().findViewById(R.id.player2Throw6);
        EditText homePlayer6Throw3 = getView().findViewById(R.id.player2Throw7);
        EditText homePlayer6Throw4 = getView().findViewById(R.id.player2Throw8);

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




        ArrayList<Throws> th = new ArrayList<>();
        System.out.println("Testi");
        th.add(new Throws(1, homePlayer1.getText().toString(), homePlayer1Throw1.getText().toString(), homePlayer1Throw2.getText().toString(), homePlayer1Throw3.getText().toString(), homePlayer1Throw4.getText().toString()));
        th.add(new Throws(2, homePlayer2.getText().toString(), homePlayer2Throw1.getText().toString(), homePlayer2Throw2.getText().toString(), homePlayer2Throw3.getText().toString(), homePlayer2Throw4.getText().toString()));
        th.add(new Throws(3, homePlayer3.getText().toString(), homePlayer3Throw1.getText().toString(), homePlayer3Throw2.getText().toString(), homePlayer3Throw3.getText().toString(), homePlayer3Throw4.getText().toString()));
        th.add(new Throws(4, homePlayer4.getText().toString(), homePlayer4Throw1.getText().toString(), homePlayer4Throw2.getText().toString(), homePlayer4Throw3.getText().toString(), homePlayer4Throw4.getText().toString()));
        th.add(new Throws(5, homePlayer5.getText().toString(), homePlayer5Throw1.getText().toString(), homePlayer5Throw2.getText().toString(), homePlayer5Throw3.getText().toString(), homePlayer5Throw4.getText().toString()));
        th.add(new Throws(6, homePlayer6.getText().toString(), homePlayer6Throw1.getText().toString(), homePlayer6Throw2.getText().toString(), homePlayer6Throw3.getText().toString(), homePlayer6Throw4.getText().toString()));
        th.add(new Throws(7, homePlayer7.getText().toString(), homePlayer7Throw1.getText().toString(), homePlayer7Throw2.getText().toString(), homePlayer7Throw3.getText().toString(), homePlayer7Throw4.getText().toString()));
        th.add(new Throws(8, homePlayer8.getText().toString(), homePlayer8Throw1.getText().toString(), homePlayer8Throw2.getText().toString(), homePlayer8Throw3.getText().toString(), homePlayer8Throw4.getText().toString()));

        th.add(new Throws(9, awayPlayer1.getText().toString(), awayPlayer1Throw1.getText().toString(), awayPlayer1Throw2.getText().toString(), awayPlayer1Throw3.getText().toString(), awayPlayer1Throw4.getText().toString()));
        th.add(new Throws(10, awayPlayer2.getText().toString(), awayPlayer2Throw1.getText().toString(), awayPlayer2Throw2.getText().toString(), awayPlayer2Throw3.getText().toString(), awayPlayer2Throw4.getText().toString()));
        th.add(new Throws(11, awayPlayer3.getText().toString(), awayPlayer3Throw1.getText().toString(), awayPlayer3Throw2.getText().toString(), awayPlayer3Throw3.getText().toString(), awayPlayer3Throw4.getText().toString()));
        th.add(new Throws(12, awayPlayer4.getText().toString(), awayPlayer4Throw1.getText().toString(), awayPlayer4Throw2.getText().toString(), awayPlayer4Throw3.getText().toString(), awayPlayer4Throw4.getText().toString()));
        th.add(new Throws(13, awayPlayer5.getText().toString(), awayPlayer5Throw1.getText().toString(), awayPlayer5Throw2.getText().toString(), awayPlayer5Throw3.getText().toString(), awayPlayer5Throw4.getText().toString()));
        th.add(new Throws(14, awayPlayer6.getText().toString(), awayPlayer6Throw1.getText().toString(), awayPlayer6Throw2.getText().toString(), awayPlayer6Throw3.getText().toString(), awayPlayer6Throw4.getText().toString()));
        th.add(new Throws(15, awayPlayer7.getText().toString(), awayPlayer7Throw1.getText().toString(), awayPlayer7Throw2.getText().toString(), awayPlayer7Throw3.getText().toString(), awayPlayer7Throw4.getText().toString()));
        th.add(new Throws(16, awayPlayer8.getText().toString(), awayPlayer8Throw1.getText().toString(), awayPlayer8Throw2.getText().toString(), awayPlayer8Throw3.getText().toString(), awayPlayer8Throw4.getText().toString()));




        for (int i = 0; i < 16; i++) {
            System.out.println((i + 1) + ". Name: " + th.get(i).getPlayer());


        }

        Context context = getActivity();

        jwr.write(context,th);

    }

    public void readGame() {
        EditText homePlayer1 = getView().findViewById(R.id.setfirstPlayer);

        EditText homePlayer1Throw1 = getView().findViewById(R.id.player1Throw1);
        EditText homePlayer1Throw2 = getView().findViewById(R.id.player1Throw2);
        EditText homePlayer1Throw3 = getView().findViewById(R.id.player1Throw3);
        EditText homePlayer1Throw4 = getView().findViewById(R.id.player1Throw4);

        EditText homePlayer2 = getView().findViewById(R.id.setsecondPlayer);

        EditText homePlayer2Throw1 = getView().findViewById(R.id.player2Throw);
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

        EditText homePlayer6Throw1 = getView().findViewById(R.id.player2Throw5);
        EditText homePlayer6Throw2 = getView().findViewById(R.id.player2Throw6);
        EditText homePlayer6Throw3 = getView().findViewById(R.id.player2Throw7);
        EditText homePlayer6Throw4 = getView().findViewById(R.id.player2Throw8);

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

        ArrayList<Throws> th = jwr.read();

        homePlayer1.setText(th.get(0).getPlayer());

        homePlayer1Throw1.setText(th.get(0).getScoreFirst());
        homePlayer1Throw2.setText(th.get(0).getScoreSecond());
        homePlayer1Throw3.setText(th.get(0).getScoreThird());
        homePlayer1Throw4.setText(th.get(0).getScoreFourth());

        homePlayer2.setText(th.get(1).getPlayer());

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

}


