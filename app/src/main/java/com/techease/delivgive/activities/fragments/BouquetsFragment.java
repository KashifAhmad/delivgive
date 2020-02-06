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
import com.techease.delivgive.adapters.BouquetsAdapter;
import com.techease.delivgive.adapters.FreeFlowersAdapter;
import com.techease.delivgive.models.getBouquetsModels.GetBouquetsResponse;
import com.techease.delivgive.models.getBouquetsModels.Datum;
import com.techease.delivgive.utils.ProgressView;
import com.techease.delivgive.utils.networking.BaseNetworking;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BouquetsFragment extends Fragment {
    private View root;
    private BouquetsAdapter adapter;
    List<Datum> flowersList = new ArrayList<>();
    @BindView(R.id.rvFlowers)
    RecyclerView rvFlowers;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_bouquet, container, false);
        initUI();
        return root;
    }

    private void initUI() {
        ButterKnife.bind(this, root);
        rvFlowers.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new BouquetsAdapter(getActivity(), flowersList);
        rvFlowers.setAdapter(adapter);
        initData();
        ProgressView.loader(getActivity());
    }

    private void initData() {
        Call<GetBouquetsResponse> flowersResponseCall = BaseNetworking.apiServices().bouquets();
        flowersResponseCall.enqueue(new Callback<GetBouquetsResponse>() {
            @Override
            public void onResponse(Call<GetBouquetsResponse> call, Response<GetBouquetsResponse> response) {
                ProgressView.mDialog.dismiss();
                if (response.isSuccessful()) {
                    flowersList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<GetBouquetsResponse> call, Throwable t) {
                ProgressView.mDialog.dismiss();
            }
        });
    }
}