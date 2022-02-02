package game.save

class GameLog {

    /*
    게임 로그를 기억하는 클래스입니다.
    게임을 진행하면서 GameIO를 통해 입력한 모든 값들을 일시적으로 기억합니다.
    이는 게임을 저장하면서 기록될 것입니다.
    gameStep 메소드가 호출되는 동안에만 적용됨
     */

    companion object {
        var gameLogs = arrayListOf<String>()
        
        fun writeLogs(logs: List<String>) {
            gameLogs.addAll(logs)
        }

        fun writeAdventureLog(logs: List<String>) {
            for (select in logs) {
                val msg = if (select == "1") "공격" else "방어"
                gameLogs.add(msg)
            }
        }

        fun writeLog(log: String) {
            gameLogs.add(log)
        }

        fun readLogs() : List<String> {
            return gameLogs
        }

    }

}