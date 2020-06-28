package com.example.shoppingapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shoppingapp.DataBaseHelper;
import com.example.shoppingapp.MainActivity;
import com.example.shoppingapp.MyAdapter;
import com.example.shoppingapp.Product;
import com.example.shoppingapp.R;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;

public class AccessoriesProduct extends AppCompatActivity {

    private ListView myListView;
    private ImageView myImage;
    private EditText titleText;
    private EditText myPrice;
    private RecyclerView accessories_list;
    private MyAdapter adp;
    private DataBaseHelper dataBaseHelper;
    private final String fashionAccessory = "https://i.imgur.com/z5guBd4.jpg";
    private final String accessoryImage = "https://i.imgur.com/zYY9thC.jpg";

    private ArrayList<Product> product_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories_product);

        accessories_list = findViewById(R.id.accessories_list);

        dataBaseHelper = new DataBaseHelper(this);

        product_list = dataBaseHelper.getAccessories();
        //Toast.makeText(this, "product: " + product_list.get(0).toString(), Toast.LENGTH_SHORT).show();

        adp = new MyAdapter(this,product_list);

        accessories_list.setAdapter(adp);
        accessories_list.setLayoutManager(new LinearLayoutManager(this));

        registerForContextMenu(accessories_list);

    }

}