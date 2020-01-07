package com.techease.ultimatesavings.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.activities.onBoardScreens.FirstIntroScreenActivity;
import com.techease.ultimatesavings.utils.AppRepository;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                startActivity(new Intent(SplashActivity.this, ServicesDetailProSignUpActivity.class));
                if (AppRepository.isLoggedIn(SplashScreenActivity.this)) {
                    startActivity(new Intent(SplashScreenActivity.this, MainBottomNavActivity.class));
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, FirstIntroScreenActivity.class));
                }
                finish();
            }
        }, 1000);
    }
}
