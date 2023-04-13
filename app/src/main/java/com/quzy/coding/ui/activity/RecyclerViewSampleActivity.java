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

import butterknife.BindView;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/07/23
 * desc   : RecyclerView Demo
 * version: 1.0
 */

public class RecyclerViewSampleActivity  extends BaseActivity {

    @BindView(R.id.swipe_target)
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
                    case 5:
                        startActivity(new Intent(getActivity(), SectionedRecyclerTestViewActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(getActivity(), ChangeRecyclerViewModeActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(getActivity(), BaseQuickRecyclerViewKotActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(getActivity(), NormalRecyclerViewKotActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(getActivity(), MoreItemRecyclerViewKotActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(getActivity(), RecyclerViewWaterfallComplexKotActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(getActivity(), ChangeRecyclerViewModeKotActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(getActivity(), RecyclerViewAverageKotActivity.class));
                        break;
                    case 13:
                        startActivity(new Intent(getActivity(), TableLayoutRecyclerViewKotActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler_sample;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    public List<String> initData(){
        List<String> data = new ArrayList<String>();
        data.add("封装有headView的RecyclerView");
        data.add("封装普通的RecyclerView");
        data.add("RecyclerView瀑布流");
        data.add("RecyclerView电商复杂界面");
        data.add("分组列表的recyclerView");
        data.add("分组选择列表的recyclerView");
        data.add("Java-RecyclerView展示模式切换");
        data.add("Kotlin-BaseQuickAdapter");
        data.add("Kotlin-普通的RecyclerView");
        data.add("Kotlin-多条目的RecyclerView");
        data.add("Kotlin-多条目瀑布流的RecyclerView");
        data.add("Kotlin-RecyclerView展示模式切换");
        data.add("Kotlin-RecyclerView横向均分、item间距、item曝光");
        data.add("Kotlin-TableLayout嵌套Fragment");
        return data;
    }
}

