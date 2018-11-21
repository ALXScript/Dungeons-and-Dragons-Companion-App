package com.example.victor.myapplication.Fragments;

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

import com.example.victor.myapplication.Classes.DatabaseAccess;
import com.example.victor.myapplication.R;

import java.util.List;

public class InventoryFragment extends Fragment {

    //General Listview Variables
    ListView inventoryListView;
    DatabaseAccess myDatabaseAccess;
    List<String> itemNames;
    ArrayAdapter<String> adapter;
    //Item Info Variables
    Dialog myDialog;
    TextView itemNameTextView;
    Button removeItemBttn;
    Button closeBttn;
    TextView itemSource;
    TextView itemType;
    TextView itemDesc;
    TextView itemCount;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        super.onCreate(savedInstanceState);

        inventoryListView = (ListView) view.findViewById(R.id.listViewInventory);
        myDatabaseAccess = DatabaseAccess.getInstance(this.getContext());
        myDatabaseAccess.open();
        itemNames = myDatabaseAccess.fillInventory();

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, itemNames);
        inventoryListView.setAdapter(adapter);

        myDialog = new Dialog(getContext()); //for item infoinventory popup

        getIDFromListView();

        return view;
    }

    private void getItemInfo(int id, Dialog myDialog) {
        Cursor data = myDatabaseAccess.getData();

        itemSource = (TextView) myDialog.findViewById(R.id.itemSourceTextView);
        itemType = (TextView) myDialog.findViewById(R.id.itemTypeTextView);
        itemDesc = (TextView) myDialog.findViewById(R.id.itemDescTextView);
        itemNameTextView = (TextView) myDialog.findViewById(R.id.itemNameTextView);
        itemCount = (TextView) myDialog.findViewById(R.id.itemCountTextView);

        while (data.moveToNext()) {
            if (data.getInt(0) == id) {
                itemSource.setText(data.getString(3));
                itemType.setText(data.getString(4));
                itemDesc.setText(data.getString(2));
                itemNameTextView.setText(data.getString(1));
            }
        }

        itemCount.setText("QTY: " + Integer.toString(myDatabaseAccess.getExistingItemCount(id)));
    }

    private void getIDFromListView() {
        //set an onItemClickListener to the ListView
        inventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                Cursor data = myDatabaseAccess.getItemIDitems(name); //get the id associated with that name
                int itemID = -1;

                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }

                if (itemID > -1) {

                    myDialog.setContentView(R.layout.popup_iteminfoinventory);

                    getItemInfo(itemID, myDialog);

                    removeItemBttn = (Button) myDialog.findViewById(R.id.itemRemoveInventoryBtn);
                    closeBttn = (Button) myDialog.findViewById(R.id.closeBtn);

                    closeBttn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });

                    final int finalItemID = itemID;
                    removeItemBttn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int itemCount = myDatabaseAccess.getExistingItemCount(finalItemID);

                            if(itemCount > 1) {
                                myDatabaseAccess.removeFromInventoriesCount(finalItemID, itemCount-1); //remove 1 from count
                                getItemInfo(finalItemID, myDialog);
                            }

                            else {
                                myDatabaseAccess.deleteItemFromInv(finalItemID);
                                adapter.remove(adapter.getItem(i));
                                adapter.notifyDataSetChanged();
                                myDialog.dismiss();
                                toastMessage("Item removed from inventory!");
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
