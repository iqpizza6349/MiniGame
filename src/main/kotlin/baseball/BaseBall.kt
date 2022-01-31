package baseball

import game.GameIO
import game.GameInfo
import game.IGame
import game.GameName
import game.save.GameSave

@GameName(name = "야구게임")
class BaseBall : IGame {

    /*
    ### 플레이방법 ###
    총 9번동안의 기회를 줌
    해당 9번 안에 랜덤으로 정해진 숫자 3개 (0~9)
    를 모두 맞추면 승리한다.

    숫자는 맞지만 위치가 틀릴 경우 -> Ball
    숫자와 위치가 맞은 경우 -> Strike
    숫자와 위치가 전부 틀릴 경우 -> Out
     */

    private var life = -1 // 목숨
    private var balls = arrayListOf(-1) // 랜덤한 공들을 저장하는 리스트
    private var finish = false
    private var count = -1 // n회말

    override fun init() {
        life = 9
        count = 1
        balls.clear()
        finish = false
    }

    override fun gameStart() {
        addBalls() // 랜덤한 공들을 집어넣음
        CheckBall.init(balls)
        while (life > 0 && !finish) {
            gameStep()
        }
        val saveWhether = gameEnd()
        val save = saveWhether.equals("Y")
        if (save) {
            gameSave()
        }
    }

    private fun addBalls() {
        for (i in 0..2) {
            randomBall()
        }
    }

    private fun randomBall() {
        var number = (Math.random() * 10).toInt()
        while (balls.contains(number)) {
            number = (Math.random() * 10).toInt()
        }
        balls.add(number)
    }

    override fun gameStep() {
        GameIO.output("${count}회말")
        val userInput = GameIO.input()
        val ball = CheckBall.getBalls(userInput)
        val strike = CheckBall.getStrike(userInput)

        if (CheckBall.isAllStrike(userInput)) {
            // 전부 스트라이크인 경우
            GameIO.output("쓰리 스트라이크! 게임 종료")
            finish = true
            return
        }

        if (CheckBall.isOut(userInput)) {
            // 만일 아웃일 경우
            GameIO.output("아웃입니다.")
        }
        else {
            GameIO.output("${strike}S ${ball}B")
        }
        life--
        count++
    }

    override fun gameEnd() : String? {
        GameIO.output("게임 종료")
        val result = if (finish) "승" else "패"

        if (!finish) {
            GameIO.output("정답은 $balls 입니다.")
        }

        GameIO.output("게임 결과: $result")
        GameIO.output("게임을 저장하시겠습니까? (Y/N)")
        return readLine()
    }

    override fun gameSave() {
        // 몇 번만에 맞추셨습니다. / 결국 못 맞추었습니다. (결과)
        val text = if (finish) {
            "${life}만에 맞추셨습니다."
        } else {
            "결국 못 맞추셨습니다. $balls"
        }

        GameSave.save(GameInfo.getGameName(BaseBall()), text)
    }
}