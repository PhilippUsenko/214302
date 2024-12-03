class SuperCar(var speed: Int = 0, var height: Int = 0, var depth: Int = 0, var status: Char = 'n') : Driving, Flying,
    Swimming, Diving {
    fun startDiving(d:Int=800) {
        if (status == 'd') println("Суперкар уже нырнул")
        else {
        speed = 70;
        depth = d;
        height = 0
        status = 'd'
        println(
            "Суперкар нырнул на глубину. Его скорость составляет $speed" +
                    " км/ч, глубина - $depth м"
        )}
    }

    fun startDriving() {
        if (status == 'r') println("Суперкар уже едет")
        else {
        speed = 60;
        height = 0
        depth = 0
        status = 'r'
        println(
            "Суперкар поехал. Его скорость составляет $speed" +
                    " км/ч, его высота и глубина - $height м"
        )}
    }

    fun startFlying(h: Int = 4500) {
        if (status == 'f') println("Суперкар уже летит")
        else {
        speed = 400;
        height = h
        depth = 0
        status = 'f'
        println(
            "Суперкар полетел. Его скорость составляет $speed" +
                    " км/ч, его высота - $height м"
        )}
    }

    fun startSwimming() {
        if (status == 's') println("Суперкар уже плывет")
        else {
        speed = 45;
        depth = 0
        height = 0
        status = 's'
        println(
            "Суперкар поплыл по поверхности. Его скорость составляет $speed" +
                    " км/ч, глубина и высота - $depth м"
        )}
    }

    override fun diveInfo(): String {
        if (status == 'd') {
            return "Суперкар плывет на глубине $depth м со скоростью $speed км/ч";
        } else return "Cуперкар не плывет на глубине"
    }

    override fun driveInfo(): String {
        if (status == 'r') {
            return "Суперкар едет со скоростью $speed км/ч";
        } else return "Cуперкар не едет"
    }

    override fun flyInfo(): String {
        if (status == 'f') {
            return "Суперкар летит на высоте $height м со скоростью $speed км/ч";
        } else return "Cуперкар не летит"
    }

    override fun swimInfo(): String {
        if (status == 's') {
            return "Суперкар плывет по поверхности скоростью $speed км/ч";
        } else return "Cуперкар не плывет"
    }

    fun increaseSpeed(s: Int) {
        when (status) {
            'd' -> (
                    if (speed + s > 90) println("\tСкорость нырнувшего суперкара не может превышать 90 км/ч")
                    else {
                        speed += s;
                        println("Скорость суперкара увеличилась до $speed км/ч")
                    })

            'r' ->
                (if (speed + s > 1200) println("\tСкорость суперкара не может превышать 1200 км/ч")
                else {
                    speed += s;
                    println("Скорость суперкара увеличилась до $speed км/ч")
                })

            'f' ->
                (if (speed + s > 2000) println("\tСкорость летящего суперкара не может превышать 2000 км/ч")
                else {
                    speed += s;
                    println("Скорость суперкара увеличилась до $speed км/ч")
                })

            's' ->
                (if (speed + s > 300) println("\tСкорость плывущего суперкара не может превышать 300 км/ч")
                else {
                    speed += s;
                    println("Скорость суперкара увеличилась до $speed км/ч")
                })

            'n' -> (println("\tСначала начните движение"))
        }
    }

    fun decreaseSpeed(s: Int) {
        when (status) {
            'd' ->
                (if (speed - s < 0) println("\tСкорость нырнувшего суперкара не может быть меньше 0 км/ч")
                else {
                    speed -= s;
                    println("Скорость суперкара снизилась до $speed км/ч")
                })

            'r' ->
                (if (speed - s < 0) {
                    status = 'n'
                    speed = 0
                    depth = 0
                    height = 0
                    println("Машина остановилась")
                } else {
                    speed -= s;
                    println("Скорость суперкара снизилась до $speed км/ч")
                })

            'f' ->
                if (speed - s < 50) println("\tСкорость летящего суперкара не может быть меньше 50 км/ч")
                else {
                    speed -= s;
                    println("Скорость суперкара снизилась до $speed км/ч")
                }

            's' ->
                (if (speed - s < 0) println("\tСкорость плывущего суперкара не может быть меньше 0 км/ч")
                else {
                    speed -= s;
                    println("Скорость суперкара снизилась до $speed км/ч")
                })

            'n' -> (println("\tСначала начните движение"))
        }
    }

    fun decreaseHeight(h: Int) {
        if (status != 'f') println("\tСначала нужно взлететь")
        else {
            if (height - h < 0) println("\tВысота суперкара не может быть меньше 0 м")
            else {
                height -= h;
                println("Высота полета суперкара снизилась до $height м")
            }
        }
    }

    fun increaseHeight(h: Int) {
        when (status) {
            'n' -> println("\tСначала начните движение")
            's', 'r' -> startFlying(h)
            'd' -> println("\tСначала нужно всплыть")
            else -> (if (height + h > 9000) println("\tВысота полета суперкара не может превышать 9000 м")
            else {
                height += h;
                println("Высота полета суперкара увеличилась до $height м")
            })
        }
    }

    fun decreaseDepth(h: Int) {
        if (status != 'd') println("\tСначала нужно нырнуть")
        else {
            if (depth - h < 0) startSwimming()
            else {
                depth -= h;
                println("Суперкар поднялся до глубины $depth м")
            }
        }
    }

    fun increaseDepth(h: Int) {
        when (status) {
            'n' -> println("\tСначала начните движение")
            's' -> startDiving(h)
            'f','r' -> println("\tЧтобы опуститься на глубину, надо быть на/в воде")
            else -> (if (height + h > 9000) println("\tГлубина погружения суперкара не может превышать 9000 м")
            else {
                height += h;
                println("Высота полета суперкара увеличилась до $height м")
            })
        }
    }
}