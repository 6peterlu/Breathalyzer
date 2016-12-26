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
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jaimedeverall on 25/12/2016.
 */

public class CalculatorFragment extends Fragment {
    private NumberPicker weight_num_picker;
    private NumberPicker standard_drinks_num_picker;
    private RadioGroup sex_buttons;
    private Button calculate_bac_button;
    public static final float MALE_CONST = 0.68f;
    public static final float FEMALE_CONST = 0.55f;
    public static final int GRAMS_PER_STANDARD_DRINK = 14;
    public static final float GRAMS_PER_POUND = 453.592f;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calculator, container, false);

        final SharedPreferences preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        int default_weight = 120;
        int default_standard_drinks = 10;
        int default_radio_button = R.id.male_radio_button;

        //set the defaults values of the number picker, so that it displays what was
        //saved from last time, the user clicked the calculate button.
        default_weight = preferences.getInt("selected_weight", default_weight);
        default_standard_drinks = preferences.getInt("selected_drinks", default_standard_drinks);
        default_radio_button = preferences.getInt("selected_radio_button", default_radio_button);

        //initialize weight number picker (in pounds).
        weight_num_picker = (NumberPicker) v.findViewById(R.id.weight_number_picker);
        int max_weight = 300;//pounds
        int min_weight = 50;//pounds
        weight_num_picker.setMinValue(min_weight);
        weight_num_picker.setMaxValue(max_weight);
        weight_num_picker.setEnabled(true);
        weight_num_picker.setValue(default_weight);
        weight_num_picker.setWrapSelectorWheel(true);

        //initialize standard drinks number picker.
        standard_drinks_num_picker = (NumberPicker) v.findViewById(R.id.standard_drinks_number_picker);
        int min_standard_drinks = 0;
        int max_standard_drinks = 30;
        standard_drinks_num_picker.setMinValue(min_standard_drinks);
        standard_drinks_num_picker.setMaxValue(max_standard_drinks);
        standard_drinks_num_picker.setEnabled(true);
        standard_drinks_num_picker.setValue(default_standard_drinks);
        standard_drinks_num_picker.setWrapSelectorWheel(true);

        //Initialize radio group and radio buttons
        sex_buttons = (RadioGroup) v.findViewById(R.id.sex_radio_group);
        sex_buttons.check(default_radio_button);

        //Initialize calculate BAC button
        calculate_bac_button = (Button) v.findViewById(R.id.calculate_bac_button);
        calculate_bac_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //BAC = ( Alcohol consumed (in g) / (Body weight in grams) * r  ) * 100.
                float grams_alcohol = standard_drinks_num_picker.getValue() * GRAMS_PER_STANDARD_DRINK;
                float body_weight_grams = weight_num_picker.getValue() * GRAMS_PER_POUND;
                float r = (sex_buttons.getCheckedRadioButtonId() == R.id.male_radio_button ? MALE_CONST : FEMALE_CONST);

                float bac = (grams_alcohol / (body_weight_grams * r)) * 100;

                //Save the calculated BAC so this can be shown later to the friend
                preferences.edit().putFloat("selected_bac", bac).commit();
                preferences.edit().putInt("selected_weight", weight_num_picker.getValue()).commit();
                preferences.edit().putInt("selected_drinks", standard_drinks_num_picker.getValue()).commit();
                preferences.edit().putInt("selected_radio_button", sex_buttons.getCheckedRadioButtonId()).commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ResultFragment())
                        .commit();
            }
        });
        return v;
    }
}