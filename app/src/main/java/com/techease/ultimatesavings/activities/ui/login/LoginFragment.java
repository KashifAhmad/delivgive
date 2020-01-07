package com.techease.ultimatesavings.activities.ui.login;

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
import androidx.lifecycle.ViewModelProviders;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.activities.ForgotPasswordActivity;
import com.techease.ultimatesavings.activities.LoginActivity;
import com.techease.ultimatesavings.activities.MainActivity;
import com.techease.ultimatesavings.activities.MainBottomNavActivity;
import com.techease.ultimatesavings.activities.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.tvSignUp)
    TextView tvSignUp;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etEmailAddress)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;
    private View view;
    private LoginViewModel mViewModel;
    private String emailAddress, password;
    private boolean valid = false;


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_fragment, container, false);
        initUI();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        tvSignUp.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSignUp:
                startActivity(new Intent(getActivity(), SignUpActivity.class));
                break;
            case R.id.ivBack:
                getActivity().onBackPressed();
                break;
            case R.id.tvForgotPassword:
                startActivity(new Intent(getActivity(), ForgotPasswordActivity.class));
                break;
        }
    }
    private boolean isValid() {
        emailAddress = etEmail.getText().toString();
        password = etPassword.getText().toString();

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

        return valid;
    }
}
