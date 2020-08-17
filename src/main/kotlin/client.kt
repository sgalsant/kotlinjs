import kotlinx.html.div
import kotlinx.html.dom.append
import org.w3c.dom.Node
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLInputElement


fun main() {
    document.addEventListener ("DOMContentLoaded", { load() })
   // window.requestAnimationFrame {  }
}

lateinit var context: CanvasRenderingContext2D


fun load() {
    val canvas = document.getElementsByTagName("canvas").item(0) as HTMLCanvasElement
    context = canvas.getContext("2d") as CanvasRenderingContext2D

    val button = document.getElementById("create")
    button?.addEventListener("click", {create()})

    window.requestAnimationFrame { draw() }

    document.body?.say("holaaaa")
}

val balls = mutableListOf(Ball(25, "blue", 0,0, 3, 2),
                          Ball( 22, "orange", 100, 100, 2, 2))
fun draw() {
    context.clearRect(0.0, 0.0, context.canvas.width.toDouble(), context.canvas.height.toDouble())

    for (ball in balls) {
        if (balls.any {it.collision(ball)}) ball.reverseSpeed()
        ball.draw(context)
    }

    window.requestAnimationFrame { draw() }
}

fun Node.say(msg: String) {
    append {
        div {
            +msg
        }
    }
}

fun create() {
    document.body?.say("clcik")
    val htmlColor = document.getElementById("color") as HTMLInputElement

    balls.add(Ball(25, htmlColor.value, 0,0, 1, 1))
}