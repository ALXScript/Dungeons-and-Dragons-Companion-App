package com.example.victor.myapplication;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class fragment_createchar_race extends Fragment {

    //variables
    Button buttonToClass;
    TextView txtvwDisplayText;
    Spinner spinnerRace;

    //array list variables
    //ArrayList<String> testDescription = new ArrayList<>();
    //ArrayList<String> raceList = new ArrayList<>();

    //string alternatives
    String [] stringRaceList;
    ArrayAdapter<String> raceListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_createchar_race, container, false);
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


        //button variables
        buttonToClass = (Button) view.findViewById(R.id.btnToClassFragment);
        buttonToClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openCharClassActivity();
                //Intent intent = new Intent(createChar.this, charClass.class);
                //startActivity(intent);

                toastMessage("button pressed");
                //Alternate way to change fragment window
                //Fragment frag = new fragment_createchar_class();
                FragmentManager fragManager = getFragmentManager();
                fragManager.beginTransaction().replace(R.id.fragment_container, new fragment_createchar_class()).commit();
            }
        });


        return view;
    }

    public void readAndParse(String assetName) {
        String readJSON = loadJSONFromAsset(assetName);
        txtvwDisplayText.setText(parse_JSON(readJSON));
    }

    public void toastMessage(String message){
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

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

    public String parse_JSON(String jsonFile){
        try{
            JSONObject raceObject = new JSONObject(jsonFile);

            String raceDescription = raceObject.getString("Description");

            if(raceObject.getBoolean("isSubrace")){
                raceDescription = raceDescription + "\n\n" + raceObject.getString("SubRaceDescription");
            }

            return raceDescription;
        }
        catch(JSONException e){
            return "Error: JSONException happened";
        }
        //return "Never went through";
    }

    public void addItemsToSpinner() {
        /*List list = new ArrayList();
        raceList.add("Nothing Selected");
        raceList.add("Dwarf");
        raceList.add("Hill Dwarf");
        raceList.add("Mountain Dwarf");
        raceList.add("Elf");
        raceList.add("High Elf");
        raceList.add("Dark Elf (Drow)");
        raceList.add("Halfling");
        raceList.add("Lightfoot Halfling");
        raceList.add("Stout Halfling");
        raceList.add("Human");
        raceList.add("Dragonborn");
        raceList.add("Gnome");
        raceList.add("Forest Gnome");
        raceList.add("Rock Gnome");
        raceList.add("Half-Elf");
        raceList.add("Half-Orc");
        raceList.add("Tiefling");
        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, raceList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRace.setAdapter(dataAdapter);*/

        //alternate way to make the spinner
        stringRaceList = getResources().getStringArray(R.array.RaceList);
        raceListAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, stringRaceList);
        raceListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRace.setAdapter(raceListAdapter);
    }

    public void selectAndParse(AdapterView adapterView, int i){
        toastMessage("In 'onItemSelected'");

        //test
        String index = adapterView.getItemAtPosition(i).toString();

        switch (index){
            case "Nothing Selected":
                txtvwDisplayText.setText("Nothing Selected");
                break;
            case "Dwarf":
                readAndParse("RaceJSONs/Dwarf.json");
                break;
            case "Hill Dwarf":
                readAndParse("RaceJSONs/Dwarf_Hill.json");
                break;
            case "Mountain Dwarf":
                readAndParse("RaceJSONs/Dwarf_Mountain.json");
                break;
            case "Elf":
                readAndParse("RaceJSONs/Elf.json");
                break;
            case "High Elf":
                readAndParse("RaceJSONs/Elf_High_Elf.json");
                break;
            case "Dark Elf (Drow)":
                readAndParse("RaceJSONs/Elf_Dark_Elf_Drow.json");
                break;
            case "Halfling":
                readAndParse("RaceJSONs/Halfling.json");
                break;
            case "Lightfoot Halfling":
                readAndParse("RaceJSONs/Halfling_Lightfoot.json");
                break;
            case "Stout Halfling":
                readAndParse("RaceJSONs/Halfling_Stout.json");
                break;
            case "Human":
                readAndParse("RaceJSONs/Human.json");
                break;
            case "Dragonborn":
                readAndParse("RaceJSONs/Dragonborn.json");
                break;
            case "Gnome":
                readAndParse("RaceJSONs/Gnome.json");
                break;
            case "Forest Gnome":
                readAndParse("RaceJSONs/Gnome_Forest_Gnome.json");
                break;
            case "Rock Gnome":
                readAndParse("RaceJSONs/Gnome_Rock_Gnome.json");
                break;
            case "Half-Elf":
                readAndParse("RaceJSONs/Half-Elf.json");
                break;
            case "Half-Orc":
                readAndParse("RaceJSONs/Half-Orc.json");
                break;
            case "Tiefling":
                readAndParse("RaceJSONs/Tiefling.json");
                break;
        }
    }

}
