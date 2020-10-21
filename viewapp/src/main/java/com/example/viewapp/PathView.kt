package com.example.viewapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import utils.ScreenUtil

class PathView : View {
    private lateinit var pathPaint: Paint
    private lateinit var rectPaint: Paint
    private lateinit var normalPath: Path
    private lateinit var rectPath: Path
    private val screenUtil: ScreenUtil = ScreenUtil.getInstance(context)

    constructor(context: Context?) : this(context, null) {}

    constructor(context: Context?, attrs: AttributeSet?) : this(
        context,
        attrs,
        0
    ) {
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        pathPaint = Paint().apply {
            color = Color.BLACK
            strokeWidth = screenUtil.dip2px(5f).toFloat()
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.STROKE
        }

        rectPaint = Paint().apply {
            color = Color.RED
            strokeWidth = screenUtil.dip2px(2f).toFloat()
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.STROKE
        }

        normalPath = Path().apply {

        }
        rectPath = Path().apply {

        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //绘制一条线
        normalPath.moveTo(200f, 200f)
        normalPath.lineTo(400f, 400f)
        canvas?.drawPath(normalPath, pathPaint)

        normalPath.moveTo(400f, 200f)
        normalPath.lineTo(200f, 200f)
        normalPath.close()
        canvas?.drawPath(normalPath, pathPaint)


        normalPath.moveTo(600f, 200f)
        normalPath.lineTo(800f, 200f)
        normalPath.lineTo(800f, 400f)
        //封闭path，如果不能成为封闭图形则不产生变化   close的作用是封闭路径，与连接当前最后一个点和第一个点并不等价。如果连接了最后一个点和第一个点仍然无法形成封闭图形，则close什么 也不做。
        normalPath.close()
        canvas?.drawPath(normalPath, pathPaint)

        //添加基础图形
        normalPath.moveTo(width / 2f, height / 2f)
        normalPath.addCircle(width / 2f, height / 2f, 100f, Path.Direction.CW)
        canvas?.drawPath(normalPath, pathPaint)

        //移动坐标到屏幕中心
//        canvas?.translate(width / 2f, height / 2f)
//        rectPath.addRect(RectF(-200f, -200f, 200f, 200f), Path.Direction.CW)
//        rectPath.setLastPoint(-300f, 300f)
//        canvas?.drawPath(rectPath, pathPaint)

//        Path.Direction.CW  顺时针方向
//        Path.Direction.CCW  顺时针方向

//        canvas?.translate(width / 2f, height / 2f)
//        rectPath.addRect(RectF(-200f, -200f, 200f, 200f), Path.Direction.CCW)
//        rectPath.setLastPoint(-300f, 300f)
//        canvas?.drawPath(rectPath, pathPaint)

        //绘制rect和一个90的弧度
        val rectF = RectF(width / 2f, height / 2f + 200f, width / 2f + 200f, height / 2f + 400f)
        canvas?.drawRect(rectF, rectPaint)

//        rectPath.moveTo(width / 2f, height / 2f + 200f)
        rectPath.addArc(rectF, 0f, 120f)
        canvas?.drawPath(rectPath, pathPaint)

    }
}