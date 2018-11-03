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
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.Objects;


public class StatsFragment extends Fragment {

    RecyclerView abilityScoreRecycler;
    AbilityScoreAdapter adapter;
    String [] abilityScoreNames;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        abilityScoreRecycler = view.findViewById(R.id.recyclerViewAbilityScores);
        abilityScoreRecycler.setHasFixedSize(true);
        abilityScoreRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        abilityScoreNames = getResources().getStringArray(R.array.AbilityScores);

        adapter = new AbilityScoreAdapter(getContext(),abilityScoreNames);
        abilityScoreRecycler.setAdapter(adapter);
        return view;
    }
}
