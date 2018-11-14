package at.atvgstudios.gitlab.thirtiethpoweroften

import processing.core.PApplet
import processing.core.PConstants
import processing.core.PImage

class App: PApplet() {
    var letters = Array(26){Light(PApplet(), ' ', 0)}
    private var letterOrder: String = "QWERTYUIOPASDFGHJKLZXCVBNM"
    var letterOrderLowerCase: String = "qwertyuiopasdfghjklzxcvbnm"
    var keyIsDown: Boolean = false
    var keyDown: Char = ' '
    var keyLight: Char = ' '
    private var background: PImage = PImage()

    var enigma: Enigma = Enigma(this)
    override fun settings() {
        fullScreen()
    }

    override fun setup() {
        frameRate(30f)
        for(i in 0 until letters.size){
            letters[i] = Light(this, letterOrder[i], i)
        }

        background = loadImage("blacktexture.jpg")
        enigma.randomRotors()
        enigma.randomPositions()
    }

    override fun draw() {
        background(0f)
        imageMode(PConstants.CORNER)
        image(background,0f,0f, width+0f, height+0f)
        enigma.show(letters)
    }

    override fun mousePressed() {
        enigma.click(mouseX, mouseY)
    }

    override fun keyPressed() {
        if(letterOrderLowerCase.indexOf(key) != -1 && !keyIsDown && !enigma.showPlugs){
            val output = enigma.runMachine(key)
            if(output == '1') return

            keyLight = output
            letters[letterOrderLowerCase.indexOf(output)].lightUp = true
            keyIsDown = true
            keyDown = key
        }
    }

    override fun keyReleased() {
        if(letterOrderLowerCase.indexOf(key) != -1 && key == keyDown){
            letters[letterOrderLowerCase.indexOf(key)].lightUp = false
            keyIsDown = false
        }
    }
}

fun main(args: Array<String>){
    PApplet.runSketch(arrayOf("ThirtiethPowerOfThen"), App())
}