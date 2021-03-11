package com.juuh.ht;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void helloworld(View v){
        System.out.println("dxd");
        System.out.println("asdasdas");
        System.out.println("asdasdas");
    }
}