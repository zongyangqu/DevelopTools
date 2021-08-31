package com.quzy.coding.ui.activity;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.bean.Fruit;
import com.quzy.coding.databinding.ActivityRecyclerviewBinding;
import com.quzy.coding.ui.adapter.RecycVerticalAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * CreateDate:2021/8/23 14:51
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
public class NormalRecyclerViewJavaActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ArrayList data = new ArrayList <Fruit>();
    RecycVerticalAdapter adapter ;

    @Override
    protected void onViewCreated() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        initData();
        adapter = new RecycVerticalAdapter(this,null);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        data.add(new Fruit("apple", R.mipmap.apple_pic));
        data.add(new Fruit("banana", R.mipmap.banana_pic));
        data.add(new Fruit("orange", R.mipmap.orange_pic));
        data.add(new Fruit("watermelon", R.mipmap.watermelon_pic));
        data.add(new Fruit("pear", R.mipmap.pear_pic));
        data.add(new Fruit("grape", R.mipmap.grape_pic));
        data.add(new Fruit("pineapple", R.mipmap.pineapple_pic));
        data.add(new Fruit("strawberry", R.mipmap.strawberry_pic));
        data.add(new Fruit("cherry", R.mipmap.cherry_pic));
        data.add(new Fruit("mango", R.mipmap.mango_pic));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }
}
