package com.coding.qzy.baselibrary.base.recyclerview.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * CreateDate:2021/12/17 10:58
 *
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.base.recyclerview.adapter
 * @Description:
 */
public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private final int SINGLE_TYPE = -1213874;


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getItemView(viewType, parent);
        VH vh = null;
        if (viewType == SINGLE_TYPE) vh = getViewHolder(view);
        else vh = getViewHolder(view, viewType);
        if (vh == null) throw new NullPointerException("the viewholder is null, please check it");
        return vh;
    }

    public abstract VH getViewHolder(@NonNull View itemView);

    public VH getViewHolder(View itemView, int viewType) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return SINGLE_TYPE;
    }


    /**
     * 返回item的view
     *
     * @return
     */
    public abstract View getItemView(int viewType, ViewGroup parent);

    /**
     * 返回Adapter每个itemn的数据 可选
     */
    public Object getItem(int position) {
        return null;
    }

    @Override
    public abstract int getItemCount();
}

