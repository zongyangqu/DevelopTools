package com.quzy.coding.ui.activity;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioFormat;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.quzy.coding.BuildConfig;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;
import com.quzy.coding.base.BaseApplication;
import com.quzy.coding.util.Singleton;
import com.quzy.coding.util.TestManager;
import com.quzy.coding.util.UZXing;
import com.quzy.coding.util.view.AudioView;
import com.zlw.main.recorderlib.RecordManager;
import com.zlw.main.recorderlib.recorder.RecordConfig;
import com.zlw.main.recorderlib.recorder.RecordHelper;
import com.zlw.main.recorderlib.recorder.listener.RecordFftDataListener;
import com.zlw.main.recorderlib.recorder.listener.RecordResultListener;
import com.zlw.main.recorderlib.recorder.listener.RecordSoundSizeListener;
import com.zlw.main.recorderlib.recorder.listener.RecordStateListener;

import java.io.File;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/11/14
 * desc   :
 * version: 1.0
 */


public class QRCodeActivity extends BaseActivity {


    @BindView(R.id.ivQrcode)
    ImageView ivQrcode;

    private static ReferenceQueue<Object> rq = new ReferenceQueue<Object>();
    @Override
    protected void onViewCreated() {
        Bitmap qrCode = UZXing.createQRImage("https://www.baidu.com/",200,200, Color.parseColor("#ffffffff"),Color.parseColor("#333333"));
        ivQrcode.setImageBitmap(qrCode);
        TestManager manager = TestManager.getInstance(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layout_qrcode;
    }


}

