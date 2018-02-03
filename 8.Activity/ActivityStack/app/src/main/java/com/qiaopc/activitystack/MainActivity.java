package com.qiaopc.activitystack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mShowClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowClass = findViewById(R.id.show_class);
        mShowClass.setText(this.toString());
    }

    public void btnStandard(View view) {
        startActivity(new Intent(this, MainActivity.class));
        mShowClass.setText(this.toString());
    }

    public void btnSingleTop(View view) {
        startActivity(new Intent(this, ActivitySingleTop.class));
    }

    public void btnSingleTask(View view) {
        startActivity(new Intent(this, ActivitySingleTask.class));
        mShowClass.setText(this.toString());
    }
}
