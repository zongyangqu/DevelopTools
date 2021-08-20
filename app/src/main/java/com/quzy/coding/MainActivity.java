package com.quzy.coding;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

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
import com.quzy.coding.ui.activity.SplashActivity;
import com.quzy.coding.util.op.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity {

    @BindView(R.id.swipe_target)
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
                Actions.doAction(getActivity(),i);
            }
        });
    }

    private void requestPermissions(){
        PermissionRequester.build().attach(this)
                .permissions(PermissionChecker.Permissions.MAIN_PERMISSIONS_MEDIA)
                .requestCode(PermissionChecker.PermissionRequestCode.MAIN_PERMISSIONS_MEDIA)
                .request();
    }

    @PermissionGranted(requestCode = PermissionChecker.PermissionRequestCode.MAIN_PERMISSIONS_MEDIA)
    public void onWritePermissionGranted() {
    }


    @PermissionDenied(requestCode = PermissionChecker.PermissionRequestCode.MAIN_PERMISSIONS_MEDIA)
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
//        List<String> data = new ArrayList<String>();
//        data = Arrays.asList(getResources().getStringArray(R.array.item_category_names));
        mainAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,Arrays.asList(getResources().getStringArray(R.array.item_category_names)));
        swipe_target.setAdapter(mainAdapter);
    }
}
