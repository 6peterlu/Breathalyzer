package com.example.peter.breathalyzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void calculator(View view){
        Intent intent = new Intent(this, Calculator.class);
        startActivity(intent);
    }
    public void breathalyzer(View view){
        Intent intent = new Intent(this, Breathalyzer.class);
        startActivity(intent);
    }
}
