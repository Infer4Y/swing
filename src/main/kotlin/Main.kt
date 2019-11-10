
import java.awt.*
import javax.swing.JFrame
import java.util.*
import kotlin.concurrent.schedule
import java.awt.Color


open class Main(title: String) : JFrame(title) {
    var i : Double = 0.toDouble()
    var l : Int = 0
    var color : Int = 0
    var rot : Double = 0.toDouble()
    var colors = arrayOfNulls<Color>(100)

    var stage : Int = 0

    init {
        size = Dimension(600,600)

        defaultCloseOperation = 3
        isVisible = true

        createBufferStrategy(4)

        val ARRAY_SIZE = 100
        val jump = 360.0 / (ARRAY_SIZE * 1.0)
        for (i in colors.indices) {
            colors[i] = Color.getHSBColor(((jump * i).toFloat()), 1.0f, 1.0f)
        }
    }



    fun stageHandler(graphics2D: Graphics2D, stage: Int){
        when(stage){
            0-> Stages.stage0.draw(graphics2D)
            1-> Stages.stage1.draw(graphics2D)
            2-> Stages.stage2.draw(graphics2D)
            3-> Stages.stage3.draw(graphics2D)
            4-> Stages.stage4.draw(graphics2D)
            else -> return
        }
    }

    override fun paint(g: Graphics?) {
        if (g == null) return
        if (bufferStrategy == null) {createBufferStrategy(4); return}

        var graphics2D : Graphics2D = bufferStrategy.drawGraphics as Graphics2D

        graphics2D.color = colors[color]
        graphics2D.fillRect(0,0,width,height)

        stageHandler(graphics2D, stage)

        graphics2D.dispose()

        bufferStrategy.show()
    }
}

object Run {
    lateinit var main: Main
    var timer: Timer = Timer()

    fun late(){
        timer.schedule(1000/60) {
            main.paint(main.graphics)
            late()
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        main = Main("Test")
        late()
    }
}