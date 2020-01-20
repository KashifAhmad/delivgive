package com.techease.ultimatesavings.activities.ui.patterns;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techease.ultimatesavings.R;

public class PatternsFragment extends Fragment {

    private PatternsViewModel mViewModel;
    private View view;

    public static PatternsFragment newInstance() {
        return new PatternsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.patterns_fragment, container, false);
        initUI();
        return view;
    }

    private void initUI() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PatternsViewModel.class);
        // TODO: Use the ViewModel
    }

}
