package com.techease.ultimatesavings.activities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.adapters.FreeFlowersAdapter;
import com.techease.ultimatesavings.models.freeFlowersModels.Datum;
import com.techease.ultimatesavings.models.freeFlowersModels.FreeFlowersResponse;
import com.techease.ultimatesavings.utils.networking.BaseNetworking;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PremiumFragment extends Fragment {
    private FreeFlowersAdapter adapter;
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
        adapter = new FreeFlowersAdapter(getActivity(), flowersList);
        rvFlowers.setAdapter(adapter);
        initData();
    }
    private void initData() {
        Call<FreeFlowersResponse> flowersResponseCall = BaseNetworking.apiServices().premiumFlowers();
        flowersResponseCall.enqueue(new Callback<FreeFlowersResponse>() {
            @Override
            public void onResponse(Call<FreeFlowersResponse> call, Response<FreeFlowersResponse> response) {
                if (response.isSuccessful()){
                    flowersList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<FreeFlowersResponse> call, Throwable t) {

            }
        });
    }
}