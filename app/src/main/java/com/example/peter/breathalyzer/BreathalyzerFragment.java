package com.example.peter.breathalyzer;

/**
 * Created by jlee29 on 12/20/16.
 */


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


public class BreathalyzerFragment extends Fragment {
    SoundMeter meter;
    private Handler handler;
    private Runnable runnable;
    int progressStatusCounter = 0;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        meter = new SoundMeter();
        meter.start();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_breathalyzer, container, false);
        handler = new Handler();
        final int delay = 20; //milliseconds
        final ProgressBar androidProgressBar = (ProgressBar)v.findViewById(R.id.progress_bar);
        final TextView analyzing = (TextView)v.findViewById(R.id.analyzing);
        runnable = new Runnable() {
            @Override
            public void run() {
                //do something
                double amplitude = meter.getAmplitude();

                if (amplitude > 1000) {//this is the highest double that can be reached by the microphone lol
                    progressStatusCounter ++;

                    analyzing.setText(R.string.analyzing_text);
                    androidProgressBar.setProgress(progressStatusCounter);
                    if(progressStatusCounter == androidProgressBar.getMax()){//if the progress bar is full
                        meter.stop();
                        ResultFragment resultFrag = new ResultFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, resultFrag)
                                .commit();
                        meter = null;
                    }
                } else {
                    progressStatusCounter = 0;
                    analyzing.setText("");
                    androidProgressBar.setProgress(progressStatusCounter);
                }
                handler.postDelayed(this, delay);
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
