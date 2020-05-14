package com.quzy.coding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.coding.qzy.baselibrary.utils.permission.PermissionChecker;
import com.coding.qzy.baselibrary.utils.permission.PermissionRequester;
import com.coding.qzy.baselibrary.utils.permission.annotation.PermissionDenied;
import com.coding.qzy.baselibrary.utils.permission.annotation.PermissionGranted;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.ui.activity.BackGroundActivity;
import com.quzy.coding.ui.activity.GuideLayerActivity;
import com.quzy.coding.ui.activity.LogUtilActivity;
import com.quzy.coding.ui.activity.QuickPositionActivity;
import com.quzy.coding.ui.activity.RecyclerViewSampleActivity;
import com.quzy.coding.ui.activity.SplashActivity;
import com.quzy.coding.ui.activity.TextSampleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.swipe_target)
    ListView swipe_target;
    ArrayAdapter mainAdapter;

    public static List<String> list = new ArrayList<String>();

    @Override
    protected void onViewCreated() {
        list.add("解决android应用被强杀或应用被回收导致的空指针问题");
        initData();
        requestPermissions();
        swipe_target.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        startActivity(new Intent(getActivity(), BackGroundActivity.class));
                        //startActivity(new Intent(getActivity(), CallOnWebViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), TextSampleActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), GuideLayerActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), QuickPositionActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), RecyclerViewSampleActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), LogUtilActivity.class));
                        break;
                }
            }
        });
    }

    private void requestPermissions(){
        PermissionRequester.build().attach(this)
                .permissions(PermissionChecker.Permissions.MAIN_PERMISSIONS)
                .requestCode(PermissionChecker.PermissionRequestCode.MAIN_PERMISSIONS)
                .request();
    }

    @PermissionGranted(requestCode = PermissionChecker.PermissionRequestCode.MAIN_PERMISSIONS)
    public void onWritePermissionGranted() {
    }


    @PermissionDenied(requestCode = PermissionChecker.PermissionRequestCode.MAIN_PERMISSIONS)
    public void onWritePermissionDenied(String info) {
    }

    @Override
    protected void protectApp() {
        Toast.makeText(getApplicationContext(),"应用被回收重启走流程",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    public void initData(){
        List<String> data = new ArrayList<String>();
//        data.add("webView中调用相机");
        data.add("背景布局、TextView、Button等");
        data.add("text文本");
        data.add("半透明引导层");
        data.add("快速查询索引");
        data.add("RecyclerView");
        data.add("Log日志");
        mainAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
        swipe_target.setAdapter(mainAdapter);
    }
}
