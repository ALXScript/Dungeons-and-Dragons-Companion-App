package com.example.victor.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class charClass extends AppCompatActivity {

    //Public variables for things
    public Button buttonToClass;
    public TextView txtvwDisplayText;
    public Spinner spinnerClass;

    //Array List Variables
    ArrayList<String> classList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createcharclass);

        //Text View variables
        txtvwDisplayText = (TextView) findViewById(R.id.txtvwJSONClass);
        txtvwDisplayText.setText("Initial Setting Text");

        //Spinner variables
        spinnerClass = (Spinner) findViewById(R.id.spinnerClass);
        addItemsToSpinner();
        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectAndParse(adapterView, i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                txtvwDisplayText.setText("Nothing Selected");
            }
        });

    }

    //Function for posting a toast message
    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //Function that calls functions to read and parse JSON
    public void readAndParse(String assetName) {
        String readJSON = loadJSONFromAsset(assetName);
        txtvwDisplayText.setText(parse_JSON(readJSON));
    }

    //Function that reads JSON and returns a string of a JSON object
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

    //Function that parses a JSON object and returns a specified portion
    public String parse_JSON(String jsonFile){
        try{
            JSONObject classObject = new JSONObject(jsonFile);

            String classDescription = classObject.getString("description") + "\n\n" + classObject.getString("creating_a_class") ;

            return classDescription;
        }
        catch(JSONException e){
            return "Error: JSONException happened";
        }
        //return "Never went through";
    }

    //function that adds items to the spinner
    public void addItemsToSpinner() {
        //List list = new ArrayList();
        classList.add("Nothing Selected");
        classList.add("Barbarian");
        classList.add("Bard");
        classList.add("Cleric");
        classList.add("Druid");
        classList.add("Fighter");
        classList.add("Monk");
        classList.add("Paladin");
        classList.add("Ranger");
        classList.add("Rogue");
        classList.add("Sorcerer");
        classList.add("Warlock");
        classList.add("Wizard");
        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, classList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClass.setAdapter(dataAdapter);
    }

    //Function that returns a pre-selected description
    public void selectAndParse(AdapterView adapterView, int i){
        toastMessage("In 'onItemSelected'");

        //test
        String index = adapterView.getItemAtPosition(i).toString();

        switch (index){
            case "Nothing Selected":
                txtvwDisplayText.setText("Nothing Selected");
                break;
            case "Barbarian":
                readAndParse("ClassJSONs/Barbarian.json");
                break;
            case "Bard":
                readAndParse("ClassJSONs/Bard.json");
                break;
            case "Cleric":
                readAndParse("ClassJSONs/Cleric.json");
                break;
            case "Druid":
                readAndParse("ClassJSONs/Druid.json");
                break;
            case "Fighter":
                readAndParse("ClassJSONs/Fighter.json");
                break;
            case "Monk":
                readAndParse("ClassJSONs/Monk.json");
                break;
            case "Paladin":
                readAndParse("ClassJSONs/Paladin.json");
                break;
            case "Ranger":
                readAndParse("ClassJSONs/Ranger.json");
                break;
            case "Rogue":
                readAndParse("ClassJSONs/Rogue.json");
                break;
            case "Sorcerer":
                readAndParse("ClassJSONs/Sorcerer.json");
                break;
            case "Warlock":
                readAndParse("ClassJSONs/Warlock.json");
                break;
            case "Wizard":
                readAndParse("ClassJSONs/Wizard.json");
                break;

        }
    }
}
