package com.quzy.coding.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.coding.qzy.baselibrary.widget.adapter.BaseRecyclerAdapter;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseApplication;
import com.quzy.coding.bean.Ware;

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
public class AssortShowAdapter extends RecyclerView.Adapter<AssortShowAdapter.AssortShowViewHolder> {

    private List<Ware> data;

    @NonNull
    @Override
    public AssortShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.assort_show_adapter, parent, false);
        return new AssortShowViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AssortShowViewHolder holder, int position) {
        Ware ware = data.get(position);
        AssortShowViewHolder viewHolder = (AssortShowViewHolder) holder;

        viewHolder.hotGoodsDescription.setText(ware.getName());
        viewHolder.hotGoodsPrice.setText(ware.getPrice());
        Glide.with(BaseApplication.getContext()).load(ware.getImgUrl()).into(holder.hotGoodsImg);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void refresh(List<Ware> datas) {
        data = datas;
        notifyDataSetChanged();
    }


    class AssortShowViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hotGoodsPrice)
        TextView hotGoodsPrice;
        @BindView(R.id.hotGoodsDescription)
        TextView hotGoodsDescription;
        @BindView(R.id.hotGoodsImg)
        ImageView hotGoodsImg;
        public AssortShowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

/*
public class AssortShowAdapter extends BaseRecyclerAdapter<Ware> {



    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.assort_show_adapter, parent, false);
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
*/
