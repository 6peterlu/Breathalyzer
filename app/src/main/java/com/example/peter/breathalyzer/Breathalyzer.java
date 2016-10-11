package com.example.peter.breathalyzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Breathalyzer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathalyzer);
    }
    public void measure(){
        Intent intent = new Intent(this, Result.class);
        startActivity(intent);
    }
}
