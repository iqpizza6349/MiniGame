package game

import baseball.BaseBall
import game.save.GameLog
import game.save.GameSave
import updown.UpDown

class GameIO {

    companion object {

        fun input(isLog: Boolean) : List<Int> {
            val values: List<String> = readLine()?.split(" ") ?: return arrayListOf()
            if (isLog) {
                GameLog.writeLog(values)
            }
            val integers = arrayListOf(-1)
            integers.clear()

            for (v in values) {
                integers.add(v.toInt())
            }

            return integers
        }

        fun output(text: String) {
            println(text)
        }
        
        fun output(text: String, delay: Long) {
            output(text)
            Thread.sleep(delay)
        }

        fun randomGameResult(finish: Boolean, game: IGame) : String? {
            output("게임 종료")
            val result = if (finish) "승" else "패"

            if (!finish) {
                val gameName = GameInfo.getGameName(game)

                if (gameName == "야구게임") {
                    output("정답은 ${BaseBall.balls} 입니다.")
                }
                else if (gameName == "업앤다운") {
                    output("정답은 ${UpDown.randomValue} 입니다.")
                }
            }

            output("게임 결과: $result")
            output("게임을 저장하시겠습니까? (Y/N)")
            return readLine()
        }

        fun randomGameSave(finish: Boolean, count: Int, game: IGame) {
            var text = if (finish) {
                "${count + 1}번 만에 맞추셨습니다."
            } else {
                "결국 못 맞추셨습니다."
            }

            val gameName = GameInfo.getGameName(game)

            text += if (gameName == "야구게임") {
                "\n${BaseBall.balls}"
            } else {
                "\n${UpDown.randomValue}"
            }

            text += "\n" + GameLog.readLogs()

            GameSave.save(gameName, text)
        }

        fun levelUp(level: Int) {
            line()
            println(level)
            line()
        }

        private fun line() {
            println("===========================")
        }

    }


}