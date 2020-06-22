package com.example.shoppingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList titlesData;
    int imagesData[];
    Context context;


    public MyAdapter(Context ctx, ArrayList titles, int images[]){
        context = ctx;
        titlesData = titles;
        imagesData = images;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_accessories_product,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
        holder.title.setText(titlesData.get(position).toString());
        holder.image.setImageResource(imagesData[position]);

            holder.row_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Clicked on:"+ titlesData.get(position).toString(), Toast.LENGTH_SHORT).show();
                    titlesData.remove(position);
                    notifyDataSetChanged();
                }
            });

    }

    @Override
    public int getItemCount() {
        return titlesData.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{

        private TextView title;
        private ImageView image;
        private ConstraintLayout row_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           /* title = itemView.findViewById(R.id.text_title1);
            image = itemView.findViewById(R.id.img1_view);
            row_layout = itemView.findViewById(R.id.const_row1_layout);*/
        }
    }
}
