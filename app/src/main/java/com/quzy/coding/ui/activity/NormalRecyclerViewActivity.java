package com.quzy.coding.ui.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.bean.Anim;
import com.quzy.coding.ui.adapter.AnimNormalAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：
 */

public class NormalRecyclerViewActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    AnimNormalAdapter adapter;

    @Override
    protected void onViewCreated() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        adapter = new AnimNormalAdapter(initData());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }


    public List<Anim> initData(){
        List<Anim> animList = new ArrayList<Anim>();
        Anim alpaca = new Anim("羊驼",R.mipmap.alpaca);
        Anim cat = new Anim("小猫",R.mipmap.cat);
        Anim crab = new Anim("螃蟹",R.mipmap.crab);
        Anim cygnus = new Anim("天鹅",R.mipmap.cygnus);
        Anim dog = new Anim("小狗",R.mipmap.dog);
        Anim elephant = new Anim("大象",R.mipmap.elephant);
        Anim frog = new Anim("青蛙",R.mipmap.frog);
        Anim giraffe = new Anim("长颈鹿",R.mipmap.giraffe);
        Anim hippo = new Anim("河马",R.mipmap.hippo);
        Anim husky = new Anim("哈士奇",R.mipmap.husky);
        Anim kangaroo = new Anim("袋鼠",R.mipmap.kangaroo);
        Anim panda = new Anim("熊猫",R.mipmap.panda);
        Anim lion = new Anim("狮子",R.mipmap.lion);
        Anim penguin = new Anim("企鹅",R.mipmap.penguin);
        Anim rabbit = new Anim("兔子",R.mipmap.rabbit);
        Anim sheep = new Anim("绵羊",R.mipmap.sheep);
        Anim squirrel = new Anim("松鼠",R.mipmap.squirrel);
        animList.add(alpaca);
        animList.add(cat);
        animList.add(crab);
        animList.add(cygnus);
        animList.add(dog);
        animList.add(elephant);
        animList.add(frog);
        animList.add(giraffe);
        animList.add(hippo);
        animList.add(husky);
        animList.add(kangaroo);
        animList.add(panda);
        animList.add(lion);
        animList.add(penguin);
        animList.add(rabbit);
        animList.add(sheep);
        animList.add(squirrel);
        return animList;
    }
}
