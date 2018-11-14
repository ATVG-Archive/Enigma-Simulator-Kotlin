package at.atvgstudios.gitlab.thirtiethpoweroften

import processing.core.PApplet
import processing.core.PConstants.CENTER
import processing.core.PVector
import java.awt.SystemColor.text



class Light(val app: PApplet, var letter: Char, var number: Int) {
    private var pos: PVector = PVector()
    var lightUp: Boolean = false

    init{
        var level: Int
        var rowPos: Int
        var x: Float
        var y: Float
        when {
            number < 10 -> {
                level = 1
                rowPos = number
                x = (rowPos+1.0f)*app.width/11
            }
            number < 19 -> {
                level = 2
                rowPos = number - 10
                x = (rowPos+1.5f)*app.width/11
            }
            else -> {
                level = 3
                rowPos = number - 19
                x = (rowPos+2f)*app.width/11
            }
        }
        y = app.height/3 + level*(app.height*2/3)/4f
        pos = PVector(x,y)
    }

    fun show() {
        if (lightUp) {
            app.imageMode(CENTER)
            app.image(app.loadImage("light.png"), pos.x, pos.y)
            app.fill(200f, 100f, 0f)
        } else {
            app.strokeWeight(5f)
            app.fill(150)
            app.ellipse(pos.x, pos.y, 80f, 80f)
            app.fill(50)
        }
        app.textAlign(CENTER, CENTER)
        app.textSize(20f)
        app.text(letter, pos.x, pos.y)
    }
}