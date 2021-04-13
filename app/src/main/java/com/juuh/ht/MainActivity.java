package com.juuh.ht;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new Scorecard_fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.FragmentLayout, fragment);
        transaction.commit();

    }

    public void getTeams(View v){
        String myurl = "https://kyykka.com/api/teams/?format=json";
        String result;
        JSONAsyncTask get = new JSONAsyncTask();
        try {
            result = get.execute(myurl).get();
            JSONArray ja = new JSONArray(result);
            for (int i = 0; i < ja.length(); i++){
                JSONObject j = (JSONObject) ja.get(i);
                String name = j.getString("name");
                int id = j.getInt("id");
                System.out.println("id: " + id + " name: " + name);
            }
        } catch (ExecutionException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}