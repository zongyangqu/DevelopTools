package com.coding.qzy.baselibrary.utils.guidelayer.listener;

import android.view.View;

import com.coding.qzy.baselibrary.utils.guidelayer.core.Controller;

/**
 * 用于引导层布局初始化
 */

public interface OnLayoutInflatedListener {

    /**
     * @param view       {@link com.app.hubert.guide.model.GuidePage#setLayoutRes(int, int...)}方法传入的layoutRes填充后的view
     * @param controller {@link Controller}
     */
    void onLayoutInflated(View view, Controller controller);
}
