class Player(val name: String) {
    private val units: MutableList<Unit> = mutableListOf()

    fun addUnit(unit: Unit) {
        units.add(unit)
    }

    fun executeBattleStrategies() {
        for (unit in units) {
            println("Юнит ${unit.id} игрока $name: ${unit.executeStrategy()}")
        }
    }

    fun changeStrategyForUnit(unitId: Int, newStrategy: BattleStrategy) {
        val unit = units.find { it.id == unitId }
        unit?.setStrategy(newStrategy) ?: println("Юнит с ID $unitId не найден.")
    }

    fun changeStrategyForAllUnits(newStrategy: BattleStrategy) {
        for (unit in units) {
            unit.setStrategy(newStrategy)
        }
    }

    fun printUnits() {
        println("У игрока $name есть следующие юниты:")
        for (unit in units) {
            println("Юнит ID: ${unit.id}")
        }
    }
}