package com.example.peter.breathalyzer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by jaimedeverall on 25/12/2016.
 */

public class SettingsFragment extends Fragment {
    private Button fakeBreathalyzerButton;
    private SeekBar bac_seekbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_settings, container, false);
        final SharedPreferences preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        bac_seekbar = (SeekBar) v.findViewById(R.id.bac_seekbar);

        //set the range of the seekbar and the initial value using preferences.
        bac_seekbar.setMax(50);
        bac_seekbar.setProgress( (int)(preferences.getFloat("selected_bac", 0.3f) * 100) );
        final TextView seekPreview = (TextView)v.findViewById(R.id.seekbar_preview);
        seekPreview.setText("Selected BAC: " + bac_seekbar.getProgress()/100.0f);

        //Change the preview of BAC on seekbar change
        bac_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged (SeekBar seekBar, int progress, boolean fromUser){

                seekPreview.setText("Selected BAC: " + bac_seekbar.getProgress()/100.0f);
            }
            //idk if theres anything we should put here
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){
                //hello friends
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){
                //sup
            }

        });

        fakeBreathalyzerButton = (Button) v.findViewById(R.id.fake_breathalyzer_button);
        fakeBreathalyzerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //set the value in shared preferences of the new BAC. and go to the Breathalyzer fragment.
                float bac = bac_seekbar.getProgress()/100.0f;
                preferences.edit().putFloat("selected_bac", bac).commit();
                preferences.edit().putBoolean("randomization_required", true).commit();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new BreathalyzerFragment())
                        .commit();
            }
        });
        return v;
    }
}
