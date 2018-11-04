package com.example.victor.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

//General database functions -----------------------------------------------------------------
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    //Itembook database functions -----------------------------------------------------------------
    public Cursor getData(){
        String query = "SELECT * FROM items";
        Cursor data = database.rawQuery(query, null);
        return data;
    }

    public List<String> getItemNames() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM items", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public Cursor getItemIDitems(String name){
        String query = "SELECT " + "id" + " FROM " + "items" +
                " WHERE " + "name" + " = '" + name + "'";
        Cursor data = database.rawQuery(query, null);
        return data;
    }

    public void deleteItemFromItembook(int id){
        String query = "DELETE FROM " + "items" + " WHERE "
                + "id" + " = '" + id + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + id + " from database.");
        database.execSQL(query);
    }

    public boolean addItemToItembook(String itemName, String descr, String source1, String type1) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", itemName);
        contentValues.put("desc", descr);
        contentValues.put("source", source1);
        contentValues.put("type", type1);

        long result = database.insert("items", null, contentValues);

        //if data is inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Inventory database functions -----------------------------------------------------------------
    public boolean isIteminInventories(int id){

        String idToCheck = Integer.toString(id);
        boolean inInventories = false;
        int idMatched = -1;

        String query = "SELECT " + "id" + " FROM " + "inventories" +
                " WHERE " + "id" + " = '" + idToCheck + "'";
        Cursor data = database.rawQuery(query, null);

        while(data.moveToNext())
        {
            idMatched = data.getInt(0);
        }

        data.close();

        if(idMatched > 0)
            inInventories = true;

        return inInventories;
    }

    public List<String> fillInventory() {
        List<String> list = new ArrayList<>();
        String query = "SELECT name FROM items, inventories WHERE items.id = inventories.id";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public boolean addToInventories(int idchar, int id, int myCount) {

        ContentValues contentValue = new ContentValues();

        contentValue.put("idchar", idchar);
        contentValue.put("id", id);
        contentValue.put("count", myCount); //hard code to 1 for now need to add to count

        Log.d(TAG, "addData: Adding " + id + " to " + "inventories");

        long result = database.insert("inventories", null, contentValue);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public void addToInventoriesCount(int id, int myCount) {

        String newCount = Integer.toString(myCount);
        String idToCheck = Integer.toString(id);

        String query = "UPDATE " + "inventories" + " SET " + "count" +
                " = '" + newCount + "' WHERE " + "id" + " = '" + idToCheck + "'";

        database.execSQL(query);
    }

    public void removeFromInventoriesCount(int id, int myCount) {

        String newCount = Integer.toString(myCount);
        String idToCheck = Integer.toString(id);

        String query = "UPDATE " + "inventories" + " SET " + "count" +
                " = '" + newCount + "' WHERE " + "id" + " = '" + idToCheck + "'";

        database.execSQL(query);
    }

    public void deleteItemFromInv(int id){
        String query = "DELETE FROM " + "inventories" + " WHERE "
                + "id" + " = '" + id + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + id + " from database.");
        database.execSQL(query);
    }

    public int getExistingItemCount(int id){

        String idToCheck = Integer.toString(id);
        int finalCount = -1;

        String query = "SELECT " + "count" + " FROM " + "inventories" +
                " WHERE " + "id" + " = '" + idToCheck + "'";

        Cursor data = database.rawQuery(query, null);

        while(data.moveToNext()) {
            finalCount = data.getInt(0);
        }

        data.close();

        return finalCount;
    }

}
