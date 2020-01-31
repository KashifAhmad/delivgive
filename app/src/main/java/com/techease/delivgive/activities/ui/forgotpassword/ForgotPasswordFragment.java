package com.techease.delivgive.activities.ui.forgotpassword;

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

import com.techease.delivgive.R;
import com.techease.delivgive.activities.ForgotPasswordSuccessMsgActivity;
import com.techease.delivgive.models.genericResponseModel.GenericResponse;
import com.techease.delivgive.utils.AppRepository;
import com.techease.delivgive.utils.Connectivity;
import com.techease.delivgive.utils.ProgressView;
import com.techease.delivgive.utils.networking.BaseNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                if (isValid()) {
                    if (Connectivity.isConnected(getActivity())) {
                        ProgressView.loader(getActivity());
                        forgotPassword();
                    } else {
                        Toast.makeText(getActivity(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.ivBack:
                getActivity().onBackPressed();
        }
    }
    private boolean isValid() {
        emailAddress = etEmail.getText().toString();
        valid = true;
        if (emailAddress.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            etEmail.setError("Please write a valid email address");
            valid = false;
        } else {
            etEmail.setError(null);
        }
        return valid;
    }
    private void forgotPassword() {
        Call<GenericResponse> signUpResponseCall = BaseNetworking.apiServices().resetPassword(emailAddress);
        signUpResponseCall.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                ProgressView.mDialog.dismiss();
                if (response.isSuccessful()) {
                    startActivity(new Intent(getActivity(), ForgotPasswordSuccessMsgActivity.class));
                    AppRepository.mPutValue(getActivity()).putString("email", emailAddress).commit();
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

