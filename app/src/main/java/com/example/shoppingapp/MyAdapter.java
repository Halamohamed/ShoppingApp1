package com.example.shoppingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList titlesData;
    ArrayList priceData;
    ArrayList imagesData;

    Context context;

    public MyAdapter( Context context, ArrayList titlesData, ArrayList priceData, ArrayList imagesData) {
        this.titlesData = titlesData;
        this.priceData = priceData;
        this.imagesData = imagesData;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_recyclerview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
        holder.title.setText(titlesData.get(position).toString());
        holder.price.setText(priceData.get(position).toString());


        //holder.image.setImageResource(imagesData[position]);
            /*holder.row_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Clicked on:"+ titlesData.get(position).toString(), Toast.LENGTH_SHORT).show();
                    titlesData.remove(position);
                    notifyDataSetChanged();
                }
            });*/

    }

    @Override
    public int getItemCount() {
        return titlesData.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{

        private TextView title,price;
        private ImageView image;
        private ConstraintLayout row_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.recycelerview_title);
            price = itemView.findViewById(R.id.recycelerview_price);

            image = itemView.findViewById(R.id.recycelerview_image);
            row_layout = itemView.findViewById(R.id.recycelerview_layout);
        }
    }
}
