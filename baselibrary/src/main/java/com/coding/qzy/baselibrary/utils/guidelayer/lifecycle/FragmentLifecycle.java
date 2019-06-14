package com.coding.qzy.baselibrary.utils.guidelayer.lifecycle;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/06/13
 * desc   :
 * version: 1.0
 */
public interface FragmentLifecycle {
    void onStart();

    void onStop();

    void onDestroyView();

    void onDestroy();
}
