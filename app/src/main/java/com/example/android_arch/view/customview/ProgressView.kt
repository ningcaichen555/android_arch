package com.example.android_arch.view.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View

class ProgressView : View {
    private var bgPaint: Paint? = null
    private var paint: Paint? = null
    private var textPaint: Paint? = null
    private var rect: RectF? = null
    private var width: Int? = null
    private var height: Int? = null
    private var progress = 0.0
    private var max = 1000.0

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        bgPaint = Paint().apply {
            color = Color.BLUE
            strokeWidth = 10.0f
            style = Paint.Style.STROKE
            isAntiAlias = true
        }
        paint = Paint().apply {
            color = Color.RED
            strokeWidth = 10.0f
            style = Paint.Style.STROKE
            isAntiAlias = true
        }
        textPaint = Paint().apply {
            color = Color.RED
            textSize = 18.0f
        }
        Log.d("ProgressView", "width=$width height=$height")


    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attributeSet, defStyleAttr, defStyleRes)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w
        height = h
        rect = RectF(5f, 5f, w - 5f, h - 5f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        canvas?.drawArc(rect!!, -180f, 180f, false, bgPaint!!)

        Log.d("ProgressView", "onDraw $width $height ${progress / max * 180f}")

        canvas?.drawArc(rect!!, -180f, (progress / max * 180f).toFloat(), false, paint!!)

        val measureTextX = textPaint?.measureText("$progress")
        canvas?.drawText(
            "$progress",
            width!! / 2 - measureTextX!! / 2,
            rect!!.height() / 3,
            textPaint!!
        )
    }

    fun setProgress(progress: Double) {
        this.progress = progress
        invalidate()
    }
}