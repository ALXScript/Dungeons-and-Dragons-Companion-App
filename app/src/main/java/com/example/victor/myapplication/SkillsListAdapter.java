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
    boolean [] skillProficiencies;
    int [] skillModifiers;
    //Constructor
    public SkillsListAdapter(Context myContext, String[] skillStrings,boolean [] skillProficiencies){
        this.myContext = myContext;
        skillNames = skillStrings;
        this.skillProficiencies=skillProficiencies;
//        this.skillModifiers=skillModifiers;
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


        String s1 = String.format("%1$-" + 20 + "s", skillNames[i]);
        String s2 = String.format("%1$-" + 20 + "s", skillNames[i+9],"width");
        s1+="+1";
        s2+="+4";
        skillListViewHolder.leftSkillsRadioButton.setText(s1);
        skillListViewHolder.rightSkillsRadioButton.setText(s2);
        if(skillProficiencies[i])
            skillListViewHolder.leftSkillsRadioButton.setChecked(true);
        if(skillProficiencies[i+9])
            skillListViewHolder.rightSkillsRadioButton.setChecked(true);

    }

    //There are 18 different Skills. Each view has two of them on the same line
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
