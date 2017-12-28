package com.example.qiaopc.myvibrator;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Vibrator mVibrator;
    private Vibrator mVibrator2;
    private static final long[] sVibratePattern = new long[] {500, 500};
    private static final long[] sVibratePattern2 = new long[] {1000, 1000};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVibrator = (Vibrator) getSystemService(MainActivity.this.VIBRATOR_SERVICE);
        mVibrator2 = (Vibrator) getSystemService(MainActivity.this.VIBRATOR_SERVICE);
    }

    public void btnStart(View v){
        mVibrator.vibrate(sVibratePattern, 0);
        mVibrator2.vibrate(sVibratePattern2, 0);
    }

    public void btnStop(View v){
        mVibrator.cancel();
    }


    private void startVibrator(Alarm alarm) {
        final boolean isVibratorOpen = alarm.vibrate;
        int callState = mTelephonyManager.getCallState();
        Log.i("isVibratorOpen:" + isVibratorOpen + ",callState:" + callState);
        if (isVibratorOpen) {
            switch (callState) {
                case TelephonyManager.CALL_STATE_IDLE:
//                    mVibrator.vibrate(sVibratePattern, 0);
                    new Thread(){
                        @Override
                        public void run() {
                            mVibrator.vibrate(500s);
                        }
                    }.start();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    // Gionee <qiaopc><2017-11-07> add for 254906 begin
//                    String effectName = "WEAK_TAP";
//                    amigoVibrator(mVibrator, effectName, 3);
                    // Gionee <qiaopc><2017-11-07> add for 254906 end
                    break;
                default:
                    mVibrator.cancel();
                    break;
            }
        }
    }
}
