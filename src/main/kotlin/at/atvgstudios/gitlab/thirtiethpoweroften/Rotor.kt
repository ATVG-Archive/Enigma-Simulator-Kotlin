package at.atvgstudios.gitlab.thirtiethpoweroften

import processing.core.PApplet
import processing.core.PConstants.CENTER

class Rotor(val app: PApplet, var rotorNo: Int, val rotorPos: Int) {
    private var wiring = arrayOf(Array(1){0})
    var position: Int = 0

    init {
        when(rotorNo){
            0 -> wiring = arrayOf(arrayOf(0, 15), arrayOf(1, 4), arrayOf(2, 25), arrayOf(3, 20), arrayOf(4, 14), arrayOf(5, 7), arrayOf(6, 23), arrayOf(7, 18), arrayOf(8, 2), arrayOf(9, 21), arrayOf(10, 5), arrayOf(11, 12), arrayOf(12, 19), arrayOf(13, 1), arrayOf(14, 6), arrayOf(15, 11), arrayOf(16, 17), arrayOf(17, 8), arrayOf(18, 13), arrayOf(19, 16), arrayOf(20, 9), arrayOf(21, 22), arrayOf(22, 0), arrayOf(23, 24), arrayOf(24, 3), arrayOf(25, 10))
            1 -> wiring = arrayOf(arrayOf(0, 25), arrayOf(1, 14), arrayOf(2, 20), arrayOf(3, 4), arrayOf(4, 18), arrayOf(5, 24), arrayOf(6, 3), arrayOf(7, 10), arrayOf(8, 5), arrayOf(9, 22), arrayOf(10, 15), arrayOf(11, 2), arrayOf(12, 8), arrayOf(13, 16), arrayOf(14, 23), arrayOf(15, 7), arrayOf(16, 12), arrayOf(17, 21), arrayOf(18, 1), arrayOf(19, 11), arrayOf(20, 6), arrayOf(21, 13), arrayOf(22, 9), arrayOf(23, 17), arrayOf(24, 0), arrayOf(25, 19))
            2 -> wiring = arrayOf(arrayOf(0, 4), arrayOf(1, 7), arrayOf(2, 17), arrayOf(3, 21), arrayOf(4, 23), arrayOf(5, 6), arrayOf(6, 0), arrayOf(7, 14), arrayOf(8, 1), arrayOf(9, 16), arrayOf(10, 20), arrayOf(11, 18), arrayOf(12, 8), arrayOf(13, 12), arrayOf(14, 25), arrayOf(15, 5), arrayOf(16, 11), arrayOf(17, 24), arrayOf(18, 13), arrayOf(19, 22), arrayOf(20, 10), arrayOf(21, 19), arrayOf(22, 15), arrayOf(23, 3), arrayOf(24, 9), arrayOf(25, 2))
            3 -> wiring = arrayOf(arrayOf(0, 8), arrayOf(1, 12), arrayOf(2, 4), arrayOf(3, 19), arrayOf(4, 2), arrayOf(5, 6), arrayOf(6, 5), arrayOf(7, 17), arrayOf(8, 0), arrayOf(9, 24), arrayOf(10, 18), arrayOf(11, 16), arrayOf(12, 1), arrayOf(13, 25), arrayOf(14, 23), arrayOf(15, 22), arrayOf(16, 11), arrayOf(17, 7), arrayOf(18, 10), arrayOf(19, 3), arrayOf(20, 21), arrayOf(21, 20), arrayOf(22, 15), arrayOf(23, 14), arrayOf(24, 9), arrayOf(25, 13))
            4 -> wiring = arrayOf(arrayOf(0, 16), arrayOf(1, 22), arrayOf(2, 4), arrayOf(3, 17), arrayOf(4, 19), arrayOf(5, 25), arrayOf(6, 20), arrayOf(7, 8), arrayOf(8, 14), arrayOf(9, 0), arrayOf(10, 18), arrayOf(11, 3), arrayOf(12, 5), arrayOf(13, 6), arrayOf(14, 7), arrayOf(15, 9), arrayOf(16, 10), arrayOf(17, 15), arrayOf(18, 24), arrayOf(19, 23), arrayOf(20, 2), arrayOf(21, 21), arrayOf(22, 1), arrayOf(23, 13), arrayOf(24, 12), arrayOf(25, 11))
        }
    }

    fun runThrough(input: Int, forward: Boolean): Int{
        if(forward){
            val x = (input+position) % 26
            return wiring[x][1]
        } else {
            for(i in 0 until 26){
                var output = (wiring[i][0]-position)
                while(output<0){
                    output += 26
                }
                output %= 26
                return output
            }
        }
        return  -1
    }

    fun show(){
        val x = app.width/2 - ((rotorPos-2)*200) +0f
        app.rectMode(CENTER)
        app.fill(255)
        app.rect(x, 200f, 50f, 120f)
        app.fill(230)
        app.rect(x, 160f, 50f, 40f)
        app.rect(x, 240f, 50f, 40f)
        app.fill(0)
        app.textSize(20f)
        when (position) {
            0 -> {
                app.text(1f, x, 160f)
                app.text(26f, x, 200f)
                app.text(25f, x, 240f)
            }
            1 -> {
                app.text(position+1f, x, 160f)
                app.text(position+0f, x, 200f)
                app.text(26f, x, 240f)
            }
            else -> {
                app.text(position+1f, x, 160f)
                app.text(position+0f, x, 200f)
                app.text(position-1f, x, 240f)
            }
        }
        app.fill(255)
        app.textSize(30f)
        app.text(rotorNo+1f, x, 100f)
    }

    fun click(x: Int, y: Int) {
        val posX = app.width / 2 - (rotorPos - 2) * 200 +0f
        if (x < posX + 25 && x > posX - 25 && y > 160 && y < 240) {
            position += 1
            position %= 26
        } else if (x < posX + 25 && x > posX - 25 && y > 70 && y < 130) {
            nextRotor()
        }
    }

    private fun nextRotor() {
        rotorNo = (rotorNo + 1) % 5
        when (rotorNo) {
            0 -> wiring = arrayOf(arrayOf(0, 15), arrayOf(1, 4), arrayOf(2, 25), arrayOf(3, 20), arrayOf(4, 14), arrayOf(5, 7), arrayOf(6, 23), arrayOf(7, 18), arrayOf(8, 2), arrayOf(9, 21), arrayOf(10, 5), arrayOf(11, 12), arrayOf(12, 19), arrayOf(13, 1), arrayOf(14, 6), arrayOf(15, 11), arrayOf(16, 17), arrayOf(17, 8), arrayOf(18, 13), arrayOf(19, 16), arrayOf(20, 9), arrayOf(21, 22), arrayOf(22, 0), arrayOf(23, 24), arrayOf(24, 3), arrayOf(25, 10))
            1 -> wiring = arrayOf(arrayOf(0, 25), arrayOf(1, 14), arrayOf(2, 20), arrayOf(3, 4), arrayOf(4, 18), arrayOf(5, 24), arrayOf(6, 3), arrayOf(7, 10), arrayOf(8, 5), arrayOf(9, 22), arrayOf(10, 15), arrayOf(11, 2), arrayOf(12, 8), arrayOf(13, 16), arrayOf(14, 23), arrayOf(15, 7), arrayOf(16, 12), arrayOf(17, 21), arrayOf(18, 1), arrayOf(19, 11), arrayOf(20, 6), arrayOf(21, 13), arrayOf(22, 9), arrayOf(23, 17), arrayOf(24, 0), arrayOf(25, 19))
            2 -> wiring = arrayOf(arrayOf(0, 4), arrayOf(1, 7), arrayOf(2, 17), arrayOf(3, 21), arrayOf(4, 23), arrayOf(5, 6), arrayOf(6, 0), arrayOf(7, 14), arrayOf(8, 1), arrayOf(9, 16), arrayOf(10, 20), arrayOf(11, 18), arrayOf(12, 8), arrayOf(13, 12), arrayOf(14, 25), arrayOf(15, 5), arrayOf(16, 11), arrayOf(17, 24), arrayOf(18, 13), arrayOf(19, 22), arrayOf(20, 10), arrayOf(21, 19), arrayOf(22, 15), arrayOf(23, 3), arrayOf(24, 9), arrayOf(25, 2))
            3 -> wiring = arrayOf(arrayOf(0, 8), arrayOf(1, 12), arrayOf(2, 4), arrayOf(3, 19), arrayOf(4, 2), arrayOf(5, 6), arrayOf(6, 5), arrayOf(7, 17), arrayOf(8, 0), arrayOf(9, 24), arrayOf(10, 18), arrayOf(11, 16), arrayOf(12, 1), arrayOf(13, 25), arrayOf(14, 23), arrayOf(15, 22), arrayOf(16, 11), arrayOf(17, 7), arrayOf(18, 10), arrayOf(19, 3), arrayOf(20, 21), arrayOf(21, 20), arrayOf(22, 15), arrayOf(23, 14), arrayOf(24, 9), arrayOf(25, 13))
            4 -> wiring = arrayOf(arrayOf(0, 16), arrayOf(1, 22), arrayOf(2, 4), arrayOf(3, 17), arrayOf(4, 19), arrayOf(5, 25), arrayOf(6, 20), arrayOf(7, 8), arrayOf(8, 14), arrayOf(9, 0), arrayOf(10, 18), arrayOf(11, 3), arrayOf(12, 5), arrayOf(13, 6), arrayOf(14, 7), arrayOf(15, 9), arrayOf(16, 10), arrayOf(17, 15), arrayOf(18, 24), arrayOf(19, 23), arrayOf(20, 2), arrayOf(21, 21), arrayOf(22, 1), arrayOf(23, 13), arrayOf(24, 12), arrayOf(25, 11))
        }
    }
}