package com.example.victor.myapplication.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victor.myapplication.Classes.BusProvider;
import com.example.victor.myapplication.R;
import com.squareup.otto.Bus;
import com.squareup.otto.Produce;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SetAbilityScoresFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SetAbilityScoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetAbilityScoresFragment extends Fragment {

    EditText editTextStr,editTextDex,editTextCon,editTextInt,editTextWis,editTextCha;
    Button buttonCalculateModifiers;
    TextView textViewStrModifier, textViewDexModifier, textViewConModifier, textViewIntModifier, textViewWisModifier, textViewChaModifier;
    boolean validInput;
    Bus BUS;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_set_ability_scores, container, false);
        super.onCreate(savedInstanceState);
        //Initialize the text views. A lot of this should be moved to a recycler view but this is a
        //'fast' n sloppy implementation
        validInput=false;
        editTextStr = view.findViewById(R.id.editTextStrScore);
        editTextDex = view.findViewById(R.id.editTextDexScore);
        editTextCon = view.findViewById(R.id.editTextConScore);
        editTextInt = view.findViewById(R.id.editTextIntScore);
        editTextWis = view.findViewById(R.id.editTextWisScore);
        editTextCha = view.findViewById(R.id.editTextChaScore);
        buttonCalculateModifiers = view.findViewById(R.id.buttonCalculateModifiers);
        textViewStrModifier = view.findViewById(R.id.textViewStrModifier);
        textViewDexModifier = view.findViewById(R.id.textViewDexModifier);
        textViewConModifier = view.findViewById(R.id.textViewConModifier);
        textViewIntModifier = view.findViewById(R.id.textViewIntModifier);
        textViewWisModifier= view.findViewById(R.id.textViewWisModifier);
        textViewChaModifier = view.findViewById(R.id.textViewChaModifier);

        buttonCalculateModifiers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validInput=true;
                String stringMod="";
                String stringScore="";
                int mod = 0;
                int score = 0;
                if(editTextStr.getText()!=null)
                {
                    stringScore = editTextStr.getText().toString();
                    score = Integer.parseInt(stringScore);
                    mod = (score-10)/2;
                    if(mod>=0) stringMod = "+";
                    stringMod += Integer.toString(mod);
                    textViewStrModifier.setText(stringMod);
                }
                else validInput=false;
                stringMod="";

                if(editTextDex.getText()!=null)
                {
                    stringScore = editTextDex.getText().toString();
                    score = Integer.parseInt(stringScore);
                    mod = (score-10)/2;
                    if(mod>=0) stringMod = "+";
                    stringMod += Integer.toString(mod);
                    textViewDexModifier.setText(stringMod);
                }
                else validInput=false;
                stringMod="";

                if(editTextCon.getText()!=null)
                {
                    stringScore = editTextCon.getText().toString();
                    score = Integer.parseInt(stringScore);
                    mod = (score-10)/2;
                    if(mod>=0) stringMod = "+";
                    stringMod += Integer.toString(mod);
                    textViewConModifier.setText(stringMod);
                }
                else validInput=false;
                stringMod="";

                if(editTextInt.getText()!=null)
                {
                    stringScore = editTextInt.getText().toString();
                    score = Integer.parseInt(stringScore);
                    mod = (score-10)/2;
                    if(mod>=0) stringMod = "+";
                    stringMod += Integer.toString(mod);
                    textViewIntModifier.setText(stringMod);
                }
                else validInput=false;
                stringMod="";

                if(editTextWis.getText()!=null)
                {
                    stringScore = editTextWis.getText().toString();
                    score = Integer.parseInt(stringScore);
                    mod = (score-10)/2;
                    if(mod>=0) stringMod = "+";
                    stringMod += Integer.toString(mod);
                    textViewWisModifier.setText(stringMod);
                }
                else validInput=false;
                stringMod="";

                if(editTextCha.getText()!=null)
                {
                    stringScore = editTextCha.getText().toString();
                    score = Integer.parseInt(stringScore);
                    mod = (score-10)/2;
                    if(mod>=0) stringMod = "+";
                    stringMod += Integer.toString(mod);
                    textViewChaModifier.setText(stringMod);
                }
                else validInput=false;

                if(!validInput)
                {
                    Toast.makeText(getActivity(),"There were errors!",Toast.LENGTH_SHORT).show();
                }
            }
        });//end OnClickListener
        BUS=BusProvider.getInstance();
        return view;
    }//end OnCreateView





    //I will want to call this function when they press the next button.
    //I may not be able to pass it an argument as a parameter???
    //I should make sure that all the values are valid first.
    //the procedure is to register with the BUS. Post the producing function Then unregistering.
//    @Produce
//    int[] sendAbilityScores(int[] abilityScores)
//    {
//        return abilityScores;
//    }
}
