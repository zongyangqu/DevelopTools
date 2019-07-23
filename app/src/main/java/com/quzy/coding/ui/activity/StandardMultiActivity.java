package com.quzy.coding.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.bean.LiBean;
import com.quzy.coding.bean.ModelHelper;
import com.quzy.coding.bean.QianBean;
import com.quzy.coding.bean.SunBean;
import com.quzy.coding.bean.WuBean;
import com.quzy.coding.bean.ZhaoBean;
import com.quzy.coding.bean.ZhengBean;
import com.quzy.coding.bean.ZhouBean;
import com.quzy.coding.ui.adapter.StandardMultiAdapter;

import java.util.List;

import butterknife.Bind;
/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/11/19
 * desc   :
 * version: 1.0
 */


public class StandardMultiActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recycler_view;

    private StandardMultiAdapter adapter;
    @Override
    protected void onViewCreated() {
        adapter = new StandardMultiAdapter();
        recycler_view.setAdapter(adapter);

        //multi item need set SpanSize
        GridLayoutManager manager = new GridLayoutManager(this, StandardMultiAdapter.SPAN_SIZE);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                return adapter.getSpanSize(position);
            }
        });
        recycler_view.setLayoutManager(manager);

        //net work request data
        List<ZhaoBean> zhaoList = ModelHelper.getZhaoList(1);
        List<QianBean> qianList = ModelHelper.getQianListCopy(10);
        List<SunBean> sunList = ModelHelper.getSunList(1);
        List<LiBean> liList = ModelHelper.getLiList(4);
        List<ZhouBean> zhouList = ModelHelper.getZhouList(10);
        List<WuBean> wuList = ModelHelper.getWuList(1);
        List<ZhengBean> zhengList = ModelHelper.getZhengList(30);

        //refresh data
        adapter.setData(zhaoList, qianList, sunList, liList, zhouList, wuList, zhengList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_standard_multi;
    }
}
