package com.quzy.coding.ui.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.coding.qzy.baselibrary.widget.IosToggleButton;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.util.TestManager;
import com.quzy.coding.util.UZXing;

import java.lang.ref.ReferenceQueue;

import butterknife.BindView;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/12/08
 * desc   :
 * version: 1.0
 */


public class CustomControlActivity extends BaseActivity {


    @BindView(R.id.ios7Btn)
    IosToggleButton ios7Btn;

    private static ReferenceQueue<Object> rq = new ReferenceQueue<Object>();
    @Override
    protected void onViewCreated() {
        ios7Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isOpen = ios7Btn.ToggleIsOpen();
                ios7Btn.setOpen(!isOpen);
                Toast.makeText(CustomControlActivity.this,"开关---"+!isOpen,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_control;
    }


}

