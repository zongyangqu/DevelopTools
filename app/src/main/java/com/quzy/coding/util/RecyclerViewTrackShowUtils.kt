package com.quzy.coding.util

import android.graphics.Rect
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import java.util.concurrent.Executors

/**
 * CreateDate:2022/7/8 15:58
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description: 安卓RecyclerView曝光量统计工具类
 * 因为不能干涉到别的页面，所以不能采用单例，只能每个页面保持一个实例
 */
class RecyclerViewTrackShowUtils {

    private val mHandler: Handler = Handler()

    private val executorService = Executors.newSingleThreadExecutor()

    private var mLastRange: IntArray? = null

    private var onExposureListener: OnExposureListener? = null

    // 进入可见列表  但是不符合曝光条件的item 只保留上一次的
    var noExpoList: ArrayList<Int> = ArrayList()

    /**
     * RecyclerView曝光回调
     */
    interface OnExposureListener {
        fun onExposure(position: Int, child: View)
    }

    /**
     * 是否开启曝光,内部使用
     */
    private var enable: Boolean = true

    private var mOnRecyclerOnTouchListener: OnRecyclerOnTouchListener? = null

    fun setOnRecyclerOnTouchListener(mOnRecyclerOnTouchListener: OnRecyclerOnTouchListener) {
        this.mOnRecyclerOnTouchListener = mOnRecyclerOnTouchListener
    }

    open interface OnRecyclerOnTouchListener {
        fun onTouch(v: View?, event: MotionEvent?)
    }

    /**
     * 统计RecyclerView里当前屏幕可见子view的曝光量
     *
     * @param recyclerView
     * @param isNotifyOrResume 当前是否是notifyDataSetChanged及其他notify操作，或者重新resume
     * @param mOnExposureListener
     */
    fun recordViewShowCount(
        recyclerView: RecyclerView?,
        isNotifyOrResume: Boolean,
        mOnExposureListener: OnExposureListener?
    ) {
        if (recyclerView == null || recyclerView.visibility != View.VISIBLE) {
            return
        }
        this.onExposureListener = mOnExposureListener
        if (this.onExposureListener == null) return
        recyclerView.post {
            // 检测RecyclerView的滚动事件
            if (isNotifyOrResume) { // 全量曝光
                mHandler.postDelayed(
                    Runnable {
                        enable = false
                        mLastRange = null
                        executeScrolled(recyclerView)
                    },
                    100
                )
            } else {
                enable = true
            }
            recyclerView.setOnTouchListener { v, event ->
                if (event?.action == MotionEvent.ACTION_DOWN || event?.action == MotionEvent.ACTION_MOVE) {
                    enable = true
                    mOnRecyclerOnTouchListener?.onTouch(v, event)
                }
                false
            }
            recyclerView.setOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (onExposureListener == null) return
                    if (!enable) return
                    executeScrolled(recyclerView)
                }
            })
        }
    }

    /**
     * 关联页面切记需要释放,退出页面重新计算曝光
     * 防止影响其他页面
     */
    fun onDestory() {
        mLastRange = null
        onExposureListener = null
    }

    /**
     * 使用线程池队列执行,将埋点统计操作放在线程中
     */
    fun executeScrolled(recyclerView: RecyclerView?) {
        try {
            getVisibleViews(recyclerView)
        } catch (e: Exception) {
        }
    }

    /**
     * 获取当前屏幕上可见的view
     *
     * @param reView
     */
    private fun getVisibleViews(reView: RecyclerView?) {
        if (reView == null ||
            reView.visibility != View.VISIBLE ||
            !reView.isShown ||
            !reView.getGlobalVisibleRect(Rect())
        ) {
            return
        }
        // 保险起见，为了不让统计影响正常业务，这里做下try-catch
        try {
            // 当前屏幕完整可见的起始位置，range[0]，range[1]
            var range: IntArray = IntArray(2)
            val manager = reView.layoutManager
            if (manager is LinearLayoutManager) {
                range = findRangeLinear(reView, manager)
            } else if (manager is GridLayoutManager) {
                range = findRangeGrid(reView, manager)
            } else if (manager is StaggeredGridLayoutManager) {
                range = findRangeStaggeredGrid(reView, manager)
            }
            if (range.size < 2) {
                return
            }

            /**
             * 当前range和mLastRange比较，取出差异值，就是变化的position
             */
            var integerList: MutableList<Int>? = null

            // 当前屏幕只有一个item完全展示时也需要执行曝光，即对于mLastRange?.get(0) == mLastRange?.get(1)也需要去重
            if (mLastRange != null && (mLastRange?.size ?: 0) >= 2) {
                // && mLastRange?.get(0) != mLastRange?.get(1)
                integerList = ArrayList()
                for (j in (mLastRange?.get(0) ?: 0)..(mLastRange?.get(1) ?: 0)) {
                    if (!noExpoList.contains(j))
                        integerList.add(j)
                }
            }
            noExpoList.clear()
            for (i in range[0]..range[1]) {
                val isNestedBottomViewHolder = isNestedBottomViewHolder(reView.findViewHolderForAdapterPosition(i))
                if (integerList != null && integerList.isNotEmpty()) {
                    // 不包含时，表示是新出现的或者重新出现的position，统计
                    if (!integerList.contains(i)) {
                        val view = manager?.findViewByPosition(i)
                        recordViewCount(i, view, isNestedBottomViewHolder)
                    }
                } else { // mLastRange为null时，表示是第一次进来展示，全统计
                    val view = manager?.findViewByPosition(i)
                    recordViewCount(i, view, isNestedBottomViewHolder)
                }
            }
            /**
             * range赋值给mLastRange，用于下一次比对差异
             */
            mLastRange = range
        } catch (e: Exception) {
//            Log.e("RecyclerViewShowUtils", "RecyclerView曝光统计异常 getVisibleViews：$message")
        }
    }

    // 获取view绑定的数据
    private fun recordViewCount(position: Int, view: View?, isNestedBottomViewHolder: Boolean) {
        if (view == null ||
            view.visibility != View.VISIBLE ||
            !view.isShown ||
            !view.getGlobalVisibleRect(Rect())
        ) {
            noExpoList.add(position)
            return
        }

        val rect = Rect()
        val cover = view.getGlobalVisibleRect(rect)

        if (!cover) {
            noExpoList.add(position)
            return
        }

        /**
         * 如果是二级悬浮吸顶tab，且tab已经显示全部可见范围
         */
        if (isNestedBottomViewHolder) {
            if (rect.height() < 60f.dpOfInt) {
                noExpoList.add(position)
                return
            }
        } else {
            if (rect.height() < view.measuredHeight / 2) {
                noExpoList.add(position)
                return
            }
        }
        onExposureListener?.onExposure(position, view)
    }

    private fun findRangeLinear(recyclerView: RecyclerView, manager: LinearLayoutManager): IntArray {
        val range = IntArray(2)
        range[0] = manager.findFirstCompletelyVisibleItemPosition()
        // 二级悬浮吸顶tab+viewpager组件因为只需要统计tab栏的曝光，所以不能全部可见时才触发，应该时部分可见时就触发，然后判断view的可见区域是否大于tab栏的高度
        val lastVisiblePosition = manager.findLastVisibleItemPosition()
        if (isNestedBottomViewHolder(recyclerView.findViewHolderForAdapterPosition(lastVisiblePosition))) {
            range[1] = lastVisiblePosition
        } else {
            range[1] = manager.findLastCompletelyVisibleItemPosition()
        }
        return range
    }

    private fun findRangeGrid(recyclerView: RecyclerView, manager: GridLayoutManager): IntArray {
        val range = IntArray(2)
        // 二级悬浮吸顶tab+viewpager组件因为只需要统计tab栏的曝光，所以不能全部可见时才触发，应该时部分可见时就触发，然后判断view的可见区域是否大于tab栏的高度
        val lastVisiblePosition = manager.findLastVisibleItemPosition()
        if (isNestedBottomViewHolder(recyclerView.findViewHolderForAdapterPosition(lastVisiblePosition))) {
            range[1] = lastVisiblePosition
        } else {
            range[1] = manager.findLastCompletelyVisibleItemPosition()
        }
        return range
    }

    private fun findRangeStaggeredGrid(recyclerView: RecyclerView, manager: StaggeredGridLayoutManager): IntArray {
        val startPos = IntArray(manager.spanCount)
        val endPos = IntArray(manager.spanCount)
        manager.findFirstCompletelyVisibleItemPositions(startPos)
        manager.findLastVisibleItemPositions(endPos)
        var hasNestedBottomViewHolder = false
        for (i in endPos.indices) {
            if (isNestedBottomViewHolder(recyclerView.findViewHolderForAdapterPosition(endPos[i]))) {
                hasNestedBottomViewHolder = true
                break
            }
        }
        if (!hasNestedBottomViewHolder) {
            manager.findLastCompletelyVisibleItemPositions(endPos)
        }
        return findRange(startPos, endPos)
    }

    private fun findRange(startPos: IntArray, endPos: IntArray): IntArray {
        var start = startPos[0]
        var end = endPos[0]
        for (i in 1 until startPos.size) {
            if (start > startPos[i]) {
                start = startPos[i]
            }
        }
        for (i in 1 until endPos.size) {
            if (end < endPos[i]) {
                end = endPos[i]
            }
        }
        return intArrayOf(start, end)
    }

    private fun isNestedBottomViewHolder(viewHolder: RecyclerView.ViewHolder?): Boolean {
        return viewHolder?.javaClass?.simpleName?.contains("NestedBottomViewHolder", true) == true
    }
}