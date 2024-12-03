package lab3

fun main() {

    print("Введите желаемое количество узлов в дереве: ")
    var size = readlnOrNull()?.toIntOrNull()
    while (size == null) {
        println("Ошибка ввода. Попробуйте ещё раз")
        size = readlnOrNull()?.toIntOrNull()
        continue
    }

    while (true) {

        print("Введите элементы дерева через пробел: ")
        val array1 = readlnOrNull()?.split(" ")?.filter { it.isNotEmpty() }?.map { it.toInt() }

        if (array1 == null || array1.size != size) {
            println("Пожалуйста, введите $size элементов, как вы и выбрали в начале.")
            continue
        }

        val root = Node(null, null, array1[0])

        val tree = BinaryTree(root)

        for(el in array1.drop(1)){
            tree.insert(el)
        }
        tree.printTree()
        while(true) {
            userMenu(tree)
            print("Желаете продолжить?\n1.Да\n НЕТ - любая другая клавиша\n")
            val input = readlnOrNull()?.toIntOrNull()
            if(input != 1)break
        }
        break
    }
}

fun userMenu(tree: BinaryTree?){
    while(true) {
        println(
            "Выберите операцию, которую хотели бы провести с деревом:" +
                    "\n1. Посчитать количество листьев\n2. Найти максимальное значение" +
                    "\n3. Найти минимальное значение" +
                    "\n4. Проверка наличия элемента в дереве"
        )

        val input = readlnOrNull()?.toIntOrNull()

        val condition = when (input) {
            1 -> {
                val leafCount = tree?.countLeafNodes()
                println("Количество листьев в заданном дереве: $leafCount")
                break
            }
            2 ->{
                val max = tree?.maximum()
                println("Максимальное значение элемента в дереве: ${max?.value}")
                break
            }
            3 -> {
                val min = tree?.minimum()
                println("Минимальное значение элемента в дереве: ${min?.value}")
                break
            }
            4 -> {
                print("Введите целое число, которое желаете проверить на наличие в заданном дереве: ")
                val element = readlnOrNull()?.toIntOrNull()
                if(tree?.findNode(element!!) == true){
                    println("Данное число присутствует среди элементов дерева")
                }
                else println("Данного элемента нет в дереве")
                break
            }
            else -> {
                println("Ошибка ввода. Выберите существующий пункт меню")
                continue
            }
        }
    }
}