package com.juuh.ht;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MatchesFragment extends Fragment {
    View v;
    Button b, b2;
    RecyclerView matchRecycler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_matches, container, false);
        EntryManager entryManager = EntryManager.getInstance();
        matchRecycler = v.findViewById(R.id.matchesRecyclerView);
        matchRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        MatchesAdapter adapter = new MatchesAdapter(getContext(), entryManager.getMatchesList());
        adapter.setOnEntryClickListener(new MatchesAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {
                System.out.println("HELLO");
                System.out.println("position: " + position);

            }
        });
        matchRecycler.setAdapter(adapter);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
