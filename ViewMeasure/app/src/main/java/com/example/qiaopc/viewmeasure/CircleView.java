package com.example.qiaopc.viewmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import static android.content.ContentValues.TAG;

/**
 * Created by qiaopc on 2017/12/16 0016.
 */

public class CircleView extends View {

    private int mCircleXY;
    private int length;
    private float mRadius;
    private RectF mArcRecF;
    private Paint mCirclePaint;
    private float mSweepValue;
    private Paint mArcPaint;
    private String mShowText;
    private Paint mTextPaint;
    private int mShowTextSize;

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        length = wm.getDefaultDisplay().getWidth();
        Log.d(TAG, "CircleView: length = " + length);
        mCircleXY = length / 2;
        mRadius = (float) (length * 0.5 / 2);

        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.RED);
        mCirclePaint.setStyle(Paint.Style.FILL);
        // 设置画笔抗锯齿效果
        mCirclePaint.setAntiAlias(true);

        mArcPaint = new Paint();
        mArcPaint.setColor(Color.BLUE);
        mArcPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔宽度
        mArcPaint.setStrokeWidth(70);
        mArcPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setTextSize(40);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mArcRecF = new RectF(
                (float) (length * 0.1),
                (float) (length * 0.1),
                (float) (length * 0.9),
                (float) (length * 0.9));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制圆
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        // 绘制弧线
        canvas.drawArc(mArcRecF, 270, mSweepValue, false, mArcPaint);
        // 绘制文字
        canvas.drawText(mShowText, 0, mShowText.length(), mCircleXY, mCircleXY + mShowTextSize , mTextPaint);
    }

    public void setSweepValue(float sweepValue) {
        if (sweepValue != 0) {
            this.mSweepValue = sweepValue;
        } else {
            mSweepValue = 25;
        }
        this.invalidate();
    }

    public void setShowText(String showText) {
        if (null == showText) {
            mShowText = "default text";
        } else {
            mShowText = showText;
        }
        mShowTextSize = mShowText.length();
    }

}
