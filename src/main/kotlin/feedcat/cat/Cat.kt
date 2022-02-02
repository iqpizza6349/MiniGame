package feedcat.cat

import feedcat.action.ActionType
import feedcat.food.FoodType
import game.save.GameLog

interface Cat {

    /*
    메소드: 먹이 주기, 츄르 주기, 장난감 투척, 상자, 휴식
    변수: 이름, 비만도, 허기
     */

    var name: String        // 이름
    var fat: Int            // 비만도
    var hunger: Int         // 허기
    var favorGrass: Boolean  // 고양이풀을 좋아하는 간
    var gender: GENDER      // 성별
    var age: Int

    fun feed(food: FoodType) {
        if (food == FoodType.CATGRASS) {
            throw GrassException("고양이 풀은 따로 해줘야합니다.")
        }

        GameLog.writeLog(food.foodName)

        this.fat += food.fatPoint
        this.hunger += food.hungerPoint
    }

    fun feedGrass() {
        if (!favorGrass) {
            throw NoDrugException("${name}은/는 고양이풀를 좋아하지 않습니다.")
        }

        GameLog.writeLog(FoodType.CATGRASS.foodName)

        this.fat += FoodType.CATGRASS.fatPoint
        this.hunger += FoodType.CATGRASS.hungerPoint
    }

    fun action(actionType: ActionType) {
        GameLog.writeLog(actionType.actionName)

        this.fat -= actionType.fatPoint
        this.hunger -= actionType.hungerPoint
    }

    fun rest(hunger: Int) {
        GameLog.writeLog("휴식")
        this.hunger -= hunger
    }

    fun addAge() {
        this.age++
    }

    fun isOldAge() : Boolean {
        // 자연사(노환)인가
        return this.age > 15
    }

    fun isStarve() : Boolean {
        // 아사인가
        return this.hunger < 0
    }

    private class GrassException(msg: String) : Exception(msg)
    private class NoDrugException(msg: String) : Exception(msg)

    enum class GENDER {
        MALE,
        FEMALE
    }

}