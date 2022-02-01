package rpg.entity

import game.GameIO

interface Entity {

    /*
    엔티티라면 당연히 가추어져야하는 기본적인 기능들을 정의해놓은 인터페이스입니다.
     */

    var health: Int
    var level: Int
    var damage: Int
    var name: String

    /**
     * 엔티티가 말을 할 수 있습니다.
     * @param text String
     */
    fun say(text: String) {
        GameIO.output(text)
    }

    fun say() {
        GameIO.output("...")
    }

    /**
     * target Entity를 power만큼 피해를 입힙니다.
     * health가 power만큼 감소합니다.
     * @param power int
     * @param target Entity
     */
    fun attack(power: Int, target: Entity) {
        target.damage(power)
    }

    fun attack(target: Entity) {
        target.damage(this.damage)
    }

    /**
     * attacker로부터 power만큼 피해를 입습니다.
     * health가 power만큼 감소합니다.
     * @param power int
     * @param attacker Entity
     */
    fun damage(power: Int, attacker: Entity) {
        health -= power
    }

    fun damage(power: Int) {
        health -= power
    }

    /**
     * 현재 Entity가 죽었는 지 살았는 지를 판단합니다.
     * @return Boolean
     */
    fun isDead() : Boolean {
        return health <= 0
    }

    fun defense(power: Int) {
        val value = if (Math.random() >= (0.8 - level * 0.1)) 0 else (power * (1 - (level * 0.2))).toInt()
        damage(value)
    }

    fun info() : String {
        return "[lvl${level}. ${name}] HP: $health DP: $damage"
    }

}