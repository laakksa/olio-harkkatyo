package com.juuh.ht;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

public class LeaderBoardFragment extends Fragment {
    View v;
    TableLayout table;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        table = v.findViewById(R.id.LeaderBoardTable);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EntryManager entryManager = EntryManager.getInstance();

        //Get data from kyykkacom API
        String myurl = "https://kyykka.com/api/teams/?format=json";
        String result;
        JSONAsyncTask get = new JSONAsyncTask();
        try {
            result = get.execute(myurl).get();
            JSONArray ja = new JSONArray(result);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject j = (JSONObject) ja.get(i);
                String abbreviation = j.getString("abbreviation");
                String name = j.getString("name");
                int id = j.getInt("id");
                int wins = j.getInt("matches_won");
                int losses = j.getInt("matches_lost");
                int ties = j.getInt("matches_tie");
                int matches_played = j.getInt("matches_played");
                int points = j.getInt("points_total");
                TeamEntry teamEntry = new TeamEntry(id, name, wins, losses, ties, matches_played,
                        points, abbreviation);
                entryManager.addTeamEntry(teamEntry);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Sort teams list by points in descending order
        ArrayList<TeamEntry> entries = entryManager.getTeamsList();
        Collections.sort(entries, Collections.reverseOrder( new Comparator<TeamEntry>() {
            @Override
            public int compare(TeamEntry o1, TeamEntry o2) {
                if (o1.points > o2.points) return 1;
                if(o1.points < o2.points) return -1;
                return 0;
            }
        }));

        //Create leaderboard table layout and populate it with data
        for (int i = 0; i < entries.size(); i++) {
            TableRow row = new TableRow(getContext());
            String abbreviation = entries.get(i).abbreviation;
            int matches_played = entries.get(i).matches_played;
            int wins = entries.get(i).wins;
            int ties = entries.get(i).ties;
            int losses = entries.get(i).losses;
            int points = entries.get(i).points;

            TextView tvName = new TextView(getContext());
            tvName.setText(abbreviation);
            TextView tvMatchesPlayed = new TextView(getContext());
            tvMatchesPlayed.setText(String.valueOf(matches_played));
            TextView tvWins = new TextView(getContext());
            tvWins.setText(String.valueOf(wins));
            TextView tvTies = new TextView(getContext());
            tvTies.setText(String.valueOf(ties));
            TextView tvLosses = new TextView(getContext());
            tvLosses.setText(String.valueOf(losses));
            TextView tvPoints = new TextView(getContext());
            tvPoints.setText(String.valueOf(points));
            tvName.setBackgroundResource(R.drawable.border);
            tvName.setPadding(20, 0, 20, 0);
            tvMatchesPlayed.setPadding(20, 0, 20, 0);
            tvMatchesPlayed.setBackgroundResource(R.drawable.border);
            tvWins.setPadding(20, 0, 20, 0);
            tvWins.setBackgroundResource(R.drawable.border);
            tvTies.setPadding(20, 0, 20, 0);
            tvTies.setBackgroundResource(R.drawable.border);
            tvLosses.setPadding(20, 0, 20, 0);
            tvLosses.setBackgroundResource(R.drawable.border);
            tvPoints.setPadding(20, 0, 20, 0);
            tvPoints.setBackgroundResource(R.drawable.border);
            row.addView(tvName);
            row.addView(tvMatchesPlayed);
            row.addView(tvWins);
            row.addView(tvTies);
            row.addView(tvLosses);
            row.addView(tvPoints);
            table.addView(row);


        }
    }


}
