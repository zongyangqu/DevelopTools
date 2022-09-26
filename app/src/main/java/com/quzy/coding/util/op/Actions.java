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
        mActionMap.put(Constants.AppUpdate,RoleEnum.AppUpdateSampleAction.name());
        mActionMap.put(Constants.AppPopWindow,RoleEnum.PopWindowAction.name());
        mActionMap.put(Constants.Progress,RoleEnum.ProgressAction.name());
        mActionMap.put(Constants.Audio,RoleEnum.AudioAction.name());
        mActionMap.put(Constants.QRCode,RoleEnum.QRAction.name());
        mActionMap.put(Constants.CustomControl,RoleEnum.Customontrol.name());
        mActionMap.put(Constants.ExternalResourceLoad,RoleEnum.ExternalResource.name());
        mActionMap.put(Constants.HotFix,RoleEnum.HotFix.name());
        mActionMap.put(Constants.Lottie,RoleEnum.Lottie.name());
        mActionMap.put(Constants.GaoSi,RoleEnum.GaoSi.name());
        mActionMap.put(Constants.ThreeDimensional,RoleEnum.ThreeDimensional.name());
        mActionMap.put(Constants.WebViewhybridSample,RoleEnum.WebViewhybridSample.name());
        mActionMap.put(Constants.WebViewRecycler,RoleEnum.WebViewRecycler.name());
        mActionMap.put(Constants.RecyclerHorizontalMore,RoleEnum.RecyclerHorizontalMore.name());
        mActionMap.put(Constants.ARouter,RoleEnum.ARouter.name());
        mActionMap.put(Constants.DrawWithRichText,RoleEnum.DrawWithRichText.name());
        mActionMap.put(Constants.ActivityTypeface,RoleEnum.TypefacceLib.name());
        mActionMap.put(Constants.ActivityAnima,RoleEnum.ActivityAnima.name());
    }


    public static void doAction(Activity activity, int module) {
        if (null == mActionMap.get(module)) {
            Toast.makeText(BaseApplication.getContext(), "此功能暂未开放，敬请期待", Toast.LENGTH_SHORT).show();
        } else {
            RoleEnum.valueOf(mActionMap.get(module)).starAction(activity);
        }
    }
}
