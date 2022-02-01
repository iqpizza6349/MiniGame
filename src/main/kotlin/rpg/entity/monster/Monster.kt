package rpg.entity.monster

import rpg.entity.Entity

class Monster(override var level: Int, override var health: Int, override var damage: Int, override var name: String) :
    Entity {
}