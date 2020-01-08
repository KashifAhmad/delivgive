package com.techease.ultimatesavings.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.utils.AppRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MakeAccountPremiumActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rbSingle)
    RadioButton rbSingle;
    @BindView(R.id.rbMonthly)
    RadioButton rbMonthly;
    @BindView(R.id.rbYearly)
    RadioButton rbYearly;
    @BindView(R.id.btnPayNow)
    Button btnPayNow;
    @BindView(R.id.ivBack)
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_account_premium);
        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);
        rbSingle.setOnClickListener(this);
        rbMonthly.setOnClickListener(this);
        rbYearly.setOnClickListener(this);
        btnPayNow.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbSingle:
                if (rbMonthly.isChecked() || rbYearly.isChecked()) {
                    rbMonthly.setChecked(false);
                    rbYearly.setChecked(false);
                    AppRepository.mPutValue(this).putString("subsType", "single").commit();
                }
                break;
            case R.id.rbMonthly:
                if (rbSingle.isChecked() || rbYearly.isChecked()) {
                    rbSingle.setChecked(false);
                    rbYearly.setChecked(false);
                    AppRepository.mPutValue(this).putString("subsType", "monthly").commit();
                }
                break;
            case R.id.rbYearly:
                if (rbSingle.isChecked() || rbMonthly.isChecked()) {
                    rbMonthly.setChecked(false);
                    rbSingle.setChecked(false);
                    AppRepository.mPutValue(this).putString("subsType", "yearly").commit();
                }
                break;
            case R.id.btnPayNow:
                startActivity(new Intent(this, PaymentOptionActivity.class));
                break;
            case R.id.ivBack:
                onBackPressed();

        }
    }
}
