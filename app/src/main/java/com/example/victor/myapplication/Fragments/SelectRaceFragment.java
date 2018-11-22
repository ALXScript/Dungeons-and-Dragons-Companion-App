package com.example.victor.myapplication.Fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victor.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class SelectRaceFragment extends Fragment {
    //Global Variables
    int abilityScores[] = new int[6];
    String alignment[] = new String[9];
    int speed;
    String ability[] = new String[10];
    String abilityDescription[] = new String[10];
    String languages[] = new String[16];
    int alignmentLength;
    int abilityLength;
    int languagesLength;

    //variables
    Button buttonToClass;
    TextView txtvwDisplayText;
    Spinner spinnerRace;

    //Dialog popupTest;
    Button buttonClose;

    //array list variables
    //ArrayList<String> testDescription = new ArrayList<>();
    //ArrayList<String> raceList = new ArrayList<>();

    //string alternatives
    String [] stringRaceList;
    ArrayAdapter<String> raceListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_select_race,container,false);
        super.onCreate(savedInstanceState);

        //TextView variables
        txtvwDisplayText = (TextView) view.findViewById(R.id.txtvwJSONResultRace);
        txtvwDisplayText.setText("Initial Setting Text");


        //spinner variables
        spinnerRace = (Spinner) view.findViewById(R.id.spinnerRace);
        addItemsToSpinner();
        spinnerRace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectAndParse(adapterView, i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                txtvwDisplayText.setText("Nothing Selected");
            }
        });

        //popupTest.setContentView(R.layout.popup_test);

        //button variables
        buttonToClass = (Button) view.findViewById(R.id.btnToClassFragment);
        buttonToClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                buttonClose = (Button) popupTest.findViewById(R.id.btnPopupTest);

                buttonClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupTest.dismiss();
                    }
                });
                */

                //toastMessage("button pressed");
                //Alternate way to change fragment window
                //Fragment frag = new SelectClassFragment();
                FragmentManager fragManager = getFragmentManager();
                fragManager.beginTransaction().replace(R.id.fragment_container, new SelectClassFragment()).commit();
            }
        });

        return view;
    }


    //Function that reads and parses json files and sets the text view display text to the descriptions
    public void readAndParse(String assetName) {
        String readJSON = loadJSONFromAsset(assetName);
        txtvwDisplayText.setText(parse_JSON(readJSON));
    }

    //Function for quickly generating a toast message
    public void toastMessage(String message){
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    //Function for loading the JSON
    public String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            toastMessage("IOException reading JSON");
            return null;
        }
        return json;
    }

    //Function that actually parses the JSON and returns a string of the Race Description
    public String parse_JSON(String jsonFile){
        try{
            //make a JSON object based off of the file that is being read
            JSONObject raceObject = new JSONObject(jsonFile);

            //************Put values inside the Global Variables*************
            //Get the ability scores
            abilityScores[0] = raceObject.getInt("AbilityScore_Strength");
            abilityScores[1] = raceObject.getInt("AbilityScore_Dexterity");
            abilityScores[2] = raceObject.getInt("AbilityScore_Constitution");
            abilityScores[3] = raceObject.getInt("AbilityScore_Intelligence");
            abilityScores[4] = raceObject.getInt("AbilityScore_Wisdom");
            abilityScores[5] = raceObject.getInt("AbilityScore_Charisma");

            //Get the Alignment Array
            JSONArray alignmentArray = raceObject.getJSONArray("Alignment");
            alignmentLength = alignmentArray.length();
            for(int i = 0; i < alignmentArray.length(); i++){
                alignment[i] = alignmentArray.getString(i);
            }

            //Get the Speed
            speed = raceObject.getInt("Speed");

            //Get the abilities and ability Description
            JSONArray abilitiesArray = raceObject.getJSONArray("Abilities");
            abilityLength = abilitiesArray.length();
            for(int i = 0; i < abilitiesArray.length(); i++){
                //Create Internal Object
                JSONObject abilitiesObject = abilitiesArray.getJSONObject(i);

                //Set the stuff
                ability[i] = abilitiesObject.getString("AbilityName");
                abilityDescription[i] = abilitiesObject.getString("AbilityDescription");
            }

            //Get the Languages
            JSONArray languagesArray = raceObject.getJSONArray("Languages");
            languagesLength = languagesArray.length();
            for(int i = 0; i < languagesArray.length(); i++){
                if (languagesArray.getString(i) == "null"){
                    //don't assign anything
                }else{
                    languages[i] = languagesArray.getString(i);
                }
            }
            //***********Done putting values in Global Variables****************

            //set temporary variable for parsing
            String raceDescription;

            //if there is a subrace, return description + subrace description else return just the description
            if (raceObject.getBoolean("isSubrace") == true){
                raceDescription = raceObject.getString("Description") + "\n\n" + raceObject.getString("SubRaceDescription");
            }
            else{
                raceDescription = raceObject.getString("Description");
            }

            testGlobalValues();

            return raceDescription;
        }
        catch(JSONException e){
            return "Error: JSONException happened";
        }
        //return "Never went through";
    }

    //Function that adds items to the spinner
    public void addItemsToSpinner() {
        //alternate way to make the spinner
        stringRaceList = getResources().getStringArray(R.array.RaceList);
        raceListAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, stringRaceList);
        raceListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRace.setAdapter(raceListAdapter);
    }

    //Function that reads and parses depending on what was selected on the spinner
    public void selectAndParse(AdapterView adapterView, int i){
        //toastMessage("In 'onItemSelected'");

        //test
        String index = adapterView.getItemAtPosition(i).toString();

        //toastMessage(index);

        switch (index){
            case "Nothing Selected":
                txtvwDisplayText.setText("Nothing Selected");
                break;
            case "Dwarf":
                readAndParse("JSONs/RaceJSONs/Dwarf.json");
                break;
            case "Hill Dwarf":
                readAndParse("JSONs/RaceJSONs/Dwarf_Hill.json");
                break;
            case "Mountain Dwarf":
                readAndParse("JSONs/RaceJSONs/Dwarf_Mountain.json");
                break;
            case "Elf":
                readAndParse("JSONs/RaceJSONs/Elf.json");
                break;
            case "High Elf":
                readAndParse("JSONs/RaceJSONs/Elf_High_Elf.json");
                break;
            case "Wood Elf":
                readAndParse("JSONs/RaceJSONs/Elf_Wood_Elf.json");
                break;
            case "Dark Elf (Drow)":
                readAndParse("JSONs/RaceJSONs/Elf_Dark_Elf_Drow.json");
                break;
            case "Halfling":
                readAndParse("JSONs/RaceJSONs/Halfling.json");
                break;
            case "Lightfoot Halfling":
                readAndParse("JSONs/RaceJSONs/Halfling_Lightfoot.json");
                break;
            case "Stout Halfling":
                readAndParse("JSONs/RaceJSONs/Halfling_Stout.json");
                break;
            case "Human":
                readAndParse("JSONs/RaceJSONs/Human.json");
                break;
            case "Dragonborn":
                readAndParse("JSONs/RaceJSONs/Dragonborn.json");
                break;
            case "Gnome":
                readAndParse("JSONs/RaceJSONs/Gnome.json");
                break;
            case "Forest Gnome":
                readAndParse("JSONs/RaceJSONs/Gnome_Forest_Gnome.json");
                break;
            case "Rock Gnome":
                readAndParse("JSONs/RaceJSONs/Gnome_Rock_Gnome.json");
                break;
            case "Half-Elf":
                readAndParse("JSONs/RaceJSONs/Half-Elf.json");
                break;
            case "Half-Orc":
                readAndParse("JSONs/RaceJSONs/Half-Orc.json");
                break;
            case "Tiefling":
                readAndParse("JSONs/RaceJSONs/Tiefling.json");
                break;
        }
    }

    //**********************TEST FUNCTIONS************************
    //Function that will toast out all the global variables
    public void testGlobalValues(){
        //Show ability scores
        //toastMessage("Ability Score Strength = " + abilityScores[0] + "\n" + "Ability Score Dexterity = " + abilityScores[1] + "\n" + "Ability Score Constitution = " + abilityScores[2] + "\n" + "Ability Score Intelligence = " + abilityScores[3] + "\n" + "Ability Score Wisdom = " + abilityScores[4] + "\n" + "Ability Score Charisma = " + abilityScores[5] + "\n");

        //Show alignment
        /*String showAlignment = "";
        for (int i = 0; i < alignmentLength; i++){
            if(i == (alignmentLength - 1)){
                showAlignment = showAlignment + alignment[i];

            }else{
                showAlignment = showAlignment + alignment[i] + "," + "\n";
            }
        }
        toastMessage(showAlignment);*/

        //Show Speed
        //toastMessage("Speed = " + speed);

        //show abilities and ability description
        /*for (int i = 0; i < abilityLength; i++){
            toastMessage("Ability Name: " + ability[i] + "\n" + "Ability Description: " + abilityDescription[i]);
        }*/

        //show languages
        /*String showLanguage = "";
        for (int i = 0; i < languagesLength; i++){
            if(i == (languagesLength - 1)){
                showLanguage = showLanguage + languages[i];

            }else{
                showLanguage = showLanguage + languages[i] + "," + "\n";
            }
        }
        toastMessage(showLanguage);*/
    }

}
