package com.quzy.coding.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;

import com.coding.qzy.baselibrary.widget.adapter.SimpleRVAdapter;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseApplication;
import com.quzy.coding.bean.Anim;
import com.quzy.coding.ui.holder.AnimViewHolder;

import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：
 */

public class AnimNormalAdapter extends SimpleRVAdapter<Anim,AnimViewHolder> {


    public AnimNormalAdapter(List dataList) {
        super(dataList);
    }

    @Override
    protected AnimViewHolder onCreateViewHolder(LayoutInflater layoutInflater, int viewTye) {
        LayoutInflater inflater = LayoutInflater.from(BaseApplication.getContext());
        View itemView = inflater.inflate(R.layout.widget_anim,null,true);
        /*RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(lp);*/
        return new AnimViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnimViewHolder holder, int position) {
        holder.bind(getItem(position),position);
    }
}
