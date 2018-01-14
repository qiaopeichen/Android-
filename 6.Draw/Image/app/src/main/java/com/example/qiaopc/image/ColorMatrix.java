package com.example.qiaopc.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class ColorMatrix extends AppCompatActivity {

    private Bitmap bitmap;
    private ImageView mImageView;
    private GridLayout mGroup;
    private int mEtWidth;
    private int mEtHeight;
    private EditText[] mEts = new EditText[20];
    private float[] mColorMatrix = new float[20];
    private boolean INVALID_FLAG = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        mImageView = findViewById(R.id.imageview);
        mGroup = findViewById(R.id.group);
        mImageView.setImageBitmap(bitmap);

        mGroup.post(new Runnable() {
            @Override
            public void run() {
                // 获取宽高信息
                mEtWidth = mGroup.getWidth() / 5;
                mEtHeight = mGroup.getHeight() / 4;
                addEts();
                initMatrix();
            }
        });
    }

    // 初始化颜色矩阵为初始状态
    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEts[i].setText(String.valueOf(1));
            } else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }

    // 添加EditText
    private void addEts() {
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(ColorMatrix.this);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            mEts[i] = editText;
            mGroup.addView(editText, mEtWidth, mEtHeight);
        }
    }

    // 重置矩阵效果
    public void btnReset(View view) {
        initMatrix();
        getMatrix();
        if (!INVALID_FLAG) {
            setImageMatrix();
        }
    }

    // 作用矩阵效果
    public void btnChange(View view) {
        getMatrix();
        if (!INVALID_FLAG) {
            setImageMatrix();
        }
    }

    // 获取矩阵值
    public void getMatrix() {
        for (int i = 0; i < 20; i++) {
            if (TextUtils.isEmpty(mEts[i].getText())) {
                Toast.makeText(ColorMatrix.this, "有参数为空", Toast.LENGTH_SHORT).show();
                INVALID_FLAG = true;
                return;
            } else {
                mColorMatrix[i] = Float.valueOf(mEts[i].getText().toString());
            }
        }
        INVALID_FLAG = false;
    }

    // 将矩阵值设置到图像
    public void setImageMatrix() {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        android.graphics.ColorMatrix colorMatrix = new android.graphics.ColorMatrix();
        colorMatrix.set(mColorMatrix);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        mImageView.setImageBitmap(bmp);
    }
}
