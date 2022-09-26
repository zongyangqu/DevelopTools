package com.quzy.coding.util.op;

import android.app.Activity;
import android.content.Intent;

import com.quzy.coding.ui.activity.ARouterSampleActivity;
import com.quzy.coding.ui.activity.ActivityAnimaActivity;
import com.quzy.coding.ui.activity.AppUpdateActivity;
import com.quzy.coding.ui.activity.BackGroundActivity;
import com.quzy.coding.ui.activity.CustomControlActivity;
import com.quzy.coding.ui.activity.DrawWithRichTextActivity;
import com.quzy.coding.ui.activity.ExternalResourceActivity;
import com.quzy.coding.ui.activity.GaoSDemoActivity;
import com.quzy.coding.ui.activity.GuideLayerActivity;
import com.quzy.coding.ui.activity.HotFixDemoActivity;
import com.quzy.coding.ui.activity.LogUtilActivity;
import com.quzy.coding.ui.activity.LottieActivity;
import com.quzy.coding.ui.activity.PopWindowActivity;
import com.quzy.coding.ui.activity.ProgressActivity;
import com.quzy.coding.ui.activity.QRCodeActivity;
import com.quzy.coding.ui.activity.QuickPositionActivity;
import com.quzy.coding.ui.activity.RecordAudioActivity;
import com.quzy.coding.ui.activity.RecordMp3Activity;
import com.quzy.coding.ui.activity.RecyclerHorizontalMoreActivity;
import com.quzy.coding.ui.activity.RecyclerViewSampleActivity;
import com.quzy.coding.ui.activity.TextSampleActivity;
import com.quzy.coding.ui.activity.ThreeDimensionalActivity;
import com.quzy.coding.ui.activity.TypefaceActivity;
import com.quzy.coding.ui.activity.WebViewRecyclerActivity;
import com.quzy.coding.ui.activity.WebViewhybridSampleActivity;
import com.quzy.coding.util.RecordVoiceMp3View;

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
    RecyclerViewKotlinAction {
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
    PopWindowAction {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, PopWindowActivity.class));
        }
    },
    LogUtilActivityAction {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, LogUtilActivity.class));
        }
    }, ProgressAction {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, ProgressActivity.class));
        };
    },AudioAction {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, RecordMp3Activity.class));
//            activity.startActivity(new Intent(activity, RecordAudioActivity.class));
        };
    },QRAction {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, QRCodeActivity.class));
        };
    },Customontrol {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, CustomControlActivity.class));
        };
    },ExternalResource {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, ExternalResourceActivity.class));
        };
    },HotFix {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, HotFixDemoActivity.class));
        };
    },Lottie {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, LottieActivity.class));
        };
    },GaoSi {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, GaoSDemoActivity.class));
        };
    },ThreeDimensional {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, ThreeDimensionalActivity.class));
        };
    },WebViewhybridSample {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, WebViewhybridSampleActivity.class));
        };
    },WebViewRecycler {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, WebViewRecyclerActivity.class));
        };
    },RecyclerHorizontalMore {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, RecyclerHorizontalMoreActivity.class));
        };
    },ARouter {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, ARouterSampleActivity.class));
        };
    },DrawWithRichText {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, DrawWithRichTextActivity.class));
        };
    },TypefacceLib {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, TypefaceActivity.class));
        };
    },ActivityAnima {
        @Override
        public void starAction(Activity activity) {
            activity.startActivity(new Intent(activity, ActivityAnimaActivity.class));
        };
    };

}
