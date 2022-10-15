package cft.shift;

import java.util.ArrayList;

public class Rectangle extends Shape{
    private final double a;
    private final double b;


    Rectangle(double a, double b)
    {
        type = FigureType.RECTANGLE;

        this.a = a;
        this.b = b;
    }



    @Override
    protected double calculateArea() {

        double area = a*b;
        return area;
    }

    @Override
    protected double calculatePerimeter() {
        double perimeter = 2*(a+b);
        return perimeter;
    }

    @Override
    protected ArrayList<Double> getUniqueParams() {
        ArrayList<Double> arr = new ArrayList<>();
        double diagonal = Math.sqrt(a*a+b*b);

        arr.add(Math.max(a,b));
        arr.add(Math.min(a,b));
        arr.add(diagonal);
        return arr;
    }



}
