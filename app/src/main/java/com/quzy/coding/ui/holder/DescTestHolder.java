package com.quzy.coding.ui.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quzy.coding.R;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/11/20
 * desc   :
 * version: 1.0
 */

public class DescTestHolder extends RecyclerView.ViewHolder {
    public TextView descView;
    public ImageView ivCheck;

    public DescTestHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        descView = (TextView) itemView.findViewById(R.id.tv_desc);
        ivCheck = (ImageView) itemView.findViewById(R.id.ivCheck);
    }
}

