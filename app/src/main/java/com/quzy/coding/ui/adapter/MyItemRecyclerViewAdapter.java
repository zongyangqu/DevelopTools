package com.quzy.coding.ui.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.quzy.coding.R;
import com.quzy.coding.bean.dummy.DummyContent;
import com.quzy.coding.util.widget.ChangeModeRecyclerView;

import java.util.List;

/**
 * CreateDate:2021/11/10 10:27
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DummyContent.DummyItem> mValues;
    private boolean mIsStagger;

    public MyItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
        mValues = items;
    }

    public void switchMode(boolean mIsStagger) {
        this.mIsStagger = mIsStagger;
    }

    public void setData(List<DummyContent.DummyItem> datas) {
        mValues = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (((RecyclerView)parent).getLayoutManager() instanceof StaggeredGridLayoutManager ) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.fragment_item_staggel, parent, false);
//            return new StaggerViewHolder(view);
//        } else {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.fragment_item, parent, false);
//            return new ViewHolder(view);
//        }

        /*if (mIsStagger) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_item_staggel, parent, false);
            return new StaggerViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_item, parent, false);
            return new ViewHolder(view);
        }*/
        if (viewType == ChangeModeRecyclerView.TYPE_STAGGER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_item_staggel, parent, false);
            return new StaggerViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_item, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (mIsStagger) {
            StaggerViewHolder staggerViewHolder = (StaggerViewHolder) holder;
            staggerViewHolder.iconView.setVisibility(View.VISIBLE);
            staggerViewHolder.mContentView.setText(mValues.get(position).details);
        } else {
            ViewHolder mHolder = (ViewHolder) holder;
            mHolder.mItem = mValues.get(position);
            mHolder.mContentView.setText(mValues.get(position).content);
            mHolder.mIdView.setText(mValues.get(position).id);
        }
    }

    /*@Override
    public int getItemViewType(int position) {
        *//**
         * 这么做保证layoutManager切换之后能及时的刷新上对的布局
         *//*
        if (getLayoutManager() instanceof LinearLayoutManager) {
            return ChangeModeRecyclerView.TYPE_LIST;
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            return ChangeModeRecyclerView.TYPE_STAGGER;
        } else {
            return ChangeModeRecyclerView.TYPE_LIST;
        }
    }*/

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class StaggerViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public View iconView;
        public TextView mContentView;

        public StaggerViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            iconView = itemView.findViewById(R.id.icon);
            mContentView = (TextView) itemView.findViewById(R.id.content);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
