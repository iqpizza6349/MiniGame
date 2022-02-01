package game

import baseball.BaseBall
import rpg.TextRPG
import updown.UpDown

class GameInfo {

    companion object {
        fun getGame(name: String?): IGame? {
            val gameList = arrayListOf(BaseBall(), UpDown(), TextRPG())

            for (game in gameList) {
                val gameName: GameName = game.javaClass.getAnnotation(GameName::class.java)
                if (gameName.name == name) {
                    return game
                }
            }

            return null
        }

        fun playGame(game: IGame?) {
            game?.init()
            game?.gameStart()
        }

        fun getGameName(game: IGame) : String {
            return game.javaClass.getAnnotation(GameName::class.java).name
        }
    }

}