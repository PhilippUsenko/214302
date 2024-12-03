fun main() {
    val dataProcessor = DataProcessor(numberOfWorkers = 4)
    println("Вводите задачи (для выхода 't'):")

    while (true) {
        val input = readLine() ?: continue

        if (input == "t") {
            break
        }

        dataProcessor.addTask {
            println("Выполняем ввод: $input")
            Thread.sleep(2000)
        }
    }

    dataProcessor.stopAllWorkers()
    println("Все задачи выполнены.")
}
