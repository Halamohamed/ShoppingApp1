package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.ui.AccessoriesProduct;
import com.example.shoppingapp.ui.ClothesProduct;
import com.example.shoppingapp.ui.ShoesProduct;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    private Button accessoriesBtn;
    private Button clothesBtn;
    private Button shoesBtn;
    private Button apiBtn;
    private Button addToDB_btn;
    private ImageView imageMain;
    private final String accessoryImage = "https://i.imgur.com/zYY9thC.jpg";

    DataBaseHelper dataBaseHelper;

    private  String dressImage = "https://imgur.com/CDOshUv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inti
        title = findViewById(R.id.title);
        accessoriesBtn = findViewById(R.id.accessories_btn);
        clothesBtn = findViewById(R.id.clothes_btn);
        shoesBtn = findViewById(R.id.shoes_btn);
        apiBtn = findViewById(R.id.api_btn);
        addToDB_btn = findViewById(R.id.addToDB_btn);
        imageMain = findViewById(R.id.image_main);

        //set onClickListener for buttons
        accessoriesBtn.setOnClickListener(this);
        clothesBtn.setOnClickListener(this);
        shoesBtn.setOnClickListener(this);
        apiBtn.setOnClickListener(this);
        addToDB_btn.setOnClickListener(this);

        dataBaseHelper = new DataBaseHelper(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.clothes_btn:
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
            case R.id.api_btn:
                Toast.makeText(MainActivity.this,"API call", Toast.LENGTH_SHORT).show();
                 intent = new Intent(this, ApiActivity.class);
                startActivity(intent);
                break;
            case R.id.addToDB_btn:
                Toast.makeText(MainActivity.this,"added product To DB", Toast.LENGTH_SHORT).show();
                //dataBaseHelper.addAccessoriesProductToDB();
                //dataBaseHelper.addClothesProductToDB();
                //dataBaseHelper.addShoesProductToDB();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmapImage = (Bitmap) data.getExtras().get(dressImage);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        return super.onContextItemSelected((MenuItem) itemInfo);
    }


}
