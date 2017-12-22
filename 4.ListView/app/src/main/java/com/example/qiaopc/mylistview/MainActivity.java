package com.example.qiaopc.mylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mData;
    private ListView mListView;
    private ViewHolderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final List<String> data = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            data.add(i+"");
//        }
//        final ListView lv_show = findViewById(R.id.lv_show);
//        final ViewHolderAdapter adapter = new ViewHolderAdapter(MainActivity.this, data);
//        lv_show.setAdapter(adapter);
        // 默认显示在第 30 个 item
//        lv_show.setSelection(30);
//        lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                lv_show.smoothScrollToPosition(30);
////                lv_show.smoothScrollByOffset(-4000);
////                lv_show.smoothScrollBy(-300,50000);
//            }
//        });
//    }
        mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mData.add("" + i);
        }
        mAdapter = new ViewHolderAdapter(MainActivity.this, mData);
        mListView = findViewById(R.id.lv_show);
        mListView.setAdapter(mAdapter);
    }

    public void btnAdd(View v) {
        mData.add("new " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
        mAdapter.notifyDataSetChanged();
        mListView.setSelection(mData.size() - 1);
    }
}
