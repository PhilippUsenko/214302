package lab4

class Room(val roomNumb: Long, val roomType: String, val capacity: Int, var cost: Double, var nightsAmount: Int,
           var additionalService: MutableList<AdditionalService>?) {

    fun addService(service: AdditionalService){
        additionalService?.add(service)
        cost += service.price
    }
}