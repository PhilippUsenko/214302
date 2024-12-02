package lab3

class BinaryTree (var root: Node?) {

    fun insert(value: Int) {
        val newNode = Node(null, null, value)
        if (root == null){
            root = newNode
        }
        else{
            var current = root
            var parent: Node?
            while(true){
                parent = current
                if(value < current!!.value){
                 current = current.leftChild
                 if(current == null){
                     parent?.leftChild = newNode
                     return
                 }
                }
                else{
                    current = current.rightChild
                    if(current == null){
                        parent?.rightChild = newNode
                        return
                    }
                }
            }
        }
    }

    val printTreeRec: (Node?, Int) -> Unit = { node, level ->
        fun recPrint(n: Node?, l: Int) {
            if (n != null) {
                recPrint(n.rightChild, l + 1)
                println("    ".repeat(l) + n.value)
                recPrint(n.leftChild, l + 1)
            }
        }
        recPrint(node, level)
    }

    val traverse: (Node?) -> Int = { localRoot ->
        val leafCounter = object {
            var count = 0
        }

        fun countLeafs(lR: Node?) {
            if (lR != null) {
                if (lR.leftChild == null && lR.rightChild == null) {
                    leafCounter.count += 1
                }
                countLeafs(lR.leftChild)
                countLeafs(lR.rightChild)
            }
        }

        countLeafs(localRoot)
        leafCounter.count
    }

    fun minimum(): Node?
    {
        var current: Node?
        var min: Node? = null
        current = root
        while (current != null)
        {
            min = current
            current = current.leftChild
        }
        return min
    }

    fun maximum(): Node?
    {
        var current: Node?
        var max: Node? = null
        current = root
        while (current != null)
        {
            max = current
            current = current.rightChild
        }
        return max
    }

    fun findNode(keyValue: Int): Boolean{
        var current: Node? = root
        while(current?.value != keyValue){
            if(keyValue < current?.value!!){
                current = current.leftChild
            }
            else{
                current = current.rightChild
            }
            if(current == null){
                return false
            }
        }
        return true
    }

    fun printTree() {
        printTreeRec(root, 0)
    }

    fun countLeafNodes(): Int {
        return traverse(root)
    }
}
