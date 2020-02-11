package com.techease.delivgive.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.techease.delivgive.R;
import com.techease.delivgive.activities.onBoardScreens.FirstIntroScreenActivity;
import com.techease.delivgive.utils.AppRepository;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MakeAccountPremiumActivity.class));
//                if (AppRepository.isLoggedIn(SplashScreenActivity.this)) {
//                    startActivity(new Intent(SplashScreenActivity.this, MainBottomNavActivity.class));
//                } else {
//                    startActivity(new Intent(SplashScreenActivity.this, FirstIntroScreenActivity.class));
//                }
                finish();
            }
        }, 1000);
    }
}
