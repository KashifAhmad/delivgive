package com.techease.ultimatesavings.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.activities.ui.forgotpassword.ForgotPasswordFragment;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ForgotPasswordFragment.newInstance())
                    .commitNow();
        }
    }
}
