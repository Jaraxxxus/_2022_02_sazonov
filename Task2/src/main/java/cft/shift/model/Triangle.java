package cft.shift.model;

import java.util.ArrayList;

public class Triangle extends Shape {
    private final double a;
    private final double b;
    private final double c;

    public Triangle(double a, double b, double c) throws Exception {
        super.type = FigureType.TRIANGLE;

        this.a = a;
        this.b = b;
        this.c = c;
        double maxSide = Math.max(a, Math.max(b, c));
        if (maxSide > a + b + c - maxSide) {
            throw (new Exception("Сторона треугольника " + maxSide + " больше суммы двух других"));
        }
        perimeter = a + b + c;
        double p = perimeter / 2;
        area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
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
        arr.add(a);
        arr.add(findAngle(a, b, c));

        arr.add(b);
        arr.add(findAngle(b, a, c));

        arr.add(c);
        arr.add(findAngle(c, a, b));

        return arr;
    }


    double findAngle(double first, double second, double third) {
        return Math.toDegrees(Math.acos((second * second + third * third - first * first) / (2 * second * third)));
    }
}
