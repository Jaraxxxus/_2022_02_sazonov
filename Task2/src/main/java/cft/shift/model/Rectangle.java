package cft.shift.model;

import java.util.ArrayList;

public class Rectangle extends Shape {
    private final double a;
    private final double b;

    public Rectangle(double a, double b) {
        type = FigureType.RECTANGLE;
        this.a = a;
        this.b = b;
        area = a * b;
        perimeter = 2 * (a + b);
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
        arr.add(Math.max(a, b));
        arr.add(Math.min(a, b));
        arr.add(calculateDiagonal());
        return arr;
    }

    double calculateDiagonal() {
        double diagonal = Math.sqrt(a * a + b * b);
        return diagonal;
    }

}
