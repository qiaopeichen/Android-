package com.qiaopc.systemtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by qiaopc on 2018/2/5 0005.
 */

public class AMProcessAdapter extends BaseAdapter {

    private List<AMProcessInfo> mListData = null;
    private LayoutInflater mInflater;

    public AMProcessAdapter(Context context, List<AMProcessInfo> listData) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mListData = listData;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.am_process_item, null);
            holder = new ViewHolder();
            holder.tvPID = convertView.findViewById(R.id.tv_pid);
            holder.tvUID = convertView.findViewById(R.id.tv_uid);
            holder.tvMemorySize = convertView.findViewById(R.id.tv_size);
            holder.tvProcessName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AMProcessInfo processInfo = (AMProcessInfo) getItem(position);
        holder.tvPID.setText("pid:" + processInfo.getPid());
        holder.tvUID.setText("uid:" + processInfo.getUid());
        holder.tvMemorySize.setText(processInfo.getMemorySize() + "KB");
        holder.tvProcessName.setText(processInfo.getProcessName());
        return convertView;
    }

    class ViewHolder {
        TextView tvPID;
        TextView tvUID;
        TextView tvMemorySize;
        TextView tvProcessName;
    }
}
