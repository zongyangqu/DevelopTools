package com.quzy.coding.bean

/**
 * CreateDate:2023/2/14 11:07
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
class SecKillCategoryVO (var categoryId: String?,
                         var categoryName: String?,
                         var categoryImg: String?)

class SecKillCategoryInfoVO :  MultipleType {
    var categories: List<SecKillCategoryVO>? = null
    var code: String? = null
    var round: Int? = null
    var shopId: String? = null
    override fun viewType(): Int = 2
}
