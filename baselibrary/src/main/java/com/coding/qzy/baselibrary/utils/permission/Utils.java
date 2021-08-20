package com.coding.qzy.baselibrary.utils.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.coding.qzy.baselibrary.utils.permission.annotation.PermissionDenied;
import com.coding.qzy.baselibrary.utils.permission.annotation.PermissionGranted;

import java.lang.annotation.Annotation;
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

public class Utils {

    static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    static List<String> findDeniedPermissions(Object source, String... permissions) {
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(toActivity(source), permission) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions;
    }

    static Activity toActivity(Object source) {
        if (source instanceof Activity) {
            return ((Activity) source);
        } else if (source instanceof Fragment) {
            return ((Fragment) source).getActivity();
        }
        return null;
    }

    static <A extends Annotation> Method findMethod(Class clazz, Class<A> annotationCls, int requestCode) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getAnnotation(annotationCls) != null) {
                if (isAnnotationWith(method, annotationCls, requestCode)) {
                    return method;
                }
            }
        }
        return null;
    }

    private static final boolean isSpecifiedMethod(Method method) {
        if (method == null) {
            return false;
        }
        Annotation annotations[] = method.getAnnotations();
        if (annotations == null || annotations.length == 0) {
            return false;
        }
        for (Annotation annotation : annotations) {
            if (annotation.getClass() == PermissionGranted.class || annotation.getClass() == PermissionDenied.class) {
                return true;
            }
        }
        return false;
    }

    static boolean isAnnotationWith(Method method, Class annotationCls, int requestCode) {
        if (annotationCls == PermissionGranted.class) {
            return method.getAnnotation(PermissionGranted.class).requestCode() == requestCode;
        } else if (annotationCls == PermissionDenied.class) {
            return method.getAnnotation(PermissionDenied.class).requestCode() == requestCode;
        }
        return false;
    }
}
