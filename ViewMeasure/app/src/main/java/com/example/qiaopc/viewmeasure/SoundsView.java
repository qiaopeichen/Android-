package com.example.qiaopc.viewmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import java.util.Random;

/**
 * Created by qiaopc on 2017/12/16 0016.
 */

public class SoundsView extends View {

    private int mRectCount;
    private float mWidth;
    private int mRectWidth;
    private int offset;
    private float currentHeight;
    private float mRectHeight;
    private Paint mPaint;
    private float mRandom;
    private final WindowManager wm;

    public SoundsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);

        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mRectHeight = wm.getDefaultDisplay().getHeight();
        mWidth = wm.getDefaultDisplay().getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mRectCount; i++) {
            // 随机赋予音量条高度
            mRandom = (float) Math.random();
            currentHeight = 1000 * mRandom;

            canvas.drawRect(
                    (float) (mWidth * 0.4 / 2 /* 从1/8处开始画音量条 */ + mRectWidth * i + offset),
                    currentHeight,
                    (float) (mWidth * 0.4 / 2 + mRectWidth * (i + 1)),
                    mRectHeight,
                    mPaint);
        }
    }

    /**
     * 矩形数量
     *
     * @param rectCount
     */
    public void setRectCount(int rectCount) {
        this.mRectCount = rectCount;
    }

    /**
     * 矩形偏移量
     *
     * @param offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * 矩形宽度
     *
     * @param rectWidth
     */
    public void setRectWidth(int rectWidth) {
        this.mRectWidth = rectWidth;
    }

}
