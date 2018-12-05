package com.example.victor.myapplication.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.victor.myapplication.R;





public class SelectClassPropertiesFragment extends Fragment {

    Button buttonToSelectClass;
    Button buttonToSetAbilityScores;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_class_properties, container, false);
        super.onCreate(savedInstanceState);

        //movement button functionality
        buttonToSelectClass = (Button) view.findViewById(R.id.btnToClassFragment);
        buttonToSelectClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage("back button pressed");
                getActivity().onBackPressed();
            }
        });

        buttonToSetAbilityScores = (Button) view.findViewById(R.id.btnToSetAbilityScores);
        buttonToSetAbilityScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragManager = getFragmentManager();
                fragManager.beginTransaction().replace(R.id.fragment_container, new SetAbilityScoresFragment()).addToBackStack(null).commit();
            }
        });
        return view;
    }

    //Function for quickly generating a toast message
    public void toastMessage(String message){
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}