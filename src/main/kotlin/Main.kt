import game.GameInfo
import game.IGame

fun main(args: Array<String>) {
    val game: String ?= readLine()
    val mainGame: IGame? = GameInfo.getGame(game)
    GameInfo.playGame(mainGame)
}
