package com.example.victor.myapplication.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.victor.myapplication.Activities.MainActivity;
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

    Button buttonToClassProperties, buttonCreateCharacter;
    EditText editTextCharacterName;
    DnDClass dnDClass;
    Race race;
    String name,raceName,className;
    int [] abilityScores={0,0,0,0,0,0};
    //AbilityScoreSender abilityScores;
    Bus BUS;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_name, container, false);
        super.onCreate(savedInstanceState);

        raceName="NA";
        className="NA";


        //Get Instance of the BUS
        BUS = BusProvider.getInstance();

        //Define Variables
        editTextCharacterName = view.findViewById(R.id.editTextCharacterName);

        //Button to go back to Class Properties Fragment
        buttonToClassProperties = (Button) view.findViewById(R.id.btnToClassPropertiesFragment);
        buttonToClassProperties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        //Button to Create Character (essentially finish the process of creating a character atm)
        buttonCreateCharacter = view.findViewById(R.id.btnCreateCharacter);
        buttonCreateCharacter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v ){
                //Get the name from the editText box
                name = editTextCharacterName.getText().toString();

                //Register the BUS
                BUS.register(this);

                //Create a new instance of a character, using the parameters from the Posterboard on the BUS
                Character newCharacter = new Character(name, abilityScores, raceName, className);

                //Post the character we just created to the BUS
                BUS.post(sendCharacter(newCharacter));

//                BUS.unregister(this);

                //Direct to Character Sheet fragment
                Intent switcher = new Intent(getActivity(), MainActivity.class);
                startActivity(switcher);

                /*
                Fragment frag = new CharacterSheetFragment();
                FragmentManager fragManager = getActivity().getFragmentManager();
                FragmentTransaction fragTrans = fragManager.beginTransaction();

                fragTrans.replace(R.id.fragment_container, frag);
                fragTrans.commit();
                */
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
        className=dnDClass.getClassName();
    }
    @Subscribe void getRace (Race race)
    {
        raceName=race.getRaceName();
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


