package com.example.farmingfriend

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class DashedCurveView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 4f
        style = Paint.Style.STROKE
        isAntiAlias = true
        pathEffect = android.graphics.DashPathEffect(floatArrayOf(15f, 15f), 0f) // dash-gap pattern
    }

    private val path = Path()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        path.reset()
        // Draw quadratic Bezier curve from (10, height - 10) to (width - 10, height - 10) with control point at center top
        path.moveTo(10f, height - 10f)
        path.quadTo(width / 2f.toFloat(), 10f, width - 10f.toFloat(), height - 10f)

        canvas.drawPath(path, paint)
    }
}
