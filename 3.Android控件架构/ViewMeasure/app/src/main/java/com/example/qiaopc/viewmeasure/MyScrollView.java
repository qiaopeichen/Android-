package com.example.qiaopc.viewmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by qiaopc on 2017/12/17 0017.
 */

public class MyScrollView extends ViewGroup {

    private int mScreenHeight;
    private float mLastY;
    private final Scroller mScroller;
    private int mStart;
    private int mEnd;

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenHeight = getHeight();
        mScroller = new Scroller(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 设置ViewGroup的高度
        mScreenHeight = getScreenHeight();
        int childcount = getChildCount();
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = childcount * mScreenHeight;
        setLayoutParams(mlp);

        for (int i = 0; i < childcount; i++) {
            View view = getChildAt(i);
            if (view.getVisibility() != View.GONE) {
                view.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);
            }
        }
    }

    /**
     * 计算屏幕高度,这里划重点，仔细看看
     *
     * @return
     */
    private int getScreenHeight() {
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                // 记录触摸起点
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = (int) (mLastY - y);
                //View移动到上边沿
                if (getScrollY() < 0) {
                    dy = 0;
                }
                //view移动到下边沿
                if (getScrollY() > mScreenHeight * 2 && dy > 0) {
                    dy = 0;
                }
                Log.e("mess", mScreenHeight+"-----height="+getHeight()+"-----------view="+(getHeight()-mScreenHeight));
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                // 记录触摸终点
                mEnd = getScrollY();
                int dScrollY = mEnd - mStart;
                Log.e("mess", "---dscrollY="+dScrollY);
                if (dScrollY > 0) {// 上滑

                    if (dScrollY < mScreenHeight / 3) {// 回彈效果
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    } else {// 滑到下一个view
                        mScroller.startScroll(0, getScrollY(), 0, mScreenHeight - dScrollY);
                    }
                } else {// 下滑
                    if (-dScrollY < mScreenHeight / 3) {// 回彈
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, -mScreenHeight - dScrollY);
                    }
                }
                break;
        }
        //不要忘了，忘了这个有点坑了就
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}



