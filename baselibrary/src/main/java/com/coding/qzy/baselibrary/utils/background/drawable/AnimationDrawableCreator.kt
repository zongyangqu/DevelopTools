package com.coding.qzy.baselibrary.utils.background.drawable

import android.content.res.TypedArray
import com.coding.qzy.baselibrary.utils.background.drawable.ICreateDrawable
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.StyleableRes
import com.coding.qzy.baselibrary.R
import java.lang.Exception

class AnimationDrawableCreator(private val animationTa: TypedArray) : ICreateDrawable {
    private var duration = 0
    private val drawable = AnimationDrawable()
    @Throws(Exception::class)
    override fun create(): Drawable {
        for (i in 0 until animationTa.indexCount) {
            val attr = animationTa.getIndex(i)
            if (attr == R.styleable.bl_anim_bl_duration) {
                duration = animationTa.getInt(attr, 0)
            } else if (attr == R.styleable.bl_anim_bl_oneshot) {
                drawable.isOneShot = animationTa.getBoolean(attr, false)
            }
        }
        if (animationTa.hasValue(R.styleable.bl_anim_bl_frame_drawable_item0)) {
            val itemDrawable = animationTa.getDrawable(R.styleable.bl_anim_bl_frame_drawable_item0)
            if (itemDrawable != null) {
                if (animationTa.hasValue(R.styleable.bl_anim_bl_duration_item0)) {
                    drawable.addFrame(itemDrawable, animationTa.getInt(R.styleable.bl_anim_bl_duration_item0, 0))
                } else {
                    drawable.addFrame(itemDrawable, duration)
                }
            }
        }
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item0, R.styleable.bl_anim_bl_duration_item0)
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item1, R.styleable.bl_anim_bl_duration_item1)
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item2, R.styleable.bl_anim_bl_duration_item2)
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item3, R.styleable.bl_anim_bl_duration_item3)
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item4, R.styleable.bl_anim_bl_duration_item4)
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item5, R.styleable.bl_anim_bl_duration_item5)
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item6, R.styleable.bl_anim_bl_duration_item6)
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item7, R.styleable.bl_anim_bl_duration_item7)
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item8, R.styleable.bl_anim_bl_duration_item8)
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item9, R.styleable.bl_anim_bl_duration_item9)
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item10, R.styleable.bl_anim_bl_duration_item10)
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item11, R.styleable.bl_anim_bl_duration_item11)
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item12, R.styleable.bl_anim_bl_duration_item12)
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item13, R.styleable.bl_anim_bl_duration_item13)
        addFrame(R.styleable.bl_anim_bl_frame_drawable_item14, R.styleable.bl_anim_bl_duration_item14)
        return drawable
    }

    private fun addFrame(@StyleableRes itemDrawableId: Int, @StyleableRes itemDurationId: Int) {
        if (animationTa.hasValue(itemDrawableId)) {
            val itemDrawable = animationTa.getDrawable(itemDrawableId)
            if (itemDrawable != null) {
                if (animationTa.hasValue(itemDurationId)) {
                    drawable.addFrame(itemDrawable, animationTa.getInt(itemDurationId, 0))
                } else {
                    drawable.addFrame(itemDrawable, duration)
                }
            }
        }
    }
}