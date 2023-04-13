package com.quzy.coding.ui.activity

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.databinding.ActivityConstraintlayoutViewBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * CreateDate:2023/4/10 17:48
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: ConstraintLayout 使用ConstraintSet动态布局
 * 在传统布局方式中，如果要改变某个控件的位置，需要获取 LayoutParams ， 后台修改属性值就行了。
 * 但是在约束布局 ConstraintLayout 中，要改变控件的约束条件，需要用到 ConstraintSet 类。主要有 5 个步骤
 */
class ConstraintLayoutActivity : BaseActivity() {

    var viewBinding :ActivityConstraintlayoutViewBinding ?= null

    override fun onViewCreated() {
        viewBinding?.view1?.onClick {
            //第一步：创建 ConstraintSet() 实例
            val set = ConstraintSet()
            //第二步：需要复制一份父布局的约束,方法有三个如下
            set.clone(viewBinding?.clCommonContent)
            //第三步：设置组件之间的约束了
            //view1，view2 顶部对齐
            //set.clear(R.id.view1, ConstraintSet.TOP) //清除view的某个约束条件
            set.connect(R.id.view1, ConstraintSet.TOP, R.id.view2, ConstraintSet.TOP)
            //view1左边对齐view2左边，距离20px
            set.connect(R.id.view1, ConstraintSet.START, R.id.view2, ConstraintSet.END, 10)
            //第四步：设置一个动画,并且要求api版本为19及以上(非必须)
            //增加过渡动画，动画也可以不用加，但是效果比较生硬
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                viewBinding?.clCommonContent?.let { view ->
                    TransitionManager.beginDelayedTransition(
                        view
                    )
                }
            }
            //第五步：apply一下使设置生效
            set.applyTo(viewBinding?.clCommonContent)
        }

        viewBinding?.view2?.onClick {
            val set = ConstraintSet()
            set.clone(viewBinding?.clCommonContent)
            set.connect(R.id.view_img1, ConstraintSet.END, R.id.view_img2, ConstraintSet.START)
            set.connect(R.id.view_img1, ConstraintSet.TOP, R.id.parent, ConstraintSet.TOP)
            set.applyTo(viewBinding?.clCommonContent)
        }


        viewBinding?.changeButton?.onClick {
            val set = ConstraintSet()
            set.load(activity,R.layout.activity_constraintlayout_view2)
            TransitionManager.beginDelayedTransition(viewBinding?.animView!!)
            set.applyTo(viewBinding?.animView!!)
//            val set = ConstraintSet()
//            set.clone(viewBinding?.animView)
//
//            set.applyTo(viewBinding?.clCommonContent)
        }
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View?{
        viewBinding = ActivityConstraintlayoutViewBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }
}