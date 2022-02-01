package rpg

import game.GameName
import game.IGame
import rpg.entity.player.Player
import rpg.map.Map

@GameName(name = "RPG")
class TextRPG : IGame {

    /*
    TextRPG 는 간단하고 여유롭게 할만한 미니게임입니다.
    보스를 이기면 그냥 깨는 간단한 게임입니다.

    플레이어 기본 스탯은 10 / 4 입니다.
    최대레벨은 5렙이며
    플레이는 단순히 선택지를 선택하는 것만으로 진행됩니다.

    싸움은 반드시 1 대 1 형태로 합니다.
     */

    companion object {
        var finish = false // 게임을 엔딩까지 보았는 가
    }

    override fun init() {
        finish = false
        Map.init()
        val player = Player(1, 10, 4, name = "USER")
        Adventure.init(player)
    }

    override fun gameStart() {

        while (!Adventure.gameOver()) {
            gameStep()
        }

        val saveWhether = gameEnd()
        val save = saveWhether.equals("Y")
        if (save) {
            gameSave()
        }
    }

    override fun gameStep() {
        Adventure.move()
    }

    override fun gameEnd(): String? {
        TODO("Not yet implemented")
    }

    override fun gameSave() {
        TODO("Not yet implemented")
    }
}