package com.example.sabdar.project7;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sabdar.project7.data.InventoryContract.InventoryEntry;
import com.example.sabdar.project7.data.InventoryDbHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.textView)
    TextView textView;
    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mDbHelper = new InventoryDbHelper(this);

        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        String BAR_SEPARATE = getString(R.string.bar_separate);
        String NEW_LINE = getString(R.string.new_line_text);

        int count;
        Cursor cursor = queryData();
        if (cursor != null) {
            count = cursor.getCount();
            StringBuilder output = new StringBuilder();
            output.append(String.format(getString(R.string.total_rows_text), count));

            output.append(NEW_LINE);
            output.append(NEW_LINE);
            output.append(getString(R.string.row_heading));
            output.append(NEW_LINE);
            output.append(NEW_LINE);

            while (cursor.moveToNext()) {
                int idColumnIndex = cursor.getColumnIndex(InventoryEntry._ID);
                int productNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME);
                int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRICE);
                int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_QUANTITY);
                int supplierNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_SUPPLIER_NAME);
                int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

                String id = String.valueOf(cursor.getInt(idColumnIndex));
                String productName = cursor.getString(productNameColumnIndex);
                String price = String.valueOf(cursor.getDouble(priceColumnIndex));
                String quantity = String.valueOf(cursor.getInt(quantityColumnIndex));
                String supplierName = cursor.getString(supplierNameColumnIndex);
                String supplierPhoneNumber = cursor.getString(supplierPhoneNumberColumnIndex);
                //append row data to TextView
                output.append(id);
                output.append(BAR_SEPARATE);
                output.append(productName);
                output.append(BAR_SEPARATE);
                output.append(price);
                output.append(BAR_SEPARATE);
                output.append(quantity);
                output.append(BAR_SEPARATE);
                output.append(supplierName);
                output.append(BAR_SEPARATE);
                output.append(supplierPhoneNumber);
                //add new line
                output.append(NEW_LINE);
            }
            textView.setText(output);
            cursor.close();
        }
    }

    private void insertData() {

        int count;
        Cursor cursor = queryData();
        if (cursor != null) {
            count = cursor.getCount();
            cursor.close();
        } else {
            count = 0;
        }


        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String productName = String.format(getString(R.string.product), count + 1);
        String supplierName = String.format(getString(R.string.supplier), count + 1);
        int quantity = (int) (Math.random() * 10 * (count + 1));
        Double price = 23.00 * (count + 1);

        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, productName);
        values.put(InventoryEntry.COLUMN_QUANTITY, quantity);
        values.put(InventoryEntry.COLUMN_PRICE, price);
        values.put(InventoryEntry.COLUMN_SUPPLIER_NAME, supplierName);
        values.put(InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER, getString(R.string.phone_number));

        long newRowId = db.insert(InventoryEntry.TABLE_NAME, null, values);
        String output = String.format(getString(R.string.product_insert), newRowId);
        Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
    }

    private Cursor queryData() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryEntry.COLUMN_PRICE,
                InventoryEntry.COLUMN_QUANTITY,
                InventoryEntry.COLUMN_SUPPLIER_NAME,
                InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER
        };

        Cursor cursor = db.query(
                InventoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        return cursor;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_insert_dummy_data:
                insertData();
                displayDatabaseInfo();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
