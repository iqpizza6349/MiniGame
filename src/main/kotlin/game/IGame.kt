package game

interface IGame {
    
    /*
    게임 개발에 필요한 인터페이스입니다.
    초기화, 게임 시작, 게임 진행 상태, 게임 종료, 기록 저장가 주된 기능입니다.
     */

    fun init()  // 게임을 초기화합니다. (게임명, 게임에 요구하는 모든 변수들 초기화를 담당합니다.)

    fun gameStart() // 게임을 실행합니다. (게임의 정보 일부가 저장됩니다.)

    fun gameStep() // 게임을 진행합니다. (게임의 핵심 영역입니다.)

    fun gameEnd(): String? // 게임을 마칩니다. (추후 게임 저장 여부를 묻습니다.)

    fun gameSave() // 게임을 마친 후, .txt 파일로 저장할 수 있습니다.

}