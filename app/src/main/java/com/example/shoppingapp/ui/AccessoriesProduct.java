package com.example.shoppingapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.shoppingapp.R;

public class AccessoriesProduct extends AppCompatActivity {

    private ListView myListView;
    private ImageView myImage;
    private EditText titleText;
    private EditText myPrice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories_product);

       /* myListView = findViewById(R.id.myProduct_listView);
        myImage = findViewById(R.id.img2_view);
        titleText = findViewById(R.id.text_title2);
        myPrice = findViewById(R.id.textView2);*/

        //registerForContextMenu(myListView);






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