package com.coding.qzy.baselibrary.utils.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.coding.qzy.baselibrary.utils.permission.annotation.PermissionDenied;
import com.coding.qzy.baselibrary.utils.permission.annotation.PermissionGranted;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2019/05/15
 * desc   :
 * version: 1.0
 */

public class PermissionDispatcher {

    static void dispatchPermissionGranted(Object source, int requestCode) {
        Method grantedMethod = Utils.findMethod(source.getClass(), PermissionGranted.class, requestCode);
        invoke(source, grantedMethod);
    }

    static void dispatchPermissionDenied(Object source, int requestCode, Object... arguments) {
        Method deniedMethod = Utils.findMethod(source.getClass(), PermissionDenied.class, requestCode);
        invoke(source, deniedMethod, arguments);
    }

    private static void invoke(Object receiver, Method method, Object... arguments) {
        if (method != null) {
            method.setAccessible(true);
        }
        try {
            method.invoke(receiver, arguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onRequestPermissionsResult(Activity source,
                                                  int requestCode,
                                                  @NonNull String[] permissions,
                                                  @NonNull int[] grantResults) {
        parseRequestPermissionResult(source, requestCode, permissions, grantResults);
    }

    public static void onRequestPermissionsResult(Fragment source,
                                                  int requestCode,
                                                  @NonNull String[] permissions,
                                                  @NonNull int[] grantResults) {
        parseRequestPermissionResult(source, requestCode, permissions, grantResults);
    }

    private static void parseRequestPermissionResult(Object source,
                                                     int requestCode,
                                                     @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        List<String> deniedPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[i]);
            }
        }
        if (deniedPermissions.size() > 0) {
            dispatchPermissionDenied(source, requestCode, PermissionChecker.getDenialPermissionDescribeInfo(deniedPermissions));
        } else {
            dispatchPermissionGranted(source, requestCode);
        }
    }
}
