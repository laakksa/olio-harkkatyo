package com.juuh.ht;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class GraphFragment extends Fragment {
    ScatterChart chart;
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_graph, container, false);
        chart = v.findViewById(R.id.graph);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EntryManager entryManager = EntryManager.getInstance();
        ArrayList<MatchEntry> matches = entryManager.getMatchesList();
        String startdate = matches.get(0).getDatetime();
        String enddate = matches.get(matches.size()-1).getDatetime();
        ArrayList<Entry> entries = new ArrayList<>();
        LocalDateTime dateTimeStart = LocalDateTime.parse(startdate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(enddate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        long weeks =Duration.between(dateTimeStart, dateTimeEnd).toDays() / 6;
        String end = null;
        for (int i = 0; i < weeks + 1; i++){
            String start = dateTimeStart.plusDays(i*6).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z";
            end = dateTimeStart.plusDays((i+1)*6).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z";
            ArrayList<WeatherEntry> weatherEntries = entryManager.getWeather(start, end);
            System.out.println("weather " + weatherEntries.size());
            for (MatchEntry match : matches){
                LocalDateTime matchDate = LocalDateTime.parse(match.getDatetime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                String matchDatestring = matchDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z";
                if (!(match.away_score == null || match.home_score == null)) {
                    float score = Float.parseFloat(String.valueOf(Integer.sum(match.home_score, match.away_score)));
                    float home_score = Float.parseFloat(String.valueOf(match.home_score));
                    float away_score = Float.parseFloat(String.valueOf(match.away_score));
                    for (WeatherEntry w : weatherEntries) {
                        if (w.getTime().equals(matchDatestring)) {
                            entries.add(new Entry(w.getTemp(), home_score));
                            entries.add(new Entry(w.getTemp(), away_score));
                        }
                    }
                }
            }
        }
        /*ArrayList<WeatherEntry> weatherEntries = entryManager.getWeather(end, dateTimeEnd.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        for (MatchEntry m : matches){
            LocalDateTime matchDate = LocalDateTime.parse(m.getDatetime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            if (!(m.away_score == null || m.home_score == null)) {
                float score = Float.parseFloat(String.valueOf(Integer.sum(m.away_score, m.home_score)));
                for (WeatherEntry w : weatherEntries) {
                    if (w.getTime().equals(matchDate.toString())) {
                        entries.add(new Entry(score, w.getTemp()));
                    }
                }
            }
        }*/

        for (Entry e : entries){
            System.out.println(e.getX() + " " +  e.getY());
        }
        ScatterDataSet dataSet = new ScatterDataSet(entries, "Label");
        ScatterData lineData = new ScatterData(dataSet);
        chart.setData(lineData);
        chart.invalidate();
        System.out.println(entries.size());

    }
}
