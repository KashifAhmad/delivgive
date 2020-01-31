package com.techease.delivgive.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import com.techease.delivgive.R;

public class ProgressView {
    public static AlertDialog mDialog;

    public static AlertDialog loader(Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.custom_dialog_layout, null);
        dialogBuilder.setView(dialogView);
        mDialog = dialogBuilder.create();
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.show();

        return mDialog;
    }
}
