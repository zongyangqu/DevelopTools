package com.quzy.coding.base

/**
 * CreateDate:2023/2/14 13:43
 * @author: zongyang qu
 * @Package： com.quzy.coding.base
 * @Description:
 */

interface ViewHolderTag<T> {
    /**
     * holder操作
     *
     * @param entity 数据实体
     */
    fun setHolder(entity: T)

    /**
     * holder操作，含payload
     *
     * @param entity 数据实体
     */
    fun setHolder(
        entity: T,
        payloads: MutableList<Any>
    )
}