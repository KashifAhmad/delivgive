package com.techease.ultimatesavings.activities.ui.verifycode;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.activities.ForgotPasswordSuccessMsgActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyCodeFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etVerifyCode)
    EditText etVerifyCode;
    @BindView(R.id.btnVerify)
    Button btnVerify;
    private View view;
    private String verifyCode;
    private boolean valid = false;
    public static VerifyCodeFragment newInstance() {
        return new VerifyCodeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.verify_code_fragment, container, false);
        return view;
    }
    private void initUI() {
        ButterKnife.bind(this, view);
        btnVerify.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVerify:
                startActivity(new Intent(getActivity(), ForgotPasswordSuccessMsgActivity.class));
                break;
            case R.id.ivBack:
                getActivity().onBackPressed();
        }
    }
    private boolean isValid() {
        verifyCode = etVerifyCode.getText().toString();
        if (verifyCode.isEmpty() ){
            etVerifyCode.setError("Please write valid code");
            valid = false;
        } else {
            etVerifyCode.setError(null);
        }
        return valid;
    }


}
