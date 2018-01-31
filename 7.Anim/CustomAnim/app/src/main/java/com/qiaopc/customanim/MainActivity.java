package com.qiaopc.customanim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnAnim(View view) {
        CustomAnim customAnim = new CustomAnim();
        customAnim.setRotateY(360);
        view.startAnimation(customAnim);
    }

    public void imgClose(View view) {
        CustomTV customTV = new CustomTV();
        view.startAnimation(customTV);
    }
}
