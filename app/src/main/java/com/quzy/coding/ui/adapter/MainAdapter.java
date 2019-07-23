package com.quzy.coding.ui.adapter;

import android.view.LayoutInflater;

import com.coding.qzy.baselibrary.utils.listViewAdapter.SimpleAdapter;
import com.quzy.coding.R;
import com.quzy.coding.ui.holder.MainViewHolder;

import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/1
 *
 * 类描述：首页数据源
 */

public class MainAdapter extends SimpleAdapter<String, MainViewHolder> {

    public MainAdapter(List<String> campuses) {
        super(campuses);
    }

    @Override
    protected MainViewHolder createViewHolder(LayoutInflater layoutInflater, int i) {
        return new MainViewHolder(layoutInflater.inflate(R.layout.widget_main_item, null));
    }

    @Override
    protected void onBindViewHolder(MainViewHolder vh, int i) {
        vh.bind(getItem(i), i);
    }
}
