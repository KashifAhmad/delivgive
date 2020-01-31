package com.techease.delivgive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.techease.delivgive.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginSignupSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.llSignUp)
    LinearLayout llSignUp;
    @BindView(R.id.llSignIn)
    LinearLayout llSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup_selection);
        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);
        llSignUp.setOnClickListener(this);
        llSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llSignUp:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.llSignIn:
                startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
