package feedcat.cat.cats

import feedcat.cat.Cat

class Sugar(
    override var name: String,
    override var fat: Int,
    override var hunger: Int,
    override var favorGrass: Boolean,
    override var gender: Cat.GENDER,
    override var age: Int
) : Cat {

    /*
    이름: 설탕
    성별: 암컷
    비만도: 0
    허기: 10
    츄르: 싫어함
     */

}