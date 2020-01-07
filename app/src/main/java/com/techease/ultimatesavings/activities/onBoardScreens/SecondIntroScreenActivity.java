package com.techease.ultimatesavings.activities.onBoardScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.activities.LoginSignupSelectionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondIntroScreenActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_intro_screen);
        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);
        ivBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNext:
                startActivity(new Intent(this, LoginSignupSelectionActivity.class));
                break;
            case R.id.ivBack:
                onBackPressed();

        }
    }
}
