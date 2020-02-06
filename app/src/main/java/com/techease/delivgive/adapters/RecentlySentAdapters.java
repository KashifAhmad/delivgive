package com.techease.delivgive.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.techease.delivgive.R;
import com.techease.delivgive.models.getUserBuckets.Datum;

import java.util.List;

/**
 * Created by kashif on 4/9/19.
 */

public class RecentlySentAdapters extends RecyclerView.Adapter<RecentlySentAdapters.MyViewHolder> {

    private Context context;
    private List<com.techease.delivgive.models.getUserBuckets.Datum> flowersList;


    public RecentlySentAdapters(Context context, List<Datum> flowersList) {
        this.flowersList = flowersList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_recently_sent_bucket_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final com.techease.delivgive.models.getUserBuckets.Datum flower = flowersList.get(position);
//        holder.tvName.setText(flower.getImage());

//        holder.tvFree.setText(flower.getTitle());
        Picasso.get().load(flower.getImage()).into(holder.ivFlowerImage, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return flowersList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivFlowerImage;
        ProgressBar progressBar;

        MyViewHolder(View view) {
            super(view);
            ivFlowerImage = view.findViewById(R.id.ivFlower);
            progressBar = view.findViewById(R.id.progressBar);


        }
    }
}