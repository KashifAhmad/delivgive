package com.techease.ultimatesavings.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.activities.ui.signup.SignUpFragment;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SignUpFragment.newInstance())
                    .commitNow();
        }
    }
}
