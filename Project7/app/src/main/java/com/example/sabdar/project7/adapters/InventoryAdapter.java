package com.example.sabdar.project7.adapters;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sabdar.project7.R;
import com.example.sabdar.project7.activity.AddProductActivity;
import com.example.sabdar.project7.activity.MainActivity;
import com.example.sabdar.project7.data.InventoryContract.InventoryEntry;


public class InventoryAdapter extends CursorAdapter {

    public InventoryAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView productName = view.findViewById(R.id.productName);
        TextView quantityValue = view.findViewById(R.id.quantityValue);
        TextView priceValue = view.findViewById(R.id.priceValue);
        final Button saleButton = view.findViewById(R.id.saleButton);
        final Button editButton = view.findViewById(R.id.editButton);

        String name = cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME));
        final int quantity = cursor.getInt(cursor.getColumnIndex(InventoryEntry.COLUMN_QUANTITY));
        double price = cursor.getDouble(cursor.getColumnIndex(InventoryEntry.COLUMN_PRICE));

        final int id = cursor.getInt(cursor.getColumnIndex(InventoryEntry._ID));


        productName.setText(name);
        quantityValue.setText(String.valueOf(quantity));
        priceValue.setText(String.valueOf(price));

        final Uri uri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, id);

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saleButton.setEnabled(false);
                if (quantity == 0) {
                    Toast.makeText(v.getContext(), R.string.product_not_available, Toast.LENGTH_SHORT).show();
                } else {
                    int newQuantity = quantity - 1;
                    ContentValues values = new ContentValues();
                    values.put(InventoryEntry.COLUMN_QUANTITY, newQuantity);
                    int rowsAffected = context.getContentResolver().update(uri, values, null, null);
                    if (rowsAffected == 1) {
                        Toast.makeText(v.getContext(), R.string.product_sale_success, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(v.getContext(), R.string.product_sale_fail, Toast.LENGTH_SHORT).show();
                    }
                }
                saleButton.setEnabled(true);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddProductActivity.class);
                intent.setData(uri);
                context.startActivity(intent);
            }
        });

    }
}
