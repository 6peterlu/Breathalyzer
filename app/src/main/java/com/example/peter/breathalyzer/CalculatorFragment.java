package com.example.peter.breathalyzer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.graphics.Color;

/**
 * Created by jlee29 on 12/20/16.
 */

public class CalculatorFragment extends Fragment {
    private Button calcButton;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_calculator, container, false);
        calcButton = (Button) v.findViewById(R.id.calc_button);
        calcButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //if (complete_inputs(view)) {
                    ResultFragment resultFrag = new ResultFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, resultFrag)
                            .commit();
                //}
            }
        });
        return v;
    }

    //error checking - rejects input if at least one field is empty. Sets empty fields to red.
    public boolean complete_inputs(View view){
        EditText weight = (EditText) view.findViewById(R.id.weight_text);
        EditText height = (EditText) view.findViewById(R.id.height_text);
        EditText shot = (EditText) view.findViewById(R.id.shot_text);
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
