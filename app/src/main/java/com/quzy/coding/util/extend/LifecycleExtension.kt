package com.quzy.coding.util.extend

import androidx.annotation.MainThread
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.apkfuns.logutils.LogUtils
import com.quzy.coding.util.Constants
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * @methodName isResumed
 * @description 是否处于resume状态
 */
@MainThread
fun Lifecycle.isResumed(): Boolean {
    return currentState.isAtLeast(Lifecycle.State.RESUMED)
}

/**
 * @methodName awaitResumed
 * @description 等待执行到resume状态
 * @param []
 * @return
 * @author Zeng ZhaoXuan
 * @time 2021/5/26 5:10 下午
 */
@MainThread
suspend inline fun Lifecycle.awaitResumed() {
    // 已经处于resume状态.
    if (isResumed()) return

    // 注册观察者，等待转换到resume状态才执行后续操作
    observeResume()
}

/**
 * @methodName observeStarted
 * @description 监听resume状态，等到resume生命周期时才做后续操作
 * @param []
 * @return
 * @author Zeng ZhaoXuan
 * @time 2021/5/26 5:10 下午
 */
@MainThread
suspend fun Lifecycle.observeResume() {
    var observer: LifecycleObserver? = null
    try {
        suspendCancellableCoroutine<Unit> { continuation ->
            observer = object : DefaultLifecycleObserver {
                override fun onResume(owner: LifecycleOwner) {
                    if (continuation.isCompleted) {
                        LogUtils.tag(Constants.LOG_TAG).d("observeResume=============${owner::class.java.simpleName}")
                        return
                    }
                    continuation.resume(Unit)
                }
            }
            observer?.let(::addObserver)
        }
    } finally {
        // 执行完操作后remove掉观察者
        observer?.let(::removeObserver)
    }
}
