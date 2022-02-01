package updown

import game.GameIO
import game.GameName
import game.IGame

@GameName(name = "업앤다운")
class UpDown : IGame {

    /*
    ### 플레이 방법 ###
    1. n과 m 정수 값을 입력받음
    2. 1부터 n 까지의 수까지 랜덤한 수를 가짐
    3. m번 동안 맞출 수 있는 기회가 주어지며, 그안에 랜덤 수 (x)를 맞추어야함

     */

    companion object {
        var randomValue = -1
    }

    private var life = -1
    private var finish = false
    private var count = -1 // 시도한 횟수

    override fun init() {
        count = 0
        finish = false
        val input = GameIO.input(false)
        life = input[0]

        val i = input[1]

        val temp = ((Math.random() * i) + 1).toInt() // 최소: 1, 최대: (random() * i) + 1
        // (..후 i보다 값이 크다면 randomValue 최대값으로 고정)
        randomValue = if (temp > i) i else temp
        CheckIndex.init(randomValue)
    }

    override fun gameStart() {
        while (life > 0 && !finish) {
            gameStep()
        }
        val saveWhether = gameEnd()
        val save = saveWhether.equals("Y")
        if (save) {
            gameSave()
        }
    }

    override fun gameStep() {
        GameIO.output("${count + 1}번째 기회")
        val input = GameIO.input(true)[0]
        val checkIndex = CheckIndex.compare(input)

        if (checkIndex == 0) {
            finish = true
            return
        }

        if (checkIndex < 0) {
            // input 이 랜덤값보다 작을 경우
            GameIO.output("${input}보다 높습니다.")
        }
        else {
            GameIO.output("${input}보다 낮습니다.")
        }
        life--
        count++
    }

    override fun gameEnd(): String? {
        return GameIO.randomGameResult(finish, this)
    }

    override fun gameSave() {
        return GameIO.randomGameSave(finish, count, this)
    }
}