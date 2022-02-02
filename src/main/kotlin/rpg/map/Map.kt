package rpg.map

import rpg.entity.Entity
import rpg.entity.monster.Monster
import rpg.entity.monster.boss.Devil

class Map {

    companion object {
        val map = HashMap<String, String>() // 구역 및 상세 영역
        val monsterMap = HashMap<String, Entity>() // 구역별 몬스터

        private fun setMap() {
            map["마을"] = "사냥터"
            map["마을 외곽"] = "슬라임 서식지"
            map["동굴"] = "던전"
            map["암흑동굴"] = "마왕이네"
        }

        private fun setMonsters() {
            monsterMap["사냥터"] = Monster(1, 8, 3, name = "SLIME")
            monsterMap["슬라임 서식지"] = Monster(3, 15, 4, name = "GUARD.SLIME")
            monsterMap["던전"] = Monster(5, 15, 6, name = "SKULL")
            monsterMap["마왕이네"] = Devil(10, 20, 10, name = "BOSS")
        }

        fun init() {
            setMap()
            setMonsters()
        }
    }

}