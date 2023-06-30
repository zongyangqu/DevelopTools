package com.quzy.coding.util

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.quzy.coding.BuildConfig
import com.quzy.coding.util.extend.awaitResumed
import com.quzy.coding.util.extend.isResumed
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * CreateDate:2023-06-29 22:58
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description: 搭配生命周期的操作触发辅助类
 */
class LifecycleOperationHelper {

    private val scope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main.immediate +
                CoroutineExceptionHandler { _, throwable ->
                    if (BuildConfig.DEBUG) {
                        Log.e("LifecycleHelper", "exception: ${throwable.message}")
                    }
                }
    )

    private var expoJob: Job? = null

    /**
     * @methodName doLifecycleOperation
     * @description 搭配生命周期判断的执行后续操作
     * @param lifecycleOwner
     * @param awaitForResumed 是否等待resume之后才执行后续操作 true: 如果当前不是resume，则会挂起后续的操作，等页面执行到resume后才会执行， false: 直接判断页面当前如果不是resume则不执行后续操作
     * @param considerHidden 是否考虑fragment的隐藏情况
     * @param doOperation 实际需要做的操作
     * @return
     * @author Zeng ZhaoXuan
     * @time 2021/5/26 5:09 下午
     */
    fun doLifecycleOperation(lifecycleOwner: LifecycleOwner?, awaitForResumed: Boolean = false, considerHidden: Boolean = true, doOperation: (() -> Unit)) {
        if (lifecycleOwner == null) {
            doOperation.invoke()
        } else {
            expoJob?.cancel()
            expoJob = scope.launch {
                when (lifecycleOwner) {
                    is Fragment -> {
                        if (awaitForResumed) {
                            lifecycleOwner.lifecycle.awaitResumed()
                            if (considerHidden) {
                                if (!isFragmentHidden(lifecycleOwner)) {
                                    doOperation.invoke()
                                }
                            } else {
                                doOperation.invoke()
                            }
                        } else {
                            if (lifecycleOwner.lifecycle.isResumed()) {
                                if (considerHidden) {
                                    if (!isFragmentHidden(lifecycleOwner)) {
                                        doOperation.invoke()
                                    }
                                } else {
                                    doOperation.invoke()
                                }
                            }
                        }
                    }
                    is ComponentActivity -> {
                        if (awaitForResumed) {
                            lifecycleOwner.lifecycle.awaitResumed()
                            doOperation.invoke()
                        } else {
                            if (lifecycleOwner.lifecycle.isResumed()) {
                                doOperation.invoke()
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @methodName isFragmentHidden
     * @description 判断当前fragment是否可见(父fragment隐藏时，子fragment也不可见)
     * @param [fragment]
     * @return
     * @author Zeng ZhaoXuan
     * @time 2021/5/26 5:10 下午
     */
    private fun isFragmentHidden(fragment: Fragment): Boolean {
        if (fragment.isHidden) {
            return true
        } else {
            val parentFragment = fragment.parentFragment ?: return fragment.isHidden
            return if (parentFragment.isHidden) {
                true
            } else {
                isFragmentHidden(parentFragment)
            }
        }
    }
}
