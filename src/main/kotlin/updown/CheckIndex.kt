package updown

class CheckIndex {

    companion object {

        private var index = -1

        fun init(index: Int) {
            this.index = index
        }

        fun compare(input: Int) : Int {
            if (input < index) {
                return -1;
            }
            else if (input > index) {
                return 1;
            }
            return 0;
        }

    }


}