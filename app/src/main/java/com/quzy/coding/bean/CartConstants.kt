package com.quzy.coding.bean


enum class CartCallBackType  {
    NORMAL, SPU, SKU, SPU_CHANGED, DISMISS
}

// 数量不变
const val OPERATETYPE_NOT: Int = 0

// 增加商品
const val OPERATETYPE_ADD: Int = 1

// 减少商品
const val OPERATETYPE_DISCOUNT: Int = 2

// 未选中
const val UN_SELECT: Int = 0

// 选中
const val SELECT: Int = 1
