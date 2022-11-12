package cft.shift.model;

import java.util.ArrayList;

abstract public class Shape {
    public FigureType type = null;

    protected double area;

    protected double perimeter;

    abstract public double getArea();

    abstract public double getPerimeter();

    public String getName() {
        return this.type.toString();
    }

    abstract public ArrayList<Double> getUniqueParams();
}
