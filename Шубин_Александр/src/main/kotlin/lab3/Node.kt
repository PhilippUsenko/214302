package lab3

class Node (var leftChild: Node?, var rightChild: Node?, var value: Int) {
    fun showValue() {
        println(value)
    }
}