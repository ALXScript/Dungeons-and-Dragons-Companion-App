package com.example.victor.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DClass extends AppCompatActivity
{
    private static final String TAG = "dclass";

    //create class variables
    private String dClass;

    //function for getting the DClass
    public String getDClass(){
        return dClass;
    };

    //function for setting the DClass
    public void setDClass(String setter){
        dClass = setter;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rolldice);
    }
}


