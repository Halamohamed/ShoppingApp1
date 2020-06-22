package com.example.shoppingapp;

import android.graphics.Bitmap;

public class Product {

    private int id;
    private Bitmap productImage;
    private String productName;
    private String category;
    private int amount;
    private Double productPrice;

    public Product(int id,Bitmap productImage, String productName, String category, Double productPrice) {
        this.id = id;
        this.productImage = productImage;
        this.productName = productName;
        this.category = category;
        this.amount = amount;
        this.productPrice = productPrice;
    }



    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
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
                ", amount=" + amount +
                ", productPrice=" + productPrice +
                '}';
    }
}
