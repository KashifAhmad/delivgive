package com.techease.ultimatesavings.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

public class ViewChanger {


    public static Fragment fragmentChanger(Context context, Fragment fragment, int id) {
        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
        return fragment;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(Configuation.MY_PREF, 0);
    }

    public static boolean isLogin(Context context){
        return getSharedPreferences(context).getBoolean("isLogin",false);
    }

}