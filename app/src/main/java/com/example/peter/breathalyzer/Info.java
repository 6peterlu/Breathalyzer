package com.example.peter.breathalyzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void calculator(){
        Intent intent = new Intent(this, Calculator.class);
        startActivity(intent);
    }
    public void breathalyzer(){
        Intent intent = new Intent(this, Breathalyzer.class);
        startActivity(intent);
    }
}
