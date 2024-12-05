fun main() {

    val player = Player("Игрок 1")

    val unit1 = Unit(AggressiveStrategy(), 1)
    val unit2 = Unit(DefensiveStrategy(), 2)

    player.addUnit(unit1)
    player.addUnit(unit2)

    while (true) {
        println("\n--- Меню ---")
        println("1. Показать текущие стратегии юнитов")
        println("2. Изменить стратегию для конкретного юнита")
        println("3. Изменить стратегию для всех юнитов")
        println("4. Выйти")

        when (readInt("Выберите действие: ")) {
            1 -> {
                player.executeBattleStrategies()
            }
            2 -> {
                player.printUnits()
                val unitId = readInt("Введите ID юнита, для которого хотите изменить стратегию: ")

                println("Выберите стратегию:")
                println("1. Агрессивная")
                println("2. Оборонительная")
                println("3. Скрытная")
                val strategyChoice = readInt("Введите номер стратегии: ")

                val chosenStrategy = chooseStrategy(strategyChoice)
                player.changeStrategyForUnit(unitId, chosenStrategy)
            }
            3 -> {
                println("Выберите стратегию для всех юнитов:")
                println("1. Агрессивная")
                println("2. Оборонительная")
                println("3. Скрытная")
                val strategyChoice = readInt("Введите номер стратегии: ")

                val chosenStrategy = chooseStrategy(strategyChoice)
                player.changeStrategyForAllUnits(chosenStrategy)
            }
            4 -> {
                println("Игра завершена.")
                break
            }
            else -> {
                println("Неправильный ввод. Попробуйте снова.")
            }
        }
    }
}

fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        val input = readLine()
        val number = input?.toIntOrNull()
        if (number != null) {
            return number
        } else {
            println("Ошибка: Введите корректное число.")
        }
    }
}

fun chooseStrategy(strategyInput: Int): BattleStrategy {
    return when (strategyInput) {
        1 -> AggressiveStrategy()
        2 -> DefensiveStrategy()
        3 -> StealthStrategy()
        else -> {
            println("Неправильный ввод. Используется агрессивная стратегия по умолчанию.")
            AggressiveStrategy()
        }
    }
}