package com.example.peter.breathalyzer;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * Created by Peter on 10/20/2016.
 */

public class InfoFragment extends Fragment {
    private Button calculatorButton;
    private Button breathButton;
    private Button settingsButton;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_info, container, false);

        TextView header = (TextView) v.findViewById(R.id.info_header);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        header.setTypeface(typeface);

        calculatorButton = (Button) v.findViewById(R.id.calc_button);
        calculatorButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CalculatorFragment calcFrag = new CalculatorFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, calcFrag)
                        .commit();
            }
        });

        settingsButton = (Button) v.findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SettingsFragment settingsFrag = new SettingsFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, settingsFrag)
                        .commit();
            }
        });

        return v;
    }
}
