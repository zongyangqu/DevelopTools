package com.coding.qzy.baselibrary.utils.background.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.coding.qzy.baselibrary.utils.background.BackgroundFactory;


public class BLRelativeLayout extends RelativeLayout {
    public BLRelativeLayout(Context context) {
        super(context);
    }

    public BLRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BLRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        BackgroundFactory.setViewBackground(context, attrs, this);
    }
}
