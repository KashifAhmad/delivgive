package com.techease.delivgive.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techease.delivgive.R;
import com.techease.delivgive.activities.SendBouquetActivity;
import com.techease.delivgive.models.freeFlowersModels.Datum;
import com.techease.delivgive.utils.AppRepository;

import java.util.List;

/**
 * Created by kashif on 4/9/19.
 */

public class FreeFlowersAdapter extends RecyclerView.Adapter<FreeFlowersAdapter.MyViewHolder> {

    private Context context;
    private List<Datum> flowersList;


    public FreeFlowersAdapter(Context context, List<Datum> flowersList) {
        this.flowersList = flowersList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_free_flowers_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Datum flower = flowersList.get(position);
        holder.tvName.setText(flower.getFlowerName());
        holder.tvFree.setText(flower.getTitle());
        Picasso.get().load(flower.getFlowerImage()).into(holder.ivFlowerImage);
        holder.ivFlowerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppRepository.mPutValue(context).putString("mBouquetSendingTitle", "Deliver Your Complimentary Flower, Bouquet, or Gift”").commit();

                AppRepository.mPutValue(context).putBoolean("fromFree", true).commit();
                AppRepository.mPutValue(context).putString("picLink", flower.getFlowerImage()).commit();
                context.startActivity(new Intent(context, SendBouquetActivity.class));
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
            tvName = view.findViewById(R.id.tvFlowerName);
            tvFree = view.findViewById(R.id.tvFree);
            ivFlowerImage = view.findViewById(R.id.ivFlower);


        }
    }
}