package com.techease.ultimatesavings.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.models.Images;
import com.techease.ultimatesavings.models.freeFlowersModels.Datum;

import java.util.List;

/**
 * Created by kashif on 4/9/19.
 */

public class CustomImagesAdapter extends RecyclerView.Adapter<CustomImagesAdapter.MyViewHolder> {

    private Context context;
    private List<Images> flowersList;
    private boolean isSelected = false;
    private int lastPosition = -1;


    public CustomImagesAdapter(Context context, List<Images> flowersList) {
        this.flowersList = flowersList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_flowers_selection_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Images flower = flowersList.get(position);
        holder.ivFlowerImage.setImageResource(flower.imageId);
        holder.ivFlowerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flower.isSelected){
                    holder.ivFlowerImage.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
                }else {
                    flower.setSelected(true);
                    lastPosition = position;
                    holder.ivFlowerImage.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return flowersList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvFree;
        ImageView ivFlowerImage;

        MyViewHolder(View view) {
            super(view);
            ivFlowerImage = view.findViewById(R.id.ivFlower);


        }
    }
}