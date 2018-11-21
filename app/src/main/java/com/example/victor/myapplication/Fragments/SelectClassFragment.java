package com.example.victor.myapplication.Fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victor.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class SelectClassFragment extends Fragment {
    //variables
    Button buttonToRace;
    TextView txtvwDisplayText;
    Spinner spinnerClass;

    //array list variables
    //ArrayList<String> testDescription = new ArrayList<>();
    //ArrayList<String> raceList = new ArrayList<>();

    //string alternatives
    String [] stringClassList;
    ArrayAdapter<String> classListAdapter;

    @Nullable
    @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
       View view=inflater.inflate(R.layout.fragment_select_class,container,false);
       super.onCreate(savedInstanceState);

        //TextView variables
        txtvwDisplayText = (TextView) view.findViewById(R.id.txtvwJSONResultClass);
        txtvwDisplayText.setText("Initial Setting Text");


        //spinner variables
        spinnerClass = (Spinner) view.findViewById(R.id.spinnerClass);
        addItemsToSpinner();
        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectAndParse(adapterView, i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                txtvwDisplayText.setText("Nothing Selected");
            }
        });


        //button variables
        buttonToRace = (Button) view.findViewById(R.id.btnToRaceFragment);
        buttonToRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragManager = getFragmentManager();
                fragManager.beginTransaction().replace(R.id.fragment_container, new SelectRaceFragment()).commit();
            }
        });

       return view;
   }


    public void readAndParse(String assetName) {
        String readJSON = loadJSONFromAsset(assetName);
        txtvwDisplayText.setText(parse_JSON(readJSON));
    }

    public void toastMessage(String message){
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            toastMessage("IOException reading JSON");
            return null;
        }
        return json;
    }

    public String parse_JSON(String jsonFile){
        try{
            JSONObject classObject = new JSONObject(jsonFile);

            String classDescription = classObject.getString("description") + "\n\n" + classObject.getString("creating_a_class") ;

            return classDescription;
        }
        catch(JSONException e){
            return "Error: JSONException happened";
        }
        //return "Never went through";
    }

    public void addItemsToSpinner() {
        /*List list = new ArrayList();
        raceList.add("Nothing Selected");
        raceList.add("Dwarf");
        raceList.add("Hill Dwarf");
        raceList.add("Mountain Dwarf");
        raceList.add("Elf");
        raceList.add("High Elf");
        raceList.add("Dark Elf (Drow)");
        raceList.add("Halfling");
        raceList.add("Lightfoot Halfling");
        raceList.add("Stout Halfling");
        raceList.add("Human");
        raceList.add("Dragonborn");
        raceList.add("Gnome");
        raceList.add("Forest Gnome");
        raceList.add("Rock Gnome");
        raceList.add("Half-Elf");
        raceList.add("Half-Orc");
        raceList.add("Tiefling");
        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, raceList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRace.setAdapter(dataAdapter);*/

        //alternate way to make the spinner
        stringClassList = getResources().getStringArray(R.array.ClassList);
        classListAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, stringClassList);
        classListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClass.setAdapter(classListAdapter);
    }

    public void selectAndParse(AdapterView adapterView, int i){
        //toastMessage("In 'onItemSelected'");

        //test
        String index = adapterView.getItemAtPosition(i).toString();

        //toastMessage(index);

        switch (index){
            case "Nothing Selected":
                txtvwDisplayText.setText("Nothing Selected");
                break;
            case "Barbarian":
                readAndParse("JSONs/ClassJSONs/Barbarian.json");
                break;
            case "Bard":
                readAndParse("JSONs/ClassJSONs/Bard.json");
                break;
            case "Cleric":
                readAndParse("JSONs/ClassJSONs/Cleric.json");
                break;
            case "Druid":
                readAndParse("JSONs/ClassJSONs/Druid.json");
                break;
            case "Fighter":
                readAndParse("JSONs/ClassJSONs/Fighter.json");
                break;
            case "Monk":
                readAndParse("JSONs/ClassJSONs/Monk.json");
                break;
            case "Paladin":
                readAndParse("JSONs/ClassJSONs/Paladin.json");
                break;
            case "Ranger":
                readAndParse("JSONs/ClassJSONs/Ranger.json");
                break;
            case "Rogue":
                readAndParse("JSONs/ClassJSONs/Rogue.json");
                break;
            case "Sorcerer":
                readAndParse("JSONs/ClassJSONs/Sorcerer.json");
                break;
            case "Warlock":
                readAndParse("JSONs/ClassJSONs/Warlock.json");
                break;
            case "Wizard":
                readAndParse("JSONs/ClassJSONs/Wizard.json");
                break;
        }
    }

}
