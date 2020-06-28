package com.example.shoppingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;


import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private final String accessoryImage = "https://i.imgur.com/zYY9thC.jpg";
    private final String dressImage = "https://i.imgur.com/0PcS4QH.png";
    private final String jeansImage =  "https://i.imgur.com/I6SbsYs.jpg";
    private final String jumpsuitImage = "https://i.imgur.com/qIAHOi8.jpg";
    private final String kidsDressImage = "https://i.imgur.com/Cxz5XeE.jpg";
    private final String shoesImage = "https://i.imgur.com/DqSYAZM.jpg";
    private final String shoesImage1 = "https://i.imgur.com/2KDMEYq.jpg";
    private final String shoesImage2 = "https://i.imgur.com/RsfigNt.jpg";

    private final String manShirt = "https://i.imgur.com/3F7GpoP.jpg";
    private final String kidsPyjamas = "https://i.imgur.com/m1EjVfZ.jpg";

    private final String fashionAccessory = "https://i.imgur.com/z5guBd4.jpg";
    private final String dressLong = "https://i.imgur.com/s2mdepq.jpg";
    private final String kidsTShirts = "https://i.imgur.com/Lx1ctvD.jpg";

// Constants for table name and column name
    private static final String ACCESSORY_TABLE = "ACCESSORIES_TABLE";
    private static final String CLOTHES_TABLE = "CLOTHES_TABLE";
    private static final String SHOES_TABLE = "SHOES_TABLE";
    private static final String COLUMN_PRODUCT_NAME = "COLUMN_PRODUCT_NAME";
    private static final String COLUMN_CATEGORY = "COLUMN_CATEGORY";
    private static final String COLUMN_PRICE = "COLUMN_PRICE";
    private static final String IMAGES = "IMAGES";
    private static final String ID = "ID";
    //private SQLiteDatabase db;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "Shopping.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Code to create product tables in database
        String createAccessoriesTableQuery = "CREATE TABLE " + ACCESSORY_TABLE + "( " + ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," + " " + IMAGES + " BLOB ," +
                COLUMN_PRODUCT_NAME + " TEXT , " + COLUMN_CATEGORY + " TEXT , " + COLUMN_PRICE + " DECIMAL)";
        db.execSQL(createAccessoriesTableQuery);

        String createClothesTableQuery = "CREATE TABLE " + CLOTHES_TABLE + "( " + ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," + " " + IMAGES + " BLOB ," +
                COLUMN_PRODUCT_NAME + " TEXT , " + COLUMN_CATEGORY + " TEXT , " + COLUMN_PRICE + " DECIMAL)";
        db.execSQL(createClothesTableQuery);

        String createShoesTableQuery = "CREATE TABLE " + SHOES_TABLE + "( " + ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," + " " + IMAGES + " BLOB ," +
                COLUMN_PRODUCT_NAME + " TEXT , " + COLUMN_CATEGORY + " TEXT , " + COLUMN_PRICE + " DECIMAL)";
        db.execSQL(createShoesTableQuery);

        //add products to database
        addAccessoriesProductToDB();
        addClothesProductToDB();
        addShoesProductToDB();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addAccessoryProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ByteArrayOutputStream outputImage = new ByteArrayOutputStream();
        Bitmap img = product.getProductImage();
        img.compress(Bitmap.CompressFormat.PNG,0,outputImage);
        byte[] imgByteData = outputImage.toByteArray();

        contentValues.put(IMAGES, imgByteData);
        contentValues.put(COLUMN_PRODUCT_NAME, product.getProductName());
        contentValues.put(COLUMN_CATEGORY, String.valueOf(product.getCategory()));
        contentValues.put(COLUMN_PRICE, String.valueOf(product.getProductPrice()));

        //insert data to the product table
        long insert = db.insert(ACCESSORY_TABLE,null, contentValues);
        if(insert == -1){
            db.close();
            return false;
        }else {
            db.close();
            return true;
        }
    }

    public boolean addClothProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ByteArrayOutputStream outputImage = new ByteArrayOutputStream();
        Bitmap img = product.getProductImage();
        img.compress(Bitmap.CompressFormat.JPEG,0,outputImage);
        byte[] imgByteData = outputImage.toByteArray();

        contentValues.put(IMAGES, String.valueOf(imgByteData));
        contentValues.put(COLUMN_PRODUCT_NAME, product.getProductName());
        contentValues.put(COLUMN_CATEGORY, String.valueOf(product.getCategory()));
        contentValues.put(COLUMN_PRICE, String.valueOf(product.getProductPrice()));

        //insert data to the product table
        long insert = db.insert(CLOTHES_TABLE,null, contentValues);
        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean addShoesProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        ByteArrayOutputStream outputImage = new ByteArrayOutputStream();
        Bitmap img = product.getProductImage();
        img.compress(Bitmap.CompressFormat.JPEG,0,outputImage);
        byte[] imgByteData = outputImage.toByteArray();
        contentValues.put(IMAGES, String.valueOf(imgByteData));
        contentValues.put(COLUMN_PRODUCT_NAME, product.getProductName());
        contentValues.put(COLUMN_CATEGORY, String.valueOf(product.getCategory()));
        contentValues.put(COLUMN_PRICE, String.valueOf(product.getProductPrice()));

        //insert data to the product table
        long insert = db.insert(SHOES_TABLE,null, contentValues);
        if(insert == -1){

            return false;
        }else {

            return true;
        }
    }

    public void addAccessoriesProductToDB(){
        Picasso.get().load(accessoryImage).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Product product1 = new Product(0,bitmap, "Accessory","ACCESSORIES", 450.0);
                addAccessoryProduct(product1);
            }
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) { }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });
        Picasso.get().load(accessoryImage).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Product product3 = new Product(2,bitmap, "Accessory","ACCESSORIES", 450.0);
                addAccessoryProduct(product3);
            }
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) { }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });

        Picasso.get().load(fashionAccessory).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Product product2 = new Product(1,bitmap, "Fashion Accessory","ACCESSORIES", 550.0);
                addAccessoryProduct(product2);
            }
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) { }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });

    }
    public void addClothesProductToDB(){
        Picasso.get().load(dressImage).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                final Product product1 = new Product(0,bitmap, "Dress","CLOTHES", 350.0);
                addClothProduct(product1);
            }
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) { }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });
        Picasso.get().load(dressLong).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Product product2 = new Product(1,bitmap, "Long Dress","CLOTHES", 390.0);
                addClothProduct(product2); }
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });
        Picasso.get().load(jeansImage).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Product product3 = new Product(2,bitmap, "Jeans","CLOTHES", 450.0);
                addClothProduct(product3);
            }
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) { }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });
        Picasso.get().load(manShirt).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Product product4 = new Product(4,BitmapFactory.decodeByteArray(manShirt.getBytes(),0,manShirt.length()), "Shirt","CLOTHES", 250.0);
                addClothProduct(product4);
            }
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) { }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });
        Picasso.get().load(kidsDressImage).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Product product5 = new Product(5,bitmap, "Kids Dress","CLOTHES", 350.0);
                addClothProduct(product5);
            }
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) { }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });

      /*


        Product product6 = new Product(6,BitmapFactory.decodeByteArray(kidsPyjamas.getBytes(),0,kidsPyjamas.length()), "Kids Pyjamas","CLOTHES", 250.0);
        addClothProduct(product6);
        addImageToClothes(kidsPyjamas, product6.getId());
        Product product7 = new Product(7,BitmapFactory.decodeByteArray(kidsTShirts.getBytes(),0,kidsTShirts.length()), "Kids T-Shirt","CLOTHES", 250.0);
        addClothProduct(product7);
        addImageToClothes(kidsTShirts, product7.getId());
        Product product8 = new Product(8,BitmapFactory.decodeByteArray(jumpsuitImage.getBytes(),0,jumpsuitImage.length()), "Jumpsuit","CLOTHES", 350.0);
        addClothProduct(product8);
        addImageToClothes(jumpsuitImage, product8.getId());*/
    }

    public void addShoesProductToDB(){
       Picasso.get().load(shoesImage).into(new Target() {
           @Override
           public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
               Product product1 = new Product(0,bitmap, "Women Shoes","SHOES", 250.0);
               addShoesProduct(product1);
           }
           @Override
           public void onBitmapFailed(Exception e, Drawable errorDrawable) { }
           @Override
           public void onPrepareLoad(Drawable placeHolderDrawable) { }
       });

        Picasso.get().load(shoesImage1).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Product product2 = new Product(1,bitmap, "Man Shoes","SHOES", 300.0);
                addShoesProduct(product2);
            }
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) { }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });
       Picasso.get().load(shoesImage2).into(new Target() {
           @Override
           public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
               Product product3 = new Product(2,bitmap, "Man Fashion Shoes","SHOES", 350.0);
               addShoesProduct(product3);
           }
           @Override
           public void onBitmapFailed(Exception e, Drawable errorDrawable) { }
           @Override
           public void onPrepareLoad(Drawable placeHolderDrawable) { }
       });
    }

    public boolean addImageToAccessoryDB (Bitmap img, int id) throws IOException {
        SQLiteDatabase db = this.getWritableDatabase();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG,0,outputStream);
        byte[] imgByteData = outputStream.toByteArray();

        ContentValues cv = new ContentValues();
        cv.put(IMAGES, imgByteData);
        long update = db.update(ACCESSORY_TABLE,cv, ID + " = " + id,null);
        outputStream.close();
        if(update == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean addImageToClothDB (Bitmap img, int id) throws IOException {
        SQLiteDatabase db = this.getWritableDatabase();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG,0,outputStream);
        byte[] imgByteData = outputStream.toByteArray();

        ContentValues cv = new ContentValues();
        cv.put(IMAGES, imgByteData);
        long update = db.update(CLOTHES_TABLE,cv, ID + " = " + id,null);
        outputStream.close();
        if(update == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean addImageToShoesDB (Bitmap img, int id) throws IOException {
        SQLiteDatabase db = this.getWritableDatabase();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG,0,outputStream);
        byte[] imgByteData = outputStream.toByteArray();

        ContentValues cv = new ContentValues();
        cv.put(IMAGES, imgByteData);
        long update = db.update(SHOES_TABLE,cv, ID + " = " + id,null);
        outputStream.close();
        if(update == -1){
            db.close();
            return false;
        }else{
            db.close();
            return true;
        }
    }


    private void addImageToAccessory(String image, final int i) {
        Picasso.get().load(image).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                try {
                    addImageToAccessoryDB(bitmap, i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) { }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });

    }
  public boolean deleteClothesProduct(){
       SQLiteDatabase db = this.getWritableDatabase();

        String deleteProductQuery = "DELETE FROM " + CLOTHES_TABLE ;

        Cursor cursor = db.rawQuery(deleteProductQuery,null);
        if(cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }
    public boolean deleteAccessoriesProduct(){
        SQLiteDatabase db = this.getWritableDatabase();

        String deleteProductQuery = "DELETE FROM " + ACCESSORY_TABLE ;

        Cursor cursor = db.rawQuery(deleteProductQuery,null);
        if(cursor.moveToFirst()){

            return true;
        }else {
            return false;
        }
    }

    public boolean deleteShoesProduct(){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteProductQuery = "DELETE FROM " + SHOES_TABLE ;
        Cursor cursor = db.rawQuery(deleteProductQuery,null);
        if(cursor.moveToFirst()){
            cursor.close();
            return true;
        }else {
            cursor.close();
            db.close();
            return false;
        }
    }

    public ArrayList<Product> getAccessories(){
       SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Product> products = new ArrayList<>();
        String getProduct = "SELECT * FROM " + ACCESSORY_TABLE  ;
        Cursor cursor = db.rawQuery(getProduct,null);
        //Log.i("ACCESS_INFO",cursor.toString());
        if(cursor.moveToFirst()){
            do {
                int product_id = cursor.getInt(0);
                byte[] product_image = cursor.getBlob(1);
                String product_name = cursor.getString(2);
                String product_category = cursor.getString(3);
                double product_price = cursor.getDouble(4);
                Bitmap img = BitmapFactory.decodeByteArray(product_image,0,product_image.length);
                Product product = new Product(product_id, img,product_name,product_category, product_price);
                products.add(product);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }
    public ArrayList<Product> getClothes(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Product> products = new ArrayList<>();
        String getProduct = "SELECT * FROM " + CLOTHES_TABLE  ;
        Cursor cursor = db.rawQuery(getProduct,null);
        if(cursor.moveToFirst()){
            do {
                int product_id = cursor.getInt(0);
                byte[] product_image = cursor.getBlob(1);
                String product_name = cursor.getString(2);
                String product_category = cursor.getString(3);
                double product_price = cursor.getDouble(4);
                Bitmap img = BitmapFactory.decodeByteArray(product_image,0,product_image.length);
                Product product = new Product(product_id, img,product_name,product_category, product_price);
                products.add(product);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return products;
    }
    public ArrayList<Product> getShoes(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Product> products = new ArrayList<>();
        String getProduct = "SELECT * FROM " + SHOES_TABLE  ;
        Cursor cursor = db.rawQuery(getProduct,null);
        if(cursor.moveToFirst()){
            do {
                int product_id = cursor.getInt(0);
                byte[] product_image = cursor.getBlob(1);
                String product_name = cursor.getString(2);
                String product_category = cursor.getString(3);
                double product_price = cursor.getDouble(4);
                Bitmap img = BitmapFactory.decodeByteArray(product_image,0,product_image.length);
                Product product = new Product(product_id, img,product_name,product_category, product_price);
                products.add(product);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return products;
    }

}
 /*   private void addImageToClothes(String image, final int i) {
        Picasso.get().load(image).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                try {
                    addImageToClothDB(bitmap, i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
        private void addImageToShoes(String image, final int i) {
            Picasso.get().load(image).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    try {
                        addImageToShoesDB(bitmap, i);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
        }
    public Bitmap getImageFromAccessoryTable(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String get_image_query = "SELECT * FROM " + ACCESSORY_TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(get_image_query,null);

        if(cursor.moveToFirst()){
            byte[] imageByteData = cursor.getBlob(1);
            cursor.close();
            return BitmapFactory.decodeByteArray(imageByteData,0,imageByteData.length);
        }

        return null;
    }
    public Bitmap getImageFromClothesTable(int id){
        SQLiteDatabase db = this.getReadableDatabase();


        String get_image_query = "SELECT * FROM " + CLOTHES_TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(get_image_query,null);

        if(cursor.moveToFirst()){
            byte[] imageByteData = cursor.getBlob(1);
            cursor.close();
            return BitmapFactory.decodeByteArray(imageByteData,0,imageByteData.length);
        }

        return null;
    }
    public Bitmap getImageFromShoesTable(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String get_image_query = "SELECT * FROM " + SHOES_TABLE + " WHERE " + ID + " = " + id;

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
    }*/




