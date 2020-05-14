package com.quzy.coding.ui.activity;

import android.widget.TextView;

import com.coding.qzy.baselibrary.utils.text.ColorPhraseUtils;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;

import butterknife.BindView;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/06/11
 * desc   :
 * version: 1.0
 */


public class TextSampleActivity extends BaseActivity {

    @BindView(R.id.textColor)
    TextView textColor;

    @Override
    protected void onViewCreated() {
        CharSequence chars = ColorPhraseUtils.from("I'm {Chinese},I love {China}").
                withSeparator("{}").
                innerColor(0xFFE6454A).
                outerColor(0xFF666666).
                format();
        textColor.setText(chars);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_text_sample;
    }
}
