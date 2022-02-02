package feedcat

import feedcat.action.ActionType
import feedcat.cat.Cat
import feedcat.cat.cats.DefaultCat
import feedcat.cat.cats.Latte
import feedcat.cat.cats.Sugar
import feedcat.food.FoodType
import game.GameIO
import game.GameInfo
import game.GameName
import game.IGame
import game.save.GameLog
import game.save.GameSave

@GameName(name = "고양이 키우기")
class FeedCat : IGame {

    /*
    고양이 키우는 게임

    활동: 먹이 주기, 츄르 주기, 장난감 투척, 상자, 휴식
     */

    companion object {
        private var cat: Cat ?= null
        private var box = false
        private var fat = false
        private var tooFat = false
    }

    override fun init() {
        // 고양이 선택하기
        box = false
        fat = false
        tooFat = false
        selectCat()
    }

    override fun gameStart() {
        // 행동 5번 하면 1년 흐른다.
        // -> 고양이는 보통 15년정도 사니까
        // 75번? 이면 충분히 애정을 붙일때 끝나니까 더 아쉬울 것으로 생각
        GameIO.output("당신은 길고양이를 만났다..", 1)
        GameIO.output("너무나도 마음에 들었기에 데리고 키우기로 했다..", 1)
        GameIO.output("고양이 이름은 ${cat?.name}으로 지었다.", 1)

        while (!cat?.isOldAge()!! && !cat?.isStarve()!! && !box && !tooFat) {
            // 자연사 혹은 아사를 하면 게임이 끝난다.
            gameStep()
        }
        val saveWhether = gameEnd()
        val save = saveWhether.equals("Y")
        if (save) {
            gameSave()
        }
    }

    private fun selectCat() {
        GameIO.output("1. 설탕(암), 2. 라떼(수), 3. 사용자 지정")
        val number = readLine()?.toInt()
        cat = when (number) {
            1 -> Sugar(name = "설탕", fat = 0, hunger = 10, favorGrass = false, gender = Cat.GENDER.FEMALE, age = 2)
            2 -> Latte(name = "라떼", fat = 0, hunger = 10, favorGrass = true, gender = Cat.GENDER.MALE, age = 3)
            else -> customCat()
        }
    }

    private fun customCat() : Cat {
        GameIO.output("고양이 이름 >> ")
        val name = readLine()
        GameIO.output("최대 포화치(최대 허기) >> ")
        val hunger = readLine()?.toInt()
        GameIO.output("츄르를 좋아하나요 (Y/N) >> ")
        val favorGrass = readLine()?.equals("Y")
        GameIO.output("고양이 성별 (M/F) >>")
        val gender = if (readLine()?.equals("M") == true) Cat.GENDER.MALE else Cat.GENDER.FEMALE
        GameIO.output("고양이 나이 >> ")
        val age = readLine()?.toInt()

        return DefaultCat(name!!, 0, hunger!!, favorGrass!!, gender, age!!)
    }

    override fun gameStep() {
        GameIO.output("${cat?.name}은 올해 ${cat?.age}살이 되었다.")
        if (cat?.isFat()!! && !fat) {
            GameIO.output("${cat?.name}은 비만이 되었습니다.")
            GameIO.output("⚠ 비만은 건강에 치명적입니다.")
            fat = true
        }
        else if (!cat?.isFat()!! && fat) {
            fat = false
        }

        if (cat?.isFat()!! && (Math.random() * 10) > 0.73142) {
            GameIO.output("${cat?.name}은 건강이 너무 악화되어 무지개다리를 건너고 말았습니다.")
            tooFat = true
            return
        }
        for (i in 0..2) {
            GameIO.output("1. 먹이 주기, 2. 츄르 주기, 3. 장난감 투척, 4. 상자, 5. 휴식")
            action(GameIO.input(false)[0])
            if (box) {
                return
            }
        }
        cat?.addAge()
    }

    private fun action(action: Int) {
        when (action) {
            1 -> feed()
            2 -> feedGrass()
            3 -> playWithToy()
            4 -> boxEvent()
            5 -> rest()
        }

        // 허기졌는 지 한 번 진행할 때마다 말해줌
        if (cat?.hunger!! < (cat?.hunger!! * 0.2)) {
            GameIO.output("${cat?.name}가 배고프다고 합니다.")
        }
    }

    private fun feed() {
        GameIO.output("무엇을 먹이겠습니까")
        GameIO.output("1. 멸치, 2. 사료, 3. 츄르")

        when (GameIO.input(false)[0]) {
            1 -> cat?.feed(FoodType.ANCHOVY)
            2 -> cat?.feed(FoodType.FEED)
            3 -> cat?.feed(FoodType.CIAO)
        }
    }
    
    private fun feedGrass() {
        try {
            cat?.feedGrass()
        } catch (e: Exception) {
            GameIO.output(e.message!!)
        }
    }

    private fun playWithToy() {
        GameIO.output("무엇을 놀게 해주겠습니까")
        GameIO.output("1. 레이저포인터, 2. 실타래, 3. 깃털 낚시대, 4. 캣타워")

        when (GameIO.input(false)[0]) {
            1 -> cat?.action(ActionType.LASER)
            2 -> cat?.action(ActionType.SKEIN)
            3 -> cat?.action(ActionType.FEATHERROD)
            4 -> cat?.action(ActionType.CATTOWER)
        }
    }

    private fun boxEvent() {
        GameIO.output("${cat?.name}은 상자에 들어갔습니다.")
        GameIO.output("그리곤 다시는 나오지 않았습니다.")
        box = true
    }

    private fun rest() {
        GameIO.output("휴식을 취했습니다.", 1)
        val restPoint = (Math.random() * 4).toInt() + 1 // 1 ~ 5
        cat?.rest(restPoint)
        GameIO.output("${cat?.name}은 ${restPoint}만큼 배가 고파졌습니다.")
    }

    override fun gameEnd(): String? {
        if (box) {
            GameIO.output("${cat?.name}은 박스를 매우 사랑하여 나오지 않았습니다...")
            GameIO.output("${cat?.name}의 우선순위 박스 >>>>>> 당신")
        }
        else if (cat?.isStarve()!!) {
            GameIO.output("${cat?.name}은 당신의 불찰로 인해 무지개다리를 건넜습니다..")
            GameIO.output("팁! 올해가 지나기전에 밥을 주면 아사를 면할 수 있습니다.")
        }
        else if (cat?.isOldAge()!!) {
            GameIO.output("${cat?.name}은 당신의 품에서 서서히 무지개다리로 향해갔습니다...")
            GameIO.output("${cat?.name}은 무지개다리에서도 당신을 기다릴 것입니다.")
        }

        GameIO.output("게임을 저장하시겠습니까? (Y/N)")
        return readLine()
    }

    override fun gameSave() {
        var text = if (box) {
            "${cat?.name}: 집사보다는 아늑한 상자가 더 좋다냥"
        } else if (cat?.isStarve()!! || tooFat) {
            "${cat?.name}: 집사가 주인 죽이네..."
        } else {
            "${cat?.name}: 너와 함께여서 좋았어"
        }

        text += "\n${GameLog.readLogs()}"
        GameSave.save(GameInfo.getGameName(this), text)
    }
}