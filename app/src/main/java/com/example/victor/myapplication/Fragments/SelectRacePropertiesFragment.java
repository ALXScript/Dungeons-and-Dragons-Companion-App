package com.example.victor.myapplication.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.victor.myapplication.R;


public class SelectRacePropertiesFragment extends Fragment {
    //Global Variables
    int abilityScores[];
    String alignment[];
    int alignmentLength;
    int speed;
    String ability[];
    int abilityLength;
    String languages[];
    int languagesLength;

    //Global Selection Variables
    String selectedLanguage;

    //Generated items
    Spinner chooseLanguageSpinner;

    //for list arrays
    String selectableLanguages[];
    int selectableLanguagesLength;
    ArrayAdapter<String> languageListAdapter;

    //Get the button
    Button buttonToClass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_race_properties, container, false);
        super.onCreate(savedInstanceState);

        //set Global Variables
        abilityScores = getArguments().getIntArray("passAbilityScores");
        alignment = getArguments().getStringArray("passAlignment");
        alignmentLength = getArguments().getInt("passAlignmentLength");
        speed = getArguments().getInt("passSpeed");
        ability = getArguments().getStringArray("passAbility");
        abilityLength = getArguments().getInt("passAbilityLength");
        languages = getArguments().getStringArray("passLanguages");
        languagesLength = getArguments().getInt("passLanguagesLength");

        //Generation of Ability Score choosables goes here

        //Generation for Alignment goes here

        //Generation based off of Abilities goes here

        //Generation based off of languages goes here
        if (checkLanguages(languages, languagesLength)) {
            //button variables
            buttonToClass = (Button) view.findViewById(R.id.btnToSelectClass);
            buttonToClass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Alternate way to change fragment window
                    /*
                    Fragment frag = new SelectClassFragment();
                    FragmentManager fragManager = getFragmentManager();
                    fragManager.beginTransaction().replace(R.id.fragment_container, new SelectClassFragment()).commit();
                    */

                    //Set the fragment before the move is made
                    Fragment frag = new SelectClassFragment();
                    FragmentManager fragManager = getFragmentManager();
                    android.app.FragmentTransaction fragTrans = fragManager.beginTransaction();

                    /*
                    //set the bundles
                    Bundle sendData = new Bundle();
                    sendData.putIntArray("passAbilityScores", abilityScores);
                    sendData.putStringArray("passAlignment", alignment);
                    sendData.putInt("passAlignmentLength", alignmentLength);
                    sendData.putInt("passSpeed", speed);
                    sendData.putStringArray("passAbility", ability);
                    sendData.putInt("passAbilityLength", abilityLength);
                    sendData.putStringArray("passLanguages", languages);
                    sendData.putInt("passLanguagesLength", languagesLength);
                    */

                    //begin transaction with arguments
                    //frag.setArguments(sendData);
                    fragTrans.replace(R.id.fragment_container, frag);
//                    fragTrans.addToBackStack(null);
                    fragTrans.commit();

                }
            });

            //create the layout
            LinearLayout LI = view.findViewById(R.id.racePropertiesLayout);
            LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            //get the array of All languages
            String allLanguages[] = getResources().getStringArray(R.array.LanguageList);

            //Get the array of all selectable languages
            //selectableLanguages = setSelectableLanguages(allLanguages, languages);
            String testLanguages[] = setSelectableLanguages(allLanguages, languages, languagesLength);

            //make the spinner
            chooseLanguageSpinner = new Spinner(getActivity());
            languageListAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, testLanguages);
            languageListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            chooseLanguageSpinner.setAdapter(languageListAdapter);

            LI.addView(chooseLanguageSpinner);
            //else takes the user directly to the next page
        }else{
            //Set the fragment before the move is made
            Fragment frag = new SelectClassFragment();
            FragmentManager fragManager = getFragmentManager();
            android.app.FragmentTransaction fragTrans = fragManager.beginTransaction();

            //begin transaction with arguments
            fragTrans.replace(R.id.fragment_container, frag);
            fragTrans.commit();
        }

        return view;
    }

    //**************CHECKER FUNCTIONS******************
    //Function that checks if there are ability score choosables

    //Function that checks if there are alignment choosables

    //Function that checks if there are Ability choosables

    //Function that checks if there are language choosables
    boolean checkLanguages(String passLanguages[], int passLangLength){
        boolean checker = false;
        for (int i = 0; i < passLangLength; i++){
            if (passLanguages[i].equals("Extra")){
                checker = true;
            }
        }
        return checker;
    }

    //**************GENERATOR FUNCTIONS****************
    //function that generates ability score choosables

    //function that generates alignment choosables

    //function that generates Abilities choosables

    //function that generates languages choosables

    //*************Other Sub Functions*****************
    String[] setSelectableLanguages(String [] passAllLanguages, String [] passKnownLanguages, int passLangLength){
        //Set counter
        selectableLanguagesLength = 0;

        //create filter array
        String filter[] = new String[16];

        //create checker boolean
        boolean checker = false;

        //loop through all 16 languages
        for (int i = 0; i < 16; i++){
            //Reset the Checker
            checker = false;

            //Loop through to see if the current allLanguage is in the Languages known by the race
            for(int j = 0; j < passLangLength; j++){
                //if the language is known, modify the checker
                if (passKnownLanguages[j].equals(passAllLanguages[i])){
                       checker = true;
                }
            }

            //If it is not already known, make that language a valid language option and increment valid language counter
            if(!checker){
                filter[selectableLanguagesLength] = passAllLanguages[i];
                selectableLanguagesLength++;
            }
        }

        //create a new list of an accurate size
        String result[] = new String[selectableLanguagesLength];

        //Populate the list with valid languages
        for (int i = 0; i < selectableLanguagesLength; i++){
            result[i] = filter[i];
        }

        //return the array of valid languages
        return result;
    }

    //Function for quickly generating a toast message
    public void toastMessage(String message){
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    //Function that makes a button invisible and disabled
    public void disableButton(Button passButton){
        passButton.setEnabled(false);
        passButton.setVisibility(View.GONE);
    }

    //Function that makes a button visible and enabled
    public void enableButton(Button passButton){
        passButton.setEnabled(true);
        passButton.setVisibility(View.VISIBLE);
    }
}
