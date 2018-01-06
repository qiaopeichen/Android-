package com.example.qiaopc.mylistview;

import android.content.Context;
import android.media.Image;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by qiaopc on 2018/1/6 0006.
 */

public class FocusListViewAdapter extends BaseAdapter {

    private List<String> mData;
    private Context mContext;
    private int mCurrentItem = -1;

    public FocusListViewAdapter(Context context, List<String> mData) {
        this.mContext = context;
        this.mData = mData;
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
        LinearLayout layout = new LinearLayout(mContext);
        layout.setOrientation(LinearLayout.VERTICAL);
        if (mCurrentItem == position) {
            layout.addView(addFocusView(position));
        } else {
            layout.addView(addNormalView(position));
        }
        return layout;
    }

    public void setmCurrentItem(int currentItem) {
        this.mCurrentItem = currentItem;
    }

    private View addFocusView(int position) {
        ImageView iv = new ImageView(mContext);
        iv.setImageResource(R.drawable.in_icon);
        return iv;
    }

    private View addNormalView(int position) {
        LinearLayout layout = new LinearLayout(mContext);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        ImageView iv = new ImageView(mContext);
        iv.setImageResource(R.drawable.in_icon);
        layout.addView(iv, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView tv = new TextView(mContext);
        tv.setText(mData.get(position));
        layout.addView(tv, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.setGravity(Gravity.CENTER);
        return layout;
    }
}
