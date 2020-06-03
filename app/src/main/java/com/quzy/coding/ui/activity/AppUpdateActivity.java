package com.quzy.coding.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.coding.qzy.baselibrary.utils.app_update.AppUpdateUtils;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.ui.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/07/23
 * desc   : RecyclerView Demo
 * version: 1.0
 */

public class AppUpdateActivity extends BaseActivity {

    @BindView(R.id.btn_update)
    Button btn_update;


    String apkPath = "http://cdn.xiaohe.com/download/client/android/baonahao/ixiaostar.apk";
    boolean isForce = false;//是否强制更新
    @Override
    protected void onViewCreated() {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String versionName = "测试版本V3.4.0";
                AppUpdateUtils.from(getActivity())
                        .serverVersionCode(999)//最新版本号
                        .serverVersionName(versionName)
                        .apkPath(apkPath)
                        .updateInfo("修复已知问题\n优化用户体验")
                        .isForce(isForce)
                        .update();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_app_update;
    }


}

