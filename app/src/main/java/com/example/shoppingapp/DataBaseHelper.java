package com.example.shoppingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

        ByteArrayOutputStream outputImage = new ByteArrayOutputStream();
        //product.getProductImage().compress(Bitmap.CompressFormat.PNG,0,outputImage);
        product.getProductImage();

        Bitmap imgByteData = product.getProductImage();

        contentValues.put(IMAGES, String.valueOf(imgByteData));
        contentValues.put(COLUMN_PRODUCT_NAME, product.getProductName());
        contentValues.put(COLUMN_CATEGORY, String.valueOf(product.getCategory()));
        contentValues.put(COLUMN_PRICE, String.valueOf(product.getProductPrice()));

        //insert data to the product table
        long insert = db.insert(PRODUCT_TABLE,null, contentValues);
        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean addImageToDB (Bitmap img) throws IOException, IOException {
        SQLiteDatabase db = this.getWritableDatabase();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG,0,outputStream);
        byte[] imgByteData = outputStream.toByteArray();


        ContentValues cv = new ContentValues();
        cv.put(IMAGES, imgByteData);

        long insert = db.insert(PRODUCT_TABLE, null, cv);
        outputStream.close();

        if(insert == -1){
            db.close();
            return false;
        }else{
            db.close();
            return true;
        }
    }
    public Bitmap getImage(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String get_image_query = "SELECT * FROM " + PRODUCT_TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(get_image_query,null);

        if(cursor.moveToFirst()){
            byte[] imageByteData = cursor.getBlob(1);
            cursor.close();
            return BitmapFactory.decodeByteArray(imageByteData,0,imageByteData.length);
        }

        return null;
    }

    public ArrayList<Bitmap> getAllImages(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Bitmap> reuslt = new ArrayList<>();

        String get_image_query = "SELECT * FROM " + PRODUCT_TABLE ;

        Cursor cursor = db.rawQuery(get_image_query,null);

        if(cursor.moveToFirst()){
            do{
                byte[] imageByteData = cursor.getBlob(1);
                reuslt.add(BitmapFactory.decodeByteArray(imageByteData,0,imageByteData.length));
            }while (cursor.moveToNext());

        }

        cursor.close();
        return reuslt;
    }


    public List<Product> getAllProduct(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Product> products = new ArrayList<>();

        String getProduct = "SELECT * FROM " + PRODUCT_TABLE ;
        Cursor cursor = db.rawQuery(getProduct,null);

        if(cursor.moveToFirst()){
            do {
                int productId = cursor.getInt(0);
                byte[] imageId = cursor.getBlob(1);
                String productName = cursor.getString(2);
                String category = cursor.getString(3);
                Double price = cursor.getDouble(4);

                Product product = new Product(productId, BitmapFactory.decodeByteArray(imageId,0,imageId.length),productName,category, price);

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
    public ArrayList<String> getAccessoriesTitle(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> products = new ArrayList<>();

        String getProduct = "SELECT " + COLUMN_PRODUCT_NAME + " FROM " + PRODUCT_TABLE ;
        Cursor cursor = db.rawQuery(getProduct,null);

        if(cursor.moveToFirst()){
            do {

                String productName = cursor.getString(2);


               // Product product = new Product(productId, BitmapFactory.decodeByteArray(imageId,0,imageId.length),productName,category, price);

                products.add(productName);

            }while (cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return products;
    }
    public ArrayList<String> getAccessoriesPrice(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> products = new ArrayList<>();

        String getProduct = "SELECT " + COLUMN_PRICE + " FROM " + PRODUCT_TABLE ;
        Cursor cursor = db.rawQuery(getProduct,null);

        if(cursor.moveToFirst()){
            do {

                String productPrice = cursor.getString(1);

                products.add(productPrice);

            }while (cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return products;
    }
    public ArrayList<Bitmap> getAccessoriesImages(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Bitmap> products = new ArrayList<>();

        String getProduct = "SELECT " + IMAGES + " FROM " + PRODUCT_TABLE ;
        Cursor cursor = db.rawQuery(getProduct,null);

        if(cursor.moveToFirst()){
            do {

                byte[] imageByteData = cursor.getBlob(1);

                products.add(BitmapFactory.decodeByteArray(imageByteData,0,imageByteData.length));

            }while (cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return products;
    }

}
