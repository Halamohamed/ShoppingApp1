package com.example.shoppingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

// Constants for table name and column name
    private static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    private static final String COLUMN_PRODUCT_NAME = "COLUMN_PRODUCT_NAME";
    private static final String COLUMN_CATEGORY = "COLUMN_CATEGORY";
    private static final String COLUMN_AMOUNT = "COLUMN_AMOUNT";
    private static final String COLUMN_PRICE = "COLUMN_PRICE";
    private static final String IMAGES = "IMAGES";
    private static final String ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "Shopping.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Code to create product table in database
        String createTableQuery = "CREATE TABLE " + PRODUCT_TABLE + "( " + ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," + " " + IMAGES + " BLOB ," +
                COLUMN_PRODUCT_NAME + " TEXT , " + COLUMN_CATEGORY + " TEXT , " + COLUMN_AMOUNT + " INTEGER , " + COLUMN_PRICE + " DECIMAL)";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(IMAGES, product.getProductImage());
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

    public List<Product> getAllProduct(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Product> products = new ArrayList<>();

        String getProduct = "SELECT * FROM " + PRODUCT_TABLE ;
        Cursor cursor = db.rawQuery(getProduct,null);

        if(cursor.moveToFirst()){
            do {
                int productId = cursor.getInt(0);
                String imageId = cursor.getString(1);
                String productName = cursor.getString(2);
                String category = cursor.getString(3);
                int amount = cursor.getInt(4);
                double price = cursor.getDouble(5);

                Product product = new Product(productId,productName, imageId,amount,price);

                products.add(product);

            }while (cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return products;
    }

    public boolean deleteProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();

        String deleteProductQuery = "DELETE FROM " + PRODUCT_TABLE + " WHERE " + ID +  " = " + product.getId();

        Cursor cursor = db.rawQuery(deleteProductQuery,null);
        if(cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }

}
