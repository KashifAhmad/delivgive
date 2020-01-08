package com.techease.ultimatesavings.activities.ui.verifycode;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.activities.ChangePasswordActivity;
import com.techease.ultimatesavings.activities.MainBottomNavActivity;
import com.techease.ultimatesavings.models.genericResponseModel.GenericResponse;
import com.techease.ultimatesavings.utils.Connectivity;
import com.techease.ultimatesavings.utils.ProgressView;
import com.techease.ultimatesavings.utils.networking.BaseNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        initUI();
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
                if (isValid()) {
                    if (Connectivity.isConnected(getActivity())) {
                        ProgressView.loader(getActivity());
                        verifyCodeCall();
                    } else {
                        Toast.makeText(getActivity(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                    }
                }                break;
            case R.id.ivBack:
                getActivity().onBackPressed();
        }
    }
    private boolean isValid() {
        verifyCode = etVerifyCode.getText().toString();
        valid  =true;
        if (verifyCode.isEmpty() ){
            etVerifyCode.setError("Please write valid code");
            valid = false;
        } else {
            etVerifyCode.setError(null);
        }
        return valid;
    }
    private void verifyCodeCall() {
        Call<GenericResponse> signUpResponseCall = BaseNetworking.apiServices().verifyCode(verifyCode);
        signUpResponseCall.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                ProgressView.mDialog.dismiss();
                if (response.isSuccessful()) {
                    startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
                } else {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                ProgressView.mDialog.dismiss();
                Toast.makeText(getActivity(), "Server Not Responding", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
