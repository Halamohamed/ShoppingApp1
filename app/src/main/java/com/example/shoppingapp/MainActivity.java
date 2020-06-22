package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.ui.AccessoriesProduct;
import com.example.shoppingapp.ui.ClothesProduct;
import com.example.shoppingapp.ui.ShoesProduct;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    private Button accessoriesBtn;
    private Button clothesBtn;
    private Button shoesBtn;
    private Button electronicBtn;
    private ListView product_listView;

    private  String accessoryImage = "https://imgur.com/zYY9thC";

    private  String dressImage = "https://imgur.com/CDOshUv";
    private  String jeansImage = "https://imgur.com/I6SbsYs";
    private String jumpsuitImage = "https://imgur.com/qIAHOi8";
    private String kidsDressImage = "https://imgur.com/Cxz5XeE";

    private Button get_btn, post_btn, put_btn, delete_btn;
   // private RequestQueue requestQueue;

    private static final String SERVER_URL= "https://reqres.in/api";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inti
        title = findViewById(R.id.title);
        accessoriesBtn = findViewById(R.id.accessories_btn);
        clothesBtn = findViewById(R.id.clothes_btn);
        shoesBtn = findViewById(R.id.shoes_btn);
        electronicBtn = findViewById(R.id.electronic_btn);
        //product_listView = findViewById(R.id.myProduct_listView);

        get_btn = findViewById(R.id.get_btn);
        post_btn = findViewById(R.id.post_btn);
        put_btn = findViewById(R.id.put_btn);
        delete_btn = findViewById(R.id.delete_btn);

        //set onClickListener for buttons
        accessoriesBtn.setOnClickListener(this);
        clothesBtn.setOnClickListener(this);
        shoesBtn.setOnClickListener(this);
        electronicBtn.setOnClickListener(this);

        //requestQueue = Volley.newRequestQueue(this);

        //registerForContextMenu(product_listView);


    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()){
            case R.id.clothes_btn:
                addProduct();
                 intent = new Intent(MainActivity.this, ClothesProduct.class);
                 Toast.makeText(MainActivity.this, "Clothes product", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
            case R.id.accessories_btn:
                 intent = new Intent(MainActivity.this, AccessoriesProduct.class);
                 Toast.makeText(MainActivity.this, "Accessories product", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
            case R.id.shoes_btn:
                intent = new Intent(MainActivity.this, ShoesProduct.class);
                Toast.makeText(MainActivity.this, "shoes product", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
            case R.id.electronic_btn:
                Toast.makeText(MainActivity.this,"Electronic product", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //VolleyNetwork.getInstance()


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        return super.onContextItemSelected(item);
    }

    public void addProduct(){
        DataBaseHelper db = new DataBaseHelper(MainActivity.this);
        Product clothProduct1 = new Product(-1, dressImage, "dress","clothes", 1, 300.90);
        Product clothProduct2 = new Product(-1, dressImage, "dress","clothes", 1, 300.90);

        Product accessoryProduct = new Product(1, accessoryImage, "accessories","Accessories",1, 250.0);

         boolean added = db.addProduct(clothProduct1);
         db.addProduct(clothProduct2);
         db.addProduct(accessoryProduct);
        Toast.makeText(MainActivity.this, "Added: " + added, Toast.LENGTH_SHORT).show();

    }
}