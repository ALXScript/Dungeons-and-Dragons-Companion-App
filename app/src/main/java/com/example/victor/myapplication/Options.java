package com.example.victor.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Options extends AppCompatActivity
{
    private static final String TAG = "Options";

    private Button btnDiceRoll, btnInventory, btnSpellBook, btnStats;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        btnDiceRoll = (Button) findViewById(R.id.diceRollBtn);
        btnInventory = (Button) findViewById(R.id.inventoryBtn);
        btnSpellBook = (Button) findViewById(R.id.spellBookBtn);
        btnStats = (Button) findViewById(R.id.statsBtn);

        btnDiceRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this, diceRoll.class);
                startActivity(intent);
            }
        });

        btnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this, inventory.class);
                startActivity(intent);
            }
        });

        btnSpellBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this, spellBook.class);
                startActivity(intent);
            }
        });

        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this, stats.class);
                startActivity(intent);
            }
        });
    }


}
