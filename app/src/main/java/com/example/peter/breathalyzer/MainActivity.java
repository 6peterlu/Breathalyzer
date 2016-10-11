package com.example.peter.breathalyzer;
//Violent delights come to violent ends

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("watsup", "onCreate: How are you");
    }
    public void next(){
        Intent intent = new Intent(this, Info.class);
        startActivity(intent);
    }
}
