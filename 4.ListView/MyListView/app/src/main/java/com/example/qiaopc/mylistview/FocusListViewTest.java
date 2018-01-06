package com.example.qiaopc.mylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FocusListViewTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_list_view_test);
        ListView listView = findViewById(R.id.focus_listView);
        List<String> data = new ArrayList<>();
        data.add("Are you ok? 1");
        data.add("Are you ok? 2");
        data.add("Are you ok? 3");
        data.add("Are you ok? 4");
        data.add("Are you ok? 5");
        final FocusListViewAdapter adapter = new FocusListViewAdapter(this, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setmCurrentItem(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
