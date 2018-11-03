package com.example.victor.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;



public class ItembookFragment extends Fragment {

    Dialog myDialog;

    ListView itemBookListView;
    DatabaseAccess myDatabaseAccess;
    List<String> itemNames;
    ArrayAdapter<String> adapter;

    TextView itemNameTextView;
    Button addItemBttn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_itembook, container, false);
        super.onCreate(savedInstanceState);

        itemBookListView = (ListView) view.findViewById(R.id.listViewItembook);
        myDatabaseAccess = DatabaseAccess.getInstance(this.getContext());
        myDatabaseAccess.open();
        itemNames = myDatabaseAccess.getItemNames();


        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, itemNames);
        itemBookListView.setAdapter(adapter);

        myDialog = new Dialog(getContext());

        getIDFromListView();

        return view;
    }

    private void addItemToInventories(int charID, int itemID)
    {
            boolean inInventories; //initially assume no duplicate items
            int itemCount;

            inInventories = myDatabaseAccess.isIteminInventories(itemID);


            if(inInventories == false) {    //item not in inventories list yet

                boolean inventoriesAdded = myDatabaseAccess.addToInventories(charID, itemID, 1);

                if(inventoriesAdded) {
                    toastMessage("Data successfully added to inventories table!");
                }
                else {
                    toastMessage("Something went wrong");
                }
            }

            else {
                itemCount = myDatabaseAccess.getExistingItemCount(itemID);
                myDatabaseAccess.addToInventoriesCount(itemID, itemCount+1); //add one to itemCount
                toastMessage("Update Count Success!");
            }
    }

    private void getIDFromListView() {
        //set an onItemClickListener to the ListView
        itemBookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                Cursor data = myDatabaseAccess.getItemIDitems(name); //get the id associated with that name
                int itemID = -1;

                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }

                if(itemID > -1){

                    myDialog.setContentView(R.layout.popup_iteminfo);

                    itemNameTextView = (TextView) myDialog.findViewById(R.id.itemNameTextView);
                    itemNameTextView.setText(name);
                    addItemBttn = (Button) myDialog.findViewById(R.id.itemAddBtn);

                    final int finalItemID = itemID;
                    addItemBttn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addItemToInventories(1, finalItemID);
                        }
                    });

                    myDialog.show();
                    }

                else{
                    toastMessage("No ID associated with that name");
                }

            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
    }
}