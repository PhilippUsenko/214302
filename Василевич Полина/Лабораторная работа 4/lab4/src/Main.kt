import kotlin.time.times

fun main() {
//    val s = 1;
//    val pair = s.to(7);
    val supercar = SuperCar()
    println(supercar.driveInfo());
    println(supercar.swimInfo());
    println(supercar.flyInfo());
    println(supercar.diveInfo());
    supercar.startFlying();
    println(supercar.flyInfo());
    supercar.increaseSpeed(100)
    supercar.increaseHeight(9000)
    supercar.increaseHeight(1000)
    supercar.decreaseHeight(4000)
    supercar.startSwimming()
    supercar.increaseDepth(400)
    supercar.increaseSpeed(15)
    supercar.decreaseSpeed(600)
    supercar.decreaseDepth(5000)
    supercar.startDriving()
    supercar.driveInfo()
    supercar.decreaseSpeed(500)
//
//    val plane = Plane()
//    plane.decreaseHeight(40)
//    plane.startDriving()
//    println(plane.flyInfo())
//    println(plane.driveInfo())
//    plane.increaseHeight(5000)
//    plane.decreaseSpeed(10000)
//    plane.decreaseSpeed(300)
//    plane.decreaseHeight(12000)
//    plane.decreaseSpeed(500)
//
//    val amphibiousCar = AmphibiousCar()
//    amphibiousCar.startSwimming()
//    amphibiousCar.decreaseSpeed(500)
//    amphibiousCar.startDriving()
//    amphibiousCar.decreaseSpeed(20)
//    amphibiousCar.decreaseSpeed(900)
//
//    val submarine = Submarine()
//    submarine.startSwimming()
//    println(submarine.diveInfo())
//    println(submarine.swimInfo())
//    submarine.increaseSpeed(20)
//    submarine.increaseDepth(300)
//    submarine.startDiving()
//    submarine.decreaseDepth(12000)
}


