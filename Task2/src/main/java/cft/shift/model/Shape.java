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

    public String getBaseInfo()
    {
        return String.format("Название: %s \nПлощадь: %.2f кв. м.\nПериметр: %.2f м.\n", getName(), area, perimeter);
    }
}
