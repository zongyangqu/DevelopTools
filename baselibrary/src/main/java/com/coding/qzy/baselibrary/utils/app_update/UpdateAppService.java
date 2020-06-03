package com.coding.qzy.baselibrary.utils.app_update;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/09/27
 * desc   :
 * version: 1.0
 */

public class UpdateAppService extends Service {

    private BroadcastReceiver receiver = new AppUpdateReceiver();
    private LocalBroadcastManager localBroadcastManager;


    @Override
    public void onCreate() {
        super.onCreate();

        // 动态注册receiver 适配8.0 receiver 静态注册没收不到广播
//        IntentFilter intentFilter = new IntentFilter("xiaohe.update");
//        registerReceiver(receiver, intentFilter);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("xiaohe.update");
        localBroadcastManager.registerReceiver(receiver,intentFilter);


    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(receiver); // 注销广播
        localBroadcastManager.unregisterReceiver(receiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
