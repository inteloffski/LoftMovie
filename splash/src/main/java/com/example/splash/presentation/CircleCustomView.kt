package com.example.splash.presentation

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

const val TAG = "CircleCustomView"

class CircleCustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    val blackPaint: Paint
    val firstCircle: Paint
    val secondCircle: Paint

    var firstRadius = 180f
    var secondRadius = 240f


    init {
        blackPaint = Paint().apply {
            color = Color.BLACK
            strokeWidth = 10f
        }


        firstCircle = Paint().apply {
            color = Color.BLACK
            strokeWidth = 6f
            style = Paint.Style.STROKE
        }

        secondCircle = Paint().apply {
            color = Color.BLACK
            strokeWidth = 54f
            style = Paint.Style.STROKE
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val midHeight = height / 2f
        val midWidth = width / 2f

        val startPoint = midWidth
        var endPoint = (midHeight - firstRadius)

        canvas?.drawLine(midWidth, midHeight, startPoint,endPoint, blackPaint)

        canvas?.drawCircle(midWidth, midHeight, firstRadius, firstCircle)
        canvas?.drawCircle(midWidth, midHeight, secondRadius, secondCircle)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        val yStart = (width / 2) + firstRadius
        val yEnd = (yStart - firstRadius)



        ValueAnimator.ofFloat(yStart, yEnd).apply {
            duration = 2000
            startDelay = 500
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener {
                //endPoint = it.animatedValue as Float
                invalidate()
            }
        }.start()
    }
}