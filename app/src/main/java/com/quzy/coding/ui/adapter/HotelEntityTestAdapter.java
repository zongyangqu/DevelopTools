package com.quzy.coding.ui.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coding.qzy.baselibrary.utils.CollectionUtils;
import com.coding.qzy.baselibrary.widget.sectioned_adapter.SectionedRecyclerViewAdapter;
import com.quzy.coding.R;
import com.quzy.coding.bean.HotelEntity;
import com.quzy.coding.ui.holder.DescTestHolder;
import com.quzy.coding.ui.holder.HeaderHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/11/20
 * desc   :
 * version: 1.0
 */

public class HotelEntityTestAdapter extends SectionedRecyclerViewAdapter<HeaderHolder, DescTestHolder, RecyclerView.ViewHolder> {


    public ArrayList<HotelEntity.TagsEntity> allTagList;
    private Context mContext;
    private LayoutInflater mInflater;

    private SparseBooleanArray mBooleanMap;

    public HotelEntityTestAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBooleanMap = new SparseBooleanArray();
    }

    public void setData(ArrayList<HotelEntity.TagsEntity> allTagList) {
       // this.mMyLiveList = allTagList;
        this.allTagList = allTagList;
        notifyDataSetChanged();
    }

    @Override
    protected int getSectionCount() {
        return CollectionUtils.isEmpty(allTagList) ? 0 : allTagList.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = allTagList.get(section).tagInfoList.size();
        if (count >= 1 && !mBooleanMap.get(section)) {
            count = 0;
        }

        return CollectionUtils.isEmpty(allTagList.get(section).tagInfoList) ? 0 : count;
    }

    //是否有footer布局
    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected HeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new HeaderHolder(mInflater.inflate(R.layout.hotel_title_item, parent, false));
    }


    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected DescTestHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new DescTestHolder(mInflater.inflate(R.layout.hotel_desc_test_item, parent, false));
    }


    @Override
    protected void onBindSectionHeaderViewHolder(final HeaderHolder holder, final int section) {
        holder.openView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpen = mBooleanMap.get(section);
                String text = isOpen ? "展开" : "关闭";
                mBooleanMap.put(section, !isOpen);
                holder.openView.setText(text);
                notifyDataSetChanged();
            }
        });

        holder.titleView.setText(allTagList.get(section).tagsName);
        holder.openView.setText(mBooleanMap.get(section) ? "关闭" : "展开");

    }


    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(DescTestHolder holder, final int section, final int position) {
        final HotelEntity.TagsEntity.TagInfo tagInfo = allTagList.get(section).tagInfoList.get(position);
        //final String name = allTagList.get(section).tagInfoList.get(position).tagName;
        holder.descView.setText(tagInfo.tagName);
        if(tagInfo.ischeck){
            holder.ivCheck.setImageResource(R.drawable.ic_checked);
        }else{
            holder.ivCheck.setImageResource(R.drawable.ic_check);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(tagInfo,section,position);
            }
        });
    }

    //private List<HotelEntity.TagsEntity> mMyLiveList;
    /*public void notifyAdapter(List<HotelEntity.TagsEntity> myLiveList, boolean isAdd) {
        if (!isAdd) {
            this.mMyLiveList = myLiveList;
        } else {
            this.mMyLiveList.addAll(myLiveList);
        }
        notifyDataSetChanged();
    }*/

    public List<HotelEntity.TagsEntity> getMyLiveList() {
        if (allTagList == null) {
            allTagList = new ArrayList<>();
        }
        return allTagList;
    }

    public interface OnItemClickListener{
        public void onItemClick(HotelEntity.TagsEntity.TagInfo tagInfo,int section, int position );
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
