package com.example.qiaopc.mylistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by qiaopc on 2017/12/22 0022.
 */

public class ViewHolderAdapter extends BaseAdapter {
    public int i = 0;
    private List<String> mData;
    private LayoutInflater mInflater;

    public ViewHolderAdapter(Context context, List<String> data) {
        mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            Log.d(TAG, "getView: " + convertView + i++);
            holder = new ViewHolder();
            // 通过 LayoutInflater 实例化布局
            convertView = mInflater.inflate(R.layout.viewholder_item, null);
            holder.img = convertView.findViewById(R.id.iv_show);
            holder.title = convertView.findViewById(R.id.tv_show);
            convertView.setTag(holder);
        } else {
            // 通过 tag 找到缓存的布局
            holder = (ViewHolder) convertView.getTag();
        }
        // 设置布局中控件要显示的视图
        holder.title.setText(mData.get(position));
        return convertView;
    }

    private final class ViewHolder {
        private ImageView img;
        private TextView title;
    }
}
