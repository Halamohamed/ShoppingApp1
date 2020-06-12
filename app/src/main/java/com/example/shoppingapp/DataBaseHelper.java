package com.example.shoppingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

// Constants for table name and column name
    private static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    private static final String COLUMN_PRODUCT_NAME = "COLUMN_PRODUCT_NAME";
    private static final String COLUMN_CATEGORY = "COLUMN_CATEGORY";
    private static final String COLUMN_AMOUNT = "COLUMN_AMOUNT";
    private static final String COLUMN_PRICE = "COLUMN_PRICE";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "Shopping.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Code to create product table in database
        String createTableQuery = "CREATE TABLE " + PRODUCT_TABLE + "( ID  INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PRODUCT_NAME + " TEXT , " + COLUMN_CATEGORY + " TEXT , " + COLUMN_AMOUNT + " INTEGER , " + COLUMN_PRICE + " DECIMAL)";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_PRODUCT_NAME, product.getProductName());
        contentValues.put(COLUMN_CATEGORY, String.valueOf(product.getCategory()));
        contentValues.put(COLUMN_AMOUNT, String.valueOf(product.getAmount()));
        contentValues.put(COLUMN_PRICE, String.valueOf(product.getProductPrice()));

        //insert data to the product table
        long insert = db.insert(PRODUCT_TABLE,null, contentValues);
        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }

}
