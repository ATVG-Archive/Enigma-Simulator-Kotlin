package at.atvgstudios.gitlab.thirtiethpoweroften

import processing.core.PApplet
import processing.core.PApplet.floor
import java.util.*

class PlugBoard(val app: PApplet) {
    var plugs = Array(10){Plug(app, 0, 0, PlugPoint(app, 0), PlugPoint(app, 0))}
    var plugPoints = Array(26){PlugPoint(app, 0)}
    var showing = false
    var movingPlug = false
    var movingPlugNo = 0

    init {
        for (i in plugPoints.indices) {
            plugPoints[i] = PlugPoint(app, i)
        }
        randomisePlugs()
    }

    fun randomisePlugs() {
        val chosen = ArrayList<Int>()
        for (i in 0..9) {
            var rand1 = floor(app.random(26f))
            while (chosen.contains(rand1)) {
                rand1 = floor(app.random(26f))
            }
            chosen.add(rand1)
            var rand2 = floor((app.random(26f)))
            while (chosen.contains(rand2)) {
                rand2 = floor((app.random(26f)))
            }
            chosen.add(rand2)
            plugs[i] = Plug(app, rand1, rand2, plugPoints[rand1], plugPoints[rand2])
            plugPoints[rand1].occupied = true
            plugPoints[rand2].occupied = true
        }
    }

    fun show() {
        for (i in 0..25) {
            plugPoints[i].show()
        }

        for (i in plugs.indices) {
            plugs[i].showPlugs()
        }

        for (i in plugs.indices) {
            plugs[i].showLines()
        }
    }

    fun runThrough(input: Int): Int {
        for (i in plugs.indices) {
            if (plugs[i].connection1 == input) {
                return plugs[i].connection2
            } else if (plugs[i].connection2 == input) {
                return plugs[i].connection1
            }
        }

        return input
    }

    fun click(x: Int, y: Int) {
        if (!movingPlug) {
            for (i in plugs.indices) {
                if (plugs[i].click(x, y)) {
                    movingPlug = true
                    movingPlugNo = i
                    return
                }
            }
        } else {
            for (i in plugPoints.indices) {
                if (plugPoints[i].click(x, y)) {
                    if (!plugPoints[i].occupied) {
                        movingPlug = false
                        if (plugs[movingPlugNo].move1) {
                            plugs[movingPlugNo].setPlugPoint(i, plugPoints[i], 1)
                            plugs[movingPlugNo].move1 = false
                        } else {
                            plugs[movingPlugNo].setPlugPoint(i, plugPoints[i], 2)
                            plugs[movingPlugNo].move2 = false
                        }
                    }
                    return
                }
            }
        }
    }
}