package com.example.splash.presentation

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator


class CircleCustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    companion object{
        const val numberConst = 0.70710678118
    }


    private var firstCirclePaint: Paint
    private var secondCirclePaint: Paint
    private var linePaint: Paint

    val blackStrokePaint: Paint

    private val firstRadius = 180f
    private val secondRadius = 240f
    private var lineAngle = -90

    private val backgroundRadius = secondRadius + 80f

    private val VP_HEIGHT = Resources.getSystem().displayMetrics.heightPixels
    private val VP_WIDTH = Resources.getSystem().displayMetrics.widthPixels
    private val HWR: Float = VP_HEIGHT.toFloat() / VP_WIDTH.toFloat()



    init {
        firstCirclePaint = Paint().apply {
            setFlags(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
            setAntiAlias(true)
            setColor(Color.BLACK)
            setStyle(Paint.Style.STROKE)
            setStrokeWidth(10f)
        }

        blackStrokePaint = Paint().apply {
            color = Color.RED
            strokeWidth = 6f
            setAlpha(60)
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
            textSize = 240F
            textAlign = Paint.Align.CENTER
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension((MeasureSpec.getSize(widthMeasureSpec) * HWR).toInt(),
            (MeasureSpec.getSize(widthMeasureSpec) * HWR).toInt())


    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width / 2f, height / 2f, firstRadius, firstCirclePaint)
        canvas.drawCircle(width / 2f, height / 2f, secondRadius, secondCirclePaint)
        drawLine(canvas, secondRadius, lineAngle)
        drawText(canvas, lineAngle)

    }

    private fun drawLine(canvas: Canvas, length: Float, angle: Int) {
        val posX = (width / 2f + length * Math.cos(Math.toRadians(angle.toDouble()))).toFloat()
        val posY = (height / 2f + length * Math.sin(Math.toRadians(angle.toDouble()))).toFloat()
        canvas.drawLine(width / 2f, height / 2f, posX, posY, linePaint)

        canvas.drawArc((width / 2f - numberConst *backgroundRadius).toFloat() ,(height / 2f - numberConst *backgroundRadius).toFloat(),(width / 2f + numberConst *backgroundRadius).toFloat(), (height / 2f + numberConst *backgroundRadius).toFloat()  ,-90F ,angle.toFloat() + 90f,true, blackStrokePaint )
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        ValueAnimator.ofPropertyValuesHolder(
            PropertyValuesHolder.ofInt("lineAngle", -90, 270)
        ).apply {
            duration = 3000
            startDelay = 250
            repeatMode = ValueAnimator.RESTART
            interpolator = LinearInterpolator()
            addUpdateListener {
                lineAngle = it.getAnimatedValue("lineAngle") as Int
                invalidate()
            }
        }.start()
    }

    private fun drawText(canvas: Canvas, angle: Int) {
        if (angle in 30 downTo -90) {
            canvas.drawText("3", width / 2f, height / 1.6F, linePaint)
        }
        if (angle in 151 downTo 30) {
            canvas.drawText("2", width / 2f, height / 1.6F, linePaint)
        }
        if (angle in 271 downTo 150) {
            canvas.drawText("1", width / 2f, height / 1.6F, linePaint)
        }

    }

}