package com.quzy.coding.ui.activity;

import android.view.View;
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
    @BindView(R.id.textAutoSize1)
    TextView textAutoSize1;
    @BindView(R.id.text2)
    TextView text2;

    @Override
    protected void onViewCreated() {
        CharSequence chars = ColorPhraseUtils.from("I'm {Chinese},I love {China}").
                withSeparator("{}").
                innerColor(0xFFE6454A).
                outerColor(0xFF666666).
                format();
        textColor.setText(chars);

        textAutoSize1.setText("我能在一行里显示");
        text2.setText("我不能在一行里显示");
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_text_sample;
    }
}
