package com.quzy.coding.ui.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.coding.qzy.baselibrary.utils.background.drawable.DrawableCreator;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.ui.fragment.BlankFragment;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/06/11
 * desc   :
 * version: 1.0
 */


public class BackGroundActivity extends BaseActivity{

    @Override
    protected void onViewCreated() {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, new BlankFragment()).commitAllowingStateLoss();
        View vAnim = findViewById(R.id.v_anim);
//        AnimationDrawable animationDrawable = (AnimationDrawable) vAnim.getBackground();
//        animationDrawable.start();
        Drawable drawable = new DrawableCreator.Builder().setCornersRadius(30)
                .setSolidColor(Color.parseColor("#FFFFFF"))
                .setStrokeColor(Color.parseColor("#FFFFFF"))
                .setStrokeWidth(10)
                .build();
        TextView tvTest1 = findViewById(R.id.tvTest1);
        tvTest1.setClickable(true);
        ColorStateList colors = new DrawableCreator.Builder().setPressedTextColor(Color.RED).setUnPressedTextColor(Color.BLUE).buildTextColor();
        tvTest1.setTextColor(colors);


        Button btnTest2 = findViewById(R.id.btnTest2);
        Drawable drawable2 = new DrawableCreator.Builder().setCornersRadius(dip2px(20))
                .setGradientAngle(0).setGradientColor(Color.parseColor("#63B8FF"), Color.parseColor("#4F94CD")).build();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            btnTest2.setBackground(drawable2);
        }else {
            btnTest2.setBackgroundDrawable(drawable2);
        }

        Button btnTest1 = findViewById(R.id.btnTest1);
        Drawable drawableTest = new DrawableCreator.Builder().setCornersRadius(dip2px(20),0,dip2px(20),0)
                .setSolidColor(Color.parseColor("#63B8FF")).build();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            btnTest1.setBackground(drawableTest);
        }else {
            btnTest1.setBackgroundDrawable(drawableTest);
        }


        Button btnTest3 = findViewById(R.id.btnTest3);
        Drawable drawable3 = new DrawableCreator.Builder().setCornersRadius(dip2px(20))
                .setRipple(true, Color.parseColor("#71C671"))
                .setSolidColor(Color.parseColor("#7CFC00"))
                .setStrokeColor(Color.parseColor("#8c6822"))
                .setStrokeWidth(dip2px(2))
                .build();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            btnTest3.setBackground(drawable3);
        }else {
            btnTest3.setBackgroundDrawable(drawable3);
        }


        TextView tvTest4 = findViewById(R.id.tvTest4);
        Drawable drawable4 = new DrawableCreator.Builder().setCornersRadius(dip2px(20))
                .setPressedDrawable(ContextCompat.getDrawable(this, R.drawable.circle_like_pressed))
                .setUnPressedDrawable(ContextCompat.getDrawable(this, R.drawable.circle_like_normal))
                .build();
        tvTest4.setClickable(true);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            tvTest4.setBackground(drawable4);
        }else {
            tvTest4.setBackgroundDrawable(drawable4);
        }


        final Button btnLike = findViewById(R.id.btn_like);
        btnLike.setOnClickListener(new View.OnClickListener() {
            int i = 1;

            @Override
            public void onClick(View v) {
                btnLike.setText(String.format("点赞+%d", i++));
            }
        });


        final Button btnLike2 = findViewById(R.id.btn_like2);
        btnLike2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnLike2.isSelected()){
                    btnLike2.setText("未点赞");
                }else {
                    btnLike2.setText("已点赞");
                }
                btnLike2.setSelected(!btnLike2.isSelected());
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_background;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    public int dip2px(float dipValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }
}
