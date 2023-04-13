package com.quzy.coding.ui.activity;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.coding.qzy.baselibrary.utils.AbToastUtil;
import com.coding.qzy.baselibrary.widget.SwitchButton;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;

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


    @BindView(R.id.switch_button)
    SwitchButton switchButton;
    @BindView(R.id.switch_button1)
    SwitchButton switchButton1;
    @BindView(R.id.switch_bt)
    Switch switch_bt;

    private static ReferenceQueue<Object> rq = new ReferenceQueue<Object>();
    @Override
    protected void onViewCreated() {

        switchButton.setChecked(true);
        switchButton1.setChecked(false);
        switchButton.isChecked();
        switchButton.toggle();     //switch state
        switchButton.toggle(false);//switch without animation
        switchButton.setShadowEffect(true);//disable shadow effect
        switchButton.setEnabled(false);//disable button
        switchButton.setEnableEffect(false);//disable the switch animation
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job
                Toast.makeText(CustomControlActivity.this,isChecked+"",Toast.LENGTH_SHORT).show();
            }
        });
        switchButton1.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job
                Toast.makeText(CustomControlActivity.this,isChecked+"",Toast.LENGTH_SHORT).show();
            }
        });

        switch_bt.setChecked(true);
        switch_bt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AbToastUtil.showToast(CustomControlActivity.this , isChecked+"");
            }
        });

    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_control;
    }


}

