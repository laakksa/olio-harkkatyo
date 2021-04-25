package com.juuh.ht;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MatchesFragment extends Fragment {
    View v;
    Button b;
    RecyclerView matchRecycler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_matches, container, false);
        EntryManager entryManager = EntryManager.getInstance();

        //Set up RecyclerView from matches arraylist using custom adapter MatchesAdapter
        matchRecycler = v.findViewById(R.id.matchesRecyclerView);
        matchRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        MatchesAdapter adapter = new MatchesAdapter(getContext(), entryManager.getMatchesList());

        //Open match info fragment on list item click
        adapter.setOnEntryClickListener(new MatchesAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {
                Fragment fragment = new MatchInfoFragment();
                Bundle args = new Bundle();

                //Send position of list item to fragment so that correct match info can be displayed
                args.putInt("position", position);
                fragment.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.frag_container, fragment);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        matchRecycler.setAdapter(adapter);
        b = v.findViewById(R.id.graphbutton);
        //Open Graph fragment on button click
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new GraphFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.frag_container, fragment);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
