package com.example.victor.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Random;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class DiceFragment extends Fragment implements View.OnClickListener{
    private ImageView d6;
    private Button rollButton, d4;
    private TextView diceResultTextView , diceQuant;

    private Random rng = new Random();
    private int[] diceCount = {0,0,0,0,0,0,0};//Number of dice being rolled
    private int[] diceResults = {0,0,0,0,0,0,0};//Size of dice. d4, d6, d8, etc.


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.d4: //diceCount[0]
                diceCount[0]++;
                if (diceCount[1] > 0)
                    diceQuant.setText(String.valueOf(diceCount[0])+"d4 + "+String.valueOf(diceCount[1])+"d6");
                else
                    diceQuant.setText(String.valueOf(diceCount[0])+"d4");
                break;
            case R.id.d6:
                diceCount[1]++;
                diceQuant.setText(String.valueOf(diceCount[1])+"d6");
                //rollDice(6, count);
                break;
            case R.id.roll:
                rollDice();
                diceCount[0] = diceCount[1] = 0; //Resetting all counts
                break;
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diceroller, container, false);
        super.onCreate(savedInstanceState);

        //Onclick listeners
        d4 = view.findViewById(R.id.d4);
        d4.setOnClickListener(this);

        d6 = view.findViewById(R.id.d6);
        d6.setOnClickListener(this);

        rollButton = view.findViewById(R.id.roll);
        rollButton.setOnClickListener(this);


        diceQuant = view.findViewById(R.id.diceQuant);
        diceResultTextView = view.findViewById(R.id.diceResultTextView);




        return view;
    }


    private void rollDice(){
        int[] diceSize = {4,6,8,10,12,20,100};
        int sum=0;
        String individualDiceRoll = "Hi", newRoll;
        boolean firstRoll = TRUE;

        for (int j= 0; j<6; j++) {
            for (int i = 0; i < diceCount[j]; i++) {
                diceResults[i] = rng.nextInt(diceSize[j]) + 1;
                if (firstRoll == TRUE) {
                    individualDiceRoll = Integer.toString(diceResults[i]);
                    firstRoll = FALSE;
                }
                else {
                    newRoll = " + " + Integer.toString(diceResults[i]);
                    individualDiceRoll += newRoll;
                }
                sum += diceResults[i];
            }
        }
        diceResultTextView.setText(individualDiceRoll+" = "+String.valueOf(sum));
    }
}

/*
        imageViewDice = view.findViewById(R.id.image_view_dice);
        d6 = view.findViewById(R.id.d6);
        diceResult = view.findViewById(R.id.diceResult);
        d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                diceResult.setText(String.valueOf(count)+"d6 = ");
                rollDice(6, count);
            }

            private void rollDice(int diceNum, int count) {
                //int randomNumber = 0;
                int sum = 0;
                //diceResult.setText(String.valueOf(randomNumber));


                for (int i =0; i<count+1; i++){
                    sum += rng.nextInt(diceNum) + 1;
                }
                diceResult.setText(String.valueOf(count)+"d6 = "+String.valueOf(sum));

//                switch (randomNumber) {
//                    case 1:
//                        imageViewDice.setImageResource(R.drawable.dice1);
//                        break;
//                    case 2:
//                        imageViewDice.setImageResource(R.drawable.dice2);
//                        break;
//                    case 3:
//                        imageViewDice.setImageResource(R.drawable.dice3);
//                        break;
//                    case 4:
//                        imageViewDice.setImageResource(R.drawable.dice4);
//                        break;
//                    case 5:
//                        imageViewDice.setImageResource(R.drawable.dice5);
//                        break;
//                    case 6:
//                        imageViewDice.setImageResource(R.drawable.dice6);
//                        break;
//                }
            }





        });

 */