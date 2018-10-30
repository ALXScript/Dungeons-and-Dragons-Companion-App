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
    private Button rollButton, d4, d8, d10, d12, d20, d100;
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
                break;
            case R.id.d8:
                diceCount[2]++;
                diceQuant.setText(String.valueOf(diceCount[2])+"d8");
                break;
            case R.id.d10:
                diceCount[3]++;
                diceQuant.setText(String.valueOf(diceCount[3])+"d10");
                break;
            case R.id.d12:
                diceCount[4]++;
                diceQuant.setText(String.valueOf(diceCount[4])+"d12");
                break;
            case R.id.d20:
                diceCount[5]++;
                diceQuant.setText(String.valueOf(diceCount[5])+"d20");
                break;
            case R.id.d100:
                diceCount[6]++;
                diceQuant.setText(String.valueOf(diceCount[6])+"d100");
                break;
            case R.id.roll:
                rollDice();
                for (int i =0; i<7; i++){
                    diceCount[i] =0;
                } //Resetting all counts
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

        d8 = view.findViewById(R.id.d8);
        d8.setOnClickListener(this);

        d10 = view.findViewById(R.id.d10);
        d10.setOnClickListener(this);

        d12 = view.findViewById(R.id.d12);
        d12.setOnClickListener(this);

        d20 = view.findViewById(R.id.d20);
        d20.setOnClickListener(this);

        d100 = view.findViewById(R.id.d100);
        d100.setOnClickListener(this);

        rollButton = view.findViewById(R.id.roll);
        rollButton.setOnClickListener(this);

        diceQuant = view.findViewById(R.id.diceQuant);
        diceResultTextView = view.findViewById(R.id.diceResultTextView);

        return view;
    }


    private void rollDice(){
        int[] diceSize = {4,6,8,10,12,20,100};
        int sum=0;
        String individualDiceRoll = "No Dice Selected", newRoll;
        boolean firstRoll = TRUE;

        for (int j= 0; j<7; j++) {
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
        if (firstRoll == FALSE) {
            diceResultTextView.setText(individualDiceRoll + " = " + String.valueOf(sum));
        }
        else {
            diceResultTextView.setText(individualDiceRoll);
        }
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