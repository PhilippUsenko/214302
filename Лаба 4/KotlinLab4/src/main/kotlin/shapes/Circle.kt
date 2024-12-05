package shapes


// Класс Circle
class Circle(private var radius: Double) : Shape() {
    override fun getArea(): Double {
        return Math.PI * radius * radius
    }

    override fun getPerimeter(): Double {
        return 2 * Math.PI * radius
    }

    fun getRadius(): Double {
        return radius
    }


}