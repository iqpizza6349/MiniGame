package game

import baseball.BaseBall

class GameInfo {

    companion object {
        fun getGame(name: String?): IGame? {
            val gameList = arrayListOf(BaseBall())

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
    }

}