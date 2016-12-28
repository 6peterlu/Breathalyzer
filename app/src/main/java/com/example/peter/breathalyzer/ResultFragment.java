package com.example.peter.breathalyzer;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;

/**
 * Created by jlee29 on 12/20/16.
 */

public class ResultFragment extends Fragment {
    private TextView bac_results;
    private ImageView greenCursor;
    private ImageView yellowCursor;
    private ImageView redCursor;
    private Button back_button;
    public static final int greenBoundaryEnd = -25;
    public static final int greenYellowBoundary = 85;
    public static final int yellowRedBoundary = 500;
    public static final int redBoundaryEnd = 740;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_result, container, false);
        bac_results = (TextView) v.findViewById(R.id.results);
        greenCursor = (ImageView) v.findViewById(R.id.green);
        yellowCursor = (ImageView) v.findViewById(R.id.yellow);
        redCursor = (ImageView) v.findViewById(R.id.red);
        SharedPreferences preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);

        float bac = preferences.getFloat("selected_bac", 0.1f);
        String results = "Your BAC is " + bac;
        bac_results.setText(results);
        if (bac < 0.03) {
            // cursor is green
            bac_results.setTextColor(Color.GREEN);
            yellowCursor.setVisibility(View.GONE);
            redCursor.setVisibility(View.GONE);
            int greenPadding = (int) Math.round(greenBoundaryEnd + (greenYellowBoundary - greenBoundaryEnd)*(bac/0.03));
            greenCursor.setX(greenPadding);
        } else if (bac < 0.25) {
            // cursor is yellow
            bac_results.setTextColor(Color.YELLOW);
            greenCursor.setVisibility(View.GONE);
            redCursor.setVisibility(View.GONE);
            int yellowPadding = (int) Math.round(greenYellowBoundary + (yellowRedBoundary - greenYellowBoundary)*(bac/0.25));
            yellowCursor.setX(yellowPadding);
        } else {
            // cursor is red
            bac_results.setTextColor(Color.RED);
            greenCursor.setVisibility(View.GONE);
            yellowCursor.setVisibility(View.GONE);
            int redPadding = (int) Math.round(yellowRedBoundary + (redBoundaryEnd - yellowRedBoundary)*(bac/0.5));
            redCursor.setX(redPadding);
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
        return v;
    }
}
