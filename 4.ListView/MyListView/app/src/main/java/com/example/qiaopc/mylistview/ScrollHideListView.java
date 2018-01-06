package com.example.qiaopc.mylistview;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.OptionalDataException;

public class ScrollHideListView extends AppCompatActivity {

    private int mTouchSlop;
    private Toolbar mToolbar;
    private ListView mListView;
    private String[] mStr = new String[20];
    private float mFirstY;
    private float mCurrentY;
    private int direction;
    private ObjectAnimator mAnimator;
    private boolean mShow = true;
    private final int TOOLBAR_HIDE = 1;
    private final int TOOLBAR_SHOW = 0;

    private View.OnTouchListener myTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mFirstY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCurrentY = event.getY();
                    if (mCurrentY - mFirstY > mTouchSlop) {
                        direction = 0; // down
                    } else if (mFirstY - mCurrentY > mTouchSlop) {
                        direction = 1; // up
                    }
                    if (direction == 1) {
                       if (mShow) {
                           toolbarAnim(TOOLBAR_HIDE); // hide
                           mShow = !mShow;
                       }
                    } else if (direction == 0) {
                        if (!mShow) {
                            toolbarAnim(TOOLBAR_SHOW); // show
                            mShow = !mShow;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_hide);
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        mToolbar = findViewById(R.id.toolbar);
        mListView = findViewById(R.id.listview);
        for (int i = 0; i < mStr.length; i++) {
            mStr[i] = "Item " + i;
        }
        View header = new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT,
                (int) getResources().getDimension(
                        R.dimen.abc_action_bar_default_height_material)));
        mListView.addHeaderView(header);
        mListView.setAdapter(new ArrayAdapter<String>(
                ScrollHideListView.this,
                android.R.layout.simple_list_item_1,
                mStr));
        mListView.setOnTouchListener(myTouchListener);
    }

    private void toolbarAnim(int flag) {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
        if (flag == TOOLBAR_SHOW) {
            mAnimator = ObjectAnimator.ofFloat(mToolbar,
                    "translationY", mToolbar.getTranslationY(), 0); // values 为动画精准坐标
        } else {
            mAnimator = ObjectAnimator.ofFloat(mToolbar, "translationY",
                    mToolbar.getTranslationY(), -mToolbar.getHeight());
        }
        mAnimator.start();
    }
}
