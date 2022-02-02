package feedcat.action

enum class ActionType(val actionName: String, val fatPoint: Int, val hungerPoint: Int) {

    LASER("레이저포인터", 3, 1),
    SKEIN("실타래", 2, 1),
    FEATHERROD("깃털 낚시대", 4, 2),
    CATTOWER("캣타워", 3, 3)

}