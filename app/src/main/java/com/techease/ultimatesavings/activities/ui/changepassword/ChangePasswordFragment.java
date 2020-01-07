package com.techease.ultimatesavings.activities.ui.changepassword;

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
import com.techease.ultimatesavings.activities.LoginActivity;
import com.techease.ultimatesavings.activities.MainBottomNavActivity;
import com.techease.ultimatesavings.activities.SignUpActivity;
import com.techease.ultimatesavings.models.changePasswordModels.ChangePasswordResponse;
import com.techease.ultimatesavings.utils.AppRepository;
import com.techease.ultimatesavings.utils.Connectivity;
import com.techease.ultimatesavings.utils.networking.BaseNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                if (isValid()) {
                    if (Connectivity.isConnected(getActivity())) {
                        loginCall();
                    } else {
                        Toast.makeText(getActivity(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                    }
                }                break;
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
    private void loginCall() {
        Call<ChangePasswordResponse> signUpResponseCall = BaseNetworking.apiServices().changePassword(AppRepository.mUserEmail(getActivity()), password);
        signUpResponseCall.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                if (response.isSuccessful()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
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
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Server Not Responding", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
