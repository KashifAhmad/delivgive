package com.techease.delivgive.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techease.delivgive.models.flowerImagesModel.Datum;
import com.techease.delivgive.utils.interfaces.FlowerListener;
import com.techease.delivgive.R;
import com.techease.delivgive.models.Images;

import java.util.List;

/**
 * Created by kashif on 4/9/19.
 */

public class CustomImagesAdapter extends RecyclerView.Adapter<CustomImagesAdapter.MyViewHolder> {

    FlowerListener flowerListener;
    private Context context;
    private List<Datum> flowersList;
    private int lastPosition = -1;

    public CustomImagesAdapter(Context context, List<Datum> flowersList, FlowerListener mListener) {
        this.flowersList = flowersList;
        this.context = context;
        this.flowerListener = mListener;
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
        final Datum flower = flowersList.get(position);
        Picasso.get().load(flower.getFlower()).into(holder.ivFlowerImage);
        holder.ivSelected.setVisibility(View.GONE);
        holder.ivFlowerImage.setBackgroundColor(flower.isSelected() ? context.getResources().getColor(R.color.colorPrimary) : context.getResources().getColor(R.color.light_gray));
        holder.ivFlowerImage.setOnClickListener(v -> {
            lastPosition = position;
            notifyDataSetChanged();
            Log.d("zma image id", String.valueOf(flower.getId()));
            flowerListener.flowerID(flower.getFlower());

        });
        if (position == lastPosition) {
            holder.ivFlowerImage.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            holder.ivFlowerImage.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
        }



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
            ivSelected = view.findViewById(R.id.ivSelected);


        }
    }
}