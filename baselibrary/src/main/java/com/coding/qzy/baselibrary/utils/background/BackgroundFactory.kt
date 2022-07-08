package com.coding.qzy.baselibrary.utils.background

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.InflateException
import android.view.LayoutInflater
import android.view.LayoutInflater.Factory2
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import androidx.collection.ArrayMap
import com.coding.qzy.baselibrary.R
import com.coding.qzy.baselibrary.utils.background.drawable.DrawableFactory.getAnimationDrawable
import com.coding.qzy.baselibrary.utils.background.drawable.DrawableFactory.getButtonDrawable
import com.coding.qzy.baselibrary.utils.background.drawable.DrawableFactory.getDrawable
import com.coding.qzy.baselibrary.utils.background.drawable.DrawableFactory.getPressDrawable
import com.coding.qzy.baselibrary.utils.background.drawable.DrawableFactory.getSelectorDrawable
import com.coding.qzy.baselibrary.utils.background.drawable.DrawableFactory.getTextSelectorColor
import com.coding.qzy.baselibrary.utils.background.view.Const
import java.lang.reflect.Constructor

class BackgroundFactory : Factory2 {
    private var mViewCreateFactory: LayoutInflater.Factory? = null
    private var mViewCreateFactory2: Factory2? = null
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View {
        var name = name
        name = switchBLViewToOriginal(name)
        var view: View? = null
        if (mViewCreateFactory2 != null) {
            view = mViewCreateFactory2!!.onCreateView(name, context, attrs)
            if (view == null) {
                view = mViewCreateFactory2!!.onCreateView(null, name, context, attrs)
            }
        } else if (mViewCreateFactory != null) {
            view = mViewCreateFactory!!.onCreateView(name, context, attrs)
        }
        return setViewBackground(name, context, attrs, view)!!
    }

    private fun switchBLViewToOriginal(name: String): String {
        var name = name
        if (name == Const.BLButton) {
            name = "Button"
        } else if (name == Const.BLCheckBox) {
            name = "CheckBox"
        } else if (name == Const.BLEditText) {
            name = "EditText"
        } else if (name == Const.BLFrameLayout) {
            name = "FrameLayout"
        } else if (name == Const.BLGridLayout) {
            name = "GridLayout"
        } else if (name == Const.BLGridView) {
            name = "GridView"
        } else if (name == Const.BLImageButton) {
            name = "ImageButton"
        } else if (name == Const.BLImageView) {
            name = "ImageView"
        } else if (name == Const.BLLinearLayout) {
            name = "LinearLayout"
        } else if (name == Const.BLListView) {
            name = "ListView"
        } else if (name == Const.BLRadioButton) {
            name = "RadioButton"
        } else if (name == Const.BLRadioGroup) {
            name = "RadioGroup"
        } else if (name == Const.BLRelativeLayout) {
            name = "RelativeLayout"
        } else if (name == Const.BLScrollView) {
            name = "ScrollView"
        } else if (name == Const.BLTextView) {
            name = "TextView"
        } else if (name == Const.BLView) {
            name = "View"
        }
        return name
    }

    fun setInterceptFactory(factory: LayoutInflater.Factory?) {
        mViewCreateFactory = factory
    }

    fun setInterceptFactory2(factory: Factory2?) {
        mViewCreateFactory2 = factory
    }

    override fun onCreateView(parent: View, name: String, context: Context, attrs: AttributeSet): View {
        return onCreateView(name, context, attrs)
    }

    companion object {
        private val sConstructorSignature = arrayOf(Context::class.java, AttributeSet::class.java)
        private val mConstructorArgs = arrayOfNulls<Any>(2)
        private val sConstructorMap: MutableMap<String?, Constructor<out View>?> = ArrayMap()
        fun setViewBackground(context: Context, attrs: AttributeSet, view: View?): View? {
            return setViewBackground(null, context, attrs, view)
        }

        private fun setViewBackground(name: String?, context: Context, attrs: AttributeSet, view: View?): View? {
            var view = view
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.background)
            val pressTa = context.obtainStyledAttributes(attrs, R.styleable.background_press)
            val selectorTa = context.obtainStyledAttributes(attrs, R.styleable.background_selector)
            val textTa = context.obtainStyledAttributes(attrs, R.styleable.text_selector)
            val buttonTa = context.obtainStyledAttributes(attrs, R.styleable.background_button_drawable)
            val otherTa = context.obtainStyledAttributes(attrs, R.styleable.bl_other)
            val animTa = context.obtainStyledAttributes(attrs, R.styleable.bl_anim)
            return try {
                if (typedArray.indexCount == 0 && selectorTa.indexCount == 0 && pressTa.indexCount == 0 && textTa.indexCount == 0 && buttonTa.indexCount == 0 && animTa.indexCount == 0) {
                    return view
                }
                if (view == null) {
                    view = createViewFromTag(context, name, attrs)
                }
                if (view == null) {
                    return null
                }
                var drawable: GradientDrawable? = null
                var stateListDrawable: StateListDrawable? = null
                if (buttonTa.indexCount > 0 && view is CompoundButton) {
                    view.setClickable(true)
                    view.buttonDrawable = getButtonDrawable(typedArray, buttonTa)
                } else if (selectorTa.indexCount > 0) {
                    stateListDrawable = getSelectorDrawable(typedArray, selectorTa)
                    view.isClickable = true
                    setDrawable(stateListDrawable, view, otherTa)
                } else if (pressTa.indexCount > 0) {
                    drawable = getDrawable(typedArray)
                    stateListDrawable = getPressDrawable(drawable, typedArray, pressTa)
                    view.isClickable = true
                    setDrawable(stateListDrawable, view, otherTa)
                } else if (typedArray.indexCount > 0) {
                    drawable = getDrawable(typedArray)
                    setDrawable(drawable, view, otherTa)
                } else if (animTa.indexCount > 0) {
                    val animationDrawable = getAnimationDrawable(animTa)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        view.background = animationDrawable
                    } else {
                        view.setBackgroundDrawable(animationDrawable)
                    }
                    if (animTa.getBoolean(R.styleable.bl_anim_bl_anim_auto_start, false)) {
                        animationDrawable.start()
                    }
                }
                if (view is TextView && textTa.indexCount > 0) {
                    view.setTextColor(getTextSelectorColor(textTa))
                }
                if (typedArray.getBoolean(R.styleable.background_bl_ripple_enable, false) &&
                        typedArray.hasValue(R.styleable.background_bl_ripple_color)) {
                    val color = typedArray.getColor(R.styleable.background_bl_ripple_color, 0)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        val contentDrawable = stateListDrawable ?: drawable
                        val rippleDrawable = RippleDrawable(ColorStateList.valueOf(color), contentDrawable, contentDrawable)
                        view.isClickable = true
                        view.background = rippleDrawable
                    } else if (stateListDrawable == null) {
                        val tmpDrawable = StateListDrawable()
                        val unPressDrawable = getDrawable(typedArray)
                        unPressDrawable.setColor(color)
                        tmpDrawable.addState(intArrayOf(-android.R.attr.state_pressed), drawable)
                        tmpDrawable.addState(intArrayOf(android.R.attr.state_pressed), unPressDrawable)
                        view.isClickable = true
                        setDrawable(tmpDrawable, view, otherTa)
                    }
                }
                view
            } catch (e: Exception) {
                e.printStackTrace()
                view
            } finally {
                typedArray.recycle()
                pressTa.recycle()
                selectorTa.recycle()
                textTa.recycle()
                buttonTa.recycle()
                otherTa.recycle()
                animTa.recycle()
            }
        }

        private fun setDrawable(drawable: Drawable, view: View, otherTa: TypedArray) {
            if (view is TextView) {
                if (otherTa.hasValue(R.styleable.bl_other_bl_position)) {
                    if (otherTa.getInt(R.styleable.bl_other_bl_position, 0) == 1) {
                        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                        view.setCompoundDrawables(drawable, null, null, null)
                    } else if (otherTa.getInt(R.styleable.bl_other_bl_position, 0) == 2) {
                        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                        view.setCompoundDrawables(null, drawable, null, null)
                    } else if (otherTa.getInt(R.styleable.bl_other_bl_position, 0) == 4) {
                        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                        view.setCompoundDrawables(null, null, drawable, null)
                    } else if (otherTa.getInt(R.styleable.bl_other_bl_position, 0) == 8) {
                        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                        view.setCompoundDrawables(null, null, null, drawable)
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        view.setBackground(drawable)
                    } else {
                        view.setBackgroundDrawable(drawable)
                    }
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.background = drawable
                } else {
                    view.setBackgroundDrawable(drawable)
                }
            }
        }

        private fun createViewFromTag(context: Context, name: String?, attrs: AttributeSet): View? {
            var name = name
            if (TextUtils.isEmpty(name)) {
                return null
            }
            if (name == "view") {
                name = attrs.getAttributeValue(null, "class")
            }
            return try {
                mConstructorArgs[0] = context
                mConstructorArgs[1] = attrs
                if (-1 == name!!.indexOf('.')) {
                    var view: View? = null
                    if ("View" == name) {
                        view = createView(context, name, "android.view.")
                    }
                    if (view == null) {
                        view = createView(context, name, "android.widget.")
                    }
                    if (view == null) {
                        view = createView(context, name, "android.webkit.")
                    }
                    view
                } else {
                    createView(context, name, null)
                }
            } catch (e: Exception) {
                Log.w("BackgroundLibrary", "cannot create 【$name】 : ")
                null
            } finally {
                mConstructorArgs[0] = null
                mConstructorArgs[1] = null
            }
        }

        @Throws(InflateException::class)
        private fun createView(context: Context, name: String?, prefix: String?): View? {
            var constructor = sConstructorMap[name]
            return try {
                if (constructor == null) {
                    val clazz = context.classLoader.loadClass(
                            if (prefix != null) prefix + name else name).asSubclass(View::class.java)
                    constructor = clazz.getConstructor(*sConstructorSignature)
                    sConstructorMap[name] = constructor
                }
                constructor!!.isAccessible = true
                constructor.newInstance(*mConstructorArgs)
            } catch (e: Exception) {
                Log.w("BackgroundLibrary", "cannot create 【$name】 : ")
                null
            }
        }
    }
}