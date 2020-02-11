package com.techease.delivgive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techease.delivgive.R;
import com.techease.delivgive.adapters.PlansAdapter;
import com.techease.delivgive.models.PlanSubscriptionModels.PlanSubscriptionResponse;
import com.techease.delivgive.models.plansListModels.Datum;
import com.techease.delivgive.models.plansListModels.GetPlansResponse;
import com.techease.delivgive.utils.AppRepository;
import com.techease.delivgive.utils.ProgressView;
import com.techease.delivgive.utils.interfaces.PlanSubscriptionID;
import com.techease.delivgive.utils.networking.BaseNetworking;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeAccountPremiumActivity extends AppCompatActivity implements View.OnClickListener, PlanSubscriptionID {


    @BindView(R.id.btnPayNow)
    Button btnPayNow;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.rvPlans)
    RecyclerView rvPlans;
    List<Datum> plansList = new ArrayList<>();
    PlansAdapter adapter;
    private int planID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_account_premium);
        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);

        btnPayNow.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        getPlans();
        adapter = new PlansAdapter(this, plansList, this);
        rvPlans.setLayoutManager(new LinearLayoutManager(this));
        rvPlans.setAdapter(adapter);
        ProgressView.loader(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPayNow:
                buyPlan();
                startActivity(new Intent(this, PaymentOptionActivity.class));
                break;
            case R.id.ivBack:
                onBackPressed();

        }
    }

    private void getPlans() {
        Call<GetPlansResponse> getPlansResponseCall = BaseNetworking.apiServices().getPlans();
        getPlansResponseCall.enqueue(new Callback<GetPlansResponse>() {
            @Override
            public void onResponse(Call<GetPlansResponse> call, Response<GetPlansResponse> response) {
                ProgressView.mDialog.dismiss();

                if (response.isSuccessful()) {
                    plansList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<GetPlansResponse> call, Throwable t) {

            }
        });
    }
    private void buyPlan(){
        Call<PlanSubscriptionResponse> planSubscription = BaseNetworking.apiServices().plansSubscription(AppRepository.mUserID(this), planID);
        planSubscription.enqueue(new Callback<PlanSubscriptionResponse>() {
            @Override
            public void onResponse(Call<PlanSubscriptionResponse> call, Response<PlanSubscriptionResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MakeAccountPremiumActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlanSubscriptionResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void planID(int id) {
        planID = id;
        Log.d("zma plan id", String.valueOf(id));
    }
}
