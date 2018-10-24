package com.example.victor.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.Random;

public class DiceFragment extends Fragment {
    private ImageView imageViewDice;
    private Button buttonDice;
    private Random rng = new Random();
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diceroller, container, false);
        super.onCreate(savedInstanceState);
        textView = view.findViewById(R.id.diceTextView);
        BusProvider.getInstance().register(this);
        imageViewDice = view.findViewById(R.id.image_view_dice);
        buttonDice = view.findViewById(R.id.d6);

        buttonDice.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                rollDice(6);
            }

            public void rollDice(int diceNum) {
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
        });

        return view;
    }


    @Subscribe
    public void getCharacter(String s)
    {
        if (textView!=null) textView.setText(s);
    }
}
