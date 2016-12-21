package com.example.peter.breathalyzer;

/**
 * Created by jlee29 on 12/20/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.ContentValues.TAG;

public class BreathalyzerFragment extends Fragment {
    //private Button measureButton;
    SoundMeter meter;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        meter = new SoundMeter();
        meter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_breathalyzer, container, false);
        /*
        measureButton = (Button) v.findViewById(R.id.measure_button);
        measureButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ResultFragment resultFrag = new ResultFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, resultFrag)
                        .commit();
            }
        });
        */
        final Handler h = new Handler();
        final int delay = 500; //milliseconds
        h.postDelayed(new Runnable(){
            public void run(){
                //do something
                double amplitude = meter.getAmplitude();
                Log.d("cat", "" + amplitude);
                if(amplitude == 32767.0) {//this is the highest double that can be reached by the microphone lol
                    meter.stop();
                    ResultFragment resultFrag = new ResultFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, resultFrag)
                            .commit();
                    meter = null;
                } else {
                    h.postDelayed(this, delay);
                }
            }
        }, delay);
        return v;
    }
}
