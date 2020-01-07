package com.techease.ultimatesavings.activities;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.activities.fragments.FreeFlowersFragment;
import com.techease.ultimatesavings.activities.fragments.FreeFlowersFragment_ViewBinding;
import com.techease.ultimatesavings.activities.fragments.PremiumFragment;
import com.techease.ultimatesavings.utils.ViewChanger;

import androidx.appcompat.app.AppCompatActivity;

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
        navigation.setSelectedItemId(R.id.navFree);
    }

}
