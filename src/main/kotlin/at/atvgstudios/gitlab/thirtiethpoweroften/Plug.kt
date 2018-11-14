package at.atvgstudios.gitlab.thirtiethpoweroften

import processing.core.PApplet
import processing.core.PConstants.CENTER



class Plug(val app: PApplet, var connection1: Int, var connection2: Int, var point1: PlugPoint, var point2: PlugPoint) {
    var move1: Boolean = false
    var move2: Boolean = false

    fun showLines(){
        app.stroke(100f, 100f, 200f, 150f)
        app.strokeWeight(3f)

        when {
            move1 -> app.line(app.mouseX+0f, app.mouseY+0f, point2.pos.x, point2.pos.y+15f)
            move2 -> app.line(point1.pos.x, point1.pos.y+15f, app.mouseX+0f, app.mouseY+0f)
            else -> app.line(point1.pos.x, point1.pos.y+15f, point2.pos.x, point2.pos.y+15f)
        }
    }

    fun showPlugs() {
        app.stroke(200)
        app.fill(40)
        app.rectMode(CENTER)

        when {
            move1 -> {
                app.rect(app.mouseX+0f, app.mouseY+0f, 30f, 70f)
                app.rect(point2.pos.x, point2.pos.y + 15f, 30f, 70f)
            }
            move2 -> {
                app.rect(point1.pos.x, point1.pos.y + 15f, 30f, 70f)
                app.rect(app.mouseX+0f, app.mouseY+0f, 30f, 70f)
            }
            else -> {
                app.rect(point1.pos.x, point1.pos.y + 15, 30f, 70f)
                app.rect(point2.pos.x, point2.pos.y + 15, 30f, 70f)
                app.fill(255)
                app.textSize(10f)
                app.text(point2.letter, point1.pos.x, point1.pos.y + 15f)
                app.text(point1.letter, point2.pos.x, point2.pos.y + 15f)
            }
        }
    }

    fun click(x: Int, y: Int): Boolean {
        if (x < point1.pos.x + 15 && x > point1.pos.x - 15 && y < point1.pos.y + 50 && y > point1.pos.y - 20) {
            move1 = true
            point1.occupied = false
            return true
        } else if (x < point2.pos.x + 15 && x > point2.pos.x - 15 && y < point2.pos.y + 50 && y > point2.pos.y - 20) {
            move2 = true
            point2.occupied = false
            return true
        }
        return false
    }

    fun setPlugPoint(plugPointNo: Int, newPoint: PlugPoint, connectionNo: Int) {
        newPoint.occupied = true
        when (connectionNo) {
            1 -> {
                point1 = newPoint
                connection1 = plugPointNo
            }
            2 -> {
                point2 = newPoint
                connection2 = plugPointNo
            }
        }
    }
}