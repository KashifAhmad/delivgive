package com.techease.delivgive.activities.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.techease.delivgive.R;
import com.techease.delivgive.activities.AccountSettingActivity;
import com.techease.delivgive.activities.LoginSignupSelectionActivity;
import com.techease.delivgive.activities.MakeAccountPremiumActivity;
import com.techease.delivgive.utils.AppRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsMenuFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.btnLogOut)
    Button btnLogout;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.llAccountSettings)
    LinearLayout llAccountSettings;
    @BindView(R.id.llSubscription)
    LinearLayout llSubscription;
    private View view;


    public static SettingsMenuFragment newInstance() {
        return new SettingsMenuFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.settings_fragment, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        btnLogout.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        llSubscription.setOnClickListener(this);
        llAccountSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogOut:
                AppRepository.mPutValue(getActivity()).putBoolean("loggedIn", false).commit();
                getActivity().finishAffinity();
                startActivity(new Intent(getActivity(), LoginSignupSelectionActivity.class));
                break;
            case R.id.llAccountSettings:
                startActivity(new Intent(getActivity(), AccountSettingActivity.class));
                break;
            case R.id.llSubscription:
                startActivity(new Intent(getActivity(), MakeAccountPremiumActivity.class));
                break;
            case R.id.ivBack:
                getActivity().onBackPressed();
        }
    }
}
