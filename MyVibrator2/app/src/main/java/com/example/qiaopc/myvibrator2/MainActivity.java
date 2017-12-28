package com.example.qiaopc.myvibrator2;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Vibrator mVibrator;
    private static final long[] sVibratePattern = new long[] {500, 500};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVibrator = (Vibrator) getSystemService(MainActivity.this.VIBRATOR_SERVICE);
    }

    public void btnStart(View v){
        mVibrator.vibrate(sVibratePattern, 0);
    }

    public void btnStop(View v){
        mVibrator.cancel();
    }
}
