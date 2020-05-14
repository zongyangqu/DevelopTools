package com.quzy.coding.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coding.qzy.baselibrary.widget.adapter.holder.SimpleRViewHolder;
import com.quzy.coding.R;
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

public class AnimViewHolder extends SimpleRViewHolder<Anim> {

    @BindView(R.id.animRes)
    ImageView animRes;
    @BindView(R.id.animName)
    TextView animName;

    public AnimViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Anim obj, int position) {
        animName.setText(obj.name);
        //Glide.with(BaseApplication.getContext()).load(obj.resourceId).into(animRes);
    }
}
