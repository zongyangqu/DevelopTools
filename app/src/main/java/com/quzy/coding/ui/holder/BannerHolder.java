package com.quzy.coding.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.quzy.coding.R;
import com.quzy.coding.bean.ZhaoBean;


/**
 * Created by ruzhan on 2017/5/1.
 */

public class BannerHolder extends RecyclerView.ViewHolder {

  private ImageView iv;

  public BannerHolder(View itemView) {
    super(itemView);
    iv = (ImageView) itemView.findViewById(R.id.banner_iv);
  }

  public void bind(int position, ZhaoBean bean) {
    iv.setImageResource(bean.getIcon());
  }
}
