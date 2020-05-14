package com.quzy.coding.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quzy.coding.R;
import com.quzy.coding.bean.Anim;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/11/16
 * desc   :
 * version: 1.0
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.StaggeredViewHolder> {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 数据集合
     */
    private List<Anim> data;

    public StaggeredAdapter(List<Anim> data, Context context) {
        this.data = data;
        this.mContext = context;
    }

    public void refresh(List<Anim> datas) {
        data = datas;
        notifyDataSetChanged();
    }

    public void addDatas(List<Anim> datas) {
        data.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public StaggeredViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beauty_item, parent, false);
        return new StaggeredViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StaggeredViewHolder holder, int position) {
        //将数据设置到item上
        Anim beauty = data.get(position);
        holder.beautyImage.setImageResource(beauty.resourceId);
        holder.nameTv.setText(beauty.name);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class StaggeredViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_item)
        ImageView beautyImage;
        @BindView(R.id.name_item)
        TextView nameTv;

        public StaggeredViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

