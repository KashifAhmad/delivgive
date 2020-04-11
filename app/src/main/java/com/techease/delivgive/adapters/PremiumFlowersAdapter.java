package com.techease.delivgive.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techease.delivgive.R;
import com.techease.delivgive.activities.MakeAccountPremiumActivity;
import com.techease.delivgive.activities.SendBouquetActivity;
import com.techease.delivgive.models.premiumFlowers.Datum;
import com.techease.delivgive.utils.AppRepository;

import java.util.List;

/**
 * Created by kashif on 4/9/19.
 */

public class PremiumFlowersAdapter extends RecyclerView.Adapter<PremiumFlowersAdapter.MyViewHolder> {

    private Context context;
    private List<com.techease.delivgive.models.premiumFlowers.Datum> flowersList;


    public PremiumFlowersAdapter(Context context, List<com.techease.delivgive.models.premiumFlowers.Datum> flowersList) {
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
        holder.rlParent.setOnClickListener(v -> {
            AppRepository.mPutValue(context).putBoolean("fromFree", true).commit();
            AppRepository.mPutValue(context).putString("picLink", flower.getFlowerImage()).commit();
            AppRepository.mPutValue(context).putString("mBouquetSendingTitle", "Deliver Your Organically Inspired Flower").commit();

            context.startActivity(new Intent(context, SendBouquetActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return flowersList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvFree;
        ImageView ivFlowerImage;
        RelativeLayout rlParent;

        MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvFlowerName);
            tvFree = view.findViewById(R.id.tvFree);
            ivFlowerImage = view.findViewById(R.id.ivFlower);
            rlParent = view.findViewById(R.id.rlParent);


        }
    }
}