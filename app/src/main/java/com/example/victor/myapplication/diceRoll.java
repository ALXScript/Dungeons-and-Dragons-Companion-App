package com.example.victor.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class diceRoll extends AppCompatActivity
{
    private static final String TAG = "diceRoll";
    private ImageView imageViewDice;
    private Button buttonDice;
    private Random rng = new Random();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_diceroller);
        imageViewDice = findViewById(R.id.imageView);

        buttonDice = findViewById(R.id.d6);
        buttonDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(2);
            }
        });
    }


    private void rollDice(int diceNum) {
        //int randomNumber = rng.nextInt(diceNum) + 1;

        switch (diceNum) {
            case 1:
                imageViewDice.setImageResource(R.drawable.dice2);
                break;
            case 2:
                imageViewDice.setImageResource(R.drawable.dice2);
                break;
            case 3:
                imageViewDice.setImageResource(R.drawable.dice2);
                break;
            case 4:
                imageViewDice.setImageResource(R.drawable.dice2);
                break;
            case 5:
                imageViewDice.setImageResource(R.drawable.dice2);
                break;
            case 6:
                imageViewDice.setImageResource(R.drawable.dice2);
                break;
        }
    }
}
