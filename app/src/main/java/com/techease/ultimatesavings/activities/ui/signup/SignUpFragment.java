package com.techease.ultimatesavings.activities.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.activities.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.tvSignIn)
    TextView tvSignIn;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etFullName)
    EditText etFullName;
    @BindView(R.id.etDOB)
    EditText etDOB;
    @BindView(R.id.etEmailAddress)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etConfirmPassword)
    EditText etConfirmPassword;
    private View view;
    private String fullName, DOB, emailAddress, password, confirmPassword;
    private boolean valid = false;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sign_up_fragment, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        tvSignIn.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSignIn:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.ivBack:
                getActivity().onBackPressed();
        }
    }

    private boolean isValid() {
        fullName = etFullName.getText().toString();
        DOB = etDOB.getText().toString();
        emailAddress = etEmail.getText().toString();
        password = etPassword.getText().toString();
        confirmPassword = etConfirmPassword.getText().toString();
        if (fullName.isEmpty()) {
            valid = false;
            etFullName.setError("Please write your name");
        } else {
            etFullName.setError(null);
        }
        if (DOB.length() == 0) {
            etDOB.setError("Please select a date");
            valid = false;
        } else {
            etDOB.setError(null);
        }
        if (emailAddress.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            etEmail.setError("Please write a valid email address");
            valid = false;
        } else {
            etEmail.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            etPassword.setError("Password should be six characters long");
            valid = false;
        } else {
            etPassword.setError(null);
        }
        if (!confirmPassword.equals(password)){
            etConfirmPassword.setError("Password doesn't match");
            valid = false;
        }else {
            etConfirmPassword.setError(null);
        }
        return valid;
    }

}
