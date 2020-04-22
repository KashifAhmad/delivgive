package com.techease.delivgive.activities.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techease.delivgive.R;
import com.techease.delivgive.activities.SendBouquetActivity;
import com.techease.delivgive.adapters.CustomImagesAdapter;
import com.techease.delivgive.adapters.PremiumFlowersDialogAdapter;
import com.techease.delivgive.models.flowerImagesModel.FlowersImagesResponse;
import com.techease.delivgive.models.premiumFlowers.Datum;
import com.techease.delivgive.models.premiumFlowers.PremiumResponse;
import com.techease.delivgive.utils.AppRepository;
import com.techease.delivgive.utils.TouchImageView;
import com.techease.delivgive.utils.interfaces.FlowerListener;
import com.techease.delivgive.utils.interfaces.PremiumFlowersLinkListener;
import com.techease.delivgive.utils.networking.BaseNetworking;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.richeditor.RichEditor;
import petrov.kristiyan.colorpicker.ColorPicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment implements
        View.OnClickListener,
        View.OnTouchListener,
        FlowerListener,
        PremiumFlowersLinkListener,
        ActivityCompat.OnRequestPermissionsResultCallback {
    private View view;
    @BindView(R.id.llColors)
    LinearLayout llColors;
    @BindView(R.id.llFlowers)
    LinearLayout llFlowers;
    //    @BindView(R.id.llPatterns)
//    LinearLayout llPatterns;
    @BindView(R.id.llTexts)
    LinearLayout llTexts;
    //    @BindView(R.id.llTemplates)
//    LinearLayout llTemplates;
    @BindView(R.id.flBouquetSpace)
    FrameLayout flBouquetSpace;
    @BindView(R.id.llSendToTop)
    LinearLayout llSendToTop;
    @BindView(R.id.llBack)
    LinearLayout llBack;
    @BindView(R.id.llBackToTop)
    LinearLayout llBackToTop;
    @BindView(R.id.llForward)
    LinearLayout llForward;
    @BindView(R.id.btnDone)
    Button btnDone;
    RecyclerView rvFlowers;
    CustomImagesAdapter adapter;
    PremiumFlowersDialogAdapter premiumFlowersAdapter;
    List<com.techease.delivgive.models.flowerImagesModel.Datum> imagesList = new ArrayList<>();
    List<Datum> premiumList = new ArrayList<>();
    public AlertDialog dialog;
    private RichEditor mEditor;
    private TextView mPreview;
    float dX;
    float dY;
    int lastAction;
    private String bouquetText, textSize, flowerLinkFromAdapter;
    private String imageID;
    private TextView textView;//dynamic textTextView textView
    private boolean isCentre = false, isJustify = false,
            isLeft = false, isRight = false, isMotionEventCalled = false;

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
//        llPatterns.setOnClickListener(this);
        llTexts.setOnClickListener(this);
//        llTemplates.setOnClickListener(this);
        llBack.setOnClickListener(this);
        llBackToTop.setOnClickListener(this);
        llForward.setOnClickListener(this);
        llSendToTop.setOnClickListener(this);
        btnDone.setOnClickListener(this);
        premiumFlowersAdapter = new PremiumFlowersDialogAdapter(getActivity(), premiumList, this);
        initPremium();
        checkPermission();


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


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        view.setOnKeyListener(null);
        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                lastAction = MotionEvent.ACTION_DOWN;
                isMotionEventCalled = true;

                break;

            case MotionEvent.ACTION_MOVE:
                view.setY(event.getRawY() + dY);
                view.setX(event.getRawX() + dX);
                lastAction = MotionEvent.ACTION_MOVE;
                isMotionEventCalled = true;

                break;

            case MotionEvent.ACTION_UP:
                if (lastAction == MotionEvent.ACTION_DOWN)
                    isMotionEventCalled = true;

//                    Toast.makeText(getActivity(), "Clicked!", Toast.LENGTH_SHORT).show();
                break;
            default:
                return false;
        }

        return true;
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
                        flBouquetSpace.setBackgroundColor(color);
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
                premiumFlowersDialog(getActivity());
                break;
            case R.id.llPatterns:
                flowersDialog(getActivity());

//                startActivity(new Intent(getActivity(), PatternsActivity.class));
                break;
            case R.id.btnDone:
                saveBitMap(getActivity(), flBouquetSpace);
                startActivity(new Intent(getActivity(), SendBouquetActivity.class));
                AppRepository.mPutValue(getActivity()).putString("mBouquetSendingTitle", "Deliver Your Custom Crafted Combination").commit();

        }


    }

    public AlertDialog textEditorDialog(Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.text_editor_dialog, null);
        EditText etText = dialogView.findViewById(R.id.etTextHere);
        EditText etTextSize = dialogView.findViewById(R.id.etTextSize);
        Button btnDone = dialogView.findViewById(R.id.btnDone);
        ImageView ivAlignCentre = dialogView.findViewById(R.id.ivAlignCentre);
        ImageView ivAlignJustify = dialogView.findViewById(R.id.ivAlignJustify);
        ImageView ivAlignLeft = dialogView.findViewById(R.id.ivAlignLeft);
        ImageView ivAlignRight = dialogView.findViewById(R.id.ivAlignRight);
        ivAlignCentre.setOnClickListener(v -> {
            isCentre = true;
            isJustify = false;
            isLeft = false;
            isRight = false;
            ivAlignJustify.setImageResource(R.mipmap.align_justify);
            ivAlignLeft.setImageResource(R.mipmap.align_left);
            ivAlignRight.setImageResource(R.mipmap.align_right);
            ivAlignCentre.setImageResource(R.mipmap.align_center_selected);

        });
        ivAlignJustify.setOnClickListener(v -> {
            isCentre = false;
            isJustify = true;
            isLeft = false;
            isRight = false;
            ivAlignCentre.setImageResource(R.mipmap.align_center);
            ivAlignLeft.setImageResource(R.mipmap.align_left);
            ivAlignRight.setImageResource(R.mipmap.align_right);
            ivAlignJustify.setImageResource(R.mipmap.align_justify_selected);
        });
        ivAlignLeft.setOnClickListener(v -> {
            isCentre = false;
            isJustify = false;
            isLeft = true;
            isRight = false;
            ivAlignCentre.setImageResource(R.mipmap.align_center);
            ivAlignJustify.setImageResource(R.mipmap.align_justify);
            ivAlignRight.setImageResource(R.mipmap.align_right);
            ivAlignLeft.setImageResource(R.mipmap.align_left_selected);

        });
        ivAlignRight.setOnClickListener(v -> {
            isCentre = false;
            isJustify = false;
            isLeft = false;
            isRight = true;
            ivAlignCentre.setImageResource(R.mipmap.align_center);
            ivAlignLeft.setImageResource(R.mipmap.align_left);
            ivAlignJustify.setImageResource(R.mipmap.align_justify);
            ivAlignRight.setImageResource(R.mipmap.align_right_selected);
        });
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
        mEditor.setOnTextChangeListener(text -> mPreview.setText(text));
        textView = new TextView(getActivity());

        btnDone.setOnClickListener(v -> {
            bouquetText = etText.getText().toString();
            textSize = etTextSize.getText().toString();
            textView.setText(bouquetText);
            LinearLayout.LayoutParams params = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            textView.setPadding(10, 10, 10, 10);
            textView.setLayoutParams(params);
            params.setMargins(30, 0, 30, 0);
            if (textSize.length() > 0) {
                textView.setTextSize(Float.parseFloat(textSize));
            }
            flBouquetSpace.addView(textView);
            textView.setBackground(getResources().getDrawable(R.drawable.round_line_border));
            if (isCentre) {
                textView.setGravity(Gravity.CENTER);
                etText.setGravity(Gravity.CENTER);
            }
            if (isJustify) {
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                etText.setGravity(Gravity.CENTER_HORIZONTAL);
            }
            if (isLeft) {
                textView.setGravity(Gravity.LEFT);
                etText.setGravity(Gravity.LEFT);
            }
            if (isRight) {
                textView.setGravity(Gravity.RIGHT);
                etText.setGravity(Gravity.CENTER);
            }
            dialog.dismiss();
            if (isMotionEventCalled) {

            } else {
                textView.setOnClickListener(v1 -> textEditorDialog(getActivity()));
            }


        });
        textView.setOnTouchListener(this);


        dialog = dialogBuilder.create();
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        return dialog;
    }


    public AlertDialog flowersDialog(Context context) {
        imagesList.clear();
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
        freeFlowersForCreateBouquet();
        btnYes.setOnClickListener(v -> {
            dialog.dismiss();
//            PhotoView imageView = new PhotoView(getActivity());
            TouchImageView imageView = new TouchImageView(getActivity());
//            ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            Picasso.get().load(imageID).into(imageView);
//            imageView.setImageResource(imageID);
            imageView.setLayoutParams(params);
            flBouquetSpace.addView(imageView);
            imageView.setOnTouchListener(this);


        });
        dialog = dialogBuilder.create();
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        return dialog;
    }

    private void freeFlowersForCreateBouquet() {
        Call<FlowersImagesResponse> imagesResponseCall = BaseNetworking.apiServices().flowers();
        imagesResponseCall.enqueue(new Callback<FlowersImagesResponse>() {
            @Override
            public void onResponse(Call<FlowersImagesResponse> call, Response<FlowersImagesResponse> response) {
                if (response.isSuccessful()) {
                    imagesList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<FlowersImagesResponse> call, Throwable t) {

            }
        });
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
                if (response.isSuccessful()) {
                    premiumList.addAll(response.body().getData());
                    premiumFlowersAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PremiumResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void flowerID(String id) {
        imageID = id;
    }

    private File saveBitMap(Context context, View drawView) {
        File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), getString(R.string.app_name));
        if (!pictureFileDir.exists()) {
            boolean isDirectoryCreated = pictureFileDir.mkdirs();
            if (!isDirectoryCreated)
                Log.i("ATG", "Can't create directory to save the image");
            return null;
        }
        String filename = pictureFileDir.getPath() + File.separator + System.currentTimeMillis() + ".jpg";
        Log.d("zma pic path", filename);
        AppRepository.mPutValue(getActivity()).putString("picPath", filename).commit();
        File pictureFile = new File(filename);
        Bitmap bitmap = getBitmapFromView(drawView);
        try {
            pictureFile.createNewFile();
            FileOutputStream oStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);
            oStream.flush();
            oStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue saving the image.");
        }
        scanGallery(context, pictureFile.getAbsolutePath());
        return pictureFile;
    }

    //create bitmap from view and returns it
    private Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    // used for scanning gallery
    private void scanGallery(Context cntx, String path) {
        try {
            MediaScannerConnection.scanFile(cntx, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String path, Uri uri) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bouquetLink(String link) {
        flowerLinkFromAdapter = link;
        dialog.dismiss();
        TouchImageView imageView = new TouchImageView(getActivity());
//            ImageView imageView = new ImageView(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Picasso.get().load(link).into(imageView);
        imageView.setLayoutParams(params);
        flBouquetSpace.addView(imageView);
        imageView.setOnTouchListener(this);
    }
}
