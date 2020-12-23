package com.example.splash.presentation

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import kotlin.math.PI


const val toDegrees = 180f / PI

class CircleCustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private  var firstCirclePaint: Paint
    private  var secondCirclePaint: Paint
    private  var linePaint: Paint
    private val firstRadius = 180f
    private val secondRadius = 240f
    private var lineAngle = -90




    init {
        firstCirclePaint = Paint().apply {
            setFlags(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
            setAntiAlias(true)
            setColor(Color.BLACK)
            setStyle(Paint.Style.STROKE)
            setStrokeWidth(10f)
        }
        secondCirclePaint = Paint().apply {

            setFlags(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
            setAntiAlias(true)
            setColor(Color.BLACK)
            setStyle(Paint.Style.STROKE)
            setStrokeWidth(40f)
        }
        linePaint = Paint().apply {
            setFlags(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
            setAntiAlias(true)
            setColor(Color.BLACK)
            setStrokeWidth(6f)
            setStyle(Paint.Style.STROKE)
        }

    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width / 2f, height / 2f, firstRadius, firstCirclePaint)
        canvas.drawCircle(width / 2f, height / 2f, secondRadius, secondCirclePaint)
        drawLine(canvas, secondRadius, lineAngle)
    }

    private fun drawLine(canvas: Canvas, length: Float, angle: Int) {
        val posX = (width / 2f + length * Math.cos(Math.toRadians(angle.toDouble()))).toFloat()
        val posY = (height / 2f + length * Math.sin(Math.toRadians(angle.toDouble()))).toFloat()
        canvas.drawLine(width / 2f, height / 2f, posX, posY, linePaint)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        ValueAnimator.ofPropertyValuesHolder(
            PropertyValuesHolder.ofInt("lineAngle", -90, 990)
        ).apply {
            duration = 3000
            repeatMode = ValueAnimator.RESTART
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener {
                lineAngle = it.getAnimatedValue("lineAngle") as Int
                invalidate()
            }
        }.start()
    }

}