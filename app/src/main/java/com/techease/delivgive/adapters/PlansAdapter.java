package com.techease.delivgive.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techease.delivgive.R;
import com.techease.delivgive.models.plansListModels.Datum;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by kashif on 4/9/19.
 */

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.MyViewHolder> {

    private Context context;
    private List<com.techease.delivgive.models.plansListModels.Datum> flowersList;


    public PlansAdapter(Context context, List<com.techease.delivgive.models.plansListModels.Datum> flowersList) {
        this.flowersList = flowersList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_plan_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Datum plan = flowersList.get(position);
        holder.rbPlan.setText(plan.getTitle());
        holder.tvPlanPrice.setText(plan.getPrice());
        if (Integer.parseInt(plan.getId()) == 1){
            holder.rbPlan.setTextColor(context.getColor(R.color.colorPrimary));
            holder.tvPlanPrice.setTextColor(context.getColor(R.color.colorPrimary));
            holder.tvPlanPrice.setTextColor(Color.WHITE);
            holder.mLayout.setBackground(context.getResources().getDrawable(R.drawable.group));
        }else if (Integer.parseInt(plan.getId()) == 2){
            holder.rbPlan.setTextColor(Color.WHITE);
            holder.tvPlanPrice.setTextColor(Color.WHITE);
            holder.mLayout.setBackground(context.getResources().getDrawable(R.drawable.group_copy));
        }else {
            holder.rbPlan.setTextColor(Color.WHITE);
            holder.tvPlanPrice.setTextColor(Color.WHITE);
            holder.mLayout.setBackground(context.getResources().getDrawable(R.drawable.group_copy_2));
        }

    }



    @Override
    public int getItemCount() {
        return flowersList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RadioButton rbPlan;
        TextView tvPlanPrice;
        ImageView ivPanImage;
        LinearLayout mLayout;

        MyViewHolder(View view) {
            super(view);
            tvPlanPrice = view.findViewById(R.id.tvPlanPrice);
            rbPlan = view.findViewById(R.id.rbPlan);
            mLayout = view.findViewById(R.id.llPlans);


        }
    }
}