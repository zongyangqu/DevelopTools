package com.quzy.coding.util;

import android.content.Context;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/11/21
 * desc   :
 * version: 1.0
 */

public class Singleton {
    private static Singleton singleton;
    private Context context;
    private Singleton(Context context) {
        this.context = context;
    }

    public static Singleton newInstance(Context context) {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null){//双重检查锁定
                    singleton = new Singleton(context);
                }
            }
        }
        return singleton;
    }
}