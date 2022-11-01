package com.quzy.coding.util

import com.apkfuns.logutils.LogUtils
import com.coding.qzy.baselibrary.widget.recorderlib.utils.FileUtils
import kotlinx.coroutines.*

/**
 * CreateDate:2022/10/10 18:24
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description:
 */
object TypefaceLoadUtil {

    private val scope by lazy {
        CoroutineScope(
            SupervisorJob() + Dispatchers.IO
        )
    }


    fun unZip(zipFileString :String, outPathString :String){
        scope.launch{
            delay(3000)
            LogUtils.tag(Constants.LOG_TAG).d("GlobalScope continue delay 3000.....")
            //FileUtils.UnZipFolder(zipFileString, outPathString)
            unZipFolder(zipFileString, outPathString)
        }
    }

    private suspend fun unZipFolder(zipFileString :String, outPathString :String){
        FileUtils.UnZipFolder(zipFileString, outPathString)
    }
}