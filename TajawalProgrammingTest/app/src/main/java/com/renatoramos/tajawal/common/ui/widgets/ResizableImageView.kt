package com.renatoramos.tajawal.common.ui.widgets

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet


class ResizableImageView : AppCompatImageView {

    private var ratio = 1.5f

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs,
            defStyleAttr)

    fun setRatio(ratio: Float) {
        this.ratio = ratio
        invalidate()
        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = (width / ratio).toInt()
        setMeasuredDimension(width, height)
    }
}