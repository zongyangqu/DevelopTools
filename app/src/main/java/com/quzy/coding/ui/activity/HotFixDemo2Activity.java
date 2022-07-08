package com.quzy.coding.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;


import butterknife.BindView;


/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/03/15
 * desc   : 热修复原理
 * version: 1.0
 */


public class HotFixDemo2Activity extends BaseActivity {


    @BindView(R.id.tvShow)
    TextView tvShow;

    @Override
    protected void onViewCreated() {
        tvShow.setText("我是bug版本");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hotfix_demo2;
    }



    @Override
    protected View getLayoutView() {
        return null;
    }


}

