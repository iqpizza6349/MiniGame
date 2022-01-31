package baseball

class CheckBall {

    companion object {

        private var gameValues: List<Int> = arrayListOf()
        
        fun init(gameValues: List<Int>) {
            this.gameValues = gameValues
        }

        fun getBalls(userInput: List<Int>) : Int {
            var ballCount = 0;

            for (user in userInput) {
                if (gameValues.contains(user)) {
                    ballCount++
                }
            }

            return ballCount - getStrike(userInput)     // 숫자 맞추었을 때, 해당 숫자가 스트라이크일 수 있기에 스트라이크 수만큼 감소
        }

        fun getStrike(userInput: List<Int>) : Int {
            var strikeCount = 0;

            for (i in userInput.indices) {
                if (userInput[i] == gameValues[i]) {
                    strikeCount++
                }
            }

            return strikeCount
        }

        fun isAllStrike(userInput: List<Int>) : Boolean {
            return getStrike(userInput) > 2
        }

        fun isOut(userInput: List<Int>) : Boolean {
            val ball = getBalls(userInput)
            val strike = getStrike(userInput)

            return (ball < 1 && strike < 1)
        }
    }

}