package com.techease.delivgive.activities.ui.accountsetting;

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

import com.techease.delivgive.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountSettingFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etFullName)
    EditText etFullName;
    @BindView(R.id.etDOB)
    EditText etDOB;
    @BindView(R.id.etEmailAddress)
    EditText etEmail;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.btnSave)
    Button btnSave;
    private View view;
    private String fullName, DOB, emailAddress, phoneNumber;
    private boolean valid = false;

    public static AccountSettingFragment newInstance() {
        return new AccountSettingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.account_setting_fragment, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        ivBack.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

    }

    private boolean isValid() {
        valid = true;
        fullName = etFullName.getText().toString();
        DOB = etDOB.getText().toString();
        emailAddress = etEmail.getText().toString();
        phoneNumber = etPhone.getText().toString();
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
        if (!phoneNumber.isEmpty()) {
            etPhone.setError("Please enter a valid phone number");
            valid = false;
        } else {
            etPhone.setError(null);
        }

        return valid;
    }
}
