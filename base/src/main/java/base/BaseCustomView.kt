package base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

/**
 * @author caichen
 * @Description 自定义view基类
 * @createTime 2020年10月27日 10:55:00
 */
open class BaseCustomView : View {
    var mWidth: Int = 0
    var mHeight: Int = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }
}