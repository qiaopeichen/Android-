package com.example.qiaopc.imageshape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qiaopc on 2018/1/21 0021.
 */

public class RoundRectShaderView extends View {

    private BitmapShader mBitmapShader;
    private Bitmap mBitmap;
    private Paint mPaint;

    public RoundRectShaderView(Context context) {
        super(context);
    }

    public RoundRectShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundRectShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
//        mPaint.setShader(mBitmapShader);
        mPaint.setShader(new LinearGradient(0, 0, 400, 400, Color.BLUE, Color.YELLOW, Shader.TileMode.REPEAT));
//        canvas.drawCircle(500, 250, 200, mPaint);
        canvas.drawRect(0, 0, 500, 500, mPaint);
    }
}
