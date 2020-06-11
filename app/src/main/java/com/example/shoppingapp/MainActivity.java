package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private CheckBox healthyBox;
    private CheckBox clothesBox;
    private CheckBox foodsBox;
    private ListView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        healthyBox = findViewById(R.id.box1);
        clothesBox = findViewById(R.id.box2);
        foodsBox = findViewById(R.id.box3);
        productList = findViewById(R.id.list_myList);


    }
}