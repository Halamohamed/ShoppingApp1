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

    private  String accessoryImage = "https://imgur.com/zYY9thC";

    private  String dressImage = "https://imgur.com/CDOshUv";
    private String jeansImage =  "https://imgur.com/I6SbsYs";
    private String jumpsuitImage = "https://imgur.com/qIAHOi8";
    private String kidsDressImage = "https://imgur.com/Cxz5XeE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories_product);

        accessories_list = findViewById(R.id.accessories_list);

        /*try {
            dataBaseHelper.addImageToDB(BitmapFactory.decodeByteArray(dressImage.getBytes(),1,dressImage.length()));
            dataBaseHelper.addImageToDB(BitmapFactory.decodeByteArray(jeansImage.getBytes(),2,dressImage.length()));

            dataBaseHelper.addImageToDB(BitmapFactory.decodeByteArray(jumpsuitImage.getBytes(),3,dressImage.length()));

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        ArrayList<String> titles = new ArrayList<>();
        titles = dataBaseHelper.getAccessoriesTitle();


        ArrayList<String> prices = new ArrayList<>();
                prices = dataBaseHelper.getAccessoriesPrice();


        ArrayList<Bitmap> images = new ArrayList<>() ;
                images = dataBaseHelper.getAllImages();

        adp = new MyAdapter(this,titles,prices,images);

        accessories_list.setAdapter(adp);
        accessories_list.setLayoutManager(new LinearLayoutManager(this));

       /* myListView = findViewById(R.id.myProduct_listView);
        myImage = findViewById(R.id.img2_view);
        titleText = findViewById(R.id.text_title2);
        myPrice = findViewById(R.id.textView2);*/

        //registerForContextMenu(myListView);






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 50) {
            if (resultCode == RESULT_OK) {
                Bitmap bitmapImage = (Bitmap) data.getExtras().get("data");
                myImage.setImageBitmap(bitmapImage);
                try {
                    boolean status = dataBaseHelper.addImageToDB(bitmapImage);
                    Snackbar ss = Snackbar.make(myListView, "Added image to db" + status, Snackbar.LENGTH_SHORT).setAction("Remove", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(AccessoriesProduct.this, "Action Clicked", Toast.LENGTH_SHORT).show();
                            //code
                        }
                    });
                    ss.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
}