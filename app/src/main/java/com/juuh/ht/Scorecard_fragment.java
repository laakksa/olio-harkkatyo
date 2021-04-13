package com.juuh.ht;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Scorecard_fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_scorecard,container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Button button = (Button)view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGame();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    public void saveGame(){
        ArrayList<Throws> th = new ArrayList<>();
        System.out.println("Testi");
        EditText inputText = getView().findViewById(R.id.setfirstPlayer);
        System.out.println(inputText.getText().toString());
        th.add(new Throws(1, getView().findViewById(R.id.setfirstPlayer).toString(), getView().findViewById(R.id.player1Throw1).toString(), getView().findViewById(R.id.player1Throw2).toString(), getView().findViewById(R.id.player1Throw3).toString(), getView().findViewById(R.id.player1Throw4).toString()));
        System.out.println(th.get(0).getPlayer());


    }
}


