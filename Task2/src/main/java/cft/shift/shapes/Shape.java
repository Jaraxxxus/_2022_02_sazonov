package cft.shift.shapes;

import cft.shift.FigureType;

import java.util.ArrayList;

abstract public class Shape {
    public FigureType type = null;
    abstract public double calculateArea();
    abstract public double calculatePerimeter();
    public String getName()
    {
        return  this.getClass().getSimpleName();
    }
    abstract public ArrayList<Double> getUniqueParams();
}
