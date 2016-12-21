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
    private Button breathalyzerButton;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);
        breathalyzerButton = (Button) v.findViewById(R.id.breath_button);
        breathalyzerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), Breathalyzer.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
