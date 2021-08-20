package com.coding.qzy.baselibrary.utils.permission;

import android.app.Activity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.util.List;

import static com.coding.qzy.baselibrary.utils.permission.PermissionDispatcher.dispatchPermissionGranted;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/05/15
 * desc   :
 * version: 1.0
 */

public class PermissionRequester {

    private Object source;
    private String[] permissions;
    private int requestCode;

    private PermissionRequester() {

    }

    public static PermissionRequester build() {
        return new PermissionRequester();
    }

    public PermissionRequester attach(Activity source) {
        this.source = source;
        initCounter++;
        return this;
    }

    public PermissionRequester attach(Fragment source) {
        this.source = source;
        initCounter++;
        return this;
    }

    public PermissionRequester permissions(String... permissions) {
        this.permissions = permissions;
        initCounter++;
        return this;
    }

    public PermissionRequester requestCode(int requestCode) {
        this.requestCode = requestCode;
        initCounter++;
        return this;
    }

    int initCounter = 0;
    static final int TOTAL_NEEDED_INIT_AMOUNT = 3;

    public void request() {
        if (initCounter != TOTAL_NEEDED_INIT_AMOUNT) {
            throw new IllegalArgumentException("Dispatcher must be work on #attach,#permissions,#requestCode are invoked.");
        }
        if (!(source instanceof Activity || source instanceof Fragment)) {
            throw new UnSupportSourceTypeException(String.format("%s is not support.", source.getClass().getName()));
        }
        if (!Utils.isOverMarshmallow()) {
            dispatchPermissionGranted(source, requestCode);
            return;
        }

        List<String> deniedPermissions = Utils.findDeniedPermissions(source, permissions);
        if (deniedPermissions.size() > 0) {
            if (source instanceof Fragment) {
                ((Fragment) source).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            } else {
                ActivityCompat.requestPermissions(Utils.toActivity(source), deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            }
        } else {
            dispatchPermissionGranted(source, requestCode);
        }

        initCounter = 0;
    }

}

