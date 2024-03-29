package com.quzy.coding.ui.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.coding.qzy.baselibrary.widget.WaveSideBar;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.bean.Contact;
import com.quzy.coding.ui.adapter.ContactsAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/06/14
 * desc   : 快速查询索引  https://github.com/gjiazhe/WaveSideBar
 * version: 1.0
 */


public class QuickPositionActivity extends BaseActivity{

    @BindView(R.id.rv_contacts)
    RecyclerView rvContacts;
    @BindView(R.id.side_bar)
    WaveSideBar sideBar;

    private ArrayList<Contact> contacts = new ArrayList<>();
    @Override
    protected void onViewCreated() {
        initData();
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_quick_position;
    }

    private void initView() {
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        ContactsAdapter adapter = new ContactsAdapter(contacts, R.layout.item_contacts);
        adapter.setOnItemClickListener(new ContactsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name) {
                Toast.makeText(QuickPositionActivity.this,name,Toast.LENGTH_SHORT).show();
            }
        });
        rvContacts.setAdapter(adapter);
        sideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                for (int i=0; i<contacts.size(); i++) {
                    if (contacts.get(i).getIndex().equals(index)) {
                        ((LinearLayoutManager) rvContacts.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    private void initData() {
        contacts.addAll(Contact.getChineseContacts());
    }
}
