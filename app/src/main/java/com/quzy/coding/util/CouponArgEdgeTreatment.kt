package com.quzy.coding.util

import com.google.android.material.shape.EdgeTreatment
import com.google.android.material.shape.ShapePath

/**
 * CreateDate:2022/7/1 10:36
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description:
 */
class CouponArgEdgeTreatment(var offset: Float, var size: Float, var isTop: Boolean) : EdgeTreatment() {
    override fun getEdgePath(length: Float, center: Float, f: Float, shapePath: ShapePath) {
        val radius = size * f
        val leftOffset = if (isTop) offset else length - offset
        shapePath.lineTo(leftOffset - radius, 0f)
        shapePath.addArc(
            leftOffset - radius, -radius,
            leftOffset + radius, radius,
            180f,
            -180f
        )
        shapePath.lineTo(length, 0f)
    }
}