package com.example.victor.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


//extends AppCompatActivity needed to be able to go from one activity to another
//public class createChar extends AppCompatActivity implements AdapterView.OnItemSelectedListener
public class createChar extends AppCompatActivity
{
    //variables
    public Button buttonToClass;
    public TextView txtvwDisplayText;
    public Spinner spinnerRace;
    ArrayList<String> testDescription = new ArrayList<>();
    ArrayList<String> raceList = new ArrayList<>();

    //A Tag to reference the class (not that important, may not be needed)
    private static final String TAG = "createChar";


    //Default Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createchar);

        //TextView variables
        txtvwDisplayText = (TextView) findViewById(R.id.jsonTestView);
        txtvwDisplayText.setText("Initial Setting Text");

        //spinner variables
        spinnerRace = (Spinner) findViewById(R.id.spinnerRace);
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
        buttonToClass = (Button) findViewById(R.id.btnClass);
        buttonToClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openCharClassActivity();
                Intent intent = new Intent(createChar.this, charClass.class);
                startActivity(intent);
            }
        });
    }

    public void readAndParse(String assetName) {
        String readJSON = loadJSONFromAsset(assetName);
        txtvwDisplayText.setText(parse_JSON(readJSON));
    }

    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void get_JSON(){
        String json = null;

        try{
            InputStream is = getAssets().open("RaceJSONs/Dragonborn.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("race").equals("Dragonborn")){
                    testDescription.add(obj.getString("Description"));
                }
            }
        }
        catch (IOException e){
            toastMessage("IOException");
            e.printStackTrace();
        }
        catch (JSONException e){
            toastMessage("JSONException");
            e.printStackTrace();
        }


    }

    public String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = getAssets().open(filename);
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
        //List list = new ArrayList();
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
        spinnerRace.setAdapter(dataAdapter);
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

    public void openCharClassActivity(){
        Intent intent = new Intent(createChar.this, charClass.class);
        startActivity(intent);
    }
}
