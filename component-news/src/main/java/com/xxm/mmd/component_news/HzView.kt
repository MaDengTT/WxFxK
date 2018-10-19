package com.xxm.mmd.component_news

import android.annotation.SuppressLint
import android.arch.persistence.room.Update
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.xxm.mmd.component_news.R.attr.*
import kotlin.concurrent.thread


/**
 * TODO: document your custom view class.
 */
class HzView : View {

    private var _exampleString: String? = null // TODO: use a default from R.string...
    private var _exampleColor: Int = Color.RED // TODO: use a default from R.color...
    private var _exampleDimension: Float = 0f // TODO: use a default from R.dimen...

    private var textPaint: TextPaint? = null
    private var textWidth: Float = 0f
    private var textHeight: Float = 0f

//    /**
//     * The text to draw
//     */
//    var exampleString: String?
//        get() = _exampleString
//        set(value) {
//            _exampleString = value
//            invalidateTextPaintAndMeasurements()
//        }

    public var rectSize:Int = 3

    public var rectInterval:Double = 10.0

    public var data:List<Int>? = null

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
                attrs, R.styleable.HzView, defStyle, 0)

//
//        _exampleColor = a.getColor(
//                R.styleable.HzView_exampleColor,
//                exampleColor)
//

        a.recycle()

        // Set up a default TextPaint object
        textPaint = TextPaint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
            textAlign = Paint.Align.LEFT
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        val paddingLeft = paddingLeft
        val paddingTop = paddingTop
        val paddingRight = paddingRight
        val paddingBottom = paddingBottom

        val contentWidth = width - paddingLeft - paddingRight
        val contentHeight = height - paddingTop - paddingBottom

        val rectWidth = width/rectSize


        val paint = Paint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
            color = Color.RED
        }

        var i = 0

        while (i<rectSize){

            val rect = Rect((i*rectWidth+rectInterval).toInt(),10,(i*rectWidth+rectWidth-rectInterval).toInt(),contentHeight);
            canvas.drawRect(rect,paint)

            i++
        }

        thread {  }

    }
}
