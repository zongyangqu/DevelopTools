package com.quzy.coding.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;

import butterknife.BindView;
import constant.UiType;
import listener.UpdateDownloadListener;
import model.UiConfig;
import model.UpdateConfig;
import update.UpdateAppUtils;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/07/23
 * desc   : https://github.com/teprinciple/UpdateAppUtils
 * version: 1.0
 */

public class AppUpdateActivity extends BaseActivity {

    @BindView(R.id.btn_update)
    Button btn_update;


    String apkPath = "http://cdn.xiaohe.com/download/client/android/baonahao/ixiaostar.apk";
    String updateContent = "1、重构版\n2、支持自定义UI\n3、增加md5校验\n4、更多功能等你探索";
    boolean isForce = false;//是否强制更新
    @Override
    protected void onViewCreated() {
        UpdateAppUtils.init(this);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateConfig updateConfig = new UpdateConfig();
                updateConfig.setCheckWifi(true);
                updateConfig.setAlwaysShowDownLoadDialog(true);
                updateConfig.setForce(isForce);
                updateConfig.setNotifyImgRes(R.mipmap.ic_launcher);
                UiConfig uiConfig = new UiConfig();
                uiConfig.setUiType(UiType.PLENTIFUL);

                UpdateAppUtils
                        .getInstance()
                        .apkUrl(apkPath)
                        .updateTitle("发现新版本V2.0.0")
                        .updateContent(updateContent)
                        .uiConfig(uiConfig)
                        .updateConfig(updateConfig)
                        .setUpdateDownloadListener(new UpdateDownloadListener() {
                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onDownload(int progress) {

                            }

                            @Override
                            public void onFinish() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        })
                        .update();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_app_update;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }
}

