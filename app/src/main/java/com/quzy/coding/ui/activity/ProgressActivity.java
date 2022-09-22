package com.quzy.coding.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.coding.qzy.baselibrary.widget.CustomProgressBar;
import com.coding.qzy.baselibrary.widget.CustomizedProgressBar;
import com.coding.qzy.baselibrary.widget.GradientProgressBar;
import com.coding.qzy.baselibrary.widget.TextProgressBar;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

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
    private CustomizedProgressBar customizedProgressBar ;

    private GradientProgressBar progressBar ;
    private GradientProgressBar progressBar2 ;
    private TextProgressBar progressBar3 ;

    @Override
    protected void onViewCreated() {
        mProgressBar = findViewById(R.id.cpb_progresbar);
        customizedProgressBar = findViewById(R.id.customizedProgressBar);
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



        progressBar = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar3 = findViewById(R.id.progressBar3);
        List<String> l1 = new ArrayList<>();
        l1.add("0");
        l1.add("20");
        l1.add("40");
        List<String> l2 = new ArrayList<>();
        l2.add("2.0%");
        l2.add("2.5%");
//        progressBar.setBottomText(l1);
//        progressBar.setTopText(l2);
        progressBar.setMaxProgress(40f);
        progressBar.setCurrentProgress(35f);

        progressBar2.initColor("#FFBDCF","#FF8E00","#cccccc");
        progressBar2.setMaxProgress(80f);
        progressBar2.setCurrentProgress(55f);

        progressBar3.initColor("#FFBDCF","#FF8E00","#cccccc");
        progressBar3.setMaxProgress(80f);
        progressBar3.setCurrentProgress(60f);

        findViewById(R.id.changeBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customizedProgressBar.initColor("#FA632D","#7D7DFF","#999999");
                customizedProgressBar.setMaxCount(100);
                customizedProgressBar.setCurrentCount(80);
            }
        });

        findViewById(R.id.changeBar2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customizedProgressBar.initColor("#FFFF00","#FF3B30","#F2F2F2");
                customizedProgressBar.setMaxCount(5000);
                customizedProgressBar.setCurrentCount(2600);
            }
        });

    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layout_progress;
    }
}
