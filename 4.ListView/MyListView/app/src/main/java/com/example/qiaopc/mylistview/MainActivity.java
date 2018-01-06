package com.example.qiaopc.mylistview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnFlexible(View v) {
        startActivity(new Intent(this, FlexibleListViewTest.class));
    }

    public void btnScrollHideListView(View v) {
        startActivity(new Intent(this, ScrollHideListView.class));
    }

    public void btnChatItem(View v) {
        startActivity(new Intent(this, ChatItemListViewTest.class));
    }
}
