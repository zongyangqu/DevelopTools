package com.quzy.coding.base;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.coding.qzy.baselibrary.utils.appreset.AppStatusConstant;
import com.coding.qzy.baselibrary.utils.appreset.AppStatusManager;
import com.coding.qzy.baselibrary.widget.external_resource.ResourceFactory;
import com.coding.qzy.baselibrary.widget.external_resource.SkinManager;
import com.quzy.coding.MainActivity;

import butterknife.ButterKnife;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/05/15
 * desc   : 支持外部加载资源的基类Activity
 * version: 1.0
 */

public abstract class BaseResourceActivity extends AppCompatActivity {

    ResourceFactory resourceFactory;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(BaseApplication.appContext.getResourceApk().exists()){
            if(SkinManager.getInstance().loadRespurceSucess()){
                resourceFactory = new ResourceFactory(this);
                getLayoutInflater().setFactory2(resourceFactory);
            }
        }
        super.onCreate(savedInstanceState);

        switch (AppStatusManager.getInstance().getAppStatus()) {
            /**
             * 应用被强杀
             */
            case AppStatusConstant.STATUS_FORCE_KILLED:
                //跳到主页,主页lauchmode SINGLETASK
                protectApp();
                break;
            /**
             * 用户被踢或者TOKEN失效
             */
            case AppStatusConstant.STATUS_KICK_OUT:
                //弹出对话框,点击之后跳到主页,清除用户信息,运行退出登录逻辑
//                Intent intent=new Intent(this,MainActivity.class);
//                startActivity(intent);
                break;
            case AppStatusConstant.STATUS_NORMAL:
                setContentView(getLayoutId());
                ButterKnife.bind(this);
                onViewCreated();
                break;
        }
    }


    protected void protectApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(AppStatusConstant.KEY_HOME_ACTION, AppStatusConstant.ACTION_RESTART_APP);
        startActivity(intent);
    }

    protected abstract void onViewCreated();

    @LayoutRes
    protected abstract int getLayoutId();

    public AppCompatActivity getActivity(){
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(BaseApplication.appContext.getResourceApk().exists() && SkinManager.getInstance().loadRespurceSucess() && null != resourceFactory){
            resourceFactory.setResource();
        }
    }
}

