package com.example.peter.breathalyzer;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
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
    private ImageView arrow;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_result, container, false);
        bac_results = (TextView) v.findViewById(R.id.results);
        bac_desc = (TextView) v.findViewById(R.id.bac_description);
        arrow = (ImageView)v.findViewById(R.id.BAC_marker);
        float bac = getBAC();
        setResultScreen(bac);
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
        return v;
    }
    private float getBAC(){
        SharedPreferences preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        float bac = preferences.getFloat("selected_bac", 0.1f);
        boolean randomizationRequired = preferences.getBoolean("randomization_required", true);
        if(randomizationRequired){
            float randomNoise = (float)((int)(Math.random() * 899 + 100) * 0.00001);
            bac = bac + randomNoise;
        }
        return bac;
    }
    private String getResultMessage(float bac){
        DecimalFormat df = new DecimalFormat("#.#####");
        df.setRoundingMode(RoundingMode.CEILING);
        String results = "Your BAC is: " + df.format(bac);
        return results;
    }
    private void setResultScreen(float bac){
        String results = getResultMessage(bac);
        bac_results.setText(results);
        if (bac < 0.03) {
            arrow.setImageResource(R.drawable.green_arrow);
            bac_results.setTextColor(Color.GREEN);
            bac_desc.setText(R.string.low_bac_message);
        } else if (bac < 0.25) {
            arrow.setImageResource(R.drawable.yellow_arrow);
            bac_results.setTextColor(Color.YELLOW);
            bac_desc.setText(R.string.medium_bac_message);
        } else {
            arrow.setImageResource(R.drawable.red_arrow);
            bac_results.setTextColor(Color.RED);
            bac_desc.setText(R.string.high_bac_message);
        }
        bac = Math.min(bac, 0.42f);
        arrow.setX(bac/0.42f * getContext().getResources().getDisplayMetrics().widthPixels - 20); //20 here is a bit arbitrary but thats what works on my screen (Nexus 5X)

    }
}
