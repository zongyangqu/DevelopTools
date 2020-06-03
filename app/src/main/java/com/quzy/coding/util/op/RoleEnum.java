package com.quzy.coding.util.op;

import android.app.Activity;
import android.content.Intent;

import com.quzy.coding.ui.activity.AppUpdateActivity;
import com.quzy.coding.ui.activity.BackGroundActivity;
import com.quzy.coding.ui.activity.GuideLayerActivity;
import com.quzy.coding.ui.activity.LogUtilActivity;
import com.quzy.coding.ui.activity.QuickPositionActivity;
import com.quzy.coding.ui.activity.RecyclerViewSampleActivity;
import com.quzy.coding.ui.activity.TextSampleActivity;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/05/22
 * desc   :
 * version: 1.0
 */


public enum RoleEnum implements RoleOperation {

    BackGroundAction {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, BackGroundActivity.class));
        }
    },
    TextSampleAction {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, TextSampleActivity.class));
        }
    },

    GuideLayerAction {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, GuideLayerActivity.class));
        }
    },
    QuickPositionAction {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, QuickPositionActivity.class));
        }
    },
    RecyclerViewSampleAction {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, RecyclerViewSampleActivity.class));
        }
    },
    AppUpdateSampleAction {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, AppUpdateActivity.class));
        }
    },
    LogUtilActivityAction {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, LogUtilActivity.class));
        }
    };

}
