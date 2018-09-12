package com.example.sabdar.project7.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.example.sabdar.project7.data.InventoryContract.*;

import com.example.sabdar.project7.data.InventoryDbHelper;


public class InventoryProvider extends ContentProvider {

    public static final String LOG_TAG = InventoryProvider.class.getSimpleName();

    private InventoryDbHelper mInventoryDbHelper;

    private static final int INVENTORY = 100;
    private static final int INVENTORY_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_INVENTORY, INVENTORY);
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_INVENTORY + "/#", INVENTORY_ID);
    }

    @Override
    public boolean onCreate() {
        mInventoryDbHelper = new InventoryDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = mInventoryDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case INVENTORY_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot Query unknown URI" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return InventoryEntry.CONTENT_LIST_TYPE;
            case INVENTORY_ID:
                return InventoryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri" + uri + "with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri insertUri;
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                insertUri = insertInventory(uri, values);
                break;
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
        return insertUri;
    }

    private Uri insertInventory(Uri uri, ContentValues values) {

        String productName = values.getAsString(InventoryEntry.COLUMN_PRODUCT_NAME);
        if (productName == null) {
            throw new IllegalArgumentException("Product Name is required");
        }
        Double price = values.getAsDouble(InventoryEntry.COLUMN_PRICE);
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Product Price is required");
        }
        Integer quantity = values.getAsInteger(InventoryEntry.COLUMN_QUANTITY);
        if (quantity == null || quantity < 0) {
            throw new IllegalArgumentException("Invalid Product Quantity");
        }
        String supplierName = values.getAsString(InventoryEntry.COLUMN_SUPPLIER_NAME);
        if(supplierName == null){
            throw new IllegalArgumentException("Supplier Name is Required");
        }
        String supplierNumber = values.getAsString(InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER);
        if(supplierNumber == null || supplierNumber.length() !=10){
            throw new IllegalArgumentException("Valid 10 Digit Supplier Phone Number is required");
        }

        SQLiteDatabase database = mInventoryDbHelper.getWritableDatabase();
        long rowId = database.insert(InventoryEntry.TABLE_NAME, null, values);
        if (rowId == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, rowId);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mInventoryDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int result;
        switch (match) {
            case INVENTORY:
                result = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case INVENTORY_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                result = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Delete is not supported for " + uri);
        }

        if (result != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return result;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        int id;
        switch (match) {
            case INVENTORY:
                id = updateInventory(uri, values, selection, selectionArgs);
                break;
            case INVENTORY_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                id = updateInventory(uri, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
        return id;
    }

    private int updateInventory(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if(values.containsKey(InventoryEntry.COLUMN_PRODUCT_NAME)) {
            String productName = values.getAsString(InventoryEntry.COLUMN_PRODUCT_NAME);
            if (productName == null) {
                throw new IllegalArgumentException("Product Name is required");
            }
        }
        if(values.containsKey(InventoryEntry.COLUMN_PRICE)) {
            Double price = values.getAsDouble(InventoryEntry.COLUMN_PRICE);
            if (price == null || price < 0) {
                throw new IllegalArgumentException("Product Price is required");
            }
        }
        if(values.containsKey(InventoryEntry.COLUMN_QUANTITY)) {
            Integer quantity = values.getAsInteger(InventoryEntry.COLUMN_QUANTITY);
            if (quantity == null || quantity < 0) {
                throw new IllegalArgumentException("Invalid Product Quantity");
            }
        }
        if(values.containsKey(InventoryEntry.COLUMN_SUPPLIER_NAME)) {
            String supplierName = values.getAsString(InventoryEntry.COLUMN_SUPPLIER_NAME);
            if (supplierName == null) {
                throw new IllegalArgumentException("Supplier Name is Required");
            }
        }
        if(values.containsKey(InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER)) {
            String supplierNumber = values.getAsString(InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER);
            if (supplierNumber == null || supplierNumber.length() != 10) {
                throw new IllegalArgumentException("Valid 10 Digit Supplier Phone Number is required");
            }
        }
        SQLiteDatabase database = mInventoryDbHelper.getWritableDatabase();
        int rowsAffected = database.update(InventoryEntry.TABLE_NAME, values, selection, selectionArgs);
        if (rowsAffected != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsAffected;
    }
}
