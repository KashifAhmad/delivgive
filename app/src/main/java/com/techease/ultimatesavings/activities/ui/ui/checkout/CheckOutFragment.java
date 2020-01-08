package com.techease.ultimatesavings.activities.ui.ui.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.activities.MainBottomNavActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckOutFragment extends Fragment implements View.OnClickListener {
    private View view;
    @BindView(R.id.btnPayNow)
    Button btnPayNow;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    public static CheckOutFragment newInstance() {
        return new CheckOutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.check_out_fragment, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        btnPayNow.setOnClickListener(this);
        ivBack.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPayNow:
                startActivity(new Intent(getActivity(), MainBottomNavActivity.class));
                getActivity().finishAffinity();
                break;
            case R.id.ivBack:
                getActivity().onBackPressed();

        }
    }
}
