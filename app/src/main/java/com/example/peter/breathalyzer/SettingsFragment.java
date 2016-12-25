package com.example.peter.breathalyzer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;

/**
 * Created by jaimedeverall on 22/12/2016.
 * One number picker for the number of standard drinks
 * One number picker for the weight of the person
 * One checkbox for the gender of the person.
 * One button for calculating the BAC and saving it. Use text view to display this success.
 */

public class SettingsFragment extends AbstractCalculatorFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = super.onCreateView(inflater, container, savedInstanceState);
        setTextForFragmentInstructions(R.string.settings_fragment_instructions);
        setTextForCalculateButton(R.string.fake_app_button_text);
        setOnClickListenerForCalculateButton(new BreathalyzerFragment());
        return v;
    }
}
