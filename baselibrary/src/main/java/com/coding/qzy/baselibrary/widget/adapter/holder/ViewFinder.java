package com.coding.qzy.baselibrary.widget.adapter.holder;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：
 */

public interface ViewFinder {

    View viewBy(@IdRes int id);

    View viewWith(Object tag);
}