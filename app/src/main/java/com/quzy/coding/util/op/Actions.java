package com.quzy.coding.util.op;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.quzy.coding.base.BaseApplication;
import com.quzy.coding.util.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/05/22
 * desc   :
 * version: 1.0
 */


public class Actions {

    static Map<Integer,String> mActionMap = new HashMap<>();

    static {
        mActionMap.put(Constants.BackGround,RoleEnum.BackGroundAction.name());
        mActionMap.put(Constants.TextSample,RoleEnum.TextSampleAction.name());
        mActionMap.put(Constants.GuideLayer,RoleEnum.GuideLayerAction.name());
        mActionMap.put(Constants.QuickPosition,RoleEnum.QuickPositionAction.name());
        mActionMap.put(Constants.RecyclerViewSample,RoleEnum.RecyclerViewSampleAction.name());
        mActionMap.put(Constants.LogUtilActivity,RoleEnum.LogUtilActivityAction.name());
    }


    public static void doAction(Activity activity, int module) {
        if (null == mActionMap.get(module)) {
            Toast.makeText(BaseApplication.getContext(), "此功能暂未开放，敬请期待", Toast.LENGTH_SHORT).show();
        } else {
            RoleEnum.valueOf(mActionMap.get(module)).starAction(activity);
        }
    }
}
