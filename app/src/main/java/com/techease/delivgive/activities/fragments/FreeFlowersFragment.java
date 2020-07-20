package com.techease.delivgive.activities.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techease.delivgive.R;
import com.techease.delivgive.adapters.FreeFlowersAdapter;
import com.techease.delivgive.models.freeFlowersModels.Datum;
import com.techease.delivgive.models.freeFlowersModels.FreeFlowersResponse;
import com.techease.delivgive.utils.ProgressView;
import com.techease.delivgive.utils.networking.BaseNetworking;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FreeFlowersFragment extends Fragment implements ActivityCompat.OnRequestPermissionsResultCallback {
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
        checkPermission();
    }

    private void initData() {
        Call<FreeFlowersResponse> flowersResponseCall = BaseNetworking.apiServices().freeFlowers();
        flowersResponseCall.enqueue(new Callback<FreeFlowersResponse>() {
            @Override
            public void onResponse(Call<FreeFlowersResponse> call, Response<FreeFlowersResponse> response) {
                ProgressView.mDialog.dismiss();
                if (response.isSuccessful()) {
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

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1001);

            }
        } else {
            // Permission has already been granted
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1001: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

}