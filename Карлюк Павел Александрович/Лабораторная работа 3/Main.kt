import java.util.*
import kotlin.math.pow

data class TreeNode(
    val value: Int,
    val left: TreeNode? = null,
    val right: TreeNode? = null
)

fun main() {
    val tree = TreeNode(
        10,
        left = TreeNode(5, left = TreeNode(3, left = TreeNode(2), right = TreeNode(4)), right = TreeNode(7)),
        right = TreeNode(20, left = TreeNode(15), right = TreeNode(30))
    )

    val countLeaves: (TreeNode?) -> Int = run {
        var countLeavesRef: (TreeNode?) -> Int = { 0 }
        countLeavesRef = { node ->
            if (node == null) {
                0
            } else if (node.left == null && node.right == null) {
                1
            } else {
                countLeavesRef(node.left) + countLeavesRef(node.right)
            }
        }
        countLeavesRef
    }

    val findMax: (TreeNode?) -> Int = run {
        var findMaxRef: (TreeNode?) -> Int = { 0 }
        findMaxRef = { node ->
            if (node == null) {
                Int.MIN_VALUE
            } else {
                val leftMax = findMaxRef(node.left)
                val rightMax = findMaxRef(node.right)
                maxOf(node.value, leftMax, rightMax)
            }
        }
        findMaxRef
    }

    val getHeight: (TreeNode?) -> Int = run {
        var heightLambda: (TreeNode?) -> Int = { 0 }
        heightLambda = { node ->
            if (node == null) 0 else 1 + maxOf(heightLambda(node.left), heightLambda(node.right))
        }
        heightLambda
    }

    val printTreeLevelOrder: (TreeNode?) -> Unit = { root ->
        val height = getHeight(root)
        val maxWidth = 2.0.pow(height).toInt() - 1
        val queue = LinkedList<TreeNode?>().apply { add(root) }

        for (level in 0..<height) {
            val levelSize = queue.size
            val spaceBetweenNodes = maxWidth / (2.0.pow(level).toInt() )

            val spaceBetweenPairs = " ".repeat(spaceBetweenNodes + 1)
            val spaceInsideNode = " ".repeat(spaceBetweenNodes / 2)

            val levelOutput = StringBuilder(" ".repeat(spaceBetweenNodes / 2))

            repeat(levelSize) {
                val node = queue.poll()

                if (node != null) {
                    levelOutput.append(spaceInsideNode)
                    levelOutput.append(node.value)
                    levelOutput.append(spaceInsideNode)
                    queue.add(node.left)
                    queue.add(node.right)
                } else {
                    levelOutput.append(spaceInsideNode)
                    levelOutput.append(" ")
                    levelOutput.append(spaceInsideNode)
                    queue.add(null)
                    queue.add(null)
                }

                levelOutput.append(spaceBetweenPairs)
            }

            println(levelOutput)
        }
    }

    println("Количество листьев: ${countLeaves(tree)}")
    println("Максимальное значение: ${findMax(tree)}")
    println("Деревце: ")
    printTreeLevelOrder(tree)

}
