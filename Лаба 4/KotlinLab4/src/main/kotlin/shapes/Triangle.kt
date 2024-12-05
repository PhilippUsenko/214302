package shapes

// Класс Triangle
class Triangle(private val base: Double, private val height: Double) : Shape() {
    override fun getArea(): Double {
        return 0.5 * base * height
    }

    override fun getPerimeter(): Double {
        // Допустим, равносторонний треугольник для простоты
        return 3 * base
    }

    fun getBase(): Double {
        return base
    }

    fun getHeight(): Double {
        return height
    }
}