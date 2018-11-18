package com.example.victor.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.lang.reflect.Constructor;

public class SkillsListAdapter extends RecyclerView.Adapter<SkillsListAdapter.SkillListViewHolder>
{
    private Context myContext;
    String [] skillNames;
    //Constructor
    public SkillsListAdapter(Context myContext, String[] skillStrings ) {
        this.myContext = myContext;
        skillNames = skillStrings;
    }


    @NonNull
    @Override
    public SkillListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(myContext);
        View view = inflater.inflate(R.layout.layout_skills, null);
        SkillListViewHolder Holder = new SkillListViewHolder(view);
        return Holder;


    }

    //This function will populate the textView within the skillsScoreHolder
    @Override
    public void onBindViewHolder(@NonNull SkillListViewHolder skillListViewHolder, int i) {

        skillListViewHolder.leftSkillsRadioButton.setText(skillNames[i]);
        skillListViewHolder.rightSkillsRadioButton.setText(skillNames[i+9]);
        if(i%3==0)
            skillListViewHolder.rightSkillsRadioButton.setChecked(true);
        if(i%5==0)
            skillListViewHolder.leftSkillsRadioButton.setChecked(true);
    }

    //There are 6 different Ability Scores
    @Override
    public int getItemCount() {
        return 9;
    }

    //The View holder is a single instance of the layout used in the recycler.
    public class SkillListViewHolder extends RecyclerView.ViewHolder{
        RadioButton leftSkillsRadioButton,rightSkillsRadioButton;

        public SkillListViewHolder(@NonNull View itemView) {
            super(itemView);

            leftSkillsRadioButton= itemView.findViewById(R.id.leftSkillsRadioButton);
            rightSkillsRadioButton= itemView.findViewById(R.id.rightSkillsRadioButton);
        }
    }


}
