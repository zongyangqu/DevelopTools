package com.coding.qzy.baselibrary.utils.listViewAdapter;

import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/1
 *
 * 类描述：ListView使用
 */

public abstract class SimpleAdapter<T, VH extends SimpleViewHolder<T>> extends BaseAdapter {

    protected List<T> dataList;

    public SimpleAdapter(List<T> tList) {
        this.dataList = tList;
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Nullable
    @Override
    public T getItem(int position) {
        if (dataList == null) {
            return null;
        }
        if (dataList.size() <= position) {
            return null;
        }
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh;
        if (convertView != null) {
            vh = ((VH) convertView.getTag());
        } else {
            vh = createViewHolder(LayoutInflater.from(parent.getContext()), getItemViewType(position));
        }
        if (vh == null) {
            throw new NullPointerException(String.format("SimpleAdapter#getView can't render View by bind null object"));
        }
        onBindViewHolder(vh, position);
        return vh.itemView;
    }

    protected abstract VH createViewHolder(LayoutInflater inflater, int viewType);

    protected abstract void onBindViewHolder(VH vh, int position);

    public void appendRefresh(List<T> tList) {
        boolean refreshAble = false;
        if (tList != null && tList.size() != 0) {
            if (dataList == null) {
                dataList = new ArrayList<>();
            }
            dataList.addAll(tList);
            refreshAble = true;
        }
        if (refreshAble) {
            notifyDataSetChanged();
        }
    }

    public void refresh(List<T> tList) {
        dataList = tList;
        notifyDataSetChanged();
    }

    public void clear() {
        refresh(null);
    }
}