package com.example.qiaopc.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;

    private static final String TAG = "JOJO";
    private Random mRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRandom = new Random();
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
//        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'Z'; i++) {
            mDatas.add("" + (char) i);
        }
    }
/////////////////////////////////

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateViewHolder: ");
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_num_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder: ");
            holder.tv.setText(mDatas.get(position));
            ViewGroup.LayoutParams layoutParams = holder.tv.getLayoutParams();
            layoutParams.height = mRandom.nextInt(200) + 50;
            holder.tv.setLayoutParams(layoutParams);
        }

        @Override
        public int getItemCount() {
            Log.d(TAG, "getItemCount: ");
            return mDatas.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = view.findViewById(R.id.id_num);
            }
        }
    }

    public void addItem(View v) {
        String temp = mDatas.get(mRandom.nextInt(25));
        mDatas.add(0, temp);
        mAdapter.notifyDataSetChanged();
    }
}
