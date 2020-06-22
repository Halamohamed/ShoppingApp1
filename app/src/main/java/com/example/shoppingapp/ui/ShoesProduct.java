package com.example.shoppingapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.shoppingapp.DataBaseHelper;
import com.example.shoppingapp.R;

public class ShoesProduct extends AppCompatActivity {

    private ListView shoesView;
    private Button show_btn;
    private ArrayAdapter shoesProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes_product);

        shoesView = findViewById(R.id.shoes_listView);
        show_btn = findViewById(R.id.shoes_show_btn);

        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper= new DataBaseHelper(ShoesProduct.this);
                showProductOnListView(dataBaseHelper);
            }
        });
    }

    public void showProductOnListView(DataBaseHelper dbHelper){
         shoesProduct = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dbHelper.getAllProduct());
         shoesView.setAdapter(shoesProduct);

    }
}