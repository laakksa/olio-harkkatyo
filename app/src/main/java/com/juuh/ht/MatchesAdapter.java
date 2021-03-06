package com.juuh.ht;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
//This adapter creates RecyclerView for MatchesFragment from arraylist of MatchEntries
public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder> {
    private ArrayList<MatchEntry> matches;
    Context context;
    public MatchesAdapter(Context c, ArrayList<MatchEntry> arrayList){
        context = c;
        matches = arrayList;
    }
    @NonNull
    @Override
    //This method creates ViewHolder for Matches from match_list_item layout file
    public MatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_list_item, parent,
                false);
        return new MatchesViewHolder(v);
    }

    @Override
    //This method binds wanted attributes from MatchEntry to ViewHolder items
    public void onBindViewHolder(@NonNull MatchesViewHolder holder, int position) {
        MatchEntry match = matches.get(position);
        LocalDateTime date = LocalDateTime.parse(match.getDatetime(),
                DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        //Set up list item from matches ArrayList
        holder.date.setText(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        holder.time.setText(date.format(DateTimeFormatter.ofPattern("HH:mm")));
        holder.hometeam.setText(String.valueOf(match.home_abbr));
        holder.awayteam.setText(String.valueOf(match.away_abbr));
        holder.homescore.setText(String.valueOf(match.home_score));
        holder.awayscore.setText(String.valueOf(match.away_score));
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    //This method sets up ViewHolder for MatchEntries
    public class MatchesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView date, hometeam, awayteam, awayscore, homescore, time;
        public MatchesViewHolder(@NonNull View itemView) {
            super(itemView);
            //Set all fields for list items
            itemView.setOnClickListener(this);
            time = itemView.findViewById(R.id.match_time_textview);
            date = itemView.findViewById(R.id.match_date_textView);
            hometeam = itemView.findViewById(R.id.home_team_textView);
            awayteam = itemView.findViewById(R.id.away_team_textview);
            awayscore = itemView.findViewById(R.id.away_score_textview);
            homescore = itemView.findViewById(R.id.home_score_textView);
    }
    //Implement OnClickListener for list items
        @Override
        public void onClick(View v) {
            if (onEntryClickListener != null){
                onEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }
    }

    //On click listener interface for list items
    private OnEntryClickListener onEntryClickListener;
    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener listener){
        onEntryClickListener = listener;
    }
}
