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
import com.techease.ultimatesavings.utils.ProgressView;
import com.techease.ultimatesavings.utils.networking.BaseNetworking;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FreeFlowersFragment extends Fragment {
    private View root;
    private FreeFlowersAdapter adapter;
    List<Datum> flowersList = new ArrayList<>();
    @BindView(R.id.rvFlowers)
    RecyclerView rvFlowers;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_free_flowers, container, false);
        initUI();
        return root;
    }

    private void initUI() {
        ButterKnife.bind(this, root);
        rvFlowers.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new FreeFlowersAdapter(getActivity(), flowersList);
        rvFlowers.setAdapter(adapter);
        initData();
        ProgressView.loader(getActivity());
    }

    private void initData() {
        Call<FreeFlowersResponse> flowersResponseCall = BaseNetworking.apiServices().freeFlowers();
        flowersResponseCall.enqueue(new Callback<FreeFlowersResponse>() {
            @Override
            public void onResponse(Call<FreeFlowersResponse> call, Response<FreeFlowersResponse> response) {
                ProgressView.mDialog.dismiss();
                if (response.isSuccessful()){
                    flowersList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<FreeFlowersResponse> call, Throwable t) {
                ProgressView.mDialog.dismiss();
            }
        });
    }
}