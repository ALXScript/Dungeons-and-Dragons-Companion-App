package com.example.victor.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AbilityScoreAdapter extends RecyclerView.Adapter<AbilityScoreAdapter.AbilityScoreViewHolder>
{
    private Context myContext;
    String[] AbilityScoreNames;
    public AbilityScoreAdapter(Context myContext, String[] AbilityScoreStrings ) {
        this.myContext = myContext;
        AbilityScoreNames=AbilityScoreStrings;
    }

    @NonNull
    @Override
    public AbilityScoreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(myContext);
        View view = inflater.inflate(R.layout.layout_ability_scores,null);
        AbilityScoreViewHolder Holder = new AbilityScoreViewHolder(view);
        return Holder;


    }

    @Override
    public void onBindViewHolder(@NonNull AbilityScoreViewHolder abilityScoreViewHolder, int i) {

        abilityScoreViewHolder.textViewAbilityScoreName.setText(AbilityScoreNames[i]);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class AbilityScoreViewHolder extends RecyclerView.ViewHolder{
        TextView textViewAbilityScoreName, textViewAbilityScoreValue,textViewAbilityScoreModifier;

        public AbilityScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAbilityScoreName = itemView.findViewById(R.id.textViewAbilityScoreName);
            textViewAbilityScoreValue = itemView.findViewById(R.id.textViewAbilityScoreValue);
            textViewAbilityScoreModifier = itemView.findViewById(R.id.textViewAbilityScoreModifier);


        }
    };
}
