package com.example.qiaopc.dragviewtest;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * Created by qiaopc on 2018/1/10 0010.
 */

public class DragView1 extends View {

    private int lastX;
    private int lastY;


    public DragView1(Context context) {
        super(context);
        initView();
    }

    public DragView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DragView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        // 给View设置背景颜色，便于观察
        setBackgroundColor(Color.BLUE);
    }

    // 视图坐标方式
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.e(TAG, "onTouchEvent: " + x +"---" + y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录触摸点坐标
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                // 在当前left,top,right,bottom的基础上加上偏移量
                // view会调用layout方法，layout中会调用onLayout方法，正常情况下我们重写onLayout就可以了
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);
                break;
        }
        return true;
    }
}
