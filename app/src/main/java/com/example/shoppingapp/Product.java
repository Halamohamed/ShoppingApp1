package com.example.shoppingapp;

import android.graphics.Bitmap;

public class Product {

    private int id;
    private Bitmap productImage;
    private String productName;
    private String category;
    private Double productPrice;

    public Product(int id,Bitmap productImage, String productName, String category, Double productPrice) {
        this.id = id;
        this.productImage = productImage;
        this.productName = productName;
        this.category = category;
        this.productPrice = productPrice;
    }



    public int getId() {
        return id;
    }

    public void setProductImage(Bitmap productImage) {
        this.productImage = productImage;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public Bitmap getProductImage() {
        return productImage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", image=" + productImage +
                ", productName='" + productName + '\'' +
                ", category=" + category +
                ", productPrice=" + productPrice +
                '}';
    }
}
