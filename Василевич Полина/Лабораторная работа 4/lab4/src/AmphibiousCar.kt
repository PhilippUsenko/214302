class AmphibiousCar(var speed: Int = 0, var status: Char = 'n') : Driving, Swimming {
    fun startDriving() {
        if (status == 'r') println("Машинка-амфибия уже едет")
        else {
            speed = 60;
            status = 'r'
            println(
                "Машинка-амфибия поехала. Ее скорость составляет $speed" +
                        " км/ч"
            )
        }
    }

    fun startSwimming() {
        if (status == 's') println("Машинка-амфибия уже плывет")
        else {
            speed = 30;
            status = 's'
            println(
                "Машинка-амфибия поплыла. Ее скорость составляет $speed" +
                        " км/ч"
            )
        }
    }

    override fun driveInfo(): String {
        if (status == 'r') {
            return "Машина-амфибия едет со скоростью $speed км/ч";
        } else return "Машина-амфибия не едет"
    }

    override fun swimInfo(): String {
        if (status == 's') {
            return "Машина-амфибия плывет по поверхности скоростью $speed км/ч";
        } else return "Машина-амфибия не плывет"
    }

    fun increaseSpeed(s: Int) {
        when (status) {
            'r' ->
                (if (speed + s > 600) println("\tСкорость машины-амфибии не может превышать 600 км/ч")
                else {
                    speed += s;
                    println("Скорость машины-амфибии увеличилась до $speed км/ч")
                })

            's' ->
                (if (speed + s > 250) println("\tСкорость плывущей машины-амфибии не может превышать 250 км/ч")
                else {
                    speed += s;
                    println("Скорость машины-амфибии увеличилась до $speed км/ч")
                })

            'n' -> (println("\tСначала начните движение"))
        }
    }

    fun decreaseSpeed(s: Int) {
        when (status) {
            'r' ->
                (if (speed - s < 0) {
                    status = 'n'
                    speed = 0
                    println("Машина остановилась")
                } else {
                    speed -= s;
                    println("Скорость машины-амфибии снизилась до $speed км/ч")
                })

            's' ->
                (if (speed - s < 0) println("\tСкорость плывущей машины-амфибии не может быть меньше 0 км/ч")
                else {
                    speed -= s;
                    println("Скорость машины-амфибии снизилась до $speed км/ч")
                })

            'n' -> (println("\tСначала начните движение"))
        }
    }
}