package com.example.victor.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListItembook extends AppCompatActivity {

    private ListView itemBookListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itembook_list);

        this.itemBookListView = (ListView) findViewById(R.id.listView);
        DatabaseAccess myDatabaseAccess = DatabaseAccess.getInstance(this);
        myDatabaseAccess.open();
        List<String> itemNames = myDatabaseAccess.getItemNames();
        myDatabaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemNames);
        this.itemBookListView.setAdapter(adapter);
    }
}

