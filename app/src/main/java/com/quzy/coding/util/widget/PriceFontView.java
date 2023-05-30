package com.quzy.coding.util.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * CreateDate:2023/5/24 15:20
 *
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.widget
 * @Description:
 */
public class PriceFontView extends AppCompatTextView {
    //将字体库改为全局静态变量  减少多控件创建的过程中的内存消耗 pj
    public static Typeface typeface = null;

    public PriceFontView(Context context) {
        super(context);
        initFontStyle();
    }

    public PriceFontView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFontStyle();
    }

    public PriceFontView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFontStyle();
    }

    /**
     * 价格文本需要使用云创字体
     * */
    private void initFontStyle() {
        if (typeface == null)
            typeface = Typeface.createFromAsset(getContext().getAssets(), "font/yhprice.ttf");
        setTypeface(typeface);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }

    public void setNormalText(CharSequence text) {
        setTypeface(Typeface.DEFAULT);
        setText(text);
    }
}
