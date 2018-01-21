package com.example.qiaopc.imageshape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qiaopc on 2018/1/21 0021.
 */

public class XfermodeView extends View {
    private Bitmap mBgBitmap, mFgBitmap;
    private Paint mPaint;
    private Canvas mCanva;
    private Path mPath;

    public XfermodeView(Context context) {
        super(context);
        init();
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        mPaint = new Paint();
//        mPaint.setAlpha(0);
//        mPaint.setXfermode(
//                new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
//        )
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
//        mPaint.setStrokeWidth(50);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPath = new Path();
//        mBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
//        mFgBitmap =

    }
}
