package com.techease.ultimatesavings.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.techease.ultimatesavings.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentOptionActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btnContinue)
    Button btnContinue;
    @BindView(R.id.rbPaypal)
    RadioButton rbPaypal;
    @BindView(R.id.rbPayCredit)
    RadioButton rbPayWithCredit;
    @BindView(R.id.ivBack)
    ImageView ivBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_option);
        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);
        rbPaypal.setOnClickListener(this);
        rbPayWithCredit.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbPaypal:
                if (rbPayWithCredit.isChecked()) {
                    rbPayWithCredit.setChecked(false);
                }
                break;
            case R.id.rbPayCredit:
                if (rbPaypal.isChecked()) {
                    rbPaypal.setChecked(false);
                }
                break;
            case R.id.btnContinue:
                startActivity(new Intent(this, AddCardActivity.class));
                break;
            case R.id.ivBack:
                onBackPressed();

        }
    }
}
