package com.quzy.coding.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coding.qzy.baselibrary.utils.AbToastUtil;
import com.coding.qzy.baselibrary.widget.sectioned_adapter.SectionedSpanSizeLookup;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.bean.HotelEntity;
import com.quzy.coding.ui.adapter.HotelEntityAdapter;
import com.quzy.coding.util.JsonUtils;

import butterknife.BindView;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/11/20
 * desc   :
 * version: 1.0
 */


public class SectionedRecyclerViewActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private HotelEntityAdapter mAdapter;


    @Override
    protected void onViewCreated() {
        initView();

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new HotelEntityAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this,4);
        //设置header
        manager.setSpanSizeLookup(new SectionedSpanSizeLookup(mAdapter,manager));
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        HotelEntity entity = JsonUtils.analysisJsonFile(this,"json");
        mAdapter.setData(entity.allTagsList);
        mAdapter.setOnItemClickListener(new HotelEntityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name) {
                AbToastUtil.showToast(getActivity(),name);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sectioned_recyclerview;
    }
}
