class Plane(var speed: Int = 0, var height: Int = 0, var status: Char = 'n') : Flying, Driving {
    fun startDriving() {
        if (status == 'r') println("Самолет уже едет")
        else {
        speed = 70;
        height = 0
        status = 'r'
        println(
            "Самолет поехал. Его скорость составляет $speed" +
                    " км/ч, его высота - $height м"
        )}
    }

    fun startFlying(h: Int = 6000) {
        if (status == 'f') println("Самолет уже летит")
        else {
        speed = 500;
        height = h
        status = 'f'
        println(
            "Самолет полетел. Его скорость составляет $speed" +
                    " км/ч, его высота - $height м"
        )}
    }

    override fun driveInfo(): String {
        if (status == 'r') {
            return "Самолет едет со скоростью $speed км/ч";
        } else return "Самолет не едет"
    }

    override fun flyInfo(): String {
        if (status == 'f') {
            return "Самолет летит на высоте $height м со скоростью $speed км/ч";
        } else return "Самолет не летит"
    }

    fun increaseSpeed(s: Int) {
        when (status) {
            'r' ->
                (if (speed + s > 70) println("\tСкорость самолета не может превышать 70 км/ч")
                else {
                    speed += s;
                    println("Скорость самолета увеличилась до $speed км/ч")
                })

            'f' ->
                (if (speed + s > 1500) println("\tСкорость летящего самолета не может превышать 1500 км/ч")
                else {
                    speed += s;
                    println("Скорость самолета увеличилась до $speed км/ч")
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
                    height = 0
                    println("Самолет остановился")
                } else {
                    speed -= s;
                    println("Скорость самолета снизилась до $speed км/ч")
                })

            'f' ->
                if (speed - s < 50) println("\tСкорость летящего самолета не может быть меньше 50 км/ч")
                else {
                    speed -= s;
                    println("Скорость самолета снизилась до $speed км/ч")
                }

            'n' -> (println("\tСначала начните движение"))
        }
    }

    fun decreaseHeight(h: Int) {
        when (status) {
            'n' -> println("\tСначала начните движение")
            'r' -> println("\tЧтобы уменьшить высоту полета самолета, он должен быть в воздухе")
            else -> (if (height - h < 0) startDriving()
            else {
                height -= h;
                println("Высота полета самолета уменьшилась до $height м")
            })
        }
    }

    fun increaseHeight(h: Int) {
        when (status) {
            'n' -> println("\tСначала начните движение")
            'r' -> startFlying(h)
            else -> (if (height + h > 10000) println("\tВысота самолета не может превышать 10000 м")
            else {
                height += h;
                println("Высота полета самолета увеличилась до $height м")
            })
        }
    }

}