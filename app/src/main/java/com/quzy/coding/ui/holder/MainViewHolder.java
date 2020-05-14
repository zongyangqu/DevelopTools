package com.quzy.coding.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.coding.qzy.baselibrary.utils.listViewAdapter.SimpleViewHolder;
import com.quzy.coding.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/1
 *
 * 类描述：
 */

public class MainViewHolder extends SimpleViewHolder<String> {

    @BindView(R.id.position)
    TextView tvPosition;
    @BindView(R.id.instruction)
    TextView instruction;

    public MainViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(String obj, int position) {
        tvPosition.setText(String.valueOf(position+1));
        instruction.setText(obj);
    }
}
