package com.example.myapplication
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView

@SuppressLint("AppCompatCustomView")
class GraphView(context : Context, attributeSet: AttributeSet?) : ImageView (context, attributeSet) {

    constructor(context: Context) : this(context, null)

    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var lineColor = Color.GREEN

    var bgLinesPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var backgroundLinesColor = Color.LTGRAY

    var pointCircle = Paint(Paint.ANTI_ALIAS_FLAG)
    var pointCircleColor = Color.RED

    var pointOuterCircle = Paint(Paint.ANTI_ALIAS_FLAG)

    var values: Array<Int>? = null
        set(value) {
            field = value
        }

    init {
        paint.color = lineColor
        paint.strokeWidth = 5F

        pointCircle.strokeWidth = 10F
        pointCircle.color = pointCircleColor

        pointOuterCircle.style = Paint.Style.STROKE
        pointOuterCircle.color = pointCircleColor
        pointOuterCircle.strokeWidth = 3F

        bgLinesPaint.color = backgroundLinesColor
        bgLinesPaint.textSize = 30F
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (values == null) {
            return
        }

        var temp = height.toFloat() / 10

        canvas?.drawLine(
            0F,
            height.toFloat() - 1,
            width.toFloat(),
            height.toFloat() - 1,
            bgLinesPaint
        )
        for (i in 0 until 10) {
            canvas?.drawLine(
                0F,
                temp * i,
                width.toFloat(),
                temp * i,
                bgLinesPaint
            )
        }

        canvas?.drawText("100", 0F, 25F, bgLinesPaint)
        for (i in 0 until 110 step 10) {
            canvas?.drawText(
                i.toString(),
                0F,
                height - (temp * (i / 10)),
                bgLinesPaint
            )
        }

        var xAxisPoint = (width / values!!.size).toFloat()
        var yAxisPoint = (height / 10).toFloat()

        for (i in 0 until values!!.size) {
            var tempNum = 0
            if (i == 0) {
                tempNum = 0
            } else {
                tempNum = values!![i - 1]
            }
            canvas?.drawLine(
                xAxisPoint * i,
                height - (((tempNum.toFloat()) / 10) * yAxisPoint),
                (xAxisPoint * i) + xAxisPoint,
                height - (((values!![i].toFloat()) / 10) * yAxisPoint),
                paint
            )

            canvas?.drawCircle(
                xAxisPoint * i,
                height - (((tempNum.toFloat()) / 10) * yAxisPoint),
                7F,
                pointCircle
            )

            canvas?.drawCircle(
                xAxisPoint * i,
                height - (((tempNum.toFloat()) / 10) * yAxisPoint),
                20F,
                pointOuterCircle
            )
        }

        canvas?.drawCircle(
            xAxisPoint * values!!.size,
            height - (((values!![values!!.size - 1].toFloat()) / 10) * yAxisPoint),
            7F,
            pointCircle
        )

        canvas?.drawCircle(
            xAxisPoint * values!!.size,
            height - (((values!![values!!.size - 1].toFloat()) / 10) * yAxisPoint),
            20F,
            pointOuterCircle
        )
    }
}