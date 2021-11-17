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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coding.qzy.baselibrary.utils.BlurTransformation;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.base.BaseResourceActivity;

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


    @BindView(R.id.glideGSImg)
    RelativeLayout glideGSImg;
    @BindView(R.id.glideImg)
    ImageView glideImg;
    @BindView(R.id.customGSImg)
    ImageView customGSImg;
    @BindView(R.id.customImg)
    ImageView customImg;
    @BindView(R.id.cutBitmap)
    LinearLayout cutBitmap;
    @BindView(R.id.cutBitImg)
    ImageView cutBitImg;


    Bitmap cutMap = null;

    private String url = "http://image.yonghuivip.com/image/16323841179726e997445c17af50b073242cefc8f6d09f8df5442.jpg?w=519&h=573";
    @Override
    protected void onViewCreated() {
        Glide.with(this).load(url).into(glideImg);
       // glideGSImg.setBackground();
        Glide.with(this).load(url).apply(RequestOptions.bitmapTransform(new BlurTransformation(this,25,3))).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                // Do something with the Drawable here
                glideGSImg.setBackground(resource);
                cutMap = drawableToBitmap(resource);
                cutBitImg.setImageDrawable(new BitmapDrawable(cutBitmap(cutMap)));
                cutBitmap.setBackground(new BitmapDrawable(cutBitmap(cutMap)));
                //Drawable drawable = new BitmapDrawable(cutMap);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                // 从任何视图中删除onResourceReady中提供的Drawable，并确保不保留对它的引用。
            }
        });
//        Glide.with(this).load(url)
//                .apply(RequestOptions.bitmapTransform(new BlurTransformation(this,25,3)))
//                .into(glideGSImg);
    }



    /**
     * 裁剪图片
     */
    public static Bitmap cutBitmap(Bitmap bm) {
        Bitmap bitmap = null;
        if (bm != null) {
            bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight()/3); //对图片的高度的一半进行裁剪
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

