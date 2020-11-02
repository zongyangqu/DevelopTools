package com.quzy.coding.ui.activity;

import android.graphics.Color;
import android.widget.Toast;

import com.coding.qzy.baselibrary.widget.CustomProgressBar;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/11/02
 * desc   :
 * version: 1.0
 */


public class ProgressActivity extends BaseActivity {

    private CustomProgressBar mProgressBar ;
    private CustomProgressBar mProgressBar2 ;
    @Override
    protected void onViewCreated() {
        mProgressBar = (CustomProgressBar) findViewById(R.id.cpb_progresbar);
        mProgressBar.setOnFinishedListener(new CustomProgressBar.OnFinishedListener() {
            @Override
            public void onFinish() {
                Toast.makeText(ProgressActivity.this,"done!",Toast.LENGTH_SHORT).show();
            }
        });
        mProgressBar.setProgressDesc("剩余");
        mProgressBar.setMaxProgress(50);
        mProgressBar.setProgressColor(Color.parseColor("#F6CB82"));
        mProgressBar.setCurProgress(50);


        mProgressBar2 = (CustomProgressBar) findViewById(R.id.cpb_progresbar2);
        mProgressBar2.setOnAnimationEndListener(new CustomProgressBar.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                Toast.makeText(ProgressActivity.this,"animation end!",Toast.LENGTH_SHORT).show();
            }
        });
        mProgressBar2.setProgressDesc("剩余");
        mProgressBar2.setMaxProgress(100);
        mProgressBar2.setProgressColor(Color.parseColor("#79aa6b"));
        mProgressBar2.setCurProgress(80,4000);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layout_progress;
    }
}
