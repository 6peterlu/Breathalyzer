package com.example.peter.breathalyzer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }
    public void calculate(View view){
        Intent intent = new Intent(this, Result.class);
        if(complete_inputs(view)) {
           startActivity(intent);
        }
    }

    //error checking - rejects input if at least one field is empty. Sets empty fields to red.
    public boolean complete_inputs(View view){
        EditText weight = (EditText) findViewById(R.id.weight_text);
        EditText height = (EditText) findViewById(R.id.height_text);
        EditText shot = (EditText) findViewById(R.id.shot_text);
        if(input_incomplete(weight) || input_incomplete(height) || input_incomplete(shot)) {
            weight.setHintTextColor(Color.RED);
            height.setHintTextColor(Color.RED);
            shot.setHintTextColor(Color.RED);
            return false;
        }
        return true;
    }

    // checks if one EditText is empty
    public boolean input_incomplete(EditText text){
        if(text.getText().toString().trim().length() == 0){
            return true;
        }
        return false;
    }
}
