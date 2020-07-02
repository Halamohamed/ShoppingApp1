package com.example.shoppingapp;

import android.app.ActionBar;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.ui.AccessoriesProduct;

import java.util.ArrayList;

import static androidx.constraintlayout.solver.widgets.Analyzer.setPosition;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnCreateContextMenuListener {

    ArrayList<Product> products_list;
    DataBaseHelper dataBaseHelper;
    private int position;


    Context context;

    public MyAdapter( Context context, ArrayList<Product>  products_list) {
        this.products_list = products_list;
        this.context = context;
    }
    public int getPosition(){
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_recyclerview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.MyViewHolder holder, final int position) {

        Product temp = products_list.get(position);

        holder.title.setText(temp.getProductName());
        holder.price.setText(String.valueOf(temp.getProductPrice()));
        holder.image.setImageBitmap(temp.getProductImage());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getAdapterPosition());
                return false;
            }
        });

    }

    @Override
    public void onViewRecycled(@NonNull MyViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return products_list.size();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Select the action");
        menu.add(Menu.NONE, R.id.menu_update_item,
                Menu.NONE, 0);
        menu.add(Menu.NONE, R.id.menu_delete_item,
                Menu.NONE, 0);
        menu.add(this.getPosition(),v.getId(),0, "Call");
    }


    public class MyViewHolder  extends RecyclerView.ViewHolder  {

        private TextView title,price;
        private ImageView image;
        private ConstraintLayout row_layout;
        RecyclerView.ViewHolder holder;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.recycelerview_title);
            price = itemView.findViewById(R.id.recycelerview_price);

            image = itemView.findViewById(R.id.recycelerview_image);
            row_layout = itemView.findViewById(R.id.recycelerview_layout);
           // itemView.setOnCreateContextMenuListener((View.OnCreateContextMenuListener) this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        Product clickedDataItem = products_list.get(position);
                        Toast.makeText(v.getContext()," you clicked " + clickedDataItem.getProductName(), Toast.LENGTH_SHORT).show();


                    }

                }
            });




            

        }



    }

}


