package com.techease.delivgive.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techease.delivgive.R;
import com.techease.delivgive.models.premiumFlowers.Datum;
import com.techease.delivgive.utils.interfaces.PremiumFlowersLinkListener;

import java.util.List;

/**
 * Created by kashif on 4/9/19.
 */

public class PremiumFlowersDialogAdapter extends RecyclerView.Adapter<PremiumFlowersDialogAdapter.MyViewHolder> {

    private Context context;
    private List<Datum> flowersList;
    private PremiumFlowersLinkListener flowersLinkListener;

    public PremiumFlowersDialogAdapter(Context context, List<Datum> flowersList, PremiumFlowersLinkListener linkListener) {
        this.flowersList = flowersList;
        this.context = context;
        this.flowersLinkListener = linkListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_premium_flowers_dialog, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Datum flower = flowersList.get(position);
        Picasso.get().load(flower.getFlowerImage()).into(holder.ivFlowerImage);
        holder.ivFlowerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowersLinkListener.bouquetLink(flower.getFlowerImage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return flowersList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvFree;
        ImageView ivFlowerImage, ivSelected;

        MyViewHolder(View view) {
            super(view);
            ivFlowerImage = view.findViewById(R.id.ivFlower);
        }
    }
}