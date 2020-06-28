package com.example.shoppingapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.shoppingapp.DataBaseHelper;
import com.example.shoppingapp.MyAdapter;
import com.example.shoppingapp.Product;
import com.example.shoppingapp.R;

import java.util.ArrayList;

public class ShoesProduct extends AppCompatActivity {

    private ListView shoesView;
    private Button show_btn;
    private ArrayAdapter shoesProduct;
    private RecyclerView shoes_list;
    private MyAdapter adp;
    private DataBaseHelper dataBaseHelper;

    private ArrayList<Product> product_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes_product);

        shoes_list = findViewById(R.id.shoes_recyclereview);

        dataBaseHelper = new DataBaseHelper(this);

        product_list = dataBaseHelper.getShoes();

        adp = new MyAdapter(this,product_list);

        shoes_list.setAdapter(adp);
        shoes_list.setLayoutManager(new LinearLayoutManager(this));

        registerForContextMenu(shoes_list);
    }

}