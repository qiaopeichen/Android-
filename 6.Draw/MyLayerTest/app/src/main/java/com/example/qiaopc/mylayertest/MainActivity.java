package com.example.qiaopc.mylayertest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyLayer(this));
    }

    public class MyLayer extends View {
        private Paint mPaint;
        private static final int LAYER_FLAGS =
                        Canvas.MATRIX_SAVE_FLAG |
                        Canvas.CLIP_SAVE_FLAG |
                        Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                        Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                        Canvas.CLIP_TO_LAYER_SAVE_FLAG;

        public MyLayer(Context context) {
            super(context);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // 画一个图层
            canvas.drawColor(Color.GRAY);
            mPaint.setColor(Color.BLUE);
            canvas.drawCircle(150, 150, 100, mPaint);

            // 入栈新图层，并设置透明度
            canvas.saveLayerAlpha(0, 0, 400, 400, 127, LAYER_FLAGS);
            mPaint.setColor(Color.RED);
            // 在新图层上操作
            canvas.drawCircle(200, 200, 100, mPaint);
            canvas.restore();
        }
    }
}
