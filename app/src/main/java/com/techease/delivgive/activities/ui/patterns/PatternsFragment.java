package com.techease.delivgive.activities.ui.patterns;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techease.delivgive.FlowerListener;
import com.techease.delivgive.R;
import com.techease.delivgive.adapters.CustomImagesAdapter;
import com.techease.delivgive.adapters.PremiumFlowersDialogAdapter;
import com.techease.delivgive.models.Images;
import com.techease.delivgive.models.premiumFlowers.Datum;
import com.techease.delivgive.models.premiumFlowers.PremiumResponse;
import com.techease.delivgive.utils.ProgressView;
import com.techease.delivgive.utils.networking.BaseNetworking;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.richeditor.RichEditor;
import petrov.kristiyan.colorpicker.ColorPicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatternsFragment extends Fragment implements View.OnClickListener, FlowerListener {

    private PatternsViewModel mViewModel;
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
    PremiumFlowersDialogAdapter premiumFlowersAdapter;
    List<Images> imagesList = new ArrayList<>();
    List<Datum> premiumList = new ArrayList<>();
    public AlertDialog dialog;
    private RichEditor mEditor;
    private TextView mPreview;

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
        ButterKnife.bind(this, view);
        llColors.setOnClickListener(this);
        llFlowers.setOnClickListener(this);
        llPatterns.setOnClickListener(this);
        llTexts.setOnClickListener(this);
        llTemplates.setOnClickListener(this);
        premiumFlowersAdapter = new PremiumFlowersDialogAdapter(getActivity(), premiumList);
        initData();
        initPremium();
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
    public AlertDialog flowersDialog(Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.flowers_dialog, null);
        dialogBuilder.setView(dialogView);
        Button btnYes = dialogView.findViewById(R.id.btnDone);
        rvFlowers = dialogView.findViewById(R.id.rvFlowers);
        rvFlowers.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        adapter = new CustomImagesAdapter(getActivity(), imagesList, this);
        rvFlowers.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btnYes.setOnClickListener(v -> {

        });
        dialog = dialogBuilder.create();
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        return dialog;
    }

    public AlertDialog premiumFlowersDialog(Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.premium_flowers_dialog, null);
        dialogBuilder.setView(dialogView);
        rvFlowers = dialogView.findViewById(R.id.rvFlowers);
        rvFlowers.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvFlowers.setAdapter(premiumFlowersAdapter);
        premiumFlowersAdapter.notifyDataSetChanged();
        dialog = dialogBuilder.create();
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        return dialog;
    }

    private void initPremium() {
        Call<PremiumResponse> flowersResponseCall = BaseNetworking.apiServices().premiumFlowers();
        flowersResponseCall.enqueue(new Callback<PremiumResponse>() {
            @Override
            public void onResponse(Call<PremiumResponse> call, Response<PremiumResponse> response) {
//                ProgressView.mDialog.dismiss();
                if (response.isSuccessful()) {
                    premiumList.addAll(response.body().getData());
                    premiumFlowersAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PremiumResponse> call, Throwable t) {
                ProgressView.mDialog.dismiss();
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PatternsViewModel.class);
        // TODO: Use the ViewModel
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
                break;
            case R.id.llTexts:
                textEditorDialog(getActivity());
                break;
            case R.id.llTemplates:
                ProgressView.loader(getActivity());
                premiumFlowersDialog(getActivity());
                break;
            case R.id.llPatterns:
//                startActivity(new Intent(getActivity(), PatternsActivity.class));
        }
    }
    public AlertDialog textEditorDialog(Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.text_editor_dialog, null);
        dialogBuilder.setView(dialogView);
        mEditor = dialogView.findViewById(R.id.editor);
        mPreview = dialogView.findViewById(R.id.preview);
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(Color.RED);
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("Insert text here...");
        mEditor.setOnTextChangeListener(text -> {
            // Do Something
            Log.d("RichEditor", "Preview " + text);
        });
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                mPreview.setText(text);
            }
        });

//        ImageButton actionUndo = dialogView.findViewById(R.id.action_undo);
//        actionUndo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.undo();
//            }
//        });

//        ImageButton actionRedo = dialogView.findViewById(R.id.action_redo);
//        actionRedo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.redo();
//            }
//        });

//        ImageButton actionBold = dialogView.findViewById(R.id.action_bold);
//        actionBold.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setBold();
//            }
//        });

//        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setItalic();
//            }
//        });
//
//        findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setSubscript();
//            }
//        });
//
//        findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setSuperscript();
//            }
//        });
//
//        findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setStrikeThrough();
//            }
//        });
//
//        findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setUnderline();
//            }
//        });
//
//        findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(1);
//            }
//        });
//
//        findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(2);
//            }
//        });
//
//        findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(3);
//            }
//        });
//
//        findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(4);
//            }
//        });
//
//        findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(5);
//            }
//        });
//
//        findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(6);
//            }
//        });
//
//        findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
//            private boolean isChanged;
//
//            @Override
//            public void onClick(View v) {
//                mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
//                isChanged = !isChanged;
//            }
//        });
//
//        findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
//            private boolean isChanged;
//
//            @Override
//            public void onClick(View v) {
//                mEditor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
//                isChanged = !isChanged;
//            }
//        });
//
//        findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setIndent();
//            }
//        });
//
//        findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setOutdent();
//            }
//        });
//
//        findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setAlignLeft();
//            }
//        });
//
//        findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setAlignCenter();
//            }
//        });
//
//        findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setAlignRight();
//            }
//        });
//
//        findViewById(R.id.action_blockquote).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setBlockquote();
//            }
//        });
//
//        findViewById(R.id.action_insert_bullets).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setBullets();
//            }
//        });
//
//        findViewById(R.id.action_insert_numbers).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setNumbers();
//            }
//        });
//
//        findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
//                        "dachshund");
//            }
//        });
//
//        findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.insertLink("https://github.com/wasabeef", "wasabeef");
//            }
//        });
//        findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.insertTodo();
//            }
//        });


        dialog = dialogBuilder.create();
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        return dialog;
    }

    @Override
    public void flowerID(int id) {

    }
}
