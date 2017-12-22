package com.example.qiaopc.mylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(i+"");
        }
        final ListView lv_show = findViewById(R.id.lv_show);
        lv_show.setAdapter(new ViewHolderAdapter(MainActivity.this, data));
        // 默认显示在第 30 个 item
//        lv_show.setSelection(30);
        lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                lv_show.smoothScrollToPosition(30);
//                lv_show.smoothScrollByOffset(-4000);
                lv_show.smoothScrollBy(-300,50000);
            }
        });

    }
}
