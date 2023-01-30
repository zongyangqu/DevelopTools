package com.quzy.coding.util.viewreport

import android.content.res.Resources
import android.os.Looper
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apkfuns.logutils.LogUtils
import com.google.gson.Gson
import com.quzy.coding.base.BaseApplication
import com.quzy.coding.bean.ViewReportConfigBean
import com.quzy.coding.util.Constants
import com.quzy.coding.util.FileUtils
import com.quzy.coding.util.extend.isShowing
import java.util.*


/**
 * CreateDate:2023/1/4 16:53
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.viewreport
 * @Description: 兜底上报util
 */
object ReportViewUtils {

    var defaultCount: Int = 3
    var reportConfig: ViewReportConfigBean? = null

    val TAG = ReportViewUtils::class.java.simpleName


    fun getRepostConfig(): ViewReportConfigBean? {
        if (reportConfig != null)
            return reportConfig
        val content = FileUtils.readJsonFile(BaseApplication.getContext(), "viewreport")
        val gson = Gson()
        reportConfig = gson.fromJson(
            content,
            ViewReportConfigBean::class.java
        )
        reportConfig?.customPagesMap =
            reportConfig?.customPages?.associateBy { it?.viewClass ?: "" }
        return reportConfig
    }

    /**
     * 判断上报是否开启
     */
    fun getRepostConfigIsOpen(): Boolean {
        if (getRepostConfig() == null)
            return false
        return getRepostConfig()?.status == 1
    }

    fun reportPageIsMatch(activity: Any?): Boolean {
        if (activity == null || getRepostConfig() == null)
            return false
        return getRepostConfig()?.customPagesMap?.contains(activity.javaClass.name) ?: false
    }


    /**
     * 判断兜底页是否展示，如果兜底显示则不进行额外上报
     */
    fun blankViewIsShow(key: String?, view : View?, id :String?) :Boolean{
        if(reportConfig == null)
            return false
        return reportConfig?.customPagesMap?.get(key)?.placeHolderViews?.contains(id) == true && view?.isShowing() == true
    }

    /**
     * 获取页面最小元素数量
     */
    fun getDefaultViewCount():Int{
        if(reportConfig == null)
            return defaultCount
        return getRepostConfig()?.minViewCount?: defaultCount
    }

    /**
     * 获取当前页面配置的核心视图ID集合
     */
    fun getCoretViewIds(key :String?): List<String>?{
        if(reportConfig == null)
            return null
        return reportConfig?.customPagesMap?.get(key)?.targetViews
    }


    fun getResourceNameBuyId(id :Int): String{
        try {
            return BaseApplication.getContext().resources.getResourceEntryName(id)
        }catch (e: Resources.NotFoundException) {
            e.printStackTrace()
        }
        return ""
    }

    fun traverseViewGroup(activity: Any?, view: View?): Int {
        var viewCount = 0
        if (null == view) {
            return 0
        }

        LogUtils.tag(Constants.LOG_TAG).d("-----------------------当前线程是主线程："+ isMainThread())

        try {
            var coreIds: List<String>? = getCoretViewIds(activity?.javaClass?.name)
            //val viewList = ArrayList<View>()
            var viewMap = mutableMapOf<String,View>()
            if (view is ViewGroup) {
                val linkedList = LinkedList<ViewGroup>()
                linkedList.add(view)
                while (!linkedList.isEmpty()) {
                    //removeFirst()删除第一个元素，并返回该元素
                    val current = linkedList.removeFirst()
                    viewCount++
                    //遍历linkedList中第一个viewGroup中的子view
                    for (i in 0 until current.childCount) {
                        if (current.getChildAt(i) is ViewGroup) {
                            linkedList.addLast(current.getChildAt(i) as ViewGroup)
                        } else {
                            viewCount++
                        }
                        var id = current.getChildAt(i).id
                        if (id > 1) {
                            var viewId = getResourceNameBuyId(id)
                            //YhStoreApplication.getContext().resources.getResourceEntryName(id)
                            if (!TextUtils.isEmpty(viewId)){
                                if (blankViewIsShow(
                                        activity?.javaClass?.name,
                                        current.getChildAt(i),
                                        viewId
                                    )
                                ) {
                                    return viewCount
                                }
                                viewMap.put(viewId,current.getChildAt(i))

                                LogUtils.tag(Constants.LOG_TAG).d(
                                    activity?.javaClass?.name + "-->" + current.getChildAt(i).javaClass.simpleName
                                            + ">>>>>>>id=" + viewId + "=== is show==" + current.getChildAt(
                                        i
                                    ).isShowing()
                                )
                            }
                        }
                    }
                }
            } else {
                var id = view.id
                if (id > 1) {
                    var viewId = getResourceNameBuyId(id)
                    //YhStoreApplication.getContext().resources.getResourceEntryName(id)
                    if (!TextUtils.isEmpty(viewId)){
                        if (blankViewIsShow(activity?.javaClass?.name, view, viewId)) {
                            return viewCount
                        }
                        LogUtils.tag(Constants.LOG_TAG).d(
                            activity?.javaClass?.name + "-->" + view.javaClass.simpleName
                                    + ">>>>>>>id=" + viewId + "=== is show==" + view.isShowing()
                        )
                        viewMap.put(viewId,view)
                    }
                    //var viewId = YhStoreApplication.getContext().resources.getResourceEntryName(id)

                }
                viewCount++
            }
            LogUtils.tag(Constants.LOG_TAG).d( "viewCount===$viewCount")

            // 核心view没有在viewTree中或者没有显示则进行异常上报  后续逻辑不在执行
            coreIds?.forEachIndexed { index, viewId ->
                LogUtils.tag(Constants.LOG_TAG).d("核心ViewId=====================$viewId")
                if (viewMap[viewId] == null || viewMap[viewId]?.isShowing() == false || viewGroupIsEmpty(viewMap[viewId])) {
                    LogUtils.tag(Constants.LOG_TAG).d(activity?.javaClass?.name+"我上报了=====================")
                    //做一些事情
                    return viewCount
                }
                if (viewMap[viewId] is RecyclerView) {
                    if((viewMap[viewId] as? RecyclerView)?.childCount?:1 < 1) {
                        (viewMap[viewId] as? RecyclerView)?.findViewHolderForAdapterPosition(1)
                        LogUtils.tag(Constants.LOG_TAG).d(activity?.javaClass?.name+"RecyclerView 为空 我上报了=====================")
                        //做一些事情
                        return viewCount
                    }
                }
            }

            if (viewCount < getDefaultViewCount()) {
                //做一些事情
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return viewCount
    }

    fun isMainThread(): Boolean {
        return Looper.getMainLooper().thread === Thread.currentThread()
    }

    fun viewGroupIsEmpty(view :View?) :Boolean{
        if (view is RecyclerView) {
            if((view as? RecyclerView)?.childCount?:1 < 1) {
                LogUtils.tag(Constants.LOG_TAG).d("RecyclerView 为空 我上报了=====================")
                return true
            }
        }
        return false
    }

}