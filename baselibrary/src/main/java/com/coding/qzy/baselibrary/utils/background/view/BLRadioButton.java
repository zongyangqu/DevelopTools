package com.coding.qzy.baselibrary.utils.background.view;

import android.content.Context;
import androidx.appcompat.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import com.coding.qzy.baselibrary.utils.background.BackgroundFactory;

public class BLRadioButton extends AppCompatRadioButton {
    public BLRadioButton(Context context) {
        super(context);
    }

    public BLRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BLRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        BackgroundFactory.setViewBackground(context, attrs, this);
    }
}
