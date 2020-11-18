package com.example.viewapp.dispatch

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Toast
import base.BaseCustomView
import utils.LogUtils

/**
 * @author caichen
 * @Description TODO
 * @createTime 2020年10月27日 11:04:00
 */
class MyCustomView : BaseCustomView {
    private lateinit var vPaint: Paint
    private lateinit var circlePath: Path
    private lateinit var circleRegion: Region
    private lateinit var clipRegion: Region

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        initPaint()
    }

    private fun initPaint() {
        vPaint = Paint().apply {
            color = Color.RED
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 100f
        }

        circleRegion = Region()

        circlePath = Path()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        circlePath.addCircle(w / 2.0f, h / 2.0f, 100f, Path.Direction.CW)
        clipRegion = Region(0, 0, w, h)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val path = circlePath
        canvas?.drawPath(path, vPaint)
        circleRegion.setPath(path, clipRegion)

        val matrix = Matrix()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x: Int = event?.x?.toInt() ?: 0
        val y: Int = event?.y?.toInt() ?: 0
        if (circleRegion.contains(x, y)) {
            LogUtils.d("onTouchEvent", "MyCustomView")
            Toast.makeText(context, "圆被点击", Toast.LENGTH_SHORT).show();
        }
        return true
    }
}