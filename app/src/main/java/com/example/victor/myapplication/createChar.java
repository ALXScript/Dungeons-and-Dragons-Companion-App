package com.example.victor.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.victor.myapplication.DClass;
import com.example.victor.myapplication.Race;


//extends AppCompatActivity needed to be able to go from one activity to another
public class createChar extends AppCompatActivity
{
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
    }

}
