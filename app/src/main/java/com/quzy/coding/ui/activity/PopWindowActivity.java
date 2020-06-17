package com.quzy.coding.ui.activity;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.coding.qzy.baselibrary.utils.app_update.AppUpdateUtils;
import com.coding.qzy.baselibrary.widget.popwindow.PopItemAction;
import com.coding.qzy.baselibrary.widget.popwindow.PopWindow;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/07/23
 * desc   : RecyclerView Demo
 * version: 1.0
 */

public class PopWindowActivity extends BaseActivity {

    @BindView(R.id.iv_arrow)
    ImageView mArrowIv;
    private PopWindow popWindow;


    @Override
    protected void onViewCreated() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_app_popwindow;
    }


    @OnClick({R.id.click1,R.id.click2,R.id.click3,R.id.click4,R.id.click5,R.id.click6,R.id.click7,R.id.click8,R.id.click9})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.click1:
               click1(view);
                break;
            case R.id.click2:
                click1(view);
                break;
            case R.id.click3:
                click3(view);
                break;
            case R.id.click4:
                click4(view);
                break;
            case R.id.click5:
                click5(view);
                break;
            case R.id.click6:
                click6(view);
                break;
            case R.id.click7:
                click7(view);
                break;
            case R.id.click8:
                click8(view);
                break;
            case R.id.click9:
                click9(view);
                break;
        }
    }

    public void click1(View view) {
        PopWindow popWindow = new PopWindow.Builder(this)
                .setStyle(PopWindow.PopWindowStyle.PopUp)
                .setTitle("注意")
                .setMessage("今天天气不错")
                .addItemAction(new PopItemAction("选项1"))
                .addItemAction(new PopItemAction("选项2", PopItemAction.PopItemStyle.Normal))
                .addItemAction(new PopItemAction("选项3", PopItemAction.PopItemStyle.Normal, new PopItemAction.OnClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(getActivity(), "选项3", Toast.LENGTH_SHORT).show();
                    }
                }))
                .addItemAction(new PopItemAction("确定", PopItemAction.PopItemStyle.Warning, new PopItemAction.OnClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(PopWindowActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                }))
                .addItemAction(new PopItemAction("取消", PopItemAction.PopItemStyle.Cancel))
                .create();
        popWindow.show();
    }

    public void click2(View view) {
        View customView = View.inflate(this, R.layout.layout_test, null);
        new PopWindow.Builder(this)
                .setStyle(PopWindow.PopWindowStyle.PopUp)
                .addItemAction(new PopItemAction("选项1"))
                .addContentView(customView)
                .addItemAction(new PopItemAction("选项2"))
                .addItemAction(new PopItemAction("确定", PopItemAction.PopItemStyle.Warning))
                .addItemAction(new PopItemAction("取消", PopItemAction.PopItemStyle.Cancel))
                .show();
    }

    public void click3(View view) {
        View customView = View.inflate(this, R.layout.layout_test, null);
        PopWindow popWindow = new PopWindow(PopWindowActivity.this, "标题", null, PopWindow.PopWindowStyle.PopUp);
        popWindow.setIsShowCircleBackground(false);
        popWindow.setIsShowLine(false);
        popWindow.addItemAction(new PopItemAction("取消", PopItemAction.PopItemStyle.Cancel));
        popWindow.addItemAction(new PopItemAction("选项1"));
        popWindow.addContentView(customView);
        popWindow.addItemAction(new PopItemAction("选项2"));
        popWindow.show();
    }

    public void click4(View view) {
        View customView = View.inflate(this, R.layout.layout_test, null);
        new PopWindow.Builder(this)
                .setStyle(PopWindow.PopWindowStyle.PopUp)
                .addItemAction(new PopItemAction("选项"))//注意这里setVIew的优先级最高，及只要执行了setView，其他添加的view都是无效的
                .setView(customView)
                .show();
    }

    public void click5(View view) {
        View customView = View.inflate(this, R.layout.layout_test, null);
        new PopWindow.Builder(this)
                .setStyle(PopWindow.PopWindowStyle.PopUp)
                .setView(customView)
                .setPopWindowMargins(dip2px(20), dip2px(0), dip2px(20), dip2px(0))
                .show();
    }

    public void click6(View view) {
        View customView = View.inflate(this, R.layout.layout_test, null);
        new PopWindow.Builder(this)
                .setStyle(PopWindow.PopWindowStyle.PopDown)
                .setIsShowCircleBackground(false)
                .addItemAction(new PopItemAction("选项1"))
                .addContentView(customView)
                .addItemAction(new PopItemAction("选项2"))
                .addItemAction(new PopItemAction("取消", PopItemAction.PopItemStyle.Cancel, new PopItemAction.OnClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(PopWindowActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                }))
                .setPopWindowMargins(dip2px(10), dip2px(0), dip2px(10), dip2px(0))
                .show(view);
    }

    public void click7(View view) {
        View customView = View.inflate(this, R.layout.layout_test, null);
        new PopWindow.Builder(this)
                .setStyle(PopWindow.PopWindowStyle.PopDown)
                .setView(customView)
                .show(view);
    }

    public void click8(View view) {
        View customView = View.inflate(this, R.layout.layout_test, null);
        new PopWindow.Builder(this)
                .setStyle(PopWindow.PopWindowStyle.PopAlert)
                .setMessage("今天天气不错")
                .setIsShowCircleBackground(false)
//                .setIsShowLine(false)
                .addItemAction(new PopItemAction("选项1"))
                .addContentView(customView)
                .addItemAction(new PopItemAction("选项2"))
                .addItemAction(new PopItemAction("取消", PopItemAction.PopItemStyle.Cancel))
                .setPopWindowMargins(dip2px(30), dip2px(0), dip2px(30), dip2px(0))
                .show();
    }

    public void click9(View view) {
        View customView = View.inflate(this, R.layout.layout_protocol, null);
        TextView tvProtocol = customView.findViewById(R.id.tvProtocol);
        Button refuse = customView.findViewById(R.id.refuse);
        Button agree = customView.findViewById(R.id.agree);
        final SpannableStringBuilder style = new SpannableStringBuilder();
        //设置文字
        style.append(getString(R.string.text_protocol));
        //设置部分文字点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(PopWindowActivity.this, "用户注册条款!", Toast.LENGTH_SHORT).show();
            }
        };
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(PopWindowActivity.this, "服务协议!", Toast.LENGTH_SHORT).show();
            }
        };

        style.setSpan(clickableSpan, 62, 70, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(clickableSpan1, 71, 79, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvProtocol.setMovementMethod(LinkMovementMethod.getInstance());
        tvProtocol.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明
        tvProtocol.setText(style);

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.dismiss();
            }
        });

        refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PopWindowActivity.this, "拒绝!", Toast.LENGTH_SHORT).show();
            }
        });


        popWindow = new PopWindow.Builder(this)
                .setStyle(PopWindow.PopWindowStyle.PopAlert)
                .setView(customView)
                .show();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}

