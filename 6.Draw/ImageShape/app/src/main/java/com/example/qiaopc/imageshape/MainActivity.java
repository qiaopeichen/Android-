package com.example.qiaopc.imageshape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnReflect(View view) {
        startActivity(new Intent(MainActivity.this, ReflectViewTest.class));
    }

    public void btnImageMatrix(View view) {
        startActivity(new Intent(MainActivity.this, ImageMatrixTest.class));
    }

    public void btnFlag(View view) {
        startActivity(new Intent(MainActivity.this, FlagBitmapMeshTest.class));
    }

    public void btnPorterDuffXfermode(View view) {
        startActivity(new Intent(MainActivity.this, XfermodeViewTest.class));
    }

    public void btnRoundRect(View view) {
        startActivity(new Intent(MainActivity.this, RoundRectTest.class));
    }
}
