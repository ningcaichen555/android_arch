package com.example.viewapp.dispatch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.orhanobut.logger.Logger
import utils.LogUtils

/**
 * @author caichen
 * @Description TODO
 * @createTime 2020年10月23日 15:19:00
 */
class View1 : View {
    constructor(context: Context) : super(context)

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)

    constructor(context: Context, attributes: AttributeSet, defStyle: Int) : super(
        context,
        attributes,
        defStyle
    )

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d("onTouchEvent", "view1")
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    override fun setOnTouchListener(l: OnTouchListener?) {

    }
}