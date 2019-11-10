import java.awt.Graphics2D
import java.awt.Image
import javax.imageio.ImageIO

abstract class Stage{
    var tick: Int = 0
    var maxTick: Int = 360

    abstract fun draw(graphics2D: Graphics2D)
}

class Stage0 : Stage(){
    override fun draw(graphics2D: Graphics2D) {
        if (maxTick == tick){ Run.main.stage = 1; return }

        graphics2D.rotate(Math.toRadians(tick.toDouble()), (Run.main.width/2).toDouble(), (Run.main.height/2).toDouble())

        graphics2D.drawImage(Stages.coffee, 0, 0, Run.main.width, Run.main.height, null)

        graphics2D.rotate(Math.toRadians(-tick.toDouble()), -(Run.main.width/2).toDouble(), -(Run.main.height/2).toDouble())

        if (tick % 18 == 0) Run.main.color++
        if (Run.main.color == 100) Run.main.color = 0
        tick++
    }
}

class Stage1 : Stage(){
    override fun draw(graphics2D: Graphics2D) {
        if (maxTick == tick){ Run.main.stage = 2; return }

        val width = Run.main.width
        val height = Run.main.height

        val transform = graphics2D.transform

        graphics2D.rotate(Math.toRadians(tick.toDouble()), (width/2).toDouble(), (height/2).toDouble())

        graphics2D.drawImage(Stages.coffee, width/4, 0, width/2, height/2, null)

        graphics2D.rotate(Math.toRadians(-tick.toDouble()), -(width/2).toDouble(), -(height/2).toDouble())

        graphics2D.transform = transform

        graphics2D.rotate(Math.toRadians((tick+180).toDouble()), (width/2).toDouble(), (height/2).toDouble())

        graphics2D.drawImage(Stages.coffee, width/4, 0, width/2, height/2, null)

        graphics2D.rotate(Math.toRadians(-(tick+180).toDouble()), -(width/2).toDouble(), -(height/2).toDouble())

        graphics2D.transform = transform

        if (tick % 18 == 0) Run.main.color++
        if (Run.main.color == 100) Run.main.color = 0
        tick++
    }
}

class Stage2 : Stage(){
    override fun draw(graphics2D: Graphics2D) {
        if (maxTick == tick){ Run.main.stage = 3; return }

        val width = Run.main.width
        val height = Run.main.height

        val transform = graphics2D.transform

        for ( i in 0..2 ){
            graphics2D.rotate(Math.toRadians((tick+(i*120)).toDouble()), (width/2).toDouble(), (height/2).toDouble())

            graphics2D.drawImage(Stages.coffee, (width/4), 0, width/2, height/2, null)

            graphics2D.rotate(Math.toRadians(-(tick+(i*120)).toDouble()), -(width/2).toDouble(), -(height/2).toDouble())

            graphics2D.transform = transform

        }

        if (tick % 18 == 0) Run.main.color++
        if (Run.main.color == 100) Run.main.color = 0
        tick++
    }
}

class Stage3 : Stage(){
    override fun draw(graphics2D: Graphics2D) {
        if (maxTick == tick){ Run.main.stage = 4; return }

        val width = Run.main.width
        val height = Run.main.height

        val transform = graphics2D.transform

        for ( i in 0..3 ){
            graphics2D.rotate(Math.toRadians((tick+(i*90)).toDouble()), (width/2).toDouble(), (height/2).toDouble())

            graphics2D.drawImage(Stages.coffee, (width/4), tick, width/2, height/2, null)

            graphics2D.rotate(Math.toRadians(-(tick+(i*90)).toDouble()), -(width/2).toDouble(), -(height/2).toDouble())

            graphics2D.transform = transform

        }

        if (tick % 18 == 0) Run.main.color++
        if (Run.main.color == 100) Run.main.color = 0
        tick++
    }
}

class Stage4 : Stage(){
    private var offset: Int = 1

    override fun draw(graphics2D: Graphics2D) {
        if (maxTick == tick){ offset = -1 }

        val width = Run.main.width
        val height = Run.main.height

        val transform = graphics2D.transform

        for ( i in 0..4 ){
            graphics2D.rotate(Math.toRadians((tick+(i*(360/5))).toDouble()), (width/2).toDouble(), (height/2).toDouble())

            graphics2D.drawImage(Stages.coffee, (width/4), tick, width/(i+2), height/(i+2), null)

            graphics2D.rotate(Math.toRadians(-(tick+(i*(360/5))).toDouble()), -(width/2).toDouble(), -(height/2).toDouble())

            graphics2D.transform = transform

        }

        if (tick % 18 == 0) Run.main.color++
        if (Run.main.color == 100) Run.main.color = 0
        tick+=offset
        if (tick == -1){offset = 1}
    }
}

object Stages {
    val coffee: Image = ImageIO.read(this.javaClass.classLoader.getResource("coffee.png"))
    val stage0: Stage = Stage0()
    val stage1: Stage = Stage1()
    val stage2: Stage = Stage2()
    val stage3: Stage = Stage3()
    val stage4: Stage = Stage4()
}