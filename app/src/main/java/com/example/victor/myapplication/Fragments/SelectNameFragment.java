package com.example.victor.myapplication.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.victor.myapplication.Classes.AbilityScoreSender;
import com.example.victor.myapplication.Classes.BusProvider;
import com.example.victor.myapplication.Classes.Character;
import com.example.victor.myapplication.Classes.DnDClass;
import com.example.victor.myapplication.Classes.Race;
import com.example.victor.myapplication.R;
import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

public class SelectNameFragment extends Fragment {

    Button buttonToClassProperties;
    DnDClass dnDClass;
    Race race;
    String name;
    int [] abilityScores;
    Bus BUS;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_name, container, false);
        super.onCreate(savedInstanceState);
        BUS = BusProvider.getInstance();

        buttonToClassProperties = (Button) view.findViewById(R.id.btnToClassPropertiesFragment);
        buttonToClassProperties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        return view;
    }

    //These are subscribe functions.
    //YOU DO NOT NEED TO CALL SUBSCRIBE FUNCTIONS
    //When you register a bus it will call any subscribe functions it needs to.
    //After the name is input and accepted call BUS.register(this)
    //Then create a charater using these variables
    //Then call BUS.post(sendCharacter("CREATED CHARACTER"));
    //BUS.unregister(this);
    @Subscribe
    public void getClass(DnDClass dnDClass)
    {
        this.dnDClass=dnDClass;
    }
    @Subscribe void getRace (Race race)
    {
        this.race=race;
    }
    @Subscribe void getAbilityScores(AbilityScoreSender abilityScoreSender)
    {
        this.abilityScores=abilityScoreSender.getAbilityScores();
    }
    //This is the produce function. It takes in an already created charater
    //Make sure you call it while the BUS is registered and inside a BUS.post()
    @Produce
    public Character sendCharacter (Character character)
    {

        return character;
    }
}


