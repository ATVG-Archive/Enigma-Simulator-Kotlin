package at.atvgstudios.gitlab.thirtiethpoweroften

import processing.core.PApplet
import processing.core.PConstants.CENTER
import processing.core.PVector
import java.awt.SystemColor.text



class PlugPoint(val app: PApplet, val no: Int) {
    var pos: PVector = PVector()
    var letter: Char = ' '
    private var letterNo: Int = 0
    private val letterOrder: String = "QWERTYUIOPASDFGHJKLZXCVBNM"
    var occupied: Boolean = false

    init{
        letter = letterOrder[no]
        var level: Int
        var rowPos: Int
        var x: Float
        var y: Float
        when {
            no < 10 -> {
                level = 1
                rowPos = no
                x = (rowPos+1.0f)*app.width/11
            }
            no < 19 -> {
                level = 2
                rowPos = no - 10
                x = (rowPos+1.5f)*app.width/11
            }
            else -> {
                level = 3
                rowPos = no - 19
                x = (rowPos+2f)*app.width/11
            }
        }
        y = app.height/3 + level*(app.height*2/3)/4f
        if(no%3 == 0){
            y += 15
        }
        pos = PVector(x,y)
    }

    fun show() {
        app.textAlign(CENTER, CENTER)
        app.textSize(20f)
        app.fill(255)
        app.text(letter, pos.x, pos.y - 40)
        app.fill(20)
        app.stroke(255)

        app.ellipse(pos.x, pos.y, 20f, 20f)
        app.ellipse(pos.x, pos.y + 30f, 20f, 20f)
    }

    fun click(x: Int, y: Int): Boolean {
        return x < pos.x + 15 && x > pos.x - 15 && y < pos.y + 35 && y > pos.y - 35
    }
}