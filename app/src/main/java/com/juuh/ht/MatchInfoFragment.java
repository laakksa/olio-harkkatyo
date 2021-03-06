package com.juuh.ht;

import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public class MatchInfoFragment extends Fragment {
    View v;
    TextView textView, score, teams, datetv;
    @Nullable
    @Override
    //This fragment opens when clicking on match in matches view
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_match_info, container, false);
        textView = v.findViewById(R.id.matchinfo_temperature);
        teams = v.findViewById(R.id.matchinfo_teams);
        score = v.findViewById(R.id.matchinfo_score);
        datetv = v.findViewById(R.id.matchinfo_date);
        EntryManager entryManager = EntryManager.getInstance();
        ArrayList<MatchEntry> matches = entryManager.getMatchesList();


        //Get position of clicked list element
        Bundle args = getArguments();
        int position  = args.getInt("position", 0);

        //Display info of selected match
        MatchEntry selectedMatch = matches.get(position);
        String date = selectedMatch.getDatetime();
        LocalDateTime localDateTime = LocalDateTime.parse(date,
                DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        String datecut = date.substring(0, date.length()-6) + "Z";
        ArrayList<WeatherEntry> weatherEntries = entryManager.getWeather(datecut,datecut);
        String weather = weatherEntries.get(0).getTemp() + "\u2103";
        datetv.setText(localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        textView.setText(weather);
        String teamstring = selectedMatch.home_abbr + " - " + selectedMatch.away_abbr;
        teams.setText(teamstring);
        String scorestring = selectedMatch.home_score + " - " + selectedMatch.away_score;
        score.setText(scorestring);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
