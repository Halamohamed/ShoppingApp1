package com.example.shoppingapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.util.ArrayList;

public class AccessoriesProduct extends AppCompatActivity {

    private ListView myListView;
    private ImageView myImage;
    private EditText titleText;
    private EditText myPrice;
    private RecyclerView accessories_list;
    private MyAdapter adp;
    private DataBaseHelper dataBaseHelper;

    private  String accessoryImage = "https://imgur.com/zYY9thC";

    private  String dressImage = "https://imgur.com/CDOshUv";
    private String jeansImage =  "https://imgur.com/I6SbsYs";
    private String jumpsuitImage = "https://imgur.com/qIAHOi8";
    private String kidsDressImage = "https://imgur.com/Cxz5XeE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories_product);

        addProduct();
        accessories_list = findViewById(R.id.accessories_list);

        ArrayList<String> titles = new ArrayList<>();
        titles = dataBaseHelper.getAccessoriesTitle();


        ArrayList<String> prices = new ArrayList<>();
                prices = dataBaseHelper.getAccessoriesPrice();


        ArrayList<Bitmap> images = new ArrayList<>() ;
                images = dataBaseHelper.getAccessoriesImages();

        adp = new MyAdapter(this,titles,prices,images);

        accessories_list.setAdapter(adp);
        accessories_list.setLayoutManager(new LinearLayoutManager(this));

       /* myListView = findViewById(R.id.myProduct_listView);
        myImage = findViewById(R.id.img2_view);
        titleText = findViewById(R.id.text_title2);
        myPrice = findViewById(R.id.textView2);*/

        //registerForContextMenu(myListView);






    }
    public void addProduct(){
        DataBaseHelper db = new DataBaseHelper(AccessoriesProduct.this);
        Product clothProduct1 = new Product(0, BitmapFactory.decodeByteArray(jeansImage.getBytes(),0,jeansImage.length()), "dress","clothes", 300.90);
        Product clothProduct2 = new Product(1, BitmapFactory.decodeByteArray(dressImage.getBytes(),0,dressImage.length()), "dress","clothes", 300.90);

        Product accessoryProduct = new Product(2, BitmapFactory.decodeByteArray(accessoryImage.getBytes(),0,accessoryImage.length()), "accessories","Accessories", 250.0);

        boolean added = db.addProduct(clothProduct1);
        db.addProduct(clothProduct2);
        db.addProduct(accessoryProduct);
        Toast.makeText(AccessoriesProduct.this, "Added: " + added, Toast.LENGTH_SHORT).show();

    }

   /* @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        return super.onContextItemSelected(item);
    }*/
}