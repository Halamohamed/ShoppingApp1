package com.example.shoppingapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.DataBaseHelper;
import com.example.shoppingapp.MyAdapter;
import com.example.shoppingapp.Product;
import com.example.shoppingapp.R;

import java.util.ArrayList;

public class AccessoriesProduct extends AppCompatActivity {
    private RecyclerView accessories_list;
    private MyAdapter adp;
    private DataBaseHelper dataBaseHelper;
    private ArrayAdapter arrayAdapter;
    private ListView listViewAccessories;
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



        registerForContextMenu(accessories_list);
        //accessories_list.setRecyclerListener(onContextItemSelected(R.menu.update_item));


        arrayAdapter = new ArrayAdapter(AccessoriesProduct.this, android.R.layout.simple_list_item_1,product_list);
        updateViews();


    }

    private void updateViews() {
        adp = new MyAdapter(this,product_list);

        accessories_list.setAdapter(adp);
        accessories_list.setLayoutManager(new LinearLayoutManager(this));
    }
    private void showMyListAccessory(){
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,dataBaseHelper.getAccessories());
        listViewAccessories.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.update_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.update_item, menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
         AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.menu_delete_item:
               // boolean status = dataBaseHelper.deleteOneAccessoryProduct((Product) arrayAdapter.getItem(info.position));
                Toast.makeText(AccessoriesProduct.this, " deleted " , Toast.LENGTH_SHORT).show();
                updateViews();
                break;
            case R.id.menu_update_item:
                AlertDialog.Builder builderDialog = new AlertDialog.Builder(this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.edit_dialog,null);
                Toast.makeText(AccessoriesProduct.this,"Updated ", Toast.LENGTH_SHORT).show();


                builderDialog.setView(dialogView);
                //declare views
                final TextView edit_name, edit_price;
                final Button save_btn;

                edit_name = dialogView.findViewById(R.id.edit_name);
                edit_price = dialogView.findViewById(R.id.edit_price);
                save_btn = dialogView.findViewById(R.id.save_btn);

                  /*final Product tempProduct = (Product) arrayAdapter.getItem(info.position);

                edit_name.setText(tempProduct.getProductName());
                edit_price.setText(String.valueOf(tempProduct.getProductPrice()));




                save_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       tempProduct.setProductName(edit_name.getText().toString());
                        Double price = Double.parseDouble(edit_price.getText().toString());
                        tempProduct.setProductPrice(price);
                        dataBaseHelper.updateAccessoryProduct(tempProduct);
                        Toast.makeText(AccessoriesProduct.this,"Updated ", Toast.LENGTH_SHORT).show();
                        updateViews();
                        showMyListAccessory();
                    }
                });
               */
                AlertDialog updateProductDialog = builderDialog.create();
                updateProductDialog.show();
                updateProductDialog.onContextItemSelected(item);
                updateProductDialog.getWindow().setLayout(800,900);

                break;

        }
        return super.onContextItemSelected(item);

    }




}