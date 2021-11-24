package com.quzy.coding.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.coding.qzy.baselibrary.widget.adapter.BaseRecyclerAdapter;
import com.coding.qzy.baselibrary.widget.adapter.SimpleRVAdapter;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseApplication;
import com.quzy.coding.bean.Anim;
import com.quzy.coding.bean.Ware;
import com.quzy.coding.ui.holder.AnimViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * CreateDate:2021/11/23 18:45
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
public class HotAdapter extends BaseRecyclerAdapter<Ware> {



    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_reclerclview_adapter, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Ware data) {
        ViewHolder holder = (ViewHolder) viewHolder;

        holder.hotGoodsDescription.setText(data.getName());
        holder.hotGoodsPrice.setText(data.getPrice());
        Glide.with(BaseApplication.getContext()).load(data.getImgUrl()).into(holder.hotGoodsImg);
    }



    class ViewHolder extends Holder {
        @BindView(R.id.buyButton)
        Button button;
        @BindView(R.id.hotGoodsPrice)
        TextView hotGoodsPrice;
        @BindView(R.id.hotGoodsDescription)
        TextView hotGoodsDescription;
        @BindView(R.id.hotGoodsImg)
        ImageView hotGoodsImg;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
