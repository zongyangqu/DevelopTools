package com.quzy.coding.ui.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.coding.qzy.baselibrary.widget.adapter.BaseRecyclerAdapter;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseApplication;
import com.quzy.coding.bean.Anim;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：
 */

public class AnimAdapter extends BaseRecyclerAdapter<Anim> {

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_anim, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Anim data) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.animName.setText(data.name);
        Glide.with(BaseApplication.getContext()).load(data.resourceId).into(holder.animRes);
    }


    class ViewHolder extends Holder {

        @BindView(R.id.animRes)
        ImageView animRes;
        @BindView(R.id.animName)
        TextView animName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
