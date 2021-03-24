package com.quzy.coding.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.coding.qzy.baselibrary.zxlib.CaptureActivity;
import com.coding.qzy.baselibrary.zxlib.util.QrCodeGenerator;
import com.quzy.coding.R;
import com.quzy.coding.base.BaseActivity;

import com.quzy.coding.util.Constants;
import java.lang.ref.ReferenceQueue;

import butterknife.BindView;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/11/14
 * desc   :
 * version: 1.0
 */


public class QRCodeActivity extends BaseActivity implements View.OnClickListener{


    @BindView(R.id.btn_qrcode)
    Button btnQrCode; // 扫码
    @BindView(R.id.txt_result)
    TextView tvResult; // 结果
    @BindView(R.id.btn_generate)
    Button btnGenerate; // 生成二维码
    @BindView(R.id.et_content)
    EditText etContent; // 待生成内容
    @BindView(R.id.img_qrcode)
    ImageView imgQrcode; // 二维码图片

    private static ReferenceQueue<Object> rq = new ReferenceQueue<Object>();
    @Override
    protected void onViewCreated() {
//        Bitmap qrCode = UZXing.createQRImage("https://www.baidu.com/",200,200, Color.parseColor("#ffffffff"),Color.parseColor("#333333"));
//        ivQrcode.setImageBitmap(qrCode);
//        TestManager manager = TestManager.getInstance(this);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layout_qrcode;
    }


    private void initView() {
        btnQrCode.setOnClickListener(this);
        btnGenerate.setOnClickListener(this);
    }

    // 开始扫码
    private void startQrCode() {
        // 申请相机权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .CAMERA)) {
                Toast.makeText(this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(QRCodeActivity.this, new String[]{Manifest.permission.CAMERA}, Constants.REQ_PERM_CAMERA);
            return;
        }
        // 申请文件读写权限（部分朋友遇到相册选图需要读写权限的情况，这里一并写一下）
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请至权限中心打开本应用的文件读写权限", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(QRCodeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.REQ_PERM_EXTERNAL_STORAGE);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(QRCodeActivity.this, CaptureActivity.class);
        startActivityForResult(intent, Constants.REQ_QR_CODE);
    }

    /**
     * 生成二维码
     */
    private void generateQrCode() {
        if (etContent.getText().toString().equals("")) {
            Toast.makeText(this, "请输入二维码内容", Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap bitmap = QrCodeGenerator.getQrCodeImage(etContent.getText().toString(), imgQrcode.getWidth(), imgQrcode.getHeight());
        if (bitmap == null) {
            Toast.makeText(this, "生成二维码出错", Toast.LENGTH_SHORT).show();
            imgQrcode.setImageBitmap(null);
        } else {
            imgQrcode.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_qrcode:
                startQrCode();
                break;
            case R.id.btn_generate:
                generateQrCode();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == Constants.REQ_QR_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(Constants.INTENT_EXTRA_KEY_QR_SCAN);
            //将扫描出的信息显示出来
            tvResult.setText(scanResult);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constants.REQ_PERM_CAMERA:
                // 摄像头权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(QRCodeActivity.this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
                }
                break;
            case Constants.REQ_PERM_EXTERNAL_STORAGE:
                // 文件读写权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(QRCodeActivity.this, "请至权限中心打开本应用的文件读写权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


}

