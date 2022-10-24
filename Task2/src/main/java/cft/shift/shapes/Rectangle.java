package cft.shift.shapes;

import cft.shift.FigureType;

import java.util.ArrayList;

public class Rectangle extends Shape {
    private final double a;
    private final double b;

    public Rectangle(double a, double b)
    {
        type = FigureType.RECTANGLE;
        this.a = a;
        this.b = b;
    }

    @Override
    public double calculateArea() {
        double area = a*b;
        return area;
    }

    @Override
    public double calculatePerimeter() {
        double perimeter = 2*(a+b);
        return perimeter;
    }

    @Override
    public ArrayList<Double> getUniqueParams() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(Math.max(a,b));
        arr.add(Math.min(a,b));
        arr.add(calculateDiagonal());
        return arr;
    }

    double calculateDiagonal()
    {
        double diagonal = Math.sqrt(a*a+b*b);
        return diagonal;
    }

}
