package com.example.shoppingapp;

public class Product {

    private int id;
    private String productName;
    private Category category;
    private int amount;
    private Double productPrice;

    public Product(int id, String productName, Category category, int amount, Double productPrice) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.amount = amount;
        this.productPrice = productPrice;
    }

    public Product(String productName, Category category) {
        this.productName = productName;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", category=" + category +
                ", amount=" + amount +
                ", productPrice=" + productPrice +
                '}';
    }
}
