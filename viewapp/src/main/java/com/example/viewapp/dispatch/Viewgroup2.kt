package com.example.viewapp.dispatch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.orhanobut.logger.Logger
import utils.LogUtils

/**
 * @author caichen
 * @Description TODO
 * @createTime 2020年10月23日 15:15:00
 */
class Viewgroup2 : RelativeLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)

    constructor(context: Context, attributes: AttributeSet, defStyle: Int) : super(
        context,
        attributes,
        defStyle
    )

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.d("dispatchTouchEvent", "Viewgroup2")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d("onTouchEvent", "Viewgroup2")
        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.d("onInterceptTouchEvent", "Viewgroup2")
        return super.onInterceptTouchEvent(ev)
    }
}