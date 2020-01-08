package com.techease.ultimatesavings.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.activities.ui.addcard.AddCardFragment;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AddCardFragment.newInstance())
                    .commitNow();
        }
    }
}
