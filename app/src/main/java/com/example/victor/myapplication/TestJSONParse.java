package com.example.victor.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TestJSONParse extends AppCompatActivity{

    private Button btnParseJSON;
    public TextView testView;
    ArrayList<String> testDescription = new ArrayList<>();


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createchar);

        btnParseJSON = (Button) findViewById(R.id.btnParseJSON);
        testView = (TextView) findViewById(R.id.jsonTestView);

        btnParseJSON.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                get_JSON();
                testView.setText(testDescription.toString());
            }
        });
    }

    public void get_JSON(){
        String json = null;

        try{
            InputStream is = getAssets().open("Dragonborn.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read();
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("race").equals("Dragonborn")){
                    testDescription.add(obj.getString("Description"));
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }


    }
}
