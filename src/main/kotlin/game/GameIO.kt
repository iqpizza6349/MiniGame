package game

class GameIO {

    companion object {
        fun input() : List<Int> {
            val values: List<String> = readLine()?.split(" ") ?: return arrayListOf()
            val integers = arrayListOf(-1);
            integers.clear()

            for (v in values) {
                integers.add(v.toInt())
            }

            return integers
        }

        fun output(text: String) {
            println(text)
        }
    }


}