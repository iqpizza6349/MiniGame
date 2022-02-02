package feedcat.food

enum class FoodType(val foodName: String, val fatPoint: Int, val hungerPoint: Int) {

    ANCHOVY("멸치", 2, 3),
    FEED("사료", 1, 1),
    CIAO("츄르", 4, 3),
    CATGRASS("고양이풀", 1, 1)

}