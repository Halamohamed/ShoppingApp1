package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ItemActivity extends AppCompatActivity {

    private ImageView itemImage;
    private TextView itemId;
    private TextView itemName;
    private TextView itemEmail;

    private ListView myListUser;
    private ArrayAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        itemImage = findViewById(R.id.item_image);
        itemId = findViewById(R.id.item_id);
        itemName = findViewById(R.id.item_name);
        itemEmail = findViewById(R.id.item_email);

        Intent intent = getIntent();

        //itemImage.setImageBitmap(intent.getByteExtra("avatar", (byte) 0));
        itemId.setText(String.valueOf(intent.getIntExtra("id",0)));
        itemName.setText(intent.getStringExtra("first_name"));
        itemEmail.setText(intent.getStringExtra("email"));

        Toast.makeText(this, "items info: ", Toast.LENGTH_SHORT).show();

        myListUser.setAdapter(adp);
    }
}