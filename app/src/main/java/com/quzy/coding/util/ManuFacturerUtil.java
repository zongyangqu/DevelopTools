package com.quzy.coding.util;

import android.os.Build;

/**
 * CreateDate:2023/6/16 16:32
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description:
 */
public class ManuFacturerUtil {

    /****************************************MIUI*********************************************/
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    public static boolean isMIUI() {
        if ("Xiaomi".equalsIgnoreCase(Build.MANUFACTURER)) {
            return true ;
        }
        return false ;
    }
    /****************************************MIUI*********************************************/

    /****************************************ZTE**********************************************/
    public static boolean isZTE() {
        if ("ZTE".equalsIgnoreCase(Build.MANUFACTURER)) {
            return true ;
        }
        return false ;
    }
    /****************************************ZTE**********************************************/

}
