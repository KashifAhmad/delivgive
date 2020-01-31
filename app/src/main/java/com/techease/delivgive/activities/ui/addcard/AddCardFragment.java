package com.techease.delivgive.activities.ui.addcard;

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

import com.techease.delivgive.R;
import com.techease.delivgive.activities.ui.CheckOutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCardFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.etCardNumber)
    EditText etCardNumber;
    @BindView(R.id.etCardHolderName)
    EditText etHolderName;
    @BindView(R.id.etExpiry)
    EditText etExpiry;
    @BindView(R.id.etCVV)
    EditText etCVV;
    @BindView(R.id.btnCheckOut)
    Button btnCheckout;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    private String cardNumber, holderName, expiry, cvv;
    private boolean valid = false;
    private View view;
    public static AddCardFragment newInstance() {
        return new AddCardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_card_fragment, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        btnCheckout.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCheckOut:
                startActivity(new Intent(getActivity(), CheckOutActivity.class));
                break;
            case R.id.ivBack:
                getActivity().onBackPressed();

        }
    }
    private boolean isValid() {
        valid = true;
        cardNumber = etCardNumber.getText().toString();
        holderName = etHolderName.getText().toString();
        cvv = etCVV.getText().toString();
        expiry = etExpiry.getText().toString();
        valid = true;
        if (cardNumber.isEmpty()) {
            valid = false;
            etCardNumber.setError("Please enter card number");
        } else {
            etCardNumber.setError(null);
        }
        if (holderName.isEmpty() || holderName.length() < 3) {
            etHolderName.setError("Please write a valid name");
            valid = false;
        } else {
            etHolderName.setError(null);
        }

        if (cvv.isEmpty() || cvv.length() < 3) {
            valid = false;
            etCVV.setError("Please write a valid cvv");
        } else {
            etCVV.setError(null);
        }
        if (expiry.isEmpty()) {
            etExpiry.setError("Please enter card expiry");
            valid = false;
        } else {
            etExpiry.setError(null);
        }
        return valid;
    }
}
