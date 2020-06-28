package com.example.shoppingapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shoppingapp.DataBaseHelper;
import com.example.shoppingapp.MyAdapter;
import com.example.shoppingapp.Product;
import com.example.shoppingapp.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;

public class ClothesProduct extends AppCompatActivity {
    private RecyclerView clothes_list;
    private MyAdapter adp;
    private DataBaseHelper dataBaseHelper;
    private final String dressImage = "https://i.imgur.com/0PcS4QH.png";
    private final String jeansImage =  "https://i.imgur.com/I6SbsYs.jpg";


    private ArrayList<Product> product_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_product);

        clothes_list = findViewById(R.id.clothes_recycleview);

        dataBaseHelper = new DataBaseHelper(this);

        product_list = dataBaseHelper.getClothes();

        adp = new MyAdapter(this,product_list);

        clothes_list.setAdapter(adp);
        clothes_list.setLayoutManager(new LinearLayoutManager(this));

        registerForContextMenu(clothes_list);
    }
}