package feedcat.cat.cats

import feedcat.cat.Cat

class DefaultCat(
    override var name: String,
    override var fat: Int,
    override var hunger: Int,
    override var favorGrass: Boolean,
    override var gender: Cat.GENDER,
    override var age: Int
) : Cat {

    /*
    사용자 지정 고양이
     */

}