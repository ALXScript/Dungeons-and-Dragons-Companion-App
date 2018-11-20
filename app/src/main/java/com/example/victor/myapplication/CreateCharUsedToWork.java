package com.example.victor.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class CreateCharUsedToWork extends AppCompatActivity
{
    //private static final String TAG = "CreateChar";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_createcharacter);
        super.onCreate(savedInstanceState);



        Fragment frag = new fragment_createchar_race();
        FragmentManager fragManager = getFragmentManager();
        fragManager.beginTransaction().replace(R.id.fragment_container, frag).commit();

    }
}

