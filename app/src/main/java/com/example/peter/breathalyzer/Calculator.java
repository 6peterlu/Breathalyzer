package com.example.peter.breathalyzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }
    public void calculate(View view){
        Intent intent = new Intent(this, Result.class);
        startActivity(intent);
    }
}
