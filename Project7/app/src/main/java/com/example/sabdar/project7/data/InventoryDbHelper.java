package com.example.sabdar.project7.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sabdar.project7.data.InventoryContract.InventoryEntry;


public class InventoryDbHelper extends SQLiteOpenHelper {
    //database name
    public static final String DATABASE_NAME = "inventory.db";
    //database version
    public static final int DATABASE_VERSION = 1;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table query
        String CREATE_INVENTORY_TABLE = "CREATE TABLE " + InventoryEntry.TABLE_NAME + " ("
                + InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_PRICE + " REAL NOT NULL, "
                + InventoryEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, "
                + InventoryEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL );";
        //execute query for create table
        db.execSQL(CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
