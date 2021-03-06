package com.techease.delivgive.activities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techease.delivgive.R;
import com.techease.delivgive.adapters.PremiumFlowersAdapter;
import com.techease.delivgive.models.premiumFlowers.Datum;
import com.techease.delivgive.models.premiumFlowers.PremiumResponse;
import com.techease.delivgive.utils.ProgressView;
import com.techease.delivgive.utils.networking.BaseNetworking;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PremiumFragment extends Fragment {
    private PremiumFlowersAdapter adapter;
    List<Datum> flowersList = new ArrayList<>();
    @BindView(R.id.rvFlowers)
    RecyclerView rvFlowers;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_premium, container, false);
        initUI();
        return root;
    }
    private void initUI() {
        ButterKnife.bind(this, root);
        rvFlowers.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new PremiumFlowersAdapter(getActivity(), flowersList);
        rvFlowers.setAdapter(adapter);
        ProgressView.loader(getActivity());
        initData();
    }
    private void initData() {
        Call<PremiumResponse> flowersResponseCall = BaseNetworking.apiServices().premiumFlowers();
        flowersResponseCall.enqueue(new Callback<PremiumResponse>() {
            @Override
            public void onResponse(Call<PremiumResponse> call, Response<PremiumResponse> response) {
                ProgressView.mDialog.dismiss();
                if (response.isSuccessful()){
                    flowersList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PremiumResponse> call, Throwable t) {
                ProgressView.mDialog.dismiss();


            }
        });
    }
}