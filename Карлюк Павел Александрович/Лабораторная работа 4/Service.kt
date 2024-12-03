class Service(val name: String, val price: Double)

object ServiceCatalog {
    val services = listOf(
        Service("Пользование минибаром", 50.0),
        Service("3 уборки в день", 30.0),
        Service("Трансфер до аэропорта", 100.0),
        Service("Завтрак в номер", 20.0)
    )
}
