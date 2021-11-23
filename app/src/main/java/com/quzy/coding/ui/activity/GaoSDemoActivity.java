package com.quzy.coding.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.coding.qzy.baselibrary.utils.BlurTransformation;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.base.BaseResourceActivity;
import com.quzy.coding.util.BlurUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/11/12
 * desc   : 高斯
 * version: 1.0
 */


public class GaoSDemoActivity extends BaseActivity {


    @BindView(R.id.glideGSBg)
    RelativeLayout glideGSBg;
    @BindView(R.id.glideImg)
    ImageView glideImg;
    @BindView(R.id.normalGSBg)
    RelativeLayout normalGSBg;
    @BindView(R.id.normalImg)
    ImageView normalImg;
    @BindView(R.id.cutBg)
    LinearLayout cutBg;


    private String url = "http://image.yonghuivip.com/image/1632383959245fda6a13c9932fa13adc0dda369abeb23d4cabf8c.jpg?w=519&h=573";
    private String url1 = "http://image.yonghuivip.com/image/16366872817568e03e10cc57e562c11385ec07dbbee71566b1f22.jpg";

    @Override
    protected void onViewCreated() {


        Glide.with(this).asBitmap()
                .load(url)
                //需要添加 .asBitmap() 方法处理,其他格式的图片不需要添加
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        StringBuilder sb  = new StringBuilder();
                        normalImg.setImageDrawable(new BitmapDrawable(resource));
                        Bitmap newBitMap1 = BlurUtil.blurBitmap(GaoSDemoActivity.this,resource,20f,0.3f,sb);
                        normalGSBg.setBackground(new BitmapDrawable(newBitMap1));
                        cutBg.setBackground(new BitmapDrawable(cutBitmap(newBitMap1)));
//                        cutBitLayout.setBackground(new BitmapDrawable(resource));
                    }
                });

       // glideGSImg.setBackground();
        Glide.with(this).load(url).into(glideImg);
        Glide.with(this).load(url).apply(RequestOptions.bitmapTransform(new BlurTransformation(this,25,3))).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                glideGSBg.setBackground(resource);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                // 从任何视图中删除onResourceReady中提供的Drawable，并确保不保留对它的引用。
            }
        });
    }





    /**
     * 裁剪图片
     */
    public static Bitmap cutBitmap(Bitmap bm) {
        Bitmap bitmap = null;
        if (bm != null) {
            //从bitmap的0，0位置开始  对bitmap裁剪一个宽度是原bitmap、高度是原bitmap一半的新图
            bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight()/3);
        }
        return bitmap;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gao_si;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }


    public static final Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap( drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}

