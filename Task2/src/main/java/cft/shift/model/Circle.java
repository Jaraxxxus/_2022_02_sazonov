package cft.shift.model;


import java.util.ArrayList;

public class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
        type = FigureType.CIRCLE;
        area = radius * radius * Math.PI;
        perimeter = radius * 2 * Math.PI;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public double getPerimeter() {
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
