package com.techease.delivgive.utils;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class TouchButton implements View.OnTouchListener {
    float dX;
    float dY;
    int lastAction;
    ImageView IV;

    public TouchButton(ImageView Image) {
        IV = Image;
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                dX = v.getX() - event.getRawX();
                dY = v.getY() - event.getRawY();
                lastAction = MotionEvent.ACTION_DOWN;
                break;

            case MotionEvent.ACTION_MOVE:
                v.setY(event.getRawY() + dY);
                v.setX(event.getRawX() + dX);
                lastAction = MotionEvent.ACTION_MOVE;
                break;

            case MotionEvent.ACTION_UP:
                if (lastAction == MotionEvent.ACTION_DOWN)
//                    Toast.makeText(getActivity(), "Clicked!", Toast.LENGTH_SHORT).show();
                    break;
            default:
                return false;
        }
        return true;
    }
}