package com.techease.delivgive.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.techease.delivgive.R;
import com.techease.delivgive.models.plansListModels.Datum;
import com.techease.delivgive.utils.interfaces.PlanSubscriptionID;

import java.util.List;

/**
 * Created by kashif on 4/9/19.
 */

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.MyViewHolder> {

    private Context context;
    private List<com.techease.delivgive.models.plansListModels.Datum> flowersList;
    private PlanSubscriptionID planID;
    private int lastSelectedPosition = -1;


    public PlansAdapter(Context context, List<com.techease.delivgive.models.plansListModels.Datum> flowersList, PlanSubscriptionID planID) {
        this.flowersList = flowersList;
        this.context = context;
        this.planID = planID;
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
        Log.d("zma position", String.valueOf(position));
        if (Integer.parseInt(plan.getId()) == 1) {
            holder.rbPlan.setTextColor(Color.WHITE);
            holder.tvPlanPrice.setTextColor(Color.WHITE);
            holder.tvPerPrice.setTextColor(Color.WHITE);
            holder.mLayout.setBackground(context.getResources().getDrawable(R.drawable.group));
        } else if (Integer.parseInt(plan.getId()) == 2) {
            holder.rbPlan.setTextColor(Color.WHITE);
            holder.tvPlanPrice.setTextColor(Color.WHITE);
            holder.tvPerPrice.setTextColor(Color.WHITE);
            holder.mLayout.setBackground(context.getResources().getDrawable(R.drawable.group_copy));
        } else {
            holder.rbPlan.setTextColor(context.getColor(R.color.colorPrimary));
            holder.tvPlanPrice.setTextColor(context.getColor(R.color.colorPrimary));
            holder.tvPerPrice.setTextColor(context.getColor(R.color.colorPrimary));
            holder.mLayout.setBackground(context.getResources().getDrawable(R.drawable.group_copy_2));
        }
        holder.rbPlan.setChecked(lastSelectedPosition == position);
        holder.rbPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();
                planID.planID(Integer.parseInt(plan.getId()));
            }
        });

    }


    @Override
    public int getItemCount() {
        return flowersList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RadioButton rbPlan;
        TextView tvPlanPrice, tvPerPrice;
        ImageView ivPanImage;
        LinearLayout mLayout;

        MyViewHolder(View view) {
            super(view);
            tvPlanPrice = view.findViewById(R.id.tvPlanPrice);
            rbPlan = view.findViewById(R.id.rbPlan);
            mLayout = view.findViewById(R.id.llPlans);
            tvPerPrice = itemView.findViewById(R.id.tvPerPrice);
//            rbPlan.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    lastSelectedPosition = getAdapterPosition();
//                    notifyDataSetChanged();
//
//                }
//            });


        }
    }
}