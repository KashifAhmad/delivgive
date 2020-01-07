package com.techease.ultimatesavings.activities.ui.changepassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.activities.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePasswordFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etConfirmPassword)
    EditText etConfirmPassword;
    @BindView(R.id.btnChangePassword)
    Button btnChangePassword;
    private View view;
    private String password, confirmPassword;
    private boolean valid = false;

    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.change_password_fragment, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        ivBack.setOnClickListener(this);
        btnChangePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChangePassword:
                startActivity(new Intent(getActivity(), SignUpActivity.class));
                break;
            case R.id.ivBack:
                getActivity().onBackPressed();
                break;
        }
    }

    private boolean isValid() {
        password = etPassword.getText().toString();
        confirmPassword = etConfirmPassword.getText().toString();

        if (password.isEmpty() || password.length() < 6) {
            etPassword.setError("Password should be six characters long");
            valid = false;
        } else {
            etPassword.setError(null);
        }
        if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
            etConfirmPassword.setError("Password doesn't matched");
            valid = false;
        } else {
            etConfirmPassword.setError(null);
        }

        return valid;
    }


}
