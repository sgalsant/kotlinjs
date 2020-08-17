import org.w3c.dom.CanvasRenderingContext2D

import kotlin.math.*

data class Ball(var radio: Int, var color: String, var x: Int, var y: Int, var speedX: Int = 0, var speedY: Int = 0) {
    fun draw(context: CanvasRenderingContext2D) {
        context.fillStyle = color
        context.beginPath()
        context.arc(x.toDouble(), y.toDouble(), radio.toDouble(), 0.0, 2 * PI)
        context.fill()

        x += speedX
        x = min(max(x, radio), context.canvas.width - radio)

        if (x - radio <= 0 || x + radio >= context.canvas.width) speedX = -speedX + listOf(-1, 0, 0, 0, 1).random()

        y += speedY
        y = min(max(y, radio), context.canvas.height - radio)
        if (y - radio <= 0 || y + radio >= context.canvas.height) speedY = -speedY + listOf(-1, 0, 0, 0, 0, 1).random()
    }

    fun reverseSpeed() {
        speedX = -speedX
        speedY = -speedY
    }

    fun collision(ball: Ball): Boolean = this !== ball && ball.x + radio >= x-radio && ball.x - radio <=x+radio &&
            ball.y + radio >= y - radio && ball.y - radio <= y + radio

}