package com.example.viewapp.dispatch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import java.util.jar.Attributes

/**
 * @author caichen
 * @Description TODO
 * @createTime 2020年10月23日 15:15:00
 */
class Viewgroup1 : ViewGroup {
    constructor(context: Context) : super(context)

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)

    constructor(context: Context, attributes: AttributeSet, defStyle: Int) : super(
        context,
        attributes,
        defStyle
    )

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        print("dispatchTouchEvent --> Viewgroup1")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        print("onTouchEvent --> Viewgroup1")
        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        print("onInterceptTouchEvent --> Viewgroup1")
        return super.onInterceptTouchEvent(ev)
    }
}