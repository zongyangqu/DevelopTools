package com.coding.qzy.baselibrary.widget.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.coding.qzy.baselibrary.widget.adapter.holder.SimpleRViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：封装RecyclerView使用的adapter
 */

public abstract class SimpleRVAdapter<T, VH extends SimpleRViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> dataList = new ArrayList<T>();

    public SimpleRVAdapter(List<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(LayoutInflater.from(parent.getContext()), viewType);
    }

    protected abstract VH onCreateViewHolder(LayoutInflater inflater, int viewTye);

    public void refresh(List<T> tList) {
        dataList = tList;
        notifyDataSetChanged();
    }

    public void appendRefresh(List<T> tList) {
        boolean refreshAble = false;
        int lastPosition = 0;
        int appendSize = 0;
        if (tList != null && tList.size() != 0) {
            if (dataList == null) {
                dataList = new ArrayList<>();
            }
            lastPosition = dataList.size();
            appendSize = tList.size();
            dataList.addAll(tList);
            refreshAble = true;
        }
        if (refreshAble) {
            notifyItemRangeInserted(lastPosition, appendSize);
        }
    }

    @Nullable
    public T getItem(int position) {
        if (dataList == null) {
            return null;
        }
        if (dataList.size() <= position) {
            return null;
        }
        return dataList.get(position);
    }

    public interface OnItemClickCallback<T> {
        void onItemClicked(T t, int position);
    }

    public void clear() {
        refresh(null);
    }
}

