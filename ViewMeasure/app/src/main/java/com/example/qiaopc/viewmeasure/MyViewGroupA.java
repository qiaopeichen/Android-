package com.example.qiaopc.viewmeasure;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import static android.content.ContentValues.TAG;

/**
 * Created by qiaopc on 2017/12/21 0021.
 */

public class MyViewGroupA extends LinearLayout {


    public MyViewGroupA(Context context) {
        super(context);
    }

    public MyViewGroupA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroupA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyViewGroupA onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "MyViewGroupA dispatchTouchEvent: " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "MyViewGroupA onInterceptTouchEvent: " + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
