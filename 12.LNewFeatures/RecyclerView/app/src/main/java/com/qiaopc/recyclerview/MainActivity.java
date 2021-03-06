package com.qiaopc.recyclerview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRcList;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Spinner mSpinner;

    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRcList = findViewById(R.id.rc_list);
        mLayoutManager = new LinearLayoutManager(this);
        mRcList.setLayoutManager(mLayoutManager);
        // 当item的改变不会影响recyclerView宽高的时候，将其设置为true避免重新计算大小
        mRcList.setHasFixedSize(true);
        // 设置显示动画
        mRcList.setItemAnimator(new DefaultItemAnimator());

        mSpinner = findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mRcList.setLayoutManager(
                            // 设置为线性布局
                            new LinearLayoutManager(MainActivity.this));
                } else if (position == 1) {
                    mRcList.setLayoutManager(
                            // 设置为表格布局
                            new GridLayoutManager(MainActivity.this, 3));
                } else if (position == 2) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // 增加测试数据
        mData.add("Recycler");
        mData.add("Recycler");
        mData.add("Recycler");
        mAdapter = new RecyclerAdapter(mData);
        mRcList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, int position) {
                // 设置点击动画
                view.animate()
                    .translationZ(15F).setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            view.animate()
                                .translationZ(1f)
                                .setDuration(500).start();
                        }
                    }).start();
            }
        });
    }

    public void addRecycler(View view) {
        mData.add("Recycler");
        int position = mData.size();
        if (position > 0) {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void delRecycler(View view) {
        int position = mData.size();
        if (position > 0) {
            mData.remove(position - 1);
            mAdapter.notifyDataSetChanged();
        }
    }
}
