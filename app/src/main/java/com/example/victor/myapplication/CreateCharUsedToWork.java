package com.example.victor.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class CreateChar extends AppCompatActivity
{
    private static final String TAG = "createChar";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createcharacter);

        /*
        Fragment frag = new fragment_createchar_race();
        FragmentManager fragManager = getFragmentManager();
        fragManager.beginTransaction().replace(R.id.fragment_container, frag).commit();
        */
    }
}

