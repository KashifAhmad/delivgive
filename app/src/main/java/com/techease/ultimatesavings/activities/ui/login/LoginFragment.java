package com.techease.ultimatesavings.activities.ui.login;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.activities.ForgotPasswordActivity;
import com.techease.ultimatesavings.activities.MainBottomNavActivity;
import com.techease.ultimatesavings.activities.SignUpActivity;
import com.techease.ultimatesavings.models.loginModels.LoginResponse;
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
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
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
        btnSignIn.setOnClickListener(this);
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
            case R.id.btnSignIn:
                if (isValid()) {
                    if (Connectivity.isConnected(getActivity())) {
                        loginCall();
                    } else {
                        Toast.makeText(getActivity(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }

    private boolean isValid() {
        emailAddress = etEmail.getText().toString();
        password = etPassword.getText().toString();
        valid = true;
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

    private void loginCall() {
        Call<LoginResponse> signUpResponseCall = BaseNetworking.apiServices().login(emailAddress, password);
        signUpResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    startActivity(new Intent(getActivity(), MainBottomNavActivity.class));
                    AppRepository.mPutValue(getActivity()).putBoolean("loggedIn", true).commit();
                    getActivity().finishAffinity();
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
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Server Not Responding", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
