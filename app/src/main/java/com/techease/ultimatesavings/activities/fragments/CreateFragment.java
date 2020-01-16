package com.techease.ultimatesavings.activities.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techease.ultimatesavings.R;
import com.techease.ultimatesavings.adapters.CustomImagesAdapter;
import com.techease.ultimatesavings.models.Images;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import petrov.kristiyan.colorpicker.ColorPicker;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment implements View.OnClickListener {
    private View view;
    @BindView(R.id.llColors)
    LinearLayout llColors;
    @BindView(R.id.llFlowers)
    LinearLayout llFlowers;
    @BindView(R.id.llPatterns)
    LinearLayout llPatterns;
    @BindView(R.id.llTexts)
    LinearLayout llTexts;
    @BindView(R.id.llTemplates)
    LinearLayout llTemplates;
    RecyclerView rvFlowers;
    CustomImagesAdapter adapter;
    List<Images> imagesList = new ArrayList<>();
    private boolean isVisible = false;
    public AlertDialog dialog;


    public CreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        llColors.setOnClickListener(this);
        llFlowers.setOnClickListener(this);
        llPatterns.setOnClickListener(this);
        llTexts.setOnClickListener(this);
        llTemplates.setOnClickListener(this);

        initData();

    }

    public void initData() {
        imagesList.add(new Images(R.drawable.bouquet_32, ""));
        imagesList.add(new Images(R.drawable.bouquet_32, ""));
        imagesList.add(new Images(R.drawable.bouquet_52, ""));
        imagesList.add(new Images(R.drawable.bouquet_32, ""));
        imagesList.add(new Images(R.drawable.bouquet_52, ""));
        imagesList.add(new Images(R.drawable.bouquet_32, ""));
        imagesList.add(new Images(R.drawable.bouquet_52, ""));
        imagesList.add(new Images(R.drawable.bouquet_32, ""));
        imagesList.add(new Images(R.drawable.bouquet_52, ""));
        imagesList.add(new Images(R.drawable.bouquet_32, ""));
        imagesList.add(new Images(R.drawable.bouquet_52, ""));
        imagesList.add(new Images(R.drawable.bouquet_32, ""));
        imagesList.add(new Images(R.drawable.bouquet_52, ""));
        imagesList.add(new Images(R.drawable.bouquet_32, ""));
        imagesList.add(new Images(R.drawable.bouquet_52, ""));


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llColors:
                ColorPicker colorPicker = new ColorPicker(getActivity());
                colorPicker.show();
                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        // put code
                    }

                    @Override
                    public void onCancel() {
                        // put code
                    }
                });
                break;
            case R.id.llFlowers:
                flowersDialog(getActivity());

//                if (isVisible) {
//                    flowersDialog(getActivity());
//                    isVisible = false;
//                } else {
//                    rvFlowers.setVisibility(View.VISIBLE);
//                    isVisible = true;
//                }

        }


    }
    public AlertDialog flowersDialog(Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.flowers_dialog, null);
        dialogBuilder.setView(dialogView);
        Button btnYes = dialogView.findViewById(R.id.btnDone);
        rvFlowers = dialogView.findViewById(R.id.rvFlowers);
        rvFlowers.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        adapter = new CustomImagesAdapter(getActivity(), imagesList);
        rvFlowers.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialog = dialogBuilder.create();
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        return dialog;
    }

}
