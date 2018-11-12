package com.example.victor.myapplication;

import android.app.Dialog;
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

public class SpellbookFragment extends Fragment {

    //General Listview Variables
    ListView spellbookListView;
    DatabaseAccess myDatabaseAccess;
    List<String> spellNames;
    ArrayAdapter<String> adapter;
    //Spell Info Variables
    Dialog myDialog;
    TextView spellNameTextView;
    Button removeSpellBttn;
    Button closeBttn;
    TextView spellSource;
    TextView spellType;
    TextView spellDesc;
    TextView spellCount;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spellbook, container, false);
        super.onCreate(savedInstanceState);

        spellbookListView = (ListView) view.findViewById(R.id.listViewSpellbook);
        myDatabaseAccess = DatabaseAccess.getInstance(this.getContext());
        myDatabaseAccess.open();
        spellNames = myDatabaseAccess.fillSpellbook();

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, spellNames);
        spellbookListView.setAdapter(adapter);

        myDialog = new Dialog(getContext()); //for spell infospellbook popup

        getIDFromListView();

        return view;
    }

    private void getSpellInfo(int id, Dialog myDialog) {
        Cursor data = myDatabaseAccess.getSpellsData();

        spellSource = (TextView) myDialog.findViewById(R.id.spellSourceTextView);
        spellType = (TextView) myDialog.findViewById(R.id.spellTypeTextView);
        spellDesc = (TextView) myDialog.findViewById(R.id.spellDescTextView);
        spellNameTextView = (TextView) myDialog.findViewById(R.id.spellNameTextView);
        spellCount = (TextView) myDialog.findViewById(R.id.spellCountTextView);

        while (data.moveToNext()) {
            if (data.getInt(0) == id) {
                spellSource.setText(data.getString(3));
                spellType.setText(data.getString(4));
                spellDesc.setText(data.getString(2));
                spellNameTextView.setText(data.getString(1));
            }
        }

        spellCount.setText("QTY: " + Integer.toString(myDatabaseAccess.getExistingSpellCount(id)));
    }

    private void getIDFromListView() {
        //set an onItemClickListener to the ListView
        spellbookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                Cursor data = myDatabaseAccess.getSpellIDSpells(name); //get the id associated with that name
                int spellID = -1;

                while (data.moveToNext()) {
                    spellID = data.getInt(0);
                }

                if (spellID > -1) {

                    myDialog.setContentView(R.layout.popup_spellinfospellbook);

                    getSpellInfo(spellID, myDialog);

                    removeSpellBttn = (Button) myDialog.findViewById(R.id.spellRemoveSpellbookBtn);
                    closeBttn = (Button) myDialog.findViewById(R.id.closeBtn);

                    closeBttn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    final int finalSpellID = spellID;
                    removeSpellBttn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int spellCount = myDatabaseAccess.getExistingSpellCount(finalSpellID);

                            if(spellCount > 1) {
                                myDatabaseAccess.removeFromSpellbooksCount(finalSpellID, spellCount-1); //remove 1 from count
                                getSpellInfo(finalSpellID, myDialog);
                            }

                            else {
                                myDatabaseAccess.deleteItemFromSpellbook(finalSpellID);
                                adapter.remove(adapter.getItem(i));
                                adapter.notifyDataSetChanged();
                                myDialog.dismiss();
                                toastMessage("Spell removed from spellbook!");
                            }
                        }
                    });

                    myDialog.show();
                } else {
                    toastMessage("No ID associated with that name");
                }

            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}

