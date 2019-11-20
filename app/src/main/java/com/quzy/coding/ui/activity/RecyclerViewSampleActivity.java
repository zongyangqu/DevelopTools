package com.quzy.coding.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.ui.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/07/23
 * desc   : RecyclerView Demo
 * version: 1.0
 */

public class RecyclerViewSampleActivity  extends BaseActivity {

    @Bind(R.id.swipe_target)
    ListView swipe_target;
    MainAdapter mainAdapter;

    @Override
    protected void onViewCreated() {
        mainAdapter = new MainAdapter(initData());
        swipe_target.setAdapter(mainAdapter);
        swipe_target.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        startActivity(new Intent(getActivity(), HeadViewRecyclerViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), NormalRecyclerViewActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), StaggeredGridActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), StandardMultiActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), SectionedRecyclerViewActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler_sample;
    }

    public List<String> initData(){
        List<String> data = new ArrayList<String>();
        data.add("封装有headView的RecyclerView");
        data.add("封装普通的RecyclerView");
        data.add("RecyclerView瀑布流");
        data.add("RecyclerView电商复杂界面");
        data.add("分组列表的recyclerView");
        return data;
    }
}

