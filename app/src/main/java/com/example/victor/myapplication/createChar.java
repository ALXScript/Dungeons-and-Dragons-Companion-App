package com.example.victor.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victor.myapplication.DClass;
import com.example.victor.myapplication.Race;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


//extends AppCompatActivity needed to be able to go from one activity to another
public class createChar extends AppCompatActivity
{
    //variables for the buttons
    public Button buttonParseJSON;
    public TextView testView;
    public EditText textBox;
    ArrayList<String> testDescription = new ArrayList<>();

    //A Tag to reference the class (not that important, may not be needed)
    private static final String TAG = "createChar";

    //******STARTING DEFINING VARIABLES********
    //Imported Classes variables classes


    //myRace.setRace();

    //Stat Variables
    private String myName;
    private int myCurrentHP;
    private int myMaxHP;
    private int myArmorClass;
    private String myHitDice;
    private int myInitiative;
    private int mySpeed;
    private int myDSSuccesses;
    private int myDSFailures;

    //Ability Scores (SDCIWC) Stats
    /*Array Legend
    0 - Strength
    1 - Dexterity
    2 - Constitution
    3 - intelligence
    4 - Wisdom
    5 - Charisma*/
    private int myAbilityScores[] = new int[6];

    //Other Stat Variables
    private boolean statInspiration;
    private int statProficiencyBonus;

    //Saving Throw Variables
    /* Array Legend
    0 - Strength
    1 - Dexterity
    2 - Constitution
    3 - intelligence
    4 - Wisdom
    5 - Charisma*/
    private boolean savingThrowActive[] = new boolean[6];
    private int savingThrow[] = new int[6];

    //Skills Variable
    /* Array Legend
    0 - Acrobatics (Dex)
    1 - Animal Handling (Wis)
    2 - Arcana (int)
    3 - Athletics (Str)
    4 - Deception (Cha)
    5 - History (int)
    6 - Insight (Wis)
    7 - intimidation (Cha)
    8 - Investigation (int)
    9 - Medicine (Wis)
    10 - Nature (int)
    11 - Perception (Wis)
    12 - Performance (Cha)
    13 - Persuasion (Cha)
    14 - Religion (int)
    15 - Sleight of Hand (Dex)
    16 - Stealth (Dex)
    17 - Survival (Wis)
    */
    private boolean skillsActive[] = new boolean[18];
    private int skills[] = new int[18];

    //Char Creation Variables
    private int mySpellBookTable;
    private int myInventoryTable;



    //Default Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //references the XML in Layout Folder
        setContentView(R.layout.createchar);

        toastMessage("inside createChar.java");

        buttonParseJSON = (Button) findViewById(R.id.btnParseJSON);
        testView = (TextView) findViewById(R.id.jsonTestView);
        textBox = (EditText) findViewById(R.id.textBox);

        testView.setText("Initial Setting Text");

        buttonParseJSON.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //@Override

                String toastMessage = "Button has been pressed";
                toastMessage("Button has been pressed");


                //get_JSON();
                //testView.setText(testDescription.toString());
                //testView.setText(parse_JSON("Dragonborn.json"));
                String readJSON = loadJSONFromAsset(textBox.getText().toString());
                testView.setText(parse_JSON(readJSON));
            }
        });
    }

    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void get_JSON(){
        String json = null;

        try{
            InputStream is = getAssets().open("Dragonborn.json");
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

            return raceDescription;
        }
        catch(JSONException e){
            return "Error: JSONException happened";
        }
        //return "Never went through";
    }

}
