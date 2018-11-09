package com.example.victor.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Race extends AppCompatActivity
{
    public static  void testFunction()
    {

    }

    private static final String TAG = "race";

    //create class variables
    private String race;

    //get race function
    public void getRace(){
        //return  race;
    }

    //set race function
    void setRace(String setter){
        race = setter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rolldice);
    }
}
