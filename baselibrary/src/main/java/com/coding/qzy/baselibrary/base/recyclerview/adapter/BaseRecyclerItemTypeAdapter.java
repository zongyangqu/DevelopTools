package com.coding.qzy.baselibrary.base.recyclerview.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.coding.qzy.baselibrary.base.recyclerview.holder.RecyclerViewHolder;

/**
 * CreateDate:2021/12/17 10:55
 *
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.base.recyclerview.adapter
 * @Description:
 */
public abstract class BaseRecyclerItemTypeAdapter extends BaseRecyclerViewAdapter<RecyclerViewHolder> {

    @Override
    public RecyclerViewHolder getViewHolder(View itemView) {
        return null;
    }

    @Override
    public abstract RecyclerViewHolder getViewHolder(View itemView, int viewType);

    @Override
    public abstract View getItemView(int viewType, ViewGroup parent);

    @Override
    public abstract int getItemCount();

    @Override
    public abstract int getItemViewType(int position) ;
}
