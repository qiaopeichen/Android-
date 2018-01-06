package com.example.qiaopc.mylistview;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

/**
 * Created by qiaopc on 2018/1/6 0006.
 */

public class FlexibleListViewTest extends Activity {
    private FlexibleListView mFlexibleListView;
    private String[] data = new String[30];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flexible);
        for (int i = 0; i < 30; i++) {
            data[i] = "" + i;
        }

        mFlexibleListView = findViewById(R.id.flexible_listview);
        mFlexibleListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                data));
    }
}
