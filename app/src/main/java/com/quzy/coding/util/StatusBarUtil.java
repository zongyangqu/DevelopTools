package com.quzy.coding.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.quzy.coding.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * CreateDate:2023/6/16 16:57
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description:
 */
public class StatusBarUtil {

    private static boolean isActivityRuning(Activity activity) {
        return !(activity == null || activity.isDestroyed() || activity.isFinishing());
    }

    public static void showDrakStatusBarIcon(Activity activity) {
        if (!isActivityRuning(activity)) return;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return;
        // 虚拟导航栏透明
        Window window = getInitWindow(activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (MIUISetStatusBarLightMode(window, true)) return;//部分小米手机再6.0机型上此方法无效
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            if (FlymeSetStatusBarLightMode(window, true)) return;
            if (MIUISetStatusBarLightMode(window, true)) return;
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.coreui_black_20));
        }
    }


    public static void showLightStatusBarIcon(Activity activity) {
        if (!isActivityRuning(activity)) return;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return;
        Window window = getInitWindow(activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (MIUISetStatusBarLightMode(window, false)) return;
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        } else {
            if (FlymeSetStatusBarLightMode(window, false)) return;
            if (MIUISetStatusBarLightMode(window, false)) return;
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.coreui_black_20));
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static Window getInitWindow(Activity activity) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        return window;
    }


    /**
     * 设置状态栏图标为深色和魅族特定的文字风格，Flyme4.0以上
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    private static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }


    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    private static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) return false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

}
