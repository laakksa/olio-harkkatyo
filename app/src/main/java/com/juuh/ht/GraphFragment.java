package com.juuh.ht;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.github.mikephil.charting.utils.ViewPortHandler;

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
    AppCompatSpinner spinner;
    View v;
    EntryManager entryManager;
    TeamEntry selectedTeam;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_graph, container, false);
        chart = v.findViewById(R.id.graph);
        spinner = v.findViewById(R.id.graph_spinner);
        entryManager = EntryManager.getInstance();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Create spinner from all teams and update graph when team selected
        ArrayList<TeamEntry> teamEntries = entryManager.getTeamsList();
        ArrayList<String> teamNames = new ArrayList<>();
        for (TeamEntry t : teamEntries){
            teamNames.add(t.abbreviation);
        }
        selectedTeam = teamEntries.get(0);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, teamNames);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTeam = teamEntries.get(position);
                updateGraph(selectedTeam);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    //This method updates the graph to show data from selected team
    public void updateGraph(TeamEntry selectedTeam){
        int selectedTeamid = selectedTeam.id;
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<MatchEntry> matches = entryManager.getMatchesList();
        //Get datetime from first and last items in matches list
        String startdate = matches.get(0).getDatetime();
        String enddate = matches.get(matches.size()-1).getDatetime();
        LocalDateTime dateTimeStart = LocalDateTime.parse(startdate,
                DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(enddate,
                DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        //Calculate number of weeks between startdate and enddate
        long weeks = Duration.between(dateTimeStart, dateTimeEnd).toDays() / 6;
        String end = null;
        //Get data from fmi in one week size blocks due to API restrictions
        for (int i = 0; i < weeks + 1; i++){
            String start = dateTimeStart.plusDays(i*6)
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z";
            end = dateTimeStart.plusDays((i+1)*6)
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z";
            ArrayList<WeatherEntry> weatherEntries = entryManager.getWeather(start, end);
            //Add selected teams scores and temperatures of games to chart entries list
            for (MatchEntry match : matches) {
                System.out.println(selectedTeamid);
                if (match.away_id == selectedTeamid) {
                    LocalDateTime matchDate = LocalDateTime.parse(match.getDatetime(),
                            DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    String matchDatestring = matchDate.format(DateTimeFormatter
                            .ISO_LOCAL_DATE_TIME) + "Z";
                    if (!(match.away_score == null || match.home_score == null)) {
                        float away_score = Float.parseFloat(String.valueOf(match.away_score));
                        for (WeatherEntry w : weatherEntries) {
                            if (w.getTime().equals(matchDatestring)) {
                                //Add selected teams match score and corresponding temperature value
                                //to chart entries list
                                entries.add(new Entry(w.getTemp(), away_score));
                            }
                        }
                    }
                } else if (match.home_id == selectedTeamid){
                    LocalDateTime matchDate = LocalDateTime.parse(match.getDatetime(),
                            DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    String matchDatestring = matchDate.format(DateTimeFormatter
                            .ISO_LOCAL_DATE_TIME) + "Z";
                    if (!(match.away_score == null || match.home_score == null)) {
                        float home_score = Float.parseFloat(String.valueOf(match.home_score));
                        for (WeatherEntry w : weatherEntries) {
                            if (w.getTime().equals(matchDatestring)) {
                                //Add selected teams match score and corresponding temperature value
                                //to chart entries list
                                entries.add(new Entry(w.getTemp(), home_score));
                            }
                        }
                    }
                }
            }
        }

        //Create and format chart from data parsed above
        Collections.sort(entries, new EntryXComparator());
        ScatterDataSet dataSet = new ScatterDataSet(entries, "Score");
        dataSet.setColor(R.color.design_default_color_primary);
        dataSet.setValueTextSize(15);
        dataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
        dataSet.setScatterShapeSize(20);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) { //formats data points in chart to int
                return "" + ((int) value);
            }
        });
        ScatterData lineData = new ScatterData(dataSet);
        chart.setData(lineData);
        chart.setPinchZoom(true);
        chart.getDescription().setEnabled(false);
        chart.invalidate();
    }
}
