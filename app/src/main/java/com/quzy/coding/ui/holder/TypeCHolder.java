package com.quzy.coding.ui.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quzy.coding.R;
import com.quzy.coding.bean.LiBean;

/**
 * Created by ruzhan on 2017/5/1.
 */

public class TypeCHolder extends RecyclerView.ViewHolder {

  private ImageView iv;
  private TextView tv;

  public TypeCHolder(View itemView) {
    super(itemView);
    iv = (ImageView) itemView.findViewById(R.id.icon_iv);
    tv = (TextView) itemView.findViewById(R.id.title_tv);
  }

  public void bind(int position, LiBean bean) {
    iv.setImageResource(bean.getIcon());
    tv.setText(bean.getText());
  }
}
