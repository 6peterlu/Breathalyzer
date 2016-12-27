package com.example.peter.breathalyzer;

/**
 * Created by jlee29 on 12/20/16.
 */

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

import static android.content.ContentValues.TAG;

public class BreathalyzerFragment extends Fragment {
    //private Button measureButton;
    SoundMeter meter;
    private Handler handler;
    private Runnable runnable;
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
        handler = new Handler();
        final int delay = 500; //milliseconds
        runnable = new Runnable() {
            @Override
            public void run() {
                //do something
                double amplitude = meter.getAmplitude();
                Log.d("cat", "" + amplitude);
                if (amplitude == 32767.0) {//this is the highest double that can be reached by the microphone lol
                    meter.stop();
                    ResultFragment resultFrag = new ResultFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, resultFrag)
                            .commit();
                    meter = null;
                } else {
                    handler.postDelayed(this, delay);
                }
            }
        };
        handler.postDelayed(runnable, delay);
        return v;
    }

    /*We override this callback in case the drunk user clicks the back button and exits the app.
     * In this case, we want the handler to stop running the runnable. Note: you cannot leave
     * the Breathalyzer Fragment if the user clicks square and then comes back to the app.*/
    @Override
    public void onPause(){
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}
