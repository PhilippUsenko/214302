open class Vehicle(
    val make: String,
    val model: String,
    val year: Int,
    var fuelConsumption: Double,  // Литры на 100 км
    var lastServiceDate: String
) {
    // расходы
    var repairCosts: Double = 0.0

    open fun addRepairCost(cost: Double) {
        repairCosts += cost
        println("Added repair cost of $cost to $make $model. Total repair costs: $repairCosts")
    }

    // планирование техобслуживания
    open fun scheduleService(serviceDate: String) {
        lastServiceDate = serviceDate
        println("Scheduled service for $make $model on $serviceDate")
    }

    fun calculateFuelConsumption(distance: Double): Double {
        return (distance / 100) * fuelConsumption
    }

    fun printRepairCosts() {
        println("$make $model has total repair costs: $repairCosts")
    }
}

class Car(
    make: String,
    model: String,
    year: Int,
    fuelConsumption: Double,
    lastServiceDate: String,
    val numOfDoors: Int
) : Vehicle(make, model, year, fuelConsumption, lastServiceDate) {


}

class Truck(
    make: String,
    model: String,
    year: Int,
    fuelConsumption: Double,
    lastServiceDate: String,
    val cargoCapacity: Double // Грузоподъемность в тоннах
) : Vehicle(make, model, year, fuelConsumption, lastServiceDate) {


    // загрузка грузовика
    fun loadCargo(weight: Double) {
        if (weight <= cargoCapacity) {
            println("Loaded $weight tons of cargo into $make $model")
        } else {
            println("Cannot load $weight tons, exceeding the cargo capacity!")
        }
    }
}

class Bus(
    make: String,
    model: String,
    year: Int,
    fuelConsumption: Double,
    lastServiceDate: String,
    val passengerCapacity: Int // Вместимость пассажиров
) : Vehicle(make, model, year, fuelConsumption, lastServiceDate) {

    fun boardPassengers(numberOfPassengers: Int) {
        if (numberOfPassengers <= passengerCapacity) {
            println("Boarded $numberOfPassengers passengers into $make $model")
        } else {
            println("Cannot board $numberOfPassengers passengers, exceeding the capacity!")
        }
    }
}

// менеджер
class FleetManager {
    private val vehicles = mutableListOf<Vehicle>()

    fun addVehicle(vehicle: Vehicle) {
        vehicles.add(vehicle)
        println("Added ${vehicle.make} ${vehicle.model} to the fleet.")
    }

    fun calculateTotalFuelConsumption(distance: Double): Double {
        return vehicles.sumOf { it.calculateFuelConsumption(distance) }
    }

    // Планирование обслуживания для всех транспортных средств
    fun scheduleFleetService(serviceDate: String) {
        vehicles.forEach { it.scheduleService(serviceDate) }
    }

    fun printFleetInfo() {
        vehicles.forEach {
            println("${it.make} ${it.model}, Year: ${it.year}, Last service: ${it.lastServiceDate}, Fuel consumption: ${it.fuelConsumption}L/100km")
        }
    }

    fun printRepairCosts() {
        vehicles.forEach { it.printRepairCosts() }
    }
}

fun main() {
    val car1 = Car("Toyota", "Corolla", 2020, 7.5, "2023-01-10", 4)
    val truck1 = Truck("Volvo", "FH16", 2022, 30.0, "2023-03-15", 20.0)
    val bus1 = Bus("Mercedes", "Sprinter", 2021, 15.0, "2023-05-20", 40)

    val fleetManager = FleetManager()
    fleetManager.addVehicle(car1)
    fleetManager.addVehicle(truck1)
    fleetManager.addVehicle(bus1)

    car1.addRepairCost(250.0)
    truck1.addRepairCost(1000.0)
    bus1.addRepairCost(500.0)

    fleetManager.printRepairCosts()

    fleetManager.printFleetInfo()

    val totalFuel = fleetManager.calculateTotalFuelConsumption(200.0)
    println("Total fuel consumption for the fleet on a 200 km route: $totalFuel liters")

    fleetManager.scheduleFleetService("2024-01-01")

    truck1.loadCargo(15.0)
    truck1.loadCargo(25.0)
    bus1.boardPassengers(30)
    bus1.boardPassengers(45)
}
