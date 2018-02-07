package com.qiaopc.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by qiaopc on 2018/2/7 0007.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    private List<String> mData;
    public OnItemClickListener itemClickListener;

    public RecyclerAdapter(List<String> data) {
        mData = data;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface  OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setOnClickListener(this);
        }

        // 通过接口回调来实现RecyclerView的点击事件
        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 将布局转化为View并传递给RecyclerView封装好的ViewHolder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 建立起viewHolder中视图与数据的关联
        ((ViewHolder)holder).textView.setText(mData.get(position) + position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
