package com.techease.delivgive.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.techease.delivgive.R;
import com.techease.delivgive.activities.fragments.CreateFragment;
import com.techease.delivgive.activities.fragments.FreeFlowersFragment;
import com.techease.delivgive.activities.fragments.PremiumFragment;
import com.techease.delivgive.utils.ViewChanger;

public class MainBottomNavActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navFree:
                ViewChanger.fragmentChanger(MainBottomNavActivity.this, new FreeFlowersFragment(), R.id.container);
                return true;
            case R.id.navPremium:
                ViewChanger.fragmentChanger(MainBottomNavActivity.this, new PremiumFragment(), R.id.container);
                return true;
            case R.id.navCreate:
                ViewChanger.fragmentChanger(MainBottomNavActivity.this, new CreateFragment(), R.id.container);
                return true;
            case R.id.navBouquets:
                Toast.makeText(this, "Coming Soon!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navProfile:
                ViewChanger.fragmentChanger(MainBottomNavActivity.this, new ProfileFragment(), R.id.container);
                return true;

        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bottom_nav);

        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navCreate);
    }

}
