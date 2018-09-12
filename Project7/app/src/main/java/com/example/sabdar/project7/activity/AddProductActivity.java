package com.example.sabdar.project7.activity;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sabdar.project7.R;
import com.example.sabdar.project7.data.InventoryContract;
import com.example.sabdar.project7.data.InventoryContract.InventoryEntry;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddProductActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOG_TAG = AddProductActivity.class.getSimpleName();
    private static final int EXISTING_PROUDCT_LOADER = 2;

    @BindView(R.id.productNameTextLayout)
    TextInputLayout productNameTextInputLayout;
    @BindView(R.id.productQuantityTextLayout)
    TextInputLayout productQuantityTextInputLayout;
    @BindView(R.id.productPriceTextLayout)
    TextInputLayout productPriceTextInputLayout;
    @BindView(R.id.supplierNameTextLayout)
    TextInputLayout supplierNameTextInputLayout;
    @BindView(R.id.supplierPhoneNumberTextLayout)
    TextInputLayout supplierPhoneNumberTextInputLayout;

    private Uri mCurrentUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mCurrentUri = intent.getData();
        if (mCurrentUri == null) {
            setTitle(getString(R.string.add_product));
            invalidateOptionsMenu();
        } else {
            setTitle(getString(R.string.edit_product));
            getLoaderManager().initLoader(EXISTING_PROUDCT_LOADER, null, this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_product, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (mCurrentUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_submit:
                saveProduct();
                break;
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

    private void deleteProduct() {
        if (mCurrentUri != null) {
            int rowsDeleted = getContentResolver().delete(mCurrentUri, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, R.string.product_delete_error, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.product_delete_success, Toast.LENGTH_SHORT).show();
            }
        }
        finish();

    }

    private void saveProduct() {

        String productName = productNameTextInputLayout.getEditText().getText().toString();
        String quantityString = productQuantityTextInputLayout.getEditText().getText().toString();
        String priceString = productPriceTextInputLayout.getEditText().getText().toString();
        String supplierName = supplierNameTextInputLayout.getEditText().getText().toString();
        String supplierPhoneNumber = supplierPhoneNumberTextInputLayout.getEditText().getText().toString();

        if (TextUtils.isEmpty(productName)) {
            Toast.makeText(this, R.string.product_name_required, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(quantityString)) {
            Toast.makeText(this, R.string.quantity_required, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(priceString)) {
            Toast.makeText(this, R.string.price_required, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(supplierName)) {
            Toast.makeText(this, R.string.supplier_name_required, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(supplierPhoneNumber)) {
            Toast.makeText(this, R.string.supplier_phone_number_required, Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.parseInt(productQuantityTextInputLayout.getEditText().getText().toString());
        if (quantity <= 0) {
            Toast.makeText(this, R.string.valid_quantity, Toast.LENGTH_SHORT).show();
            return;
        }

        Double price = Double.valueOf(productPriceTextInputLayout.getEditText().getText().toString());
        if (price <= 0) {
            Toast.makeText(this, R.string.valid_price, Toast.LENGTH_SHORT).show();
            return;
        }

        if (supplierPhoneNumber.length() != 10) {
            Toast.makeText(this, R.string.valid_supplier_number, Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, productName);
        values.put(InventoryEntry.COLUMN_QUANTITY, quantity);
        values.put(InventoryEntry.COLUMN_PRICE, price);
        values.put(InventoryEntry.COLUMN_SUPPLIER_NAME, supplierName);
        values.put(InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER, supplierPhoneNumber);

        if (mCurrentUri == null) {
            Uri uri = getContentResolver().insert(InventoryEntry.CONTENT_URI, values);
            long rowId = ContentUris.parseId(uri);
            if (rowId != -1) {
                Toast.makeText(this, String.format(getString(R.string.product_added_id_text), rowId), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.product_save_error, Toast.LENGTH_SHORT).show();
            }
        } else {
            int rowsAffected = getContentResolver().update(mCurrentUri, values, null, null);
            if (rowsAffected == 0) {
                Toast.makeText(this, R.string.product_update_error, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.product_update_success, Toast.LENGTH_SHORT).show();

            }
        }
        finish();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryEntry.COLUMN_PRICE,
                InventoryEntry.COLUMN_QUANTITY,
                InventoryEntry.COLUMN_SUPPLIER_NAME,
                InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER
        };
        return new CursorLoader(this, mCurrentUri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor.moveToFirst()) {
            String productName = cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME));
            double price = cursor.getDouble(cursor.getColumnIndex(InventoryEntry.COLUMN_PRICE));
            int quantity = cursor.getInt(cursor.getColumnIndex(InventoryEntry.COLUMN_QUANTITY));
            String supplierName = cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_SUPPLIER_NAME));
            String supplierPhoneNumber = cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER));

            productNameTextInputLayout.getEditText().setText(productName);
            productPriceTextInputLayout.getEditText().setText(String.valueOf(price));
            productQuantityTextInputLayout.getEditText().setText(String.valueOf(quantity));
            supplierNameTextInputLayout.getEditText().setText(supplierName);
            supplierPhoneNumberTextInputLayout.getEditText().setText(supplierPhoneNumber);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_product);
        builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteProduct();
            }
        });

        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
