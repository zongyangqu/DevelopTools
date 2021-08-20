package com.coding.qzy.baselibrary.widget.adapter.holder;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：SimpleRVAdapter使用
 */

public abstract class SimpleRViewHolder<T> extends RecyclerView.ViewHolder implements ViewFinder {

    public SimpleRViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T obj, int position);

    @Override
    public View viewBy(@IdRes int id) {
        return itemView == null ? null : itemView.findViewById(id);
    }

    @Override
    public View viewWith(Object tag) {
        return itemView == null ? null : itemView.findViewWithTag(tag);
    }
}
