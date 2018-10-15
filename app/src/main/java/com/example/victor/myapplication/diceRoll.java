package com.example.victor.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class diceRoll extends AppCompatActivity
{
    private static final String TAG = "diceRoll";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rolldice);
    }
}
