package feedcat.cat

import feedcat.action.ActionType
import feedcat.food.FoodType

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
        if (food == FoodType.CIAO) {
            throw GrassException("츄르 먹이는 따로 해줘야합니다.")
        }

        this.fat += food.fatPoint
        this.hunger += food.hungerPoint
    }

    fun feedCIAO() {
        if (!favorGrass) {
            throw NoDrugException("${name}은/는 츄르를 좋아하지 않습니다.")
        }
        
        this.fat += FoodType.CIAO.fatPoint
        this.hunger += FoodType.CIAO.hungerPoint
    }

    fun action(actionType: ActionType) {
        this.fat -= actionType.fatPoint
        this.hunger -= actionType.hungerPoint
    }

    fun rest(hunger: Int) {
        this.hunger -= hunger
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