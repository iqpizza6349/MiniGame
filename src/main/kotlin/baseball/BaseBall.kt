package baseball

import game.GameIO
import game.IGame
import game.GameName

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

    companion object {
        var balls = arrayListOf(-1) // 랜덤한 공들을 저장하는 리스트
    }

    private var life = -1 // 목숨
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
        val temp = (Math.random() * 10).toInt()
        var number = if (temp == 10) 9 else temp

        while (balls.contains(number)) {
            val t = (Math.random() * 10).toInt()
            number = if (t == 10) 9 else t
        }
        balls.add(number)
    }

    override fun gameStep() {
        GameIO.output("${count}회말")
        val userInput = GameIO.input(true)
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
        return GameIO.randomGameResult(finish, this)
    }

    override fun gameSave() {
        GameIO.randomGameSave(finish, count, this)
    }
}