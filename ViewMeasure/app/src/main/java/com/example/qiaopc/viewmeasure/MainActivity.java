package com.example.qiaopc.viewmeasure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TopBar mTopbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

//        mTopbar = findViewById(R.id.topBar);
//        mTopbar.setOnTopbarClickListener(new TopBar.ITopbarClickListener() {
//            @Override
//            public void leftClick() {
//                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void rightClick() {
//                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // 控制 topbar 上组件的状态
//        mTopbar.setButtonVisable(0, false);

        CircleView circle = findViewById(R.id.circle);
        circle.setSweepValue(358);
        circle.setShowText("mainText");
    }
}
