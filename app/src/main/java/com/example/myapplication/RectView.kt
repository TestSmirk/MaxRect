package com.example.myapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import java.util.jar.Attributes

class RectView(context:Context,attributes: AttributeSet)  : LinearLayout(context,attributes) {
    private val rectPaint =Paint().apply {
        color = Color.GRAY
        strokeWidth = 5f
    }

    init{
        setWillNotDraw(false)
    }
    private var mRotation=0
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event==null) {
            return false
        }
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                mRotation = event.rawY.toInt()
            }
            else -> {
            }
        }
        invalidate()
        return true

    }
    private val frameRect = RectF(0f,0f,100f,100f)
    private val viewPoint = PointF(0f,0f)
    private val viewPointArray = floatArrayOf(0f,0f)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas==null) {
            return
        }
        if (childCount>0){
            val view = getChildAt(0)
        frameRect.set(0f,0f, view.width.toFloat(), view.height.toFloat())
            view.rotation = (mRotation%360).toFloat()
            view.matrix.mapRect(frameRect)//key code
        }
        canvas.drawRect(frameRect.apply {
            offsetTo(width/2f-width()/2f,height/2f-height()/2f)

        },rectPaint)

    }
}