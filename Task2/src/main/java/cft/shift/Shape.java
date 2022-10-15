package cft.shift;

import java.util.ArrayList;

abstract public class Shape {
    public FigureType type = null;
    abstract protected double calculateArea();
    abstract protected double calculatePerimeter();
    protected String getName()
    {
        return  this.getClass().getSimpleName();
    }
    abstract protected ArrayList<Double> getUniqueParams();



}
