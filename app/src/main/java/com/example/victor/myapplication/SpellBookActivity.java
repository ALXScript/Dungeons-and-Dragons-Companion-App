package com.example.victor.myapplication;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class SpellBookActivity extends AppCompatActivity {
    private static final String TAG = "SpellBookActivity";
    private ImageView imageViewDice;
    private Button buttonDice;
    private Random rng = new Random();

    public class MainActivity extends FragmentActivity {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_diceroller);

            buttonDice = buttonDice.findViewById(R.id.d6);
            buttonDice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rollDice(6);
                }
            });
        }


        private void rollDice(int diceNum) {
            int randomNumber = rng.nextInt(diceNum) + 1;

            switch (randomNumber) {
                case 1:
                    imageViewDice.setImageResource(R.drawable.dice1);
                    break;
                case 2:
                    imageViewDice.setImageResource(R.drawable.dice2);
                    break;
                case 3:
                    imageViewDice.setImageResource(R.drawable.dice3);
                    break;
                case 4:
                    imageViewDice.setImageResource(R.drawable.dice4);
                    break;
                case 5:
                    imageViewDice.setImageResource(R.drawable.dice5);
                    break;
                case 6:
                    imageViewDice.setImageResource(R.drawable.dice6);
                    break;
            }
        }
    }
}
