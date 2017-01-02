package com.example.peter.breathalyzer;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import java.math.RoundingMode;
import java.text.DecimalFormat;


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

        SharedPreferences preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        float bac = preferences.getFloat("selected_bac", 0.1f);
        boolean randomizationRequired = preferences.getBoolean("randomization_required", true);
        if(randomizationRequired){
            float randomNoise = (float)((int)(Math.random() * 899 + 100) * 0.00001);
            bac = bac + randomNoise;
        }
        DecimalFormat df = new DecimalFormat("#.#####");
        df.setRoundingMode(RoundingMode.CEILING);
        String results = "Your BAC is: " + df.format(bac);
        bac_results.setText(results);
        ImageView arrow = (ImageView)v.findViewById(R.id.BAC_marker);
        if (bac < 0.03) {
            arrow.setImageResource(R.drawable.green_arrow);
            bac_results.setTextColor(Color.GREEN);
            bac_desc.setText("Minimal or no alcohol content detected.");
        } else if (bac < 0.25) {
            arrow.setImageResource(R.drawable.yellow_arrow);
            bac_results.setTextColor(Color.YELLOW);
            bac_desc.setText("You may be experiencing minor memory and motor impairment. Your balance and reaction times are heavily impacted at this level. Take plenty of water and do not drive at this level.");
        } else {
            arrow.setImageResource(R.drawable.red_arrow);
            bac_results.setTextColor(Color.RED);
            bac_desc.setText("Your mental and physical functions are or will soon be severely impaired. You are at a high risk of asphyxiation due to vomit or a sudden loss of consciousness. Seek help from others around you and medical attention immediately.");
        }
        back_button = (Button) v.findViewById(R.id.home_screen_button);

        bac = Math.min(bac, 0.42f);
        arrow.setX(bac/0.42f * getContext().getResources().getDisplayMetrics().widthPixels - 20); //20 here is a bit arbitrary but thats what works on my screen (Nexus 5X)
        back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                InfoFragment infoFrag = new InfoFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, infoFrag)
                        .commit();
            }
        });
        return v;
    }
}
