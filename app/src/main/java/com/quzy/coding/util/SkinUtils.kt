package com.quzy.coding.util

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import java.io.File

/**
 * CreateDate:2023/6/16 17:44
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description:
 */
object SkinUtils {
    var resourceInfoBean: ResourceInfoBean? = null

    private const val MAIN = "main_"
    private const val ACTIVITY = "activity_"


    /**
     * @method getColor
     * @description 根据colorId读取颜色
     * @date: 2020/4/26 5:17 PM
     * @author: ZhaoXuan.Zeng
     * @param [context, colorId]
     * @return
     */
    fun getColor(context: Context, colorId: Int): Int {
        val resources = resourceInfoBean?.resources
        return if (resources == null) {
            ContextCompat.getColor(context.applicationContext, colorId)
        } else {
            val color = ResourceCollectUtils.getColorFromThemePkg(context.applicationContext.resources.getResourceEntryName(colorId), resources, resourceInfoBean?.pkgName)
            if (color == 0) {
                ContextCompat.getColor(context.applicationContext, colorId)
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    resources.getColor(color, null)
                } else {
                    resources.getColor(color)
                }
            }
        }
    }

    /**
     * @method getColorList
     * @description 根据colorListId读取颜色
     * @date: 2020/4/26 5:17 PM
     * @author: ZhaoXuan.Zeng
     * @param [context, colorId]
     * @return
     */
    fun getColorList(context: Context, colorListId: Int): ColorStateList? {
        val resources = resourceInfoBean?.resources
        return if (resources == null) {
            ContextCompat.getColorStateList(context.applicationContext, colorListId)
        } else {
            val color = ResourceCollectUtils.getColorFromThemePkg(context.applicationContext.resources.getResourceEntryName(colorListId), resources, resourceInfoBean?.pkgName)
            if (color == 0) {
                ContextCompat.getColorStateList(context.applicationContext, colorListId)
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    resources.getColorStateList(color, null)
                } else {
                    resources.getColorStateList(color)
                }
            }
        }
    }

    /**
     * @method getDrawable
     * @description 根据drawableId获取对应drawable
     * @date: 2020/4/26 5:29 PM
     * @author: ZhaoXuan.Zeng
     * @param [context, drawableId]
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getDrawable(context: Context, drawableId: Int): Drawable {
        val resources = resourceInfoBean?.resources
        return if (resources == null) {
            context.applicationContext.resources.getDrawable(drawableId, null)
        } else {
            val drawable = ResourceCollectUtils.getDrawableIdFromThemePkg(context.applicationContext.resources.getResourceEntryName(drawableId), resources, resourceInfoBean?.pkgName)
            if (drawable == 0) {
                context.applicationContext.resources.getDrawable(drawableId, null)
            } else {
                resources.getDrawable(drawable, null)
            }
        }
    }

    /**
     * @method getString
     * @description 根据stringId获取对应string
     * @date: 2020/6/8 10:05 AM
     * @author: ZhaoXuan.Zeng
     * @param [context, strId]
     * @return
     */
    fun getString(context: Context, strId: Int): String {
        val resources = resourceInfoBean?.resources
        return if (resources == null) {
            context.applicationContext.resources.getString(strId)
        } else {
            val str = ResourceCollectUtils.getStringIdFromThemePkg(context.applicationContext.resources.getResourceEntryName(strId), resources, resourceInfoBean?.pkgName)
            if (str == 0) {
                context.applicationContext.resources.getString(strId)
            } else {
                resources.getString(str)
            }
        }
    }

    /**
     * @method changeImageColor
     * @description 用指定颜色给指定图片着色
     * @date: 2020/6/15 9:56 AM
     * @author: ZhaoXuan.Zeng
     * @param [context, imgRes, colorId]
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getDrawableByChangeImageColor(context: Context, imgRes: Int, colorId: Int): Drawable {
        val orig = context.resources.getDrawable(imgRes, null)
        val wrap = DrawableCompat.wrap(orig).mutate()
        DrawableCompat.setTint(wrap, getColor(context, colorId))
        return wrap
    }



    /**
     * @method checkMainFileExists
     * @description 判断本地主皮肤包文件是否存在，如果不存在则新建，返回null就代表已存在不用执行下载逻辑
     * @date: 2020/6/8 10:35 AM
     * @author: ZhaoXuan.Zeng
     * @param [context, fileName]
     * @return
     */
    private fun checkMainFileExists(context: Context?, fileName: String): File? {
        val mainFile = File(getMainFileDir(context), fileName)
        return if (!mainFile.exists()) {
            mainFile.parentFile?.mkdirs()
            mainFile
        } else {
            null
        }
    }


    /**
     * @method getMainFileDir
     * @description 主皮肤包本地文件父路径
     * @date: 2020/6/8 10:36 AM
     * @author: ZhaoXuan.Zeng
     * @param [context]
     * @return
     */
    fun getMainFileDir(context: Context?): String {
        return "${context?.applicationContext?.getExternalFilesDir(null)}${File.separator}skin${File.separator}main"
    }

    /**
     * @method getActivityFileDir
     * @description 活动皮肤病本地文件父路径
     * @date: 2020/6/8 10:48 AM
     * @author: ZhaoXuan.Zeng
     * @param [context]
     * @return
     */
    fun getActivityFileDir(context: Context?): String {
        return "${context?.applicationContext?.getExternalFilesDir(null)}${File.separator}skin${File.separator}activity"
    }

}