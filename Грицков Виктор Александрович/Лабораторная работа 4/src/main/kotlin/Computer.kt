import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.style.markers.None

class Computer(
    var cpu: CPU,
    var gpu: GPU,
    var memory: Memory,
    var storage: Storage,
    var powerSupply: PowerSupply
) {
    private val performanceHistory = mutableListOf(evaluatePerformanceScore())
    private val chart = XYChartBuilder().width(800).height(600).title("Performance Over Time").xAxisTitle("Step").yAxisTitle("Performance").build()
    private val chartWrapper = SwingWrapper(chart)

    init {
        chart.addSeries("Performance", listOf(0), performanceHistory).marker = None()
        chartWrapper.displayChart()
    }

    private fun evaluatePerformanceScore(): Double {
        val cpuScore = cpu.cores * cpu.frequency
        val gpuScore = gpu.vram * 10
        val memoryScore = memory.size
        val storageScore = if (storage.type == "SSD") storage.size * 2 else storage.size
        return cpuScore + gpuScore + memoryScore + storageScore
    }

    private fun updatePerformanceHistory() {
        performanceHistory.add(evaluatePerformanceScore())
        chart.updateXYSeries("Performance", (0 until performanceHistory.size).toList(), performanceHistory, null)
        chartWrapper.repaintChart()
    }

    fun replaceCPU(newCPU: CPU) {
        cpu = newCPU
        println("\nCPU replaced with ${cpu.model}")
        updatePerformanceHistory()
    }

    fun replaceGPU(newGPU: GPU) {
        gpu = newGPU
        println("\nGPU replaced with ${gpu.model}")
        updatePerformanceHistory()
    }

    fun upgradeMemory(extraMemory: Int) {
        memory.size += extraMemory
        println("\nMemory increased to ${memory.size} GB")
        updatePerformanceHistory()
    }

    fun replaceStorage(newStorage: Storage) {
        storage = newStorage
        println("\nStorage replaced with ${storage.type} of ${storage.size} GB")
        updatePerformanceHistory()
    }

    fun evaluatePerformance(): String {
        val totalScore = evaluatePerformanceScore()
        return when {
            totalScore > 100 -> "\nHigh performance"
            totalScore > 50 -> "\nMedium performance"
            else -> "\nLow performance"
        }
    }

    fun displayConfiguration() {
        println()
        println("Computer Configuration:")
        println(cpu)
        println(gpu)
        println(memory)
        println(storage)
        println(powerSupply)
    }
}

class CPU(val model: String, val cores: Int, val frequency: Double) {
    override fun toString(): String {
        return "CPU: $model, $cores cores, $frequency GHz"
    }
}

class GPU(val model: String, val vram: Int) {
    override fun toString(): String {
        return "GPU: $model, $vram GB VRAM"
    }
}

class Memory(var size: Int) {
    override fun toString(): String {
        return "Memory: $size GB"
    }
}

class Storage(val type: String, val size: Int) {
    override fun toString(): String {
        return "Storage: $type, $size GB"
    }
}

class PowerSupply(val wattage: Int) {
    override fun toString(): String {
        return "Power Supply: $wattage W"
    }
}

fun main() {
    println("---COMPUTER BUILD---:")

    val cpuModel = promptString("Enter CPU model:")
    val cpuCores = promptInt("Enter number of CPU cores:")
    val cpuFrequency = promptDouble("Enter CPU frequency (in GHz):")
    val gpuModel = promptString("Enter GPU model:")
    val gpuVram = promptInt("Enter VRAM size (in GB):")
    val memorySize = promptInt("Enter memory size (in GB):")
    val storageType = promptStorageType("Enter storage type (SSD or HDD):")
    val storageSize = promptInt("Enter storage size (in GB):")
    val powerWattage = promptInt("Enter power supply wattage (in W):")

    val myComputer = Computer(
        cpu = CPU(cpuModel, cpuCores, cpuFrequency),
        gpu = GPU(gpuModel, gpuVram),
        memory = Memory(memorySize),
        storage = Storage(storageType, storageSize),
        powerSupply = PowerSupply(powerWattage)
    )

    myComputer.displayConfiguration()
    println("Initial performance evaluation: ${myComputer.evaluatePerformance()}")

    while (true) {
        println("\nChoose a component to replace or enter '5' to exit:")
        println("1 - Replace CPU")
        println("2 - Replace GPU")
        println("3 - Upgrade memory")
        println("4 - Replace storage")
        println("5 - Exit")

        when (readLine()) {
            "1" -> {
                val newCpuModel = promptString("Enter new CPU model:")
                val newCpuCores = promptInt("Enter new number of cores:")
                val newCpuFrequency = promptDouble("Enter new CPU frequency:")
                myComputer.replaceCPU(CPU(newCpuModel, newCpuCores, newCpuFrequency))
            }
            "2" -> {
                val newGpuModel = promptString("Enter new GPU model:")
                val newGpuVram = promptInt("Enter new VRAM size (in GB):")
                myComputer.replaceGPU(GPU(newGpuModel, newGpuVram))
            }
            "3" -> {
                val extraMemory = promptInt("Enter amount of memory to add (in GB):")
                myComputer.upgradeMemory(extraMemory)
            }
            "4" -> {
                val newStorageType = promptStorageType("Enter new storage type (SSD or HDD):")
                val newStorageSize = promptInt("Enter new storage size (in GB):")
                myComputer.replaceStorage(Storage(newStorageType, newStorageSize))
            }
            "5" -> {
                println("Exiting program.")
                break
            }
            else -> println("Invalid choice. Please try again.")
        }

        println("Updated performance evaluation: ${myComputer.evaluatePerformance()}")
        myComputer.displayConfiguration()
    }
}

fun promptString(message: String): String {
    while (true) {
        println(message)
        val input = readLine()
        if (!input.isNullOrBlank()) return input
        println("Error: Input cannot be empty.")
    }
}

fun promptInt(message: String): Int {
    while (true) {
        println(message)
        val input = readLine()?.toIntOrNull()
        if (input != null) return input
        println("Error: Please enter an integer.")
    }
}

fun promptDouble(message: String): Double {
    while (true) {
        println(message)
        val input = readLine()?.toDoubleOrNull()
        if (input != null) return input
        println("Error: Please enter a number.")
    }
}

fun promptStorageType(message: String): String {
    while (true) {
        println(message)
        val input = readLine()?.uppercase()
        if (input == "SSD" || input == "HDD") return input
        println("Error: Enter 'SSD' or 'HDD'.")
    }
}
