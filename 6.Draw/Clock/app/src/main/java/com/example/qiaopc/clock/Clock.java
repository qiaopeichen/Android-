package com.example.qiaopc.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.icu.util.Calendar;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qiaopc on 2018/1/12 0012.
 */

public class Clock extends View {

    private int mHeight, mWidth;
    private final int DEFAULT_POINT_BACK_LENGTH = 50;
    private final int DEFAULT_INTEGRAL_POINT_LENGTH = 30;
    private final int DEFAULT_UNINTEGRAL_POINT_LENGTH = 15;

    public Clock(Context context) {
        super(context);
    }

    public Clock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Clock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(final Canvas canvas) {
        // 获取宽高参数
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        // 画外圈
        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5);
        canvas.drawCircle(mWidth/2, mHeight/2, mWidth/2, paintCircle);
        // 画刻度
        Paint painDegree = new Paint();
        paintCircle.setStrokeWidth(3);
        for (int i = 0; i < 60; i++) {
            // 区分整点与非整点
            if (i % 5 == 0) {
                painDegree.setStrokeWidth(5);
                painDegree.setTextSize(DEFAULT_INTEGRAL_POINT_LENGTH);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + 60, painDegree);
            } else {
                painDegree.setStrokeWidth(3);
                painDegree.setTextSize(DEFAULT_UNINTEGRAL_POINT_LENGTH);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 30, painDegree);
            }
            // 通过旋转画布简化坐标运算
            canvas.rotate(6, mWidth / 2, mHeight / 2);
        }

        // 画圆心
        Paint paintPointer = new Paint();
        paintPointer.setStrokeWidth(30);
        canvas.drawPoint(mWidth / 2, mHeight / 2, paintPointer);
        canvas.save();

        // 平移画布
        canvas.translate(mWidth / 2, mHeight / 2);


        // 画数字
        int degressNumberSize = 30;
        Paint paintDegreeNumber = new Paint();
        paintDegreeNumber.setTextAlign(Paint.Align.CENTER);
        paintDegreeNumber.setTextSize(degressNumberSize);
        for(int i=0;i<12;i++){
            float[] temp = calculatePoint((i+1) * 30, mWidth / 2 - DEFAULT_INTEGRAL_POINT_LENGTH - degressNumberSize - 20);
            canvas.drawText((i+1)+"", temp[2], temp[3] + degressNumberSize/2, paintDegreeNumber);
        }





        int hourPointerLength = 200;
        int minutePointerLength = 300;
        int secondPointerLength = 400;
        //画指针
        Paint paintHour = new Paint();
        paintHour.setAntiAlias(true);
        paintHour.setStrokeWidth(15);
        Paint paintMinute = new Paint();
        paintMinute.setAntiAlias(true);
        paintMinute.setStrokeWidth(10);
        Paint paintSecond = new Paint();
        paintSecond.setAntiAlias(true);
        paintSecond.setStrokeWidth(5);
        Calendar now = Calendar.getInstance();
        // 一周360度，通过每小时乘以相应的度数比例，从而得到角度
        float[] hourPoints = calculatePoint(now.get(Calendar.HOUR_OF_DAY) * 30f % 360 + now.get(Calendar.MINUTE) * 6f / 360, hourPointerLength);
        canvas.drawLine(hourPoints[0], hourPoints[1], hourPoints[2], hourPoints[3], paintHour);
        float[] minutePoints = calculatePoint(now.get(Calendar.MINUTE) * 6f, minutePointerLength);
        canvas.drawLine(minutePoints[0], minutePoints[1], minutePoints[2], minutePoints[3], paintMinute);
        float[] secondPoints = calculatePoint(now.get(Calendar.SECOND) * 6f, secondPointerLength);
        canvas.drawLine(secondPoints[0], secondPoints[1], secondPoints[2], secondPoints[3], paintSecond);
        postInvalidateDelayed(1000);
    }

    /**
     * 根据角度和长度计算线段的起点和终点的坐标
     * @param angle
     * @param length
     * @return
     */
    private float[] calculatePoint(float angle, float length){
        float[] points = new float[4];
        if(angle <= 90f){
            // 因为时钟是逆时针旋转，所以我们要把x和y倒置，让它随着角度的增大而镜像角度变小绘制。
            points[0] = -(float) Math.sin(angle*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[1] = (float) Math.cos(angle*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[2] = (float) Math.sin(angle*Math.PI/180) * length;
            points[3] = -(float) Math.cos(angle*Math.PI/180) * length;
        }else if(angle <= 180f){
            points[0] = -(float) Math.cos((angle-90)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[1] = -(float) Math.sin((angle-90)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[2] = (float) Math.cos((angle-90)*Math.PI/180) * length;
            points[3] = (float) Math.sin((angle-90)*Math.PI/180) * length;
        }else if(angle <= 270f){
            points[0] = (float) Math.sin((angle-180)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[1] = -(float) Math.cos((angle-180)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[2] = -(float) Math.sin((angle-180)*Math.PI/180) * length;
            points[3] = (float) Math.cos((angle-180)*Math.PI/180) * length;
        }else if(angle <= 360f){
            points[0] = (float) Math.cos((angle-270)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[1] = (float) Math.sin((angle-270)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[2] = -(float) Math.cos((angle-270)*Math.PI/180) * length;
            points[3] = -(float) Math.sin((angle-270)*Math.PI/180) * length;
        }
        return points;
    }
}
