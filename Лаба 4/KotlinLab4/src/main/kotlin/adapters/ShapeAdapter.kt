package adapters

import shapes.Circle
import shapes.Rectangle
import shapes.Shape
import shapes.Triangle

// Адаптер для Shape
class ShapeAdapter(private val shape: Shape) {

    fun getDetails() {
        when (shape) {
            is Circle -> {
                val radius = shape.getRadius()

                // Площади для вписанных и описанных фигур (треугольник и прямоугольник)
                val inscribedRectangleArea = 2 * radius * radius
                val circumscribedRectangleArea = (2 * radius) * (2 * radius)

                val inscribedTriangleArea = (Math.pow(radius * Math.sqrt(3.0), 2.0)) / 4
                val circumscribedTriangleArea = (Math.pow(radius * 2, 2.0) * Math.sqrt(3.0)) / 4

                println("Круг:")
                println("Площадь вписанного прямоугольника: $inscribedRectangleArea")
                println("Площадь описанного прямоугольника: $circumscribedRectangleArea")
                println("Площадь вписанного треугольника: $inscribedTriangleArea")
                println("Площадь описанного треугольника: $circumscribedTriangleArea")
            }

            is Rectangle -> {
                val width = shape.getWidth()
                val height = shape.getHeight()

                // Площади для вписанных и описанных фигур (круг и треугольник)
                val inscribedCircleArea = Math.PI * Math.pow(minOf(width, height) / 2, 2.0)
                val circumscribedCircleArea = Math.PI * Math.pow(Math.sqrt(Math.pow(width, 2.0) + Math.pow(height, 2.0)) / 2, 2.0)

                val inscribedTriangleArea = 0.5 * width * height
                val circumscribedTriangleArea = (width * height) / 2

                println("Прямоугольник:")
                println("Площадь вписанного круга: $inscribedCircleArea")
                println("Площадь описанного круга: $circumscribedCircleArea")
                println("Площадь вписанного треугольника: $inscribedTriangleArea")
                println("Площадь описанного треугольника: $circumscribedTriangleArea")
            }

            is Triangle -> {
                val base = shape.getBase()
                val height = shape.getHeight()

                // Площади для вписанных и описанных фигур (круг и прямоугольник)
                val inscribedCircleArea = Math.PI * Math.pow(height / 2, 2.0)
                val circumscribedCircleArea = Math.PI * Math.pow(base / 2, 2.0)

                val inscribedRectangleArea = 0.5 * base * height
                val circumscribedRectangleArea = base * height

                println("Треугольник:")
                println("Площадь вписанного круга: $inscribedCircleArea")
                println("Площадь описанного круга: $circumscribedCircleArea")
                println("Площадь вписанного прямоугольника: $inscribedRectangleArea")
                println("Площадь описанного прямоугольника: $circumscribedRectangleArea")
            }
        }
    }

    fun getArea(): Double {
        return shape.getArea()
    }

    fun getPerimeter(): Double {
        return shape.getPerimeter()
    }
}
