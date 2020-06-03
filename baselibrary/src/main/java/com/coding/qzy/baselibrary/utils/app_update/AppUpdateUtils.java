package com.coding.qzy.baselibrary.utils.app_update;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.coding.qzy.baselibrary.R;


/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/10/17
 * desc   :
 * version: 1.0
 */


public class AppUpdateUtils {

    private final String TAG = "AppUpdateUtils";
    public static final int CHECK_BY_VERSION_NAME = 1001;
    public static final int CHECK_BY_VERSION_CODE = 1002;
    public static final int DOWNLOAD_BY_APP = 1003;
    public static final int DOWNLOAD_BY_BROWSER = 1004;

    private Activity activity;
    private int checkBy = CHECK_BY_VERSION_CODE;
    private int downloadBy = DOWNLOAD_BY_APP;
    private int serverVersionCode = 0;
    private String apkPath="";
    private String serverVersionName="";
    private boolean isForce = false; //是否强制更新
    private int localVersionCode = 0;
    private String localVersionName="";
    public static boolean needFitAndroidN = true; //提供给 整个工程不需要适配到7.0的项目 置为false
    public static boolean showNotification = true;
    private String updateInfo = "";
    private boolean loading = false;




    public AppUpdateUtils needFitAndroidN(boolean needFitAndroidN) {
        AppUpdateUtils.needFitAndroidN = needFitAndroidN;
        return this;
    }

    private AppUpdateUtils(Activity activity) {
        this.activity = activity;
        getAPPLocalVersion(activity);
    }

    public static AppUpdateUtils from(Activity activity){
        return new AppUpdateUtils(activity);
    }

    public AppUpdateUtils checkBy(int checkBy){
        this.checkBy = checkBy;
        return this;
    }

    public AppUpdateUtils apkPath(String apkPath){
        this.apkPath = apkPath;
        return this;
    }

    public AppUpdateUtils downloadBy(int downloadBy){
        this.downloadBy = downloadBy;
        return this;
    }

    public AppUpdateUtils showNotification(boolean showNotification){
        this.showNotification = showNotification;
        return this;
    }

    public AppUpdateUtils updateInfo(String updateInfo){
        this.updateInfo = updateInfo;
        return this;
    }



    public AppUpdateUtils serverVersionCode(int serverVersionCode){
        this.serverVersionCode = serverVersionCode;
        return this;
    }

    public AppUpdateUtils serverVersionName(String  serverVersionName){
        this.serverVersionName = serverVersionName;
        return this;
    }

    public AppUpdateUtils isForce(boolean  isForce){
        this.isForce = isForce;
        return this;
    }

    //获取apk的版本号 currentVersionCode
    private  void getAPPLocalVersion(Context ctx) {
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            localVersionName = info.versionName; // 版本名
            localVersionCode = info.versionCode; // 版本号
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update(){

        switch (checkBy){
            case CHECK_BY_VERSION_CODE:
                if (serverVersionCode >localVersionCode){
                    toUpdate();
                }else {
                    Log.i(TAG,"当前版本是最新版本"+serverVersionCode+"/"+serverVersionName);
                }
                break;

            case CHECK_BY_VERSION_NAME:
                if (!serverVersionName.equals(localVersionName)){
                    toUpdate();
                }else {
                    Log.i(TAG,"当前版本是最新版本"+serverVersionCode+"/"+serverVersionName);
                }
                break;
        }

    }

    private void toUpdate() {

        realUpdate();

    }

    private void realUpdate() {
        activity.startService(new Intent(activity, UpdateAppService.class));
        ConfirmDialog dialog = new ConfirmDialog(activity, new Callback() {
            @Override
            public void callback(int position) {
                switch (position){
                    case 0:  //cancle
                        if (isForce)System.exit(0);
                        break;
                    case 1:  //sure
                        if (downloadBy == DOWNLOAD_BY_APP) {
                            if (isWifiConnected(activity)){
                                Toast.makeText(activity,activity.getString(R.string.apk_loding),Toast.LENGTH_SHORT).show();
                                if(!loading){
                                    loading = true;
                                    AppDownloadUtils.download(activity, apkPath, serverVersionName);
                                }
                            }else {
                                new ConfirmDialog(activity, new Callback() {
                                    @Override
                                    public void callback(int position) {
                                        if (position==1){
                                            Toast.makeText(activity,activity.getString(R.string.apk_loding),Toast.LENGTH_SHORT).show();
                                            if(!loading){
                                                loading = true;
                                                AppDownloadUtils.download(activity, apkPath, serverVersionName);
                                            }
                                        }else {
                                            if (isForce)activity.finish();
                                        }
                                    }
                                },isForce).setContent("目前手机不是WiFi状态\n确认是否继续下载更新？").show();
                            }

                        }/*else if (downloadBy == DOWNLOAD_BY_BROWSER){
                            Toast.makeText(activity,activity.getString(R.string.apk_loding),Toast.LENGTH_SHORT).show();
                            if(!loading){
                                loading = true;
                                AppDownloadUtils.downloadForWebView(activity,apkPath);
                            }
                        }*/
                        break;
                }
            }
        },isForce);

        String content = "发现新版本:"/*+serverVersionName*/+"\n是否下载更新?";
        if (!TextUtils.isEmpty(updateInfo)){
            content = "发现新版本"/*+serverVersionName*/+"是否下载更新?\n"+updateInfo;
        }
        dialog .setContent(content);
        dialog.setCancelable(false);
        dialog.show();
    }


    /**
     * 检测wifi是否连接
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            @SuppressLint("MissingPermission") NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }
}