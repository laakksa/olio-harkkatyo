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

//This fragment opens when clicking on leaderboard in bottom navigation bar
public class LeaderBoardFragment extends Fragment {
    View v;
    TableLayout table;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        table = v.findViewById(R.id.LeaderBoardTable);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EntryManager entryManager = EntryManager.getInstance();


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
        createTableLayout(entries);

    }
    //This method creates leaderboard tablelayout from Arraylist containing TeamEntries
    private void createTableLayout(ArrayList<TeamEntry> entries){
        //Create leaderboard table layout and populate it with data
        for (int i = 0; i < entries.size(); i++) {
            TableRow row = new TableRow(getContext());
            String position = i + 1 + ".";
            String abbreviation = entries.get(i).abbreviation;
            int matches_played = entries.get(i).matches_played;
            int wins = entries.get(i).wins;
            int ties = entries.get(i).ties;
            int losses = entries.get(i).losses;
            int points = entries.get(i).points;

            //Create and format textviews for cells in table and add them to row, then add
            // row to tablelayout
            TextView tvPosition = new TextView(getContext());
            tvPosition.setText(position);
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
            tvPosition.setBackgroundResource(R.drawable.border);
            tvPosition.setPadding(20, 10, 20, 10);
            tvPosition.setTextSize(15);
            tvName.setBackgroundResource(R.drawable.border);
            tvName.setPadding(20, 10, 20, 10);
            tvName.setTextSize(15);
            tvMatchesPlayed.setPadding(20, 10, 20, 10);
            tvMatchesPlayed.setBackgroundResource(R.drawable.border);
            tvMatchesPlayed.setTextSize(15);
            tvWins.setPadding(20, 10, 20, 10);
            tvWins.setBackgroundResource(R.drawable.border);
            tvWins.setTextSize(15);
            tvTies.setPadding(20, 10, 20, 10);
            tvTies.setBackgroundResource(R.drawable.border);
            tvTies.setTextSize(15);
            tvLosses.setPadding(20, 10, 20, 10);
            tvLosses.setBackgroundResource(R.drawable.border);
            tvLosses.setTextSize(15);
            tvPoints.setPadding(20, 10, 20, 10);
            tvPoints.setBackgroundResource(R.drawable.border);
            tvPoints.setTextSize(15);
            row.setWeightSum(100);
            tvPosition.setLayoutParams( new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 10f));
            row.addView(tvPosition);
            tvName.setLayoutParams( new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 50f));
            row.addView(tvName);
            tvMatchesPlayed.setLayoutParams( new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 8f));
            row.addView(tvMatchesPlayed);
            tvWins.setLayoutParams( new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 8f));
            row.addView(tvWins);
            tvTies.setLayoutParams( new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 8f));
            row.addView(tvTies);
            tvLosses.setLayoutParams( new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 8f));
            row.addView(tvLosses);
            tvPoints.setLayoutParams( new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 8f));
            row.addView(tvPoints);
            table.addView(row);



        }
    }

}
