package com.quzy.coding.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.coding.qzy.baselibrary.widget.external_resource.SkinManager;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseResourceActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
 * time   : 2021/03/15
 * desc   : 热修复原理
 * version: 1.0
 */


public class HotFixDemoActivity extends BaseResourceActivity {


    @BindView(R.id.hotFix)
    Button hotFix;
    @BindView(R.id.removeHotfixBt)
    Button removeHotfixBt;
    @BindView(R.id.killSelfBt)
    Button killSelfBt;
    @BindView(R.id.startActivity)
    Button startActivity;

    File apk;

    @Override
    protected void onViewCreated() {
        apk = new File(getCacheDir() + "/hotfix.dex");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hotfix_demo;
    }

    @OnClick({R.id.hotFix,R.id.removeHotfixBt,R.id.startActivity,R.id.killSelfBt})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.hotFix:
                download(view);
                break;
            case R.id.removeHotfixBt:
                if (apk.exists()) {
                    apk.delete();
                }
                break;
            case R.id.startActivity:
                startActivity(new Intent(HotFixDemoActivity.this,HotFixDemo2Activity.class));
                break;
            case R.id.killSelfBt:
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
        }
    }


    private void download(final View v) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://xiaohe-online.oss-cn-beijing.aliyuncs.com/Emulation/audios/homework/hotfix.dex")
                .get()
                .build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        v.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HotFixDemoActivity.this, "出错啦", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(HotFixDemoActivity.this, "成功啦", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }


    /**
     * 将插件先移动到私有路径下面
     * @param context
     */
//    public static void copyToPrivateFile(Context context){
//        //将这个修复包先移动到一个安全的位置-私有路径 /data/datd/com.maniu.mn_2020_12_15/
//        File odex = context.getDir("plugin", Context.MODE_PRIVATE);
//        //定义补丁的名字
//        String name = "plugin.apk";
//        //获取到补丁复制过去的完整File对象 /data/datd/com.maniu.mn_2020_12_15/odex/out.dex
//        File file = new File(odex.getAbsoluteFile(),name);
//        //复制过去的时候  要先判断一下  目的地是否已经存在这个名字的文件
//        if(file.exists()){
//            // 删除重名的
//            file.delete();
//        }
//        String filePath = file.getAbsolutePath();
//        //创建文件输入输出流
//        FileInputStream is =null;
//        FileOutputStream os = null;
//        try {
//            //起点
//            is = new FileInputStream(new File(Environment.getExternalStorageDirectory(), name));
//            //终点
//            os = new FileOutputStream(filePath);
//            int len = 0;
//            byte[] buffer = new byte[1024];
//            while ((len = is.read(buffer)) != -1) {
//                os.write(buffer, 0, len);
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }  finally {
//            try {
//                os.close();
//                is.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }


}

