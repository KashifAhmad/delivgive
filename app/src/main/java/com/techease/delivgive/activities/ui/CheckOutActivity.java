package com.techease.delivgive.activities.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.techease.delivgive.R;
import com.techease.delivgive.activities.ui.ui.checkout.CheckOutFragment;

public class CheckOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_out_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CheckOutFragment.newInstance())
                    .commitNow();
        }
    }
}
