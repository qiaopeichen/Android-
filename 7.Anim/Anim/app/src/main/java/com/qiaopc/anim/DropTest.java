package com.qiaopc.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DropTest extends AppCompatActivity {

    private LinearLayout mHiddenView;
    private float mDensity;
    private int mHiddenViewMeasureHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_test);
        mHiddenView = findViewById(R.id.hidden_view);
        // 获取像素密度
        mDensity = getResources().getDisplayMetrics().density;
        // 获取布局的高度
        mHiddenViewMeasureHeight = (int) (mDensity * 40 + 0.5);
    }

    public void llClick(View view) {
        if (mHiddenView.getVisibility() == View.GONE) {
            // 打开动画
            animateOpen(mHiddenView);
        } else {
            // 关闭动画
            animateClose(mHiddenView);
        }
    }

    private void animateOpen(View view) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(view, 0, mHiddenViewMeasureHeight);
        animator.start();
    }

    private void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    private ValueAnimator createDropAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }
}
