package com.example.peter.breathalyzer;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.Color;


/**
 * Created by jlee29 on 12/20/16.
 */

public class ResultFragment extends Fragment {
    private TextView bac_results;
    private TextView bac_desc;
    private Button back_button;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_result, container, false);
        bac_results = (TextView) v.findViewById(R.id.results);
        bac_desc = (TextView) v.findViewById(R.id.bac_description);
        int randomNoise = (int)(Math.random() * 899 + 100);
        SharedPreferences preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        float bac = preferences.getFloat("selected_bac", 0.1f);
        String results = "Your BAC is: " + bac + Integer.toString(randomNoise);
        bac_results.setText(results);
        ProgressBar indicator = (ProgressBar)v.findViewById(R.id.indicator);
        indicator.setProgress((int)(bac*100));
        if (bac < 0.03) {
            bac_results.setTextColor(Color.GREEN);
            bac_desc.setText("Minimal or no alcohol content detected.");
        } else if (bac < 0.25) {
            bac_results.setTextColor(Color.YELLOW);
            bac_desc.setText("You may be experiencing minor memory and motor impairment. Your balance and reaction times are heavily impacted at this level. Take plenty of water and do not drive at this level.");
        } else {
            bac_results.setTextColor(Color.RED);
            bac_desc.setText("Your mental and physical functions are or will soon be severely impaired. You are at a high risk of asphyxiation due to vomit or a sudden loss of consciousness. Seek help from others around you and/or medical attention immediately.");
        }
        back_button = (Button) v.findViewById(R.id.home_screen_button);
        back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                InfoFragment infoFrag = new InfoFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, infoFrag)
                        .commit();
            }
        });
        ImageView arrow = (ImageView)v.findViewById(R.id.arrow);
        if(bac >= 0.03 && bac < 0.25){
            arrow.setImageResource(R.drawable.yellow_arrow);
        } else if(bac >= 0.25){
            arrow.setImageResource(R.drawable.red_arrow);
        }

        arrow.setX((float)indicator.getProgress()/indicator.getMax() * getContext().getResources().getDisplayMetrics().widthPixels - 13);
        return v;
    }
}
