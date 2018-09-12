package com.example.sabdar.project7.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sabdar.project7.R;
import com.example.sabdar.project7.data.InventoryContract;
import com.example.sabdar.project7.data.InventoryContract.InventoryEntry;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_PRODUCT_LOADER = 3;
    private static final String QUANTITY_RATE = "quantity_rate";
    private Uri mCurrentUri;

    @BindView(R.id.detailsProductName)
    TextView detailsProductName;
    @BindView(R.id.detailsPrice)
    TextView detailsPrice;
    @BindView(R.id.detailsQuantity)
    TextView detailsQuantity;
    @BindView(R.id.detailsSupplierName)
    TextView detailsSupplierName;
    @BindView(R.id.detailsSupplierPhoneNumber)
    TextView detailsSupplierPhoneNumber;
    @BindView(R.id.detailsQuantityRate)
    EditText detailsQuantityRate;
    @BindView(R.id.detailsIncreaseQuantity)
    Button increaseButton;
    @BindView(R.id.detailsDecreaseQuantity)
    Button decreaseButton;
    @BindView(R.id.detailsOrderButton)
    Button orderButton;
    @BindView(R.id.detailsDeleteButton)
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        setTitle(getString(R.string.product_details));

        Intent intent = getIntent();
        mCurrentUri = intent.getData();
        if (mCurrentUri == null) {
            finish();
        }
        addDefaultQuantity();
        getLoaderManager().initLoader(EXISTING_PRODUCT_LOADER, null, this);
    }

    private void addDefaultQuantity() {
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
        int quantityRate = sp.getInt(QUANTITY_RATE, 1);
        detailsQuantityRate.setText(Integer.toString(quantityRate));
    }

    private void updateQuantityRate() {
        int quantity = Integer.parseInt(detailsQuantityRate.getText().toString());
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(QUANTITY_RATE, quantity);
        detailsQuantityRate.setText(Integer.toString(quantity));
        editor.commit();
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

            detailsProductName.setText(productName);
            detailsPrice.setText(String.format(getString(R.string.format_price), convertPrice(price)));
            detailsQuantity.setText(String.valueOf(quantity));
            detailsSupplierName.setText(supplierName);
            detailsSupplierPhoneNumber.setText(supplierPhoneNumber);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private String convertPrice(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }

    @OnClick(R.id.detailsOrderButton)
    public void orderProduct() {
        String phoneNumber = detailsSupplierPhoneNumber.getText().toString();
        Intent dialIntent = new Intent();
        dialIntent.setAction(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse(String.format("tel:%s", phoneNumber)));
        startActivity(dialIntent);
    }

    private void deleteProduct() {
        deleteButton.setEnabled(false);
        if (mCurrentUri != null) {
            int rowsDeleted = getContentResolver().delete(mCurrentUri, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, R.string.product_delete_error, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.product_delete_success, Toast.LENGTH_SHORT).show();
            }
        }
        deleteButton.setEnabled(true);
        finish();
    }

    @OnClick(R.id.detailsDecreaseQuantity)
    public void decreaseQuantity() {
        decreaseButton.setEnabled(false);
        updateQuantity(2);
        decreaseButton.setEnabled(true);

    }

    @OnClick(R.id.detailsIncreaseQuantity)
    public void increaseQuantity() {
        increaseButton.setEnabled(false);
        updateQuantity(1);
        increaseButton.setEnabled(true);

    }

    private void updateQuantity(int type) {
        String quantityRateString = detailsQuantityRate.getText().toString();
        if (TextUtils.isEmpty(quantityRateString)) {
            Toast.makeText(this, "Invalid Quantity Rate", Toast.LENGTH_SHORT).show();
            return;
        }
        int quantityRate = Integer.parseInt(quantityRateString);
        if (quantityRate < 1) {
            Toast.makeText(this, R.string.valid_quantity_rate, Toast.LENGTH_SHORT).show();
            return;
        }
        int quantity = Integer.parseInt(detailsQuantity.getText().toString());
        int newQuantity;
        if (type == 1) {
            newQuantity = quantity + quantityRate;
        } else {
            newQuantity = quantity - quantityRate;
            if (newQuantity < 0) {
                Toast.makeText(this, R.string.valid_quantity_rate, Toast.LENGTH_SHORT).show();
                return;
            }
        }
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_QUANTITY, newQuantity);

        int rowsAffected = getContentResolver().update(mCurrentUri, values, null, null);

        if (rowsAffected == 1) {
            updateQuantityRate();
            Toast.makeText(this, R.string.quantity_update_success, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isTelephonyEnabled() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        return telephonyManager != null && telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY;
    }

    @OnClick(R.id.detailsDeleteButton)
    public void showDeleteConfirmationDialog() {
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
