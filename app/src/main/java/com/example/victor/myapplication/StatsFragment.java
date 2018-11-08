package com.example.victor.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


public class StatsFragment extends Fragment {

    ImageButton buttonLowerHealth, buttonIncreaseHealth;
    ProgressBar progressBar;
    RecyclerView abilityScoreRecycler, skillsRecyclerView;
    AbilityScoreAdapter abilityScoreAdapter;
    SkillsListAdapter skillsListAdapter;
    String [] abilityScoreNames,skillNames;
    TextView textViewHealthValue;
    String displayHealth;


    int currentHealth;
    int maxHealth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        //Load in the ability scores
        maxHealth = 26;
        abilityScoreRecycler = view.findViewById(R.id.recyclerViewAbilityScores);
        abilityScoreRecycler.setHasFixedSize(true);
        abilityScoreRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        abilityScoreNames = getResources().getStringArray(R.array.AbilityScores);
        abilityScoreAdapter = new AbilityScoreAdapter(getContext(), abilityScoreNames);
        abilityScoreRecycler.setAdapter(abilityScoreAdapter);


        skillsRecyclerView = view.findViewById(R.id.skillsRecyclerView);
        skillsRecyclerView.setHasFixedSize(true);
        skillsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        skillNames = getResources().getStringArray(R.array.Skills);
        skillsListAdapter = new SkillsListAdapter(getContext(),skillNames);
        skillsRecyclerView.setAdapter(skillsListAdapter);


        textViewHealthValue=view.findViewById(R.id.textViewHealthValue);

        buttonLowerHealth=view.findViewById(R.id.buttonLowerHealth);
        buttonIncreaseHealth=view.findViewById(R.id.buttonIncreaseHealth);

        //Initialize Health Bar
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setMax(maxHealth);
        progressBar.setProgress(16);
        currentHealth=progressBar.getProgress();
        displayHealth = (Integer.toString(currentHealth)+ "/" + Integer.toString(maxHealth));

        textViewHealthValue.setText(displayHealth);
        buttonLowerHealth.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (progressBar != null)
                {
                    progressBar.incrementProgressBy(-1);
                    currentHealth=progressBar.getProgress();
                    displayHealth = (Integer.toString(currentHealth)+ "/" + Integer.toString(maxHealth));
                    textViewHealthValue.setText(displayHealth);


                }
            }
        });

        buttonIncreaseHealth.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if ( progressBar != null)
                {
                    progressBar.incrementProgressBy(1);
                    currentHealth=progressBar.getProgress();
                    displayHealth = (Integer.toString(currentHealth)+ "/" + Integer.toString(maxHealth));
                    textViewHealthValue.setText(displayHealth);
                }

            }
        });


        return view;
    }
}
