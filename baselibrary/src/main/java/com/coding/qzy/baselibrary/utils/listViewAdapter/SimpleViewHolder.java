package com.coding.qzy.baselibrary.utils.listViewAdapter;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * 作者：quzongyang
 * <p>
 * 创建时间：2018/2/1
 * <p>
 * 类描述：
 */

public abstract class SimpleViewHolder<T> implements ViewFinder {

    public final View itemView;

    public SimpleViewHolder(View itemView) {
        if (itemView == null) {
            throw new NullPointerException("itemView can't be null.");
        }
        this.itemView = itemView;
        itemView.setTag(this);
    }

    @Override
    public final View viewBy(@IdRes int id) {
        return itemView != null ? itemView.findViewById(id) : null;
    }

    @Override
    public final View viewWith(Object tag) {
        return itemView != null ? itemView.findViewWithTag(tag) : null;
    }

    public abstract void bind(T obj, int position);
}
