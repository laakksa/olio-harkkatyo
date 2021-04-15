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
import java.util.ArrayList;
import java.util.Date;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder> {
    private ArrayList<MatchEntry> matches;
    Context context;
    public MatchesAdapter(Context c, ArrayList<MatchEntry> arrayList){
        context = c;
        matches = arrayList;
    }
    @NonNull
    @Override
    public MatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_list_item, parent, false);
        return new MatchesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesViewHolder holder, int position) {
        MatchEntry match = matches.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String datestring = "null";
        try {
            datestring = sdf2.format(sdf.parse(match.getDatetime()));
        } catch (ParseException e){
            e.printStackTrace();
        }
        holder.date.setText(datestring);
        holder.hometeam.setText(String.valueOf(match.home_abbr));
        holder.awayteam.setText(String.valueOf(match.away_abbr));
        holder.homescore.setText(String.valueOf(match.home_score));
        holder.awayscore.setText(String.valueOf(match.away_score));
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class MatchesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView date, hometeam, awayteam, awayscore, homescore;
        public MatchesViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            date = itemView.findViewById(R.id.match_date_textView);
            hometeam = itemView.findViewById(R.id.home_team_textView);
            awayteam = itemView.findViewById(R.id.away_team_textview);
            awayscore = itemView.findViewById(R.id.away_score_textview);
            homescore = itemView.findViewById(R.id.home_score_textView);
    }

        @Override
        public void onClick(View v) {
            if (onEntryClickListener != null){
                onEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }
    }
    private OnEntryClickListener onEntryClickListener;
    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener listener){
        onEntryClickListener = listener;
    }
}
