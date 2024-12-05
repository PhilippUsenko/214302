class Unit(private var strategy: BattleStrategy, val id: Int) {

    fun setStrategy(newStrategy: BattleStrategy) {
        strategy = newStrategy
    }

    fun executeStrategy(): String {
        return strategy.execute()
    }
}