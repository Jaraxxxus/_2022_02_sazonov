package cft.shift;

import java.util.ArrayList;

public class Triangle extends Shape {
    private final double a;
    private final double b;
    private final double c;
    private final double perimeter;


    Triangle(double a, double b, double c)
    {
        super.type = FigureType.TRIANGLE;

        this.a = a;
        this.b = b;
        this.c = c;
        perimeter = a + b + c;
    }

    @Override
    protected double calculateArea() {
        double p = perimeter/2;
        double area = Math.sqrt(p * (p-a) * (p-b) * (p-c));
        return area;
    }

    @Override
    protected double calculatePerimeter() {

        return perimeter;
    }

    @Override
    protected ArrayList<Double> getUniqueParams() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(a);
        arr.add(findAngle(a,b,c));

        arr.add(b);
        arr.add(findAngle(b,a,c));

        arr.add(c);
        arr.add(findAngle(c,a,c));

        return arr;
    }


    private double findAngle(double first, double second, double third)
    {
        return Math.toDegrees(Math.acos((second*second + third*third - first*first)/(2*second*third)));
    }
}
