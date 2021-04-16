package com.juuh.ht;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navBar = findViewById(R.id.bottom_navigation);
        navBar.setOnNavigationItemSelectedListener(navListener);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction
                    .TRANSIT_FRAGMENT_OPEN).replace(R.id.frag_container,
                    new LeaderBoardFragment()).commit();
        }

    }

    //Set up bottom navigation bar
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_leaderboard:
                    selectedFragment = new LeaderBoardFragment();
                    break;
                case R.id.nav_matches:
                    selectedFragment = new MatchesFragment();
                    break;
                case R.id.nav_scorecard:
                    break;
                case R.id.nav_profile:
                    break;
            }
            if (selectedFragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                    selectedFragment).commit();
            return true;
            } else{
                return false;
            }
        }
    };

}