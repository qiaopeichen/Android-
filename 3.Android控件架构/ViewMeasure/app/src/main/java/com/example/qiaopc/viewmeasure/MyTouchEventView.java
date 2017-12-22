package com.example.qiaopc.viewmeasure;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * Created by qiaopc on 2017/12/21 0021.
 */

public class MyTouchEventView extends View {


    public MyTouchEventView(Context context) {
        super(context);
    }

    public MyTouchEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouchEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyView onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyView dispatchTouchEvent: " + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

    }
}
