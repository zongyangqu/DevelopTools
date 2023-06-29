package com.quzy.coding.base.activity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.AppBarLayout;
import com.quzy.coding.R;
import com.quzy.coding.base.LoadingView;
import com.quzy.coding.base.YhLoadingDialog;
import com.quzy.coding.util.ManuFacturerUtil;
import com.quzy.coding.util.StatusBarUtil;

import org.jetbrains.annotations.Nullable;

/**
 * CreateDate:2023/6/16 16:29
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.base.activity
 * @Description:
 */
public abstract class BaseYHActivity extends AppCompatActivity
        implements View.OnClickListener, ILoginCheck, BasePageInterFace {

    public static final int FLAG_HIDE_TITLE = 1 << 0;
    public static final int FLAG_HIDE_BACK = 1 << 1;
    public static final int FLAG_HIDE_SEARCH = 1 << 2;
    public static final int FLAG_SHOW_CLOSE = 1 << 3;
    public static final int REQUESTCODE_LOGIN = 15698;

    private final int SPEED_SHAKE_THRESHOLD =
            ManuFacturerUtil.isMIUI() || ManuFacturerUtil.isZTE() ? 8 : 10;
    private final int SHAKE_THRESHOLD = 20;


    private View viewBg;
    protected LoadingView mLoadingContainer;
    protected Toolbar mToolbar;
    protected AppBarLayout mAppBarLayout;

    private boolean isDataEmpty = true;
    private LayoutInflater mInflater;
    private FragmentManager mFragmentManager = null;
    private FragmentTransaction mFragmentTransaction = null;

    private YhLoadingDialog loadingDialog;
    private FrameLayout mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor();
        getWindow()
                .setFlags(
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        initBaseView();
        beforeInitView();
        initView();
        updateSkinUI();
    }

    protected void beforeInitView() {
    }

    protected void initView() {
    }

    protected void updateSkinUI() {
    }

    protected void setStatusBarColor() {
        StatusBarUtil.showDrakStatusBarIcon(this);
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected boolean customContentView() {
        return false;
    }

    private void initBaseView() {
        if (customContentView()) {
            setContentView(getMainContentResId());
        } else {
            setRootView();
            mInflater = LayoutInflater.from(this);
            inflateMainView();
            mRootView = findViewById(android.R.id.content);
            mLoadingContainer = findViewById(R.id.loading_cover);
            mLoadingContainer.setOnClickListener(this);

            initToolbar();
        }
        if (enableLoadFragment()) {
            initFragment();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected View inflateMainView() {
        return mInflater.inflate(
                getMainContentResId(), (ViewGroup) findViewById(R.id.main_view), true);
    }

    private void initFragment() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        setFragment();
    }

    /**
     * 开启加载Fragment功能，默认不加载，需要加载复写return true
     *
     * @return
     */
    protected boolean enableLoadFragment() {
        return false;
    }

    /**
     * 加载Fragment
     */
    protected void setFragment() {
    }

    public FragmentManager getParentSupportFragmentManager() {
        return mFragmentManager;
    }

    public FragmentTransaction getParentSupportFragmentTransaction() {
        return mFragmentTransaction;
    }

    /**
     * 当用户登录成功返回的时候
     */
    @Override
    public void onLoginActivityResult(int result) {
    }

    @Override
    public boolean isAtyAlive() {
        return !isFinishing();
    }

    @Nullable
    @Override
    public Activity getAtyContext() {
        return this;
    }

    /**
     * 是否要隐藏返回键
     */
    public boolean hideNavigationIcon() {
        return false;
    }

    public void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            if (getToolbarTitle() != 0) mToolbar.setTitle(getToolbarTitle());
            mAppBarLayout = (AppBarLayout) findViewById(R.id.common_view);
            if (mAppBarLayout != null) {
                mAppBarLayout.setBackgroundResource(R.color.white);
            }
            mToolbar.setBackgroundResource(R.color.white);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(!hideNavigationIcon());
            mToolbar.setNavigationOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                        }
                    });
        }
    }

    public void initToolbar(int toolbarColor) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            if (getToolbarTitle() != 0) mToolbar.setTitle(getToolbarTitle());
            mAppBarLayout = (AppBarLayout) findViewById(R.id.common_view);
            if (mAppBarLayout != null) {
                mAppBarLayout.setBackgroundResource(toolbarColor);
            }
            mToolbar.setBackgroundResource(toolbarColor);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(!hideNavigationIcon());
            mToolbar.setNavigationOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                        }
                    });
        }
    }

    public void setOnNavgationClickListener(View.OnClickListener onNavgationClickListener) {
        if (onNavgationClickListener != null) {
            mToolbar.setNavigationOnClickListener(onNavgationClickListener);
        }
    }



    //    TOOLBAR相关的方法
    protected @StringRes
    int getToolbarTitle() {
        return 0;
    }

    protected void setToolbarTitle(String title) {
        if (TextUtils.isEmpty(title)) return;
        mToolbar.setTitle(title);
        resetToolbarNavgationClick();
    }

    public void resetToolbarNavgationClick() {
        mToolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
    }

    protected void setRootView() {
        setContentView(R.layout.activity_base_toolbar);
    }

    public abstract @LayoutRes
    int getMainContentResId();

    public @Deprecated
    @StringRes
    int getTitleResId() {
        return -1;
    }

    public void setCloseButtonVisible(boolean visible) {
        // TODO 浏览器里需要处理关闭cLOSE按钮的操作

    }

    public void setWindowFlag(int flag) {
    }

    @Override
    public void onClick(View view) {
        if (view == mLoadingContainer) {
            onLoadingCoverClicked();
        }
    }

    // 显示loadingDialog
    public void showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = YhLoadingDialog.Companion.createDialog(this);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        loadingDialog.show();
    }

    // 取消LoadingDialog
    public void closeLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.cancel();
        }
    }

    public void onLoadingCoverClicked() {
    }

    public void onErrorCoverClicked() {
        mLoadingContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void enableSkeleton(int layoutid) {
        mLoadingContainer.skeleton(layoutid);
    }

    @Override
    public void showLoadingView(boolean show) {
        setLoadingContainerVisible(show);
    }

    /**
     * 此方法不区分网络和服务器异常,后续会弃用
     *
     * @param b
     */
    public void showErrorView(boolean b) {
        setErrorContainerVisible(b);
    }

    /**
     * 此方法区分网络和服务器异常，适用xml文件中已配置NetWorkExceptionView
     *
     * @param errorCode 异常code
     */
    protected void showErrorView(int errorCode, int marginTop, int marginBottom) {
        //showErrorView(errorCode, null, null, marginTop, marginBottom);
    }

    /**
     * 此方法区分网络和服务器异常，适用xml文件中已配置NetWorkExceptionView
     *
     * @param errorCode    异常code
     * @param errorMessage 异常信息
     * @param errorImage   ErrorView展示的图片
     */
    protected void showErrorView(
            int errorCode,
            String errorMessage,
            String errorImage,
            int marginTop,
            int marginBottom) {
        mLoadingContainer.setVisibility(View.GONE);
        switch (errorCode) {
//            case StatusCode.CURRENTLIMITING_CODE: {
//                if (isDataEmpty()) {
//                    initShowErrorView(
//                            errorCode, errorMessage, errorImage, marginTop, marginBottom);
//                } else {
//                    if (errorMessage != null && !TextUtils.isEmpty(errorMessage)) {
//                        UiUtil.showToast(errorMessage);
//                    }
//                }
//            }
//            default: {
//                initShowErrorView(errorCode, errorMessage, errorImage, marginTop, marginBottom);
//            }
        }
    }


    public void setDataEmpty(boolean isDataEmpty) {
        this.isDataEmpty = isDataEmpty;
    }

    // 判断页面是否有数据 ，默认没有 由子类复写
    protected boolean isDataEmpty() {
        return isDataEmpty;
    }




    public void setLoadingContainerVisible(final boolean visible) {
        runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        if (visible) {
                            mLoadingContainer.setVisibility(View.VISIBLE);
                        } else {
                            mLoadingContainer.setVisibility(View.GONE);
                        }
                    }
                });
    }

    /**
     * 此方法不区分网络和服务器异常,后续会弃用
     *
     * @param visible
     */
    public void setErrorContainerVisible(final boolean visible) {
        runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        mLoadingContainer.setVisibility(View.GONE);
                    }
                });
    }




    public void setLoadingBackGround(int colorId) {
        if (mLoadingContainer != null) {
            mLoadingContainer.setBackgroundResource(colorId);
        }
    }


    @Override
    public void onBackPressed() {
        if (mLoadingContainer.getVisibility() == View.VISIBLE) {
            mLoadingContainer.setVisibility(View.GONE);
        }
        super.onBackPressed();
    }

    public AppBarLayout getmAppBarLayout() {
        return mAppBarLayout;
    }

    public Toolbar getmToolbar() {
        return mToolbar;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
