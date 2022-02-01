package rpg

import game.GameIO
import rpg.entity.Entity
import rpg.entity.player.Player
import rpg.map.Map

class Adventure {
    
    /*
    GameIO output 을 위주로 입력을 받아
    이를 기반으로 게임을 이어나감

    단, 한 번 간 곳은 map 에서 사라짐 (다시는 돌아갈 수 없음)

     */

    companion object {

        private var map = Map.map
        private var stageName = "마을"
        private var stage = map[stageName]
        private var player: Player ?= null

        fun init(player: Player) {
            this.player = player
            stage = map["마을"]
            GameIO.output("당신은 암흑 동굴에 사는 마왕을 쓰러뜨리면 됩니다.")
        }

        fun move() {
            battleStep(Map.monsterMap[stage])
        }

        fun gameOver() : Boolean {
            return player?.isDead()!!
        }

        private fun battle(target: Entity) {
            // 전투를 함
            GameIO.output(player?.info()!!)
            GameIO.output(target.info())

            GameIO.output("1. 공격 / 2. 방어")
            val action = GameIO.input(true)[0]

            if (action == 1) {
                val tempHP = target.health
                if (Math.random() >= 1 - player?.level!! * 0.1) {
                    player?.attack(player?.damage!! * 2, target)
                }
                else {
                    player?.attack(target)
                }

                GameIO.output("${target.name}에게 ${tempHP - target.health}만큼 피해를 주었다.")
            }

            if (target.isDead()) {
                GameIO.output("${target.name}을/를 쓰러뜨렸다.")
                return
            }

            val tempHP = player?.health!!

            val miss = Math.random() < 1 - (target.level * 0.2) // 몬스터 빗나감 효과

            if (!miss) {
                if (action != 1) {
                    player?.defense(target.damage)
                    GameIO.output("방어를 하였다.")
                } else {
                    target.attack(player!!)
                }

                GameIO.output("${tempHP - player?.health!!}의 피해를 입었다.")

                if (player?.isDead()!!) {
                    GameIO.output("패배했다.")
                }
            }
            else {
                GameIO.output("${target.name}은 본인 무기를 다루는 데 서투르는 듯 보인다. 0의 피해를 입었다.")
            }
        }

        private fun battleStep(entity: Entity?) {
            GameIO.output("${stage}에서 ${entity?.name}을 만났다.")
            while (!entity?.isDead()!! && !player?.isDead()!!) {
                battle(entity)
            }

            if (!player?.isDead()!!) {
                player?.addExp((entity.level * 10))!!

                if (player?.checkLevel()!!) {
                    GameIO.levelUp(player?.level!!)
                }
                stageName = findStage(stageName)
                stage = map[stageName]
            }
        }

        private fun findStage(currentStage: String?) : String {
            return when (currentStage) {
                "마을" -> "마을 외곽"
                "마을 외곽" -> "동굴"
                "동굴" -> "암흑동굴"
                else -> "마을"
            }
        }

    }

}