package com.example.peter.breathalyzer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    private EditText weight;
    private EditText height;
    private EditText shot;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_calculator, container, false);
        weight = (EditText) v.findViewById(R.id.weight_text);
        height = (EditText) v.findViewById(R.id.height_text);
        shot = (EditText) v.findViewById(R.id.shot_text);
        calcButton = (Button) v.findViewById(R.id.calc_button);
        calcButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if (complete_inputs(weight, height, shot)) {
                    ResultFragment resultFrag = new ResultFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, resultFrag)
                            .commit();
                }
            }
        });
        return v;
    }

    // checks if one EditText is empty
    public boolean input_incomplete(EditText text){
        if(text.getText().toString().trim().length() == 0){
            return true;
        }
        return false;
    }

    //error checking - rejects input if at least one field is empty. Sets empty fields to red.
    public boolean complete_inputs(EditText _weight, EditText _height, EditText _shot) {
        if(input_incomplete(_weight) || input_incomplete(_height) || input_incomplete(_shot)) {
            _weight.setHintTextColor(Color.RED);
            _height.setHintTextColor(Color.RED);
            _shot.setHintTextColor(Color.RED);
            return false;
        }
        return true;
    }


}
