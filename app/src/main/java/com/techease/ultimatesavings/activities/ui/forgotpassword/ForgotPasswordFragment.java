package com.techease.ultimatesavings.activities.ui.forgotpassword;

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
import com.techease.ultimatesavings.activities.ui.login.LoginViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etEmailAddress)
    EditText etEmail;
    @BindView(R.id.btnRecover)
    Button btnRecover;
    private View view;
    private String emailAddress;
    private boolean valid = false;
    public static ForgotPasswordFragment newInstance() {
        return new ForgotPasswordFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.forgot_password_fragment, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        btnRecover.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRecover:
                startActivity(new Intent(getActivity(), ForgotPasswordSuccessMsgActivity.class));
                break;
            case R.id.ivBack:
                getActivity().onBackPressed();
        }
    }
    private boolean isValid() {
        emailAddress = etEmail.getText().toString();
        if (emailAddress.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            etEmail.setError("Please write a valid email address");
            valid = false;
        } else {
            etEmail.setError(null);
        }
        return valid;
    }
}

