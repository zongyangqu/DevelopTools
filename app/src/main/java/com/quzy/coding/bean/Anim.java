package com.quzy.coding.bean;

import android.support.annotation.DrawableRes;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：
 */

public class Anim {

    public String name;
    public int resourceId;

    public Anim(String name,@DrawableRes int resourceId){
        this.name = name;
        this.resourceId = resourceId;
    }
}
