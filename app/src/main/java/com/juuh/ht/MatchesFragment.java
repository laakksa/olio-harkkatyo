package com.juuh.ht;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MatchesFragment extends Fragment {
    View v;
    Button b;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_matches, container, false);
        b = v.findViewById(R.id.fragbutton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EntryManager entryManager = EntryManager.getInstance();
                ArrayList<WeatherEntry> entries = entryManager.getWeather("2021-01-01T12:00:00Z", "2021-01-05T12:00:00");
                for (WeatherEntry w : entries){
                    System.out.println(w.getTime());
                    System.out.println(w.getTemp());
                    System.out.println("VÄLI");
                }
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
