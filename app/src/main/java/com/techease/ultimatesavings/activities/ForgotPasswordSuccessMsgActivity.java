package com.techease.ultimatesavings.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.techease.ultimatesavings.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordSuccessMsgActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btnGoToEmail)
    Button btnGoEmail;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_success_msg);
        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);
        btnGoEmail.setOnClickListener(this);
        ivBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGoToEmail:
                break;
            case R.id.ivBack:
                onBackPressed();
        }
    }
}
