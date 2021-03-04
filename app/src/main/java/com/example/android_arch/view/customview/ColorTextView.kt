package com.example.android_arch.view.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log

class ColorTextView : androidx.appcompat.widget.AppCompatTextView {
    private var progress = 0
    private val text = "hello world"
    private val redPaint by lazy {
        Paint().apply {
            color = Color.RED
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
            textSize = 18.0f
        }
    }
    private val bluePaint by lazy {
        Paint().apply {
            color = Color.BLUE
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
            textSize = 18.0f
        }
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    fun setProgress(progress: Int) {
        this.progress = progress
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        val measureTextLength = text.length - 1
        val middle = measureTextLength * progress
        drawColorText(canvas, middle, measureTextLength)
        drawOriginText(canvas, middle, measureTextLength)
    }

    private fun drawOriginText(
        canvas: Canvas?,
        middle: Int,
        totalLength: Int
    ) {
        canvas?.drawText(text, 0, totalLength, 0f, (height / 2.0).toFloat(), bluePaint)
    }

    private fun drawColorText(
        canvas: Canvas?,
        middle: Int,
        totalLength: Int
    ) {
        Log.d("ColorTextView", "middle = $middle")
        canvas?.drawText(text, 0, middle, 0f, (height / 2.0).toFloat(), redPaint)
    }

}