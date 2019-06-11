package com.coding.qzy.baselibrary.utils.appreset;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/11/02
 * desc   : App状态管理类  app在后台被被内存回收时使用 重启APP
 * version: 1.0
 */
public class AppStatusManager {

    public int appStatus= AppStatusConstant.STATUS_FORCE_KILLED; //APP状态 初始值为没启动 不在前台状态
    public static AppStatusManager appStatusManager;

    private AppStatusManager() {
    }

    public static AppStatusManager getInstance() {
        if (appStatusManager == null) {
            appStatusManager = new AppStatusManager();
        }
        return appStatusManager;
    }

    public int getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(int appStatus) {
        this.appStatus = appStatus;
    }
}
