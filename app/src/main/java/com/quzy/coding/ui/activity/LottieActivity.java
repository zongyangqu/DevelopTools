package com.quzy.coding.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.base.BaseResourceActivity;

import java.io.File;
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
 * desc   : Lottie动画
 * version: 1.0
 */


public class LottieActivity extends BaseActivity {



    @Override
    protected void onViewCreated() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lottie;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }
}

