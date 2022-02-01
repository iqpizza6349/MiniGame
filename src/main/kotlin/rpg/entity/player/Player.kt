package rpg.entity.player

import game.GameIO
import rpg.entity.Entity

class Player(override var level: Int, override var health: Int, override var damage: Int, override var name: String) : Entity {

    var exp = 0
    private val lvl = arrayListOf(10, 20, 30, 40)

    override fun say(text: String) {
        GameIO.output(text)
    }

    fun addExp(exp: Int) {
        this.exp += exp
    }

    fun checkLevel() : Boolean {
        return if (exp >= lvl[level - 1]) {
            this.exp = 0
            level += 1
            damage += 1
            health += 5
            true
        } else {
            false
        }
    }

}