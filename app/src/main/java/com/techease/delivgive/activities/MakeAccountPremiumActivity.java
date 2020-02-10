package com.techease.delivgive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techease.delivgive.R;
import com.techease.delivgive.adapters.PlansAdapter;
import com.techease.delivgive.models.plansListModels.Datum;
import com.techease.delivgive.models.plansListModels.GetPlansResponse;
import com.techease.delivgive.utils.ProgressView;
import com.techease.delivgive.utils.networking.BaseNetworking;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeAccountPremiumActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btnPayNow)
    Button btnPayNow;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.rvPlans)
    RecyclerView rvPlans;
    List<Datum> plansList = new ArrayList<>();
    PlansAdapter adapter;


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
        adapter = new PlansAdapter(this, plansList);
        rvPlans.setLayoutManager(new LinearLayoutManager(this));
        rvPlans.setAdapter(adapter);
        ProgressView.loader(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.rbSingle:
//                if (rbMonthly.isChecked() || rbYearly.isChecked()) {
//                    rbMonthly.setChecked(false);
//                    rbYearly.setChecked(false);
//                    AppRepository.mPutValue(this).putString("subsType", "single").commit();
//                }
//                break;
//            case R.id.rbMonthly:
//                if (rbSingle.isChecked() || rbYearly.isChecked()) {
//                    rbSingle.setChecked(false);
//                    rbYearly.setChecked(false);
//                    AppRepository.mPutValue(this).putString("subsType", "monthly").commit();
//                }
//                break;
//            case R.id.rbYearly:
//                if (rbSingle.isChecked() || rbMonthly.isChecked()) {
//                    rbMonthly.setChecked(false);
//                    rbSingle.setChecked(false);
//                    AppRepository.mPutValue(this).putString("subsType", "yearly").commit();
//                }
//                break;
            case R.id.btnPayNow:
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
}
