package com.example.qiaopc.viewmeasure;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qiaopc on 2017/12/15 0015.
 */

public class MyView extends View {

    private static Context mContext;

    // 只实现单参数的构造方法会报错，具体原因未知，猜测可能和第二个参数有关
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Resources resources = mContext.getResources();
        Bitmap bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher);
        Bitmap bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_round).copy(Bitmap.Config.ARGB_8888, true);
        canvas.drawBitmap(bitmap1, 0, 0, null);
        canvas.drawBitmap(bitmap2, 200, 200, null);

        Canvas canvas1 = new Canvas(bitmap2);
        canvas1.drawARGB(128,255,0,0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 0;

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = result < specSize ? result : specSize;
            }
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 0;

        if (specMode == MeasureSpec.EXACTLY) {
            return specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
    
}
