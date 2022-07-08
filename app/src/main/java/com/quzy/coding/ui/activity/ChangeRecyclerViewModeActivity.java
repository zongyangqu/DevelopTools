package com.quzy.coding.ui.activity;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.bean.dummy.DummyContent;
import com.quzy.coding.ui.adapter.MyItemRecyclerViewAdapter;
import com.quzy.coding.util.widget.ChangeModeRecyclerView;

import butterknife.BindView;

/**
 * CreateDate:2021/11/10 10:14
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
public class ChangeRecyclerViewModeActivity extends BaseActivity {

    ChangeModeRecyclerView recyclerView;
    private MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;
    TextView mode_switch_btn;
    private int mColumnCount = 1;
    @Override
    protected void onViewCreated() {
        recyclerView = findViewById(R.id.list);
        mode_switch_btn = findViewById(R.id.mode_switch_btn);
        recyclerView.setHasFixedSize(true);
        mode_switch_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (1 == mColumnCount) {
                    mColumnCount = 2;
                    ((TextView) v).setText(R.string.list_mode_stagger);
                    myItemRecyclerViewAdapter.switchMode(true);
                    recyclerView.switchLayoutManager(new StaggeredGridLayoutManager(mColumnCount, StaggeredGridLayoutManager.VERTICAL));
                } else {
                    mColumnCount = 1;
                    ((TextView) v).setText(R.string.list_mode_list);
                    myItemRecyclerViewAdapter.switchMode(false);
                    recyclerView.switchLayoutManager(new LinearLayoutManager(getActivity()));
                }
            }
        });

        if (1 >= mColumnCount) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(mColumnCount, StaggeredGridLayoutManager.VERTICAL));
        }
        myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(DummyContent.generyData(0));
        recyclerView.setAdapter(myItemRecyclerViewAdapter);
        recyclerView.setAutoLoadMoreEnable(false);
        myItemRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_mode_recyclerview;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }
}
