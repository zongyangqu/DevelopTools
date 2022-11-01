package com.quzy.coding;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.coding.qzy.baselibrary.utils.permission.PermissionChecker;
import com.coding.qzy.baselibrary.utils.permission.PermissionRequester;
import com.coding.qzy.baselibrary.utils.permission.annotation.PermissionDenied;
import com.coding.qzy.baselibrary.utils.permission.annotation.PermissionGranted;
import com.coding.qzy.baselibrary.widget.recorderlib.utils.FileUtils;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.bean.event.DialogEvent;
import com.quzy.coding.ui.activity.HotFixDemoActivity;
import com.quzy.coding.ui.activity.SplashActivity;
import com.quzy.coding.ui.manager.CouponNewCustomerDialogManager;
import com.quzy.coding.util.Constants;
import com.quzy.coding.util.TypefaceLoadUtil;
import com.quzy.coding.util.op.Actions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import kotlinx.coroutines.GlobalScope;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends BaseActivity {

    @BindView(R.id.swipe_target)
    ListView swipe_target;
    ArrayAdapter mainAdapter;
    File apk;
    //https://xiaohe-online.oss-cn-beijing.aliyuncs.com/Emulation/audios/homework/fzgz.zip
    private final String typefaceUrl = Constants.DOWNLOAD_TYPEFACE_ZIP_NAME;

    public static List<String> list = new ArrayList<String>();

    @Override
    protected void onViewCreated() {
        EventBus.getDefault().register(this);
        list.add("解决android应用被强杀或应用被回收导致的空指针问题");
        //CouponNewCustomerDialogManager.Companion.setCurActivity(this);
        initData();
        requestPermissions();
        swipe_target.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Actions.doAction(getActivity(),i);
            }
        });
        apk = new File(getCacheDir() + typefaceUrl);
        LogUtils.tag(Constants.LOG_TAG).d(apk.getAbsolutePath());
        download(swipe_target);
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

    @Override
    protected View getLayoutView() {
        return null;
    }

    public void initData(){
//        List<String> data = new ArrayList<String>();
//        data = Arrays.asList(getResources().getStringArray(R.array.item_category_names));
        mainAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,Arrays.asList(getResources().getStringArray(R.array.item_category_names)));
        swipe_target.setAdapter(mainAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dialogEvent(DialogEvent dialogEvent){
        CouponNewCustomerDialogManager.Companion.doRequestCoupon(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    /**
     * 下载字体库
     * @param v
     */
    private void download(final View v) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://xiaohe-online.oss-cn-beijing.aliyuncs.com/Emulation/audios/homework"+typefaceUrl)
                .get()
                .build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        v.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "下载字体出错啦", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            FileOutputStream fos = new FileOutputStream(apk);
                            fos.write(response.body().bytes());
                            fos.close();
                            String finalPath =
                                    getCacheDir() + "/typeface";
                            File unZipFile = new File(finalPath);
                            LogUtils.tag(Constants.LOG_TAG).d("finalPath--->"+unZipFile.getAbsolutePath());
                            TypefaceLoadUtil.INSTANCE.unZip(apk.getAbsolutePath(), finalPath);
                            Thread.sleep(2000);
                            LogUtils.tag(Constants.LOG_TAG).d("continue.....");
                            // if (unZipFile.exists()) deleteCacheFiles(unZipFile);
                            //FileUtils.UnZipFolder(apk.getAbsolutePath(), finalPath);
                           // apk.delete();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        v.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "下载字体成功啦", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

    private void deleteCacheFiles(File file) {
        try {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0 ; i <files.length ;i++) {
                    deleteCacheFiles(files[i]);
                }
            }
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
