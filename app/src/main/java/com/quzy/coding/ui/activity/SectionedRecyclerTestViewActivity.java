package com.quzy.coding.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coding.qzy.baselibrary.utils.AbToastUtil;
import com.coding.qzy.baselibrary.widget.sectioned_adapter.SectionedSpanSizeLookup;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.bean.HotelEntity;
import com.quzy.coding.ui.adapter.HotelEntityAdapter;
import com.quzy.coding.ui.adapter.HotelEntityTestAdapter;
import com.quzy.coding.util.JsonUtils;

import butterknife.Bind;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/11/20
 * desc   :
 * version: 1.0
 */


public class SectionedRecyclerTestViewActivity extends BaseActivity {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private HotelEntityTestAdapter mAdapter;


    @Override
    protected void onViewCreated() {
        initView();

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new HotelEntityTestAdapter(this);
        //GridLayoutManager manager = new GridLayoutManager(this,4);
        //设置header
        //manager.setSpanSizeLookup(new SectionedSpanSizeLookup(mAdapter,manager));
        LinearLayoutManager manager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        HotelEntity entity = JsonUtils.analysisJsonFile(this,"json");
        mAdapter.setData(entity.allTagsList);
        //mAdapter.notifyAdapter(entity.allTagsList,false);
        mAdapter.setOnItemClickListener(new HotelEntityTestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(HotelEntity.TagsEntity.TagInfo tagInfo,int section, int position) {
                HotelEntity.TagsEntity.TagInfo  t = mAdapter.getMyLiveList().get(section).tagInfoList.get(position);
                t.ischeck = !t.ischeck;
                //AbToastUtil.showToast(getActivity(),name);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sectioned_recyclerview;
    }
}
