package com.example.peter.breathalyzer;

/**
 * Created by jlee29 on 12/20/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.ContentValues.TAG;

public class BreathalyzerFragment extends Fragment {
    private Button measureButton;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_breathalyzer, container, false);
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
        return v;
    }
}
