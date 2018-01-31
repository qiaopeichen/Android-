package com.qiaopc.customanim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by qiaopc on 2018/1/31 0031.
 */

public class CustomTV extends Animation {

    private int mCenterWidth;
    private int mCenterHeight;
    private Camera mCamera = new Camera();
    private float mRotateY = 0.0f;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        // 设置默认时长
        setDuration(1000);
        // 动画结束后保留状态
        setFillAfter(true);
        // 设置默认插值器
        setInterpolator(new AccelerateInterpolator());
        mCenterHeight = height / 2;
        mCenterWidth = width / 2;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Matrix matrix = t.getMatrix();
        matrix.preScale(1, 1 - interpolatedTime, mCenterWidth, mCenterHeight);
    }
}
