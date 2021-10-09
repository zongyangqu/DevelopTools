package com.coding.qzy.baselibrary.utils.background

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity

/**
 * minSdkVersion最小为14，建议minSdkVersion >= 16
 * 如果minSdkVersion < 16:bl_gradient_angle, bl_gradient_startColor, bl_gradient_centerColor, bl_gradient_endColor会失效，其他正常
 *
 * Created by xiaoqi on 2018/9/9
 */
object BackgroundLibrary {
    fun inject(context: Context?): LayoutInflater {
        val inflater: LayoutInflater
        inflater = if (context is Activity) {
            context.layoutInflater
        } else {
            LayoutInflater.from(context)
        }
        val factory = BackgroundFactory()
        if (context is AppCompatActivity) {
            val delegate = context.delegate
            factory.setInterceptFactory { name, context, attrs -> delegate.createView(null, name, context, attrs) }
        }
        inflater.factory2 = factory
        return inflater
    }

    /**
     * used for activity which has addFactory
     * 如果因为其他库已经设置了factory，可以使用该方法去进行inject，在其他库的setFactory后面调用即可
     * @param context
     */
    fun inject2(context: Context?): LayoutInflater {
        val inflater: LayoutInflater
        inflater = if (context is Activity) {
            context.layoutInflater
        } else {
            LayoutInflater.from(context)
        }
        try {
            val field = LayoutInflater::class.java.getDeclaredField("mFactorySet")
            field.isAccessible = true
            field.setBoolean(inflater, false)
            val factory = BackgroundFactory()
            if (inflater.factory2 != null) {
                factory.setInterceptFactory2(inflater.factory2)
            } else if (inflater.factory != null) {
                factory.setInterceptFactory(inflater.factory)
            }
            inflater.factory2 = factory
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        return inflater
    }
}