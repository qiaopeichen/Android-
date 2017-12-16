package com.example.qiaopc.viewmeasure;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by qiaopc on 2017/12/16 0016.
 */

public class TopBar extends RelativeLayout {

    private final int mLeftTextColor;
    private final Drawable mLeftBackground;
    private final String mLeftText;
    private final int mRightTextColor;
    private final Drawable mRightBackground;
    private final String mRightText;
    private final float mTitleTextSize;
    private final int mTitleTextColor;
    private final String mTitle;
    private final Button mLeftButton;
    private final Button mRightButton;
    private final TextView mTitleView;
    private final LayoutParams mLeftParams;
    private final LayoutParams mRightParams;
    private final LayoutParams mTitleParams;

    private ITopbarClickListener mListner;


    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 通过这个方法，将你在 attrs.xml 中定义的 declare-styleable 的所有属性的值存储到 TypedArray 中
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        // 从 TypedArray 中取出对应的值来为要设置的属性赋值
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);
        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 10);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        mTitle = ta.getString(R.styleable.TopBar_title);
        // 获取完 TypedArray 的值后，一般要调用 recycle 方法来避免重新创建的时候的错误
        ta.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        // 为组件元素设定相应的布局元素
        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        // 添加到 ViewGroup
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitleParams);

        // 按钮的点击事件，不需要具体的实现，只需调用接口的方法，回调的时候，会有具体的实现
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.rightClick();
            }
        });

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListner.leftClick();
            }
        });
    }

    /**
     * 设置按钮的显示与否，通过id区分按钮，flag区分是否显示
     * @param id id
     * @param flag 是否显示
     */
    public void setButtonVisable(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftButton.setVisibility(VISIBLE);
            } else {
                mRightButton.setVisibility(VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftButton.setVisibility(GONE);
            } else {
                mRightButton.setVisibility(GONE);
            }
        }
    }

    // 暴露一个方法给调用者来注册接口回调
    // 通过接口来获得回调者对接口方法的实现
    public void setOnTopbarClickListener(ITopbarClickListener mListner) {
        this.mListner = mListner;
    }

    // 接口对象，实现回调机制，在回调方法中通过映射的接口对象调用接口中的方法而不用去考虑如何实现，具体的实现由调用者去创建
    public interface ITopbarClickListener {
        // 左按钮点击事件
        void leftClick();
        // 右按钮点击事件
        void rightClick();
    }
}
