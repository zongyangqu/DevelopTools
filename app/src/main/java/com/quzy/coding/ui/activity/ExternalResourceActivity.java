package com.quzy.coding.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.coding.qzy.baselibrary.utils.log.LogTools;
import com.coding.qzy.baselibrary.widget.SwitchButton;
import com.coding.qzy.baselibrary.widget.external_resource.SkinManager;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.base.BaseResourceActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/12/08
 * desc   :
 * version: 1.0
 */


public class ExternalResourceActivity extends BaseResourceActivity {


    @BindView(R.id.downLoadApk)
    TextView downLoadApk;
    @BindView(R.id.next)
    TextView next;
    @Override
    protected void onViewCreated() {
        apk = new File(getCacheDir() + "/resource.apk");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_external_resource;
    }

    @OnClick({R.id.downLoadApk,R.id.next})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.downLoadApk:
                download(view);
                break;

            case R.id.next:
                startActivity(new Intent(ExternalResourceActivity.this,ExternalResource2Activity.class));
                break;
        }
    }


    File apk;
    private void download(final View v) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://xiaohe-online.oss-cn-beijing.aliyuncs.com/Emulation/resource/3.9.6/resource.apk")
                .get()
                .build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        v.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ExternalResourceActivity.this, "出错啦", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            FileOutputStream fos = new FileOutputStream(apk);
                            fos.write(response.body().bytes());
                            fos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        v.post(new Runnable() {
                            @Override
                            public void run() {
                                SkinManager.getInstance().loadResourceApk(apk.getPath());
                                Toast.makeText(ExternalResourceActivity.this, "成功啦", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }


}

