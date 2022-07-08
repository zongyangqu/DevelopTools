package com.quzy.coding.util.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * CreateDate:2021/10/28 11:19
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.widget
 * @Description:
 */
public class IconFont extends AppCompatTextView {
    //将字体库改为全局静态变量  减少多控件创建的过程中的内存消耗 pj
    public static Typeface typeface = null;
    public IconFont(Context context) {
        this(context , null) ;
    }

    public IconFont(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initFont() ;
    }

    private void initFont() {
        if (typeface == null)
            typeface = Typeface.createFromAsset(getContext().getAssets(), "font/yhfont.ttf") ;
        setTypeface(typeface);
    }


}
