package com.quzy.coding.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.quzy.coding.R;
import com.quzy.coding.bean.LiBean;
import com.quzy.coding.bean.QianBean;
import com.quzy.coding.bean.SunBean;
import com.quzy.coding.bean.WuBean;
import com.quzy.coding.bean.ZhaoBean;
import com.quzy.coding.bean.ZhengBean;
import com.quzy.coding.bean.ZhouBean;
import com.quzy.coding.ui.holder.BannerHolder;
import com.quzy.coding.ui.holder.TypeAHolder;
import com.quzy.coding.ui.holder.TypeBHolder;
import com.quzy.coding.ui.holder.TypeCHolder;
import com.quzy.coding.ui.holder.TypeDHolder;
import com.quzy.coding.ui.holder.TypeEHolder;
import com.quzy.coding.ui.holder.TypeFHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ruzhan on 2017/4/20.
 */

public class StandardMultiAdapter extends RecyclerView.Adapter {

  public static final int SPAN_SIZE = 120;

  private static final int TYPE_ZHAO = 1000;
  private static final int TYPE_QIAN = 1001;
  private static final int TYPE_SUN = 1002;
  private static final int TYPE_LI = 1003;
  private static final int TYPE_ZHOU = 1004;
  private static final int TYPE_WU = 1005;
  private static final int TYPE_ZHENG = 1006;

  private static final int TYPE_QIAN_1 = 1007;

  private List<Object> data = new ArrayList<>();

  public void setData(List<ZhaoBean> zhaoList, List<QianBean> qianList, List<SunBean> sunList,
                      List<LiBean> liList, List<ZhouBean> zhouList, List<WuBean> wuList,
                      List<ZhengBean> zhengList) {
    data.addAll(zhaoList);
    data.addAll(qianList);
    data.addAll(sunList);
    data.addAll(liList);
    data.addAll(zhouList);
    data.addAll(wuList);
    data.addAll(zhengList);
    notifyDataSetChanged();
  }

  @Override
  public int getItemViewType(int position) {
    Object object = data.get(position);
    if (object instanceof ZhaoBean) {
      return TYPE_ZHAO;
    } else if (object instanceof QianBean) {
      QianBean qb = (QianBean) object;
      if(qb.getIndex() == 0 || qb.getIndex() == 3){
        return TYPE_QIAN_1;
      }else{
        return TYPE_QIAN;
      }
    } else if (object instanceof SunBean) {
      return TYPE_SUN;
    } else if (object instanceof LiBean) {
      return TYPE_LI;
    } else if (object instanceof ZhouBean) {
      return TYPE_ZHOU;
    } else if (object instanceof WuBean) {
      return TYPE_WU;
    } else if (object instanceof ZhengBean) {
      return TYPE_ZHENG;
    }
    return TYPE_ZHENG;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == TYPE_ZHAO) {
      return new BannerHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false));
    } else if (viewType == TYPE_QIAN || viewType == TYPE_QIAN_1) {
      return new TypeAHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_a, parent, false));
    } else if (viewType == TYPE_SUN) {
      return new TypeBHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_b, parent, false));
    } else if (viewType == TYPE_LI) {
      return new TypeCHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_c, parent, false));
    } else if (viewType == TYPE_ZHOU) {
      return new TypeDHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_d, parent, false));
    } else if (viewType == TYPE_WU) {
      return new TypeEHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_e, parent, false));
    } else if (viewType == TYPE_ZHENG) {
      return new TypeFHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_f, parent, false));
    }
    return new TypeFHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_f, parent, false));
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    Object object = data.get(position);
    int viewType = getItemViewType(position);

    if (viewType == TYPE_ZHAO) {
      ((BannerHolder) holder).bind(position, (ZhaoBean) object);
    } else if (viewType == TYPE_QIAN || viewType == TYPE_QIAN_1) {
      ((TypeAHolder) holder).bind(position, (QianBean) object);
    } else if (viewType == TYPE_SUN) {
      ((TypeBHolder) holder).bind(position, (SunBean) object);
    } else if (viewType == TYPE_LI) {
      ((TypeCHolder) holder).bind(position, (LiBean) object);
    } else if (viewType == TYPE_ZHOU) {
      ((TypeDHolder) holder).bind(position, (ZhouBean) object);
    } else if (viewType == TYPE_WU) {
      ((TypeEHolder) holder).bind(position, (WuBean) object);
    } else if (viewType == TYPE_ZHENG) {
      ((TypeFHolder) holder).bind(position, (ZhengBean) object);
    }
  }

  @Override
  public int getItemCount() {
    return data == null ? 0 : data.size();
  }

  public int getSpanSize(int position) {
    int viewType = getItemViewType(position);

    if (viewType == TYPE_ZHAO) {
      return SPAN_SIZE;
    } else if (viewType == TYPE_QIAN) {
      return SPAN_SIZE /4;
    } else if (viewType == TYPE_QIAN_1) {
      return SPAN_SIZE / 2;
    } else if (viewType == TYPE_SUN) {
      return SPAN_SIZE;
    } else if (viewType == TYPE_LI) {
      return SPAN_SIZE / 2;
    } else if (viewType == TYPE_ZHOU) {
      return SPAN_SIZE / 5;
    } else if (viewType == TYPE_WU) {
      return SPAN_SIZE;
    } else if (viewType == TYPE_ZHENG) {
      return SPAN_SIZE / 2;
    }
    return SPAN_SIZE;
  }
}
