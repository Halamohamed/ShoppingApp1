package com.example.shoppingapp;

import java.sql.Blob;

public class Product {

    private int id;
    private String productImage;
    private String productName;
    private String category;
    private int amount;
    private Double productPrice;

    public Product(int id,String productImage, String productName, String category, int amount, Double productPrice) {
        this.id = id;
        this.productImage = productImage;
        this.productName = productName;
        this.category = category;
        this.amount = amount;
        this.productPrice = productPrice;
    }

    public Product(int id,String productName,String productImage, int amount,double price) {
        this.id = id;
        this.productName = productName;
        this.productImage = productImage;
        this.amount = amount;
        this.productPrice = price;
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

    public String getProductImage() {
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
