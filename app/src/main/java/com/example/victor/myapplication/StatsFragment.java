package com.example.victor.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.Objects;


public class StatsFragment extends Fragment {

    ProgressBar healthBar;
    Button increaseHealthButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        healthBar = view.findViewById(R.id.healthProgressBar);
        increaseHealthButton = view.findViewById(R.id.increaseHealthButton);

        increaseHealthButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                healthBar.incrementProgressBy(1);
            }
        });
        return view;
    }
}
