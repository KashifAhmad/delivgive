package com.techease.ultimatesavings.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.activities.ui.accountsetting.AccountSettingFragment;

public class AccountSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_setting_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AccountSettingFragment.newInstance())
                    .commitNow();
        }
    }
}
