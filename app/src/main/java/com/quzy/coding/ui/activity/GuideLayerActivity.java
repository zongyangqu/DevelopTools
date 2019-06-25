package com.quzy.coding.ui.activity;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.coding.qzy.baselibrary.utils.background.drawable.DrawableCreator;
import com.coding.qzy.baselibrary.utils.guidelayer.NewbieGuide;
import com.coding.qzy.baselibrary.utils.guidelayer.core.Controller;
import com.coding.qzy.baselibrary.utils.guidelayer.listener.OnGuideChangedListener;
import com.coding.qzy.baselibrary.utils.guidelayer.listener.OnHighlightDrewListener;
import com.coding.qzy.baselibrary.utils.guidelayer.listener.OnLayoutInflatedListener;
import com.coding.qzy.baselibrary.utils.guidelayer.listener.OnPageChangedListener;
import com.coding.qzy.baselibrary.utils.guidelayer.model.GuidePage;
import com.coding.qzy.baselibrary.utils.guidelayer.model.HighLight;
import com.coding.qzy.baselibrary.utils.guidelayer.model.HighlightOptions;
import com.coding.qzy.baselibrary.utils.guidelayer.model.RelativeGuide;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.ui.fragment.BlankFragment;

import butterknife.Bind;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/06/11
 * desc   : 引导层 https://github.com/huburt-Hu/NewbieGuide
 * version: 1.0
 */


public class GuideLayerActivity extends BaseActivity{

    private final String TAG = this.getClass().getSimpleName();

    @Bind(R.id.btn_simple)
    Button btnSimple;
    @Bind(R.id.btn_anchor)
    Button btnAnchor;
    @Bind(R.id.btn_dialog)
    Button btnDialog;
    @Bind(R.id.btn_multi)
    Button btnMulti;
    private Animation enterAnimation;
    private Animation exitAnimation;

    @Override
    protected void onViewCreated() {
        enterAnimation = new AlphaAnimation(0f, 1f);
        enterAnimation.setDuration(600);
        enterAnimation.setFillAfter(true);

        exitAnimation = new AlphaAnimation(1f, 0f);
        exitAnimation.setDuration(600);
        exitAnimation.setFillAfter(true);

        /**
         * 普通用法
         */
        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewbieGuide.with(GuideLayerActivity.this)
                        .setLabel("guide1")
//                        .setShowCounts(3)//控制次数
                        .alwaysShow(true)//总是显示，调试时可以打开
                        .addGuidePage(GuidePage.newInstance()
                                .addHighLight(btnSimple)
                                .setLayoutRes(R.layout.view_guide_simple))
                        .show();
            }
        });

        /**
         * 多层引导页
         */
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Controller controller = NewbieGuide.with(GuideLayerActivity.this)
                        .setLabel("guide2")
                        //.setShowCounts(3)//控制次数
                        .setOnGuideChangedListener(new OnGuideChangedListener() {
                            @Override
                            public void onShowed(Controller controller) {
                                Log.e(TAG, "NewbieGuide onShowed: ");
                                //引导层显示
                            }

                            @Override
                            public void onRemoved(Controller controller) {
                                Log.e(TAG, "NewbieGuide  onRemoved: ");
                                //引导层消失（多页切换不会触发）
                            }
                        })
                        .setOnPageChangedListener(new OnPageChangedListener() {
                            @Override
                            public void onPageChanged(int page) {
                                //引导页切换，page为当前页位置，从0开始
                                Toast.makeText(GuideLayerActivity.this, "引导页切换：" + page, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .alwaysShow(true)//总是显示，调试时可以打开
                        .addGuidePage(GuidePage.newInstance()
                                .addHighLight(btnSimple)
                                .addHighLight(new RectF(0, 800, 200, 1200))
                                .setEverywhereCancelable(false)//是否点击任意地方跳转下一页或者消失引导层，默认true
                                //.setBackgroundColor(getResources().getColor(R.color.background))//设置背景色，建议使用有透明度的颜色
                                .setLayoutRes(R.layout.view_guide_simple,R.id.ivNext))
                        .addGuidePage(GuidePage.newInstance()
                                .addHighLight(btnDialog)
                                .setLayoutRes(R.layout.view_guide_simple1))
                        .addGuidePage(//添加一页引导页
                                GuidePage.newInstance()//创建一个实例
                                        .addHighLight(btnMulti)//添加高亮的view
                                        .setLayoutRes(R.layout.view_guide_simple2)//设置引导页布局
                                        .setOnLayoutInflatedListener(new OnLayoutInflatedListener() {
                                            @Override
                                            public void onLayoutInflated(View view, Controller controller) {
                                                //引导页布局填充后回调，用于初始化
                                                TextView tv = view.findViewById(R.id.textView2);
                                                tv.setText("我是动态设置的文本");
                                            }
                                        })
                                       // .setEnterAnimation(enterAnimation)//进入动画
                                        .setExitAnimation(exitAnimation)//退出动画
                        ).build();
                controller.show();
                //controller.remove();
            }
        });

        /**
         * 设置anchor 及 自定义绘制图形
         */
        final View anchorView = findViewById(R.id.ll_anchor);
        final Button btnAnchor = (Button) findViewById(R.id.btn_anchor);
        btnAnchor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HighlightOptions options = new HighlightOptions.Builder()
                        .setOnHighlightDrewListener(new OnHighlightDrewListener() {
                            @Override
                            public void onHighlightDrew(Canvas canvas, RectF rectF) {
                                Paint paint = new Paint();
                                paint.setColor(Color.WHITE);
                                paint.setStyle(Paint.Style.STROKE);
                                paint.setStrokeWidth(10);
                                paint.setPathEffect(new DashPathEffect(new float[]{20, 20}, 0));
                                canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2 + 10, paint);
                            }
                        })
                        .build();
                NewbieGuide.with(GuideLayerActivity.this)
                        .setLabel("anchor")
                        .anchor(anchorView)
                        .alwaysShow(true)//总是显示，调试时可以打开
                        .addGuidePage(GuidePage.newInstance()
                                .addHighLightWithOptions(btnAnchor, HighLight.Shape.CIRCLE, options)
                                .setLayoutRes(R.layout.view_guide_anchor))
                        .show();
            }
        });

        /**
         * 指定RectF高亮
         */
        findViewById(R.id.btn_rect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewbieGuide.with(GuideLayerActivity.this)
                        .setLabel("rect")
                        .alwaysShow(true)//总是显示，调试时可以打开
                        .addGuidePage(GuidePage.newInstance()
                                .addHighLight(new RectF(0, 800, 500, 1000))
                        )
                        .show();
            }
        });


        /**
         * 对话框形式
         */
        final Button btnDialog = (Button) findViewById(R.id.btn_dialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewbieGuide.with(GuideLayerActivity.this)
                        .setLabel("guide3")
                        .alwaysShow(true)//总是显示，调试时可以打开
                        .addGuidePage(GuidePage.newInstance()
                                .addHighLight(btnDialog)
                                .setEverywhereCancelable(false)//是否点击任意位置消失引导页
                                .setLayoutRes(R.layout.view_guide_dialog, R.id.btn_ok)
                                .setOnLayoutInflatedListener(new OnLayoutInflatedListener() {

                                    @Override
                                    public void onLayoutInflated(View view, Controller controller) {
                                        TextView tv = view.findViewById(R.id.tv_text);
                                        tv.setText("this like dialog");
                                    }
                                }))
                        .show();
            }
        });


        /**
         *相对位置展示引导布局
         */
        final View btnRelative = findViewById(R.id.btn_relative);
        btnRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HighlightOptions options = new HighlightOptions.Builder()
                        .setRelativeGuide(new RelativeGuide(R.layout.view_relative_guide, Gravity.LEFT, 100) {
                            @Override
                            protected void onLayoutInflated(View view, Controller controller) {
                                TextView textView = view.findViewById(R.id.tv);
                                textView.setText("inflated");
                            }
                        })
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(GuideLayerActivity.this, "highlight click", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
                GuidePage page = GuidePage.newInstance()
                        .addHighLightWithOptions(btnRelative, options);
                NewbieGuide.with(GuideLayerActivity.this)
                        .setLabel("relative")
                        .alwaysShow(true)//总是显示，调试时可以打开
                        .addGuidePage(page)
                        .show();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide_layer;
    }


}
