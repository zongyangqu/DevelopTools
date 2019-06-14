package com.coding.qzy.baselibrary.utils.guidelayer.listener;

import com.coding.qzy.baselibrary.utils.guidelayer.core.Controller;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/06/13
 * desc   : 引导层显示和消失的监听
 * version: 1.0
 */

public interface OnGuideChangedListener {
    /**
     * 当引导层显示时回调
     *
     * @param controller
     */
    void onShowed(Controller controller);

    /**
     * 当引导层消失时回调
     *
     * @param controller
     */
    void onRemoved(Controller controller);
}
