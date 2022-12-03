package cft.shift.model;


import java.util.ArrayList;

public class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
        type = FigureType.CIRCLE;
    }

    @Override
    public double getArea() {
        double area = radius * radius * Math.PI;
        return area;
    }

    @Override
    public double getPerimeter() {
        double perimeter = radius * 2 * Math.PI;
        return perimeter;
    }

    @Override
    public ArrayList<Double> getUniqueParams() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(radius);
        double diameter = radius * 2;
        arr.add(diameter);
        return arr;
    }
}
