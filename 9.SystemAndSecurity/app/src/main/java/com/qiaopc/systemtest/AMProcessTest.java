package com.qiaopc.systemtest;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AMProcessTest extends AppCompatActivity {

    private ListView mListView;
    private ActivityManager mActivityManager;
    private List<AMProcessInfo> mAmProcessInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amprocess_test);
        mListView = findViewById(R.id.listView_am_process);
        // 得到ActivityManager
        mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        mListView.setAdapter(new AMProcessAdapter(this, getRunningProcessInfo()));
    }

    private List<AMProcessInfo> getRunningProcessInfo() {
        mAmProcessInfoList = new ArrayList<>();
        // 通过ActivityManager获取runningAppProcess
        List<ActivityManager.RunningAppProcessInfo> appProcessList =
                mActivityManager.getRunningAppProcesses();

        for (int i = 0; i < appProcessList.size(); i++) {
            // 通过RunningAppProcess 得到每个正在运行的AppInfo
            ActivityManager.RunningAppProcessInfo info = appProcessList.get(i);
            int pid = info.pid;
            int uid = info.uid;
            String processName = info.processName;
            int[] memoryPid = new int[] {pid};
            // 通过ProcessMemoryInfo，以pid来得到memoryInfo
            Debug.MemoryInfo[] memoryInfo = mActivityManager.getProcessMemoryInfo(memoryPid);
            int memorySize = memoryInfo[0].getTotalPss();
            // 存入AMProcessInfo的javaBean里
            AMProcessInfo processInfo = new AMProcessInfo();
            processInfo.setPid("" + pid);
            processInfo.setUid("" + uid);
            processInfo.setMemorySize("" + memorySize);
            processInfo.setProcessName(processName);
            mAmProcessInfoList.add(processInfo);
        }
        return mAmProcessInfoList;
    }
}
