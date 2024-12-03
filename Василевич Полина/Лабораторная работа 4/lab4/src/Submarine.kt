class Submarine(var speed: Int = 0, var depth: Int = 0, var status: Char = 'n') : Swimming, Diving {
    fun startDiving(d: Int = 1000) {
        if (status=='d') println("Субмарина уже нырнула")
        else{
            speed = d;
            depth = 1000;
            status = 'd'
            println(
                "Субмарина нырнула на глубину. Ее скорость составляет $speed" +
                        " км/ч, глубина - $depth м"
            )
        }
    }

    fun startSwimming() {
        if (status=='s') println("Субмарина уже плывет")
        else{
        speed = 20;
        depth = 0
        status = 's'
        println(
            "Субмарина поплыла по поверхности. Ее скорость составляет $speed" +
                    " км/ч, глубина - $depth м"
        )}
    }

    override fun diveInfo(): String {
        if (status == 'd') {
            return "Субмарина плывет на глубине $depth м со скоростью $speed км/ч";
        } else return "Субмарина не плывет на глубине"
    }

    override fun swimInfo(): String {
        if (status == 's') {
            return "Субмарина плывет по поверхности скоростью $speed км/ч";
        } else return "Субмарина не плывет"
    }

    fun increaseSpeed(s: Int) {
        when (status) {
            'd' -> (
                    if (speed + s > 120) println("\tСкорость нырнувшей субмарины не может превышать 120 км/ч")
                    else {
                        speed += s;
                        println("Скорость субмарины увеличилась до $speed км/ч")
                    })


            's' ->
                (if (speed + s > 30) println("\tСкорость плывущей субмарины не может превышать 30 км/ч")
                else {
                    speed += s;
                    println("Скорость субмарины увеличилась до $speed км/ч")
                })

            'n' -> (println("\tСначала начните движение"))
        }
    }

    fun decreaseSpeed(s: Int) {
        when (status) {
            'd' ->
                (if (speed - s < 0) println("\tСкорость нырнувшей субмарины не может быть меньше 0 км/ч")
                else {
                    speed -= s;
                    println("Скорость субмарины снизилась до $speed км/ч")
                })

            's' ->
                (if (speed - s < 0) println("\tСкорость плывущей субмарины не может быть меньше 0 км/ч")
                else {
                    speed -= s;
                    println("Скорость субмарины снизилась до $speed км/ч")
                })

            'n' -> (println("\tСначала начните движение"))
        }
    }

    fun decreaseDepth(h: Int) {
        if (status != 'd') println("\tСначала нужно нырнуть")
        else {
            if (depth - h < 0) startSwimming()
            else {
                depth -= h;
                println("Субмарина поднялась до глубины $depth м")
            }
        }
    }

    fun increaseDepth(h: Int) {
        when (status) {
            'n' -> println("\tСначала начните движение")
            's' -> startDiving(h)
            else -> (if (depth + h > 12000) println("\tСубмарина не может опуститься глубже 12000 м")
                else {
                    depth += h;
                    println("Субмарина опустилась до глубины $depth м")
            })
        }
    }
}