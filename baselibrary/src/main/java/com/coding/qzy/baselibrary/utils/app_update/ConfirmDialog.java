package com.coding.qzy.baselibrary.utils.app_update;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.coding.qzy.baselibrary.R;


/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/10/17
 * desc   : 升级对话框
 * version: 1.0
 */


public class ConfirmDialog extends Dialog {

    Callback callback;
    private TextView content;
    private TextView sureBtn;
    private TextView cancleBtn;
    private boolean isForce;

    public ConfirmDialog(Context context, Callback callback, boolean isForce) {
        super(context, R.style.ConfirmUpdateDialog);
        this.callback = callback;
        this.isForce = isForce;
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_confirm, null);
        sureBtn = (TextView)mView.findViewById(R.id.dialog_confirm_sure);
        cancleBtn = (TextView)mView.findViewById(R.id.dialog_confirm_cancle);
        content = (TextView) mView.findViewById(R.id.dialog_confirm_title);
        if(isForce){
            cancleBtn.setVisibility(View.GONE);
        }else{
            cancleBtn.setVisibility(View.VISIBLE);
        }

        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callback(1);
                if(!isForce){
                    ConfirmDialog.this.cancel();
                }else{
                    sureBtn.setText("更新下载中...");
                    //sureBtn.setEnabled(false);
                }
            }
        });
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callback(0);
                ConfirmDialog.this.cancel();
            }
        });
        super.setContentView(mView);
    }


    public ConfirmDialog setContent(String s){
        content.setText(s);
        return this;
    }


}
