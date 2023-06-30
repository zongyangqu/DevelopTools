package com.quzy.coding.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * CreateDate:2021/10/19 16:54
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
@Parcelize
open class CommonProductBean : ProductBean(), Parcelable {
    fun isSoldOut(): Boolean {
        return inStock == 0
    }

    /**
     * @method isSkuProduct
     * @description 是否是批次商品
     * @date: 2021/3/31 9:22 PM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    fun isSkuProduct(): Boolean {
        return batch?.batchFlag ?: 0 == 2
    }



    /**
     * @method isPreprocessProduct
     * @description 是否是预处理商品
     * @date: 2021/4/10 4:57 PM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    fun isPreprocessProduct(): Boolean {
        return preprocess != 0
    }

    companion object {
        const val MARKET_RANKING = "marketRanking" // 营销推荐语
        const val BATCH_TIME_DESC = "batchTimeDesc" // 上市日期
    }

    /**
     * 是否上市日期，recSlogan默认是营销推荐语
     * @return Boolean
     */
    fun isBatchTime(): Boolean {
        return recSloganType == BATCH_TIME_DESC
    }
}
