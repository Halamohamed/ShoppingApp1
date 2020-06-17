package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    private Button accessoriesBox;
    private Button clothesBox;
    private Button shoesBox;
    private ListView productList;

    /*private Button get_btn, post_btn, put_btn, delete_btn;
    private RequestQueue requestQueue;

    private static final String SERVER_URL= "https://reqres.in/api";
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        accessoriesBox = findViewById(R.id.accessories_btn);
        clothesBox = findViewById(R.id.clothes_btn);
        shoesBox = findViewById(R.id.shoes_btn);
       // productList = findViewById(R.id.list_myList);

      /*  get_btn = findViewById(R.id.get_btn);
        post_btn = findViewById(R.id.post_btn);
        put_btn = findViewById(R.id.put_btn);
        delete_btn = findViewById(R.id.delete_btn);

        get_btn.setOnClickListener(this);
        post_btn.setOnClickListener(this);
        put_btn.setOnClickListener(this);
        delete_btn.setOnClickListener(this);

        requestQueue = Volley.newRequestQueue(this);
*/

      clothesBox.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this, ClothesProduct.class);
              startActivity(intent);
          }
      });
      accessoriesBox.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this, AccessoriesProduct.class);
              startActivity(intent);
          }
      });
    }

    @Override
    public void onClick(View v) {

    }

    //VolleyNetwork.getInstance()




}