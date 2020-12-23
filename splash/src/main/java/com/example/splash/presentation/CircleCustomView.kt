import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

const val toDegrees = 180/ PI

class CircleCustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var blackPaint: Paint
    val firstCircle: Paint
    val secondCircle: Paint

    var firstRadius = 180f
    var secondRadius = 240f




    var xStart = firstRadius * cos((0f * toDegrees).toFloat())
    var yStart = secondRadius * sin((0f * toDegrees ).toFloat())

    var xEnd = secondRadius * cos((360f * toDegrees ).toFloat())
    var yEnd = secondRadius * sin((360f * toDegrees ).toFloat())


    val startX = height / 2f
    val startY = width / 2f

    var stopX = startX
    var stopY = (startY - secondRadius)





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



        canvas?.drawLine(startX, startY, stopX, stopY, blackPaint)

        canvas?.drawCircle(startX, startY, firstRadius, firstCircle)
        canvas?.drawCircle(startX, startY, secondRadius, secondCircle)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        val xHolder =
            PropertyValuesHolder.ofFloat("stopX", xStart, xEnd)

        val yHolder =
            PropertyValuesHolder.ofFloat("stopY",  yStart, yEnd)





        ValueAnimator.ofPropertyValuesHolder(xHolder, yHolder).apply {
            duration = 2000
            startDelay = 500
            interpolator = AccelerateInterpolator()
            addUpdateListener {
                stopX = it.getAnimatedValue("stopX") as Float
                stopY = it.getAnimatedValue("stopY") as Float
                invalidate()
            }
        }.start()
    }
}