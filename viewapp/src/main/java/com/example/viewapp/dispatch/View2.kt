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
class View2:View {
    constructor(context: Context) : super(context)

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)

    constructor(context: Context, attributes: AttributeSet, defStyle: Int) : super(
        context,
        attributes,
        defStyle
    )

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d("onTouchEvent","view2")
        return super.onTouchEvent(event)
    }

}