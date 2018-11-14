package at.atvgstudios.gitlab.thirtiethpoweroften

import processing.core.PApplet
import processing.core.PApplet.floor
import java.awt.SystemColor.text
import javax.swing.Spring.height





class Enigma(val app: PApplet) {
    var rotor1: Rotor = Rotor(app, 0,0)
    var rotor2: Rotor = Rotor(app, 0,0)
    var rotor3: Rotor = Rotor(app, 0,0)
    var end: EndThing = EndThing()
    var plugBoard: PlugBoard = PlugBoard(app)
    var showPlugs: Boolean = false
    val letterOrderLowerCase: String = "qwertyuiopasdfghjklzxcvbnm"

    fun setRotors(first: Int, second: Int, third: Int) {
        if (first != second && first != third && second != third) {
            rotor1 = Rotor(app, first, 1)
            rotor2 = Rotor(app, second, 2)
            rotor3 = Rotor(app, third, 3)
        }
    }

    fun setRotorPositions(first: Int, second: Int, third: Int) {
        rotor1.position = first
        rotor2.position = second
        rotor3.position = third
    }

    fun runMachine(inputChar: Char): Char {
        if (rotor1.rotorNo == rotor2.rotorNo || rotor3.rotorNo == rotor2.rotorNo || rotor1.rotorNo == rotor3.rotorNo) {
            println("Error rotors cannot have the same number")
            return '1'
        }
        val inputNo = letterOrderLowerCase.indexOf(inputChar)

        var currentNo = inputNo
        currentNo = plugBoard.runThrough(currentNo)
        currentNo = rotor1.runThrough(currentNo, true)
        currentNo = rotor2.runThrough(currentNo, true)
        currentNo = rotor3.runThrough(currentNo, true)
        currentNo = end.runThrough(currentNo, true)
        currentNo = rotor3.runThrough(currentNo, false)
        currentNo = rotor2.runThrough(currentNo, false)
        currentNo = rotor1.runThrough(currentNo, false)
        currentNo = plugBoard.runThrough(currentNo)
        if (currentNo == -1) {
            println(rotor1.position)
            println(rotor2.position)
            println(rotor3.position)
        }

        if (currentNo == inputNo) {
            println(inputNo)
            println(rotor1.position)
            println(rotor2.position)
            println(rotor3.position)
        }
        moveRotors()

        return letterOrderLowerCase[currentNo]
    }

    fun moveRotors() {
        rotor1.position += 1
        if (rotor1.position != 26) return
        rotor1.position = 0
        rotor2.position += 1
        if (rotor2.position == 26) {
            rotor2.position = 0
            rotor3.position += 1
            if (rotor3.position == 26) {
                rotor3.position = 0
            }
        }
    }

    fun show(letters: Array<Light>) {
        if (!showPlugs) {
            app.stroke(0)
            for (i in 0 until letters.size) {
                letters[i].show()
            }
            rotor1.show()
            rotor2.show()
            rotor3.show()
            if (rotor1.rotorNo == rotor2.rotorNo || rotor3.rotorNo == rotor2.rotorNo || rotor1.rotorNo == rotor3.rotorNo) {
                app.fill(255f, 0f, 0f)
                app.text("Cannot use the same rotor twice", app.width / 2f, 50f)
            }
        } else {
            plugBoard.show()
        }
    }

    fun randomRotors() {
        val rand1 = floor(app.random(5f))
        var rand2 = floor(app.random(5f))
        while (rand1 == rand2) {
            rand2 = floor(app.random(5f))
        }

        var rand3 = floor(app.random(5f))
        while (rand1 == rand3 || rand2 == rand3) {
            rand3 = floor(app.random(5f))
        }
        setRotors(rand1, rand2, rand3)
    }

    fun randomPositions() {
        setRotorPositions(floor(app.random(26f)), floor(app.random(26f)), floor(app.random(26f)))
    }

    fun click(x: Int, y: Int) {
        if (y > app.height * (9.0 / 10.0) && !plugBoard.movingPlug) {//if clicking the bottom of the screen then switch between plugs anad lamps
            showPlugs = !showPlugs
        } else {

            rotor1.click(x, y)
            rotor2.click(x, y)
            rotor3.click(x, y)
            plugBoard.click(x, y)
        }
    }

    fun processWord(input: CharArray): CharArray {
        val output = CharArray(input.size)
        for (i in input.indices) {
            output[i] = runMachine(input[i])
        }
        return output
    }
}