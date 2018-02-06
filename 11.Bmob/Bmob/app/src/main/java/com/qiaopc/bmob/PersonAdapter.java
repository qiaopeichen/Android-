package com.qiaopc.bmob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaopc on 2018/2/6 0006.
 */

public class PersonAdapter extends BaseAdapter {

    Context mContext = null;
    private List<PersonDomain> mPersonDomainList = new ArrayList<>();
    private final LayoutInflater mLayoutInflater;

    public PersonAdapter(Context context, List<PersonDomain> personDomainList) {
        mPersonDomainList = personDomainList;
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mPersonDomainList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPersonDomainList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.person_item, null);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_address = convertView.findViewById(R.id.tv_address);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(mPersonDomainList.get(position).getName());
        viewHolder.tv_address.setText(mPersonDomainList.get(position).getAddress());
        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
        TextView tv_address;
    }
}
