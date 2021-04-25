package com.juuh.ht;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Context;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("settings", MODE_PRIVATE);
        currentUser = preferences.getString("currentUser", null);
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
                    //Check if user is logged in, if not redirect to login screen
                    currentUser = preferences.getString("currentUser", null);
                    if (currentUser != null){
                        selectedFragment = new Scorecard_fragment();
                    } else {
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("selectedFragment", "Scorecard");
                        selectedFragment = new LoginFragment();
                        selectedFragment.setArguments(bundle1);
                    }

                    break;
                case R.id.nav_profile:
                    //Check if user is logged in, if not redirect to login screen
                    currentUser = preferences.getString("currentUser", null);
                    if (currentUser != null){
                        selectedFragment = new ProfileFragment();
                    } else {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("selectedFragment", "Profile");
                        selectedFragment = new LoginFragment();
                        selectedFragment.setArguments(bundle2);
                    }
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