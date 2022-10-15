package cft.shift;


import java.util.ArrayList;

public class Circle extends Shape{
    private final double radius;
    Circle(double radius)
    {
        this.radius = radius;
        type = FigureType.CIRCLE;
    }

    @Override
    protected double calculateArea() {
        double area = radius*radius*Math.PI;
        return area;
    }

    @Override
    protected double calculatePerimeter() {
        double perimeter = radius*2*Math.PI;
        return perimeter;
    }

    @Override
    protected ArrayList<Double> getUniqueParams() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(radius);
        double diameter= radius*2;
        arr.add(diameter);
        return arr;
    }


}
