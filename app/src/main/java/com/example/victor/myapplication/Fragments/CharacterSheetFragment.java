package com.example.victor.myapplication.Fragments;

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
import android.widget.Toast;

import com.example.victor.myapplication.Adapters.AbilityScoreAdapter;
import com.example.victor.myapplication.Adapters.SkillsListAdapter;
import com.example.victor.myapplication.Classes.BusProvider;
import com.example.victor.myapplication.Classes.Character;
import com.example.victor.myapplication.Classes.DnDClass;
import com.example.victor.myapplication.Classes.Race;
import com.example.victor.myapplication.R;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class CharacterSheetFragment extends Fragment {

    Character currentPlayerCharacter;
    ImageButton buttonLowerHitPoints, buttonIncreaseHitPoints;
    ProgressBar progressBar;
    RecyclerView abilityScoreRecycler, skillsRecyclerView;
    AbilityScoreAdapter abilityScoreAdapter;
    SkillsListAdapter skillsListAdapter;
    String [] abilityScoreNames,skillNames;
    TextView textViewHitPointValue, textViewClassName, textViewCharacterName;
    String displayHitPoints;
    View view;
    Bus BUS;
    int skillModifiers[]= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int abilityScores[] = {10,10,10,10,10,10};
    int [] abilityScoreModifiers={0,0,0,0,0,0};
    String name = "NA";
    String className="Bard";//TODO FIX ME
    //FIXME WOW
    boolean skillProficiencies[];


    int currentHitPoints;
    int maxHitPoints =0;

    //Alex Code
    TextView textViewSpeedValue, textViewHitDiceValue;
    int speedValue;
    String hitDiceValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_character_sheet, container, false);

        //Used to load PlayerCharacter
        BUS = BusProvider.getInstance();
        BUS.register(this);

//        toastMessage("Im in");
        textViewCharacterName = view.findViewById(R.id.textViewCharacterName);
        textViewCharacterName.setText(name);

        textViewClassName=view.findViewById(R.id.textViewClassName);
        textViewClassName.setText(className);

        //Load in the ability scores
        if (currentPlayerCharacter!=null)  abilityScores = currentPlayerCharacter.getAbilityScores();
        abilityScoreRecycler = view.findViewById(R.id.recyclerViewAbilityScores);
        abilityScoreRecycler.setHasFixedSize(true);
        abilityScoreRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.);
        abilityScoreNames = getResources().getStringArray(R.array.AbilityScores);
        abilityScoreAdapter = new AbilityScoreAdapter(getContext(), abilityScoreNames,abilityScores,abilityScoreModifiers);
        abilityScoreRecycler.setAdapter(abilityScoreAdapter);

        //load in skills
        skillsRecyclerView = view.findViewById(R.id.skillsRecyclerView);
        skillsRecyclerView.setHasFixedSize(false);
        skillsRecyclerView.setNestedScrollingEnabled(false);
        skillsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        skillNames = getResources().getStringArray(R.array.Skills);
        skillsListAdapter = new SkillsListAdapter(getContext(),skillNames, skillProficiencies,skillModifiers);
        skillsRecyclerView.setAdapter(skillsListAdapter);

        //Health bar ..............................................................................
        textViewHitPointValue =view.findViewById(R.id.textViewHealthValue);
        buttonLowerHitPoints =view.findViewById(R.id.buttonLowerHealth);
        buttonIncreaseHitPoints =view.findViewById(R.id.buttonIncreaseHealth);

        //Initialize Health Bar Values
        if (currentPlayerCharacter!=null) {
            currentHitPoints = currentPlayerCharacter.getCurrentHitPoints();
            maxHitPoints = currentPlayerCharacter.getMaxHitPoints();
        }
        else{currentHitPoints=maxHitPoints=100;}
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setMax(maxHitPoints);
        progressBar.setProgress(currentHitPoints);
        displayHitPoints = (Integer.toString(currentHitPoints)+ "/" + Integer.toString(maxHitPoints));

        //these functions need to cause a pop-up that will ask the user for a value to heal/damage
        textViewHitPointValue.setText(displayHitPoints);
        buttonLowerHitPoints.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (progressBar != null)
                {
                    progressBar.incrementProgressBy(-1);
                    currentHitPoints =progressBar.getProgress();
                    displayHitPoints = (Integer.toString(currentHitPoints)+ "/" + Integer.toString(maxHitPoints));
                    textViewHitPointValue.setText(displayHitPoints);
                }
            }
        });

        buttonIncreaseHitPoints.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if ( progressBar != null)
                {
                    progressBar.incrementProgressBy(1);
                    currentHitPoints =progressBar.getProgress();
                    displayHitPoints = (Integer.toString(currentHitPoints)+ "/" + Integer.toString(maxHitPoints));
                    textViewHitPointValue.setText(displayHitPoints);
                }

            }
        });
        //......................................................................................

        //Alex Code
        //get the text views
        textViewSpeedValue = view.findViewById(R.id.textViewSpeedValue);
        textViewHitDiceValue = view.findViewById(R.id.textViewHitDiceValue);
        //set the appropriate values
        if(currentPlayerCharacter!=null){
            textViewSpeedValue.setText(String.valueOf(speedValue));
            textViewHitDiceValue.setText(String.valueOf(hitDiceValue));
        }


        return view;
    }//end OnCreate
    /*

     Subscription is the complement to event publishing—it lets you receive notification
      that an event has occurred. To subscribe to an event, annotate a method with
      @Subscribe. The method should take only a single parameter,
      the type of which will be the event you wish to subscribe to.
      You DONT need to call the function, the Otto Bus API will do so
      In order to receive an event you need to register with the Bus
      */
    @Subscribe
    public void getCharacter(Character sampleCharacter)
    {
        currentPlayerCharacter = sampleCharacter;
        abilityScores = currentPlayerCharacter.getAbilityScores();
        abilityScoreModifiers=currentPlayerCharacter.getAllAbilityScoreModifiers();
        skillModifiers=currentPlayerCharacter.getAllSkillModifiers();
        skillProficiencies=currentPlayerCharacter.getAllSkillProficiencies();
        name=currentPlayerCharacter.getName();
        maxHitPoints=currentPlayerCharacter.getMaxHitPoints();
        currentHitPoints=currentPlayerCharacter.getCurrentHitPoints();
        className=currentPlayerCharacter.getClassName();

        //Alex Code
        speedValue = 30;
        hitDiceValue = currentPlayerCharacter.getMyHitDice();

    }

    @Subscribe
    public void getClass (DnDClass dnDClass)
    {
        className=dnDClass.getClassName();
    }
    private void toastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
