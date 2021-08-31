package com.quzy.coding.ui.activity;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.coding.qzy.baselibrary.utils.AbToastUtil;
import com.coding.qzy.baselibrary.widget.refreshview.SpringView;
import com.coding.qzy.baselibrary.widget.refreshview.container.MeituanFooter;
import com.coding.qzy.baselibrary.widget.refreshview.container.MeituanHeader;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.bean.Anim;
import com.quzy.coding.ui.adapter.StaggeredAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/11/16
 * desc   :
 * version: 1.0
 */


public class StaggeredGridActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.springview)
    SpringView springView;

    int total_page = 3;
    int currentPage = 1;

    private List<Anim> data;
    private StaggeredAdapter adapter;

    @Override
    protected void onViewCreated() {
        //线性布局Manager
        // LinearLayoutManager recyclerViewLayoutManager = new LinearLayoutManager(this);
        //recyclerViewLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //网络布局Manager
        //GridLayoutManager recyclerViewLayoutManager = new GridLayoutManager(this, 3);
        //使用瀑布流布局
        // 第一个参数 spanCount 列数,第二个参数 orentation 排列方向
        //给recyclerView设置LayoutManager
        StaggeredGridLayoutManager recyclerViewLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        initData();
        adapter = new StaggeredAdapter(data, this);
        recyclerView.setAdapter(adapter);
        setListener();
    }

    public void setListener() {
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                initData();
                adapter.refresh(data);
                springView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                if (total_page > currentPage) {
                    currentPage = currentPage + 1;
                    adapter.addDatas(addData());
                    springView.onFinishFreshAndLoad();
                } else {
                    springView.onFinishFreshAndLoad();
                    AbToastUtil.showToast(getActivity(), "没有更多了，亲！");
                }
            }
        });
        springView.setHeader(new MeituanHeader(getActivity()));
        springView.setFooter(new MeituanFooter(getActivity()));
//        springView.setHeader(new RotationHeader(getActivity()));
//        springView.setFooter(new RotationFooter(getActivity()));
    }

    /**
     * 生成一些数据添加到集合中
     */
    private void initData() {
        Anim anim;
        currentPage = 1;
        data = new ArrayList<Anim>();
        for (int i = 0; i < 5; i++) {
            anim = new Anim("狮子" + i, R.mipmap.shizi);
            data.add(anim);
            anim = new Anim("老虎" + i, R.mipmap.artboard);
            data.add(anim);
            anim = new Anim("daxiang" + i, R.mipmap.daxiang);
            data.add(anim);
            anim = new Anim("狗" + i, R.mipmap.gougou);
            data.add(anim);
            anim = new Anim("狗" + i, R.mipmap.gougou1);
            data.add(anim);
            anim = new Anim("猫" + i, R.mipmap.mao);
            data.add(anim);
            anim = new Anim("哈士奇" + i, R.mipmap.hashiqi);
            data.add(anim);
            anim = new Anim("哈士奇" + i, R.mipmap.hashiqi1);
            data.add(anim);
            anim = new Anim("老虎" + i, R.mipmap.artboard);
            data.add(anim);
            anim = new Anim("猫" + i, R.mipmap.mao);
            data.add(anim);
        }
    }

    private List<Anim> addData() {
        List<Anim> data = new ArrayList<Anim>();
        data.add(new Anim("哈士奇" , R.mipmap.hashiqi));
        data.add(new Anim("哈士奇" , R.mipmap.hashiqi1));
        data.add(new Anim("老虎" , R.mipmap.artboard));
        data.add(new Anim("猫" , R.mipmap.mao));
        data.add(new Anim("狮子" , R.mipmap.shizi));
        data.add(new Anim("老虎" , R.mipmap.artboard));
        data.add(new Anim("daxiang" , R.mipmap.daxiang));
        data.add(new Anim("狗" , R.mipmap.gougou));
        data.add(new Anim("狗" , R.mipmap.gougou1));
        data.add(new Anim("猫" , R.mipmap.mao));
        return data;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_staggered_activity;
    }
}
