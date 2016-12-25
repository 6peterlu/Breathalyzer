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

public class CalculatorFragment extends AbstractCalculatorFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = super.onCreateView(inflater, container, savedInstanceState);
        setTextForFragmentInstructions(R.string.calculator_fragment_instructions);
        setTextForCalculateButton(R.string.calculate_bac_button_text);
        setOnClickListenerForCalculateButton(new ResultFragment());
        return v;
    }
}
