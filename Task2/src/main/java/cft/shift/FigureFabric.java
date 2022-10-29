package cft.shift;


import cft.shift.exception.BadTriangleParams;
import cft.shift.shapes.Circle;
import cft.shift.shapes.Rectangle;
import cft.shift.shapes.Shape;
import cft.shift.shapes.Triangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class FigureFabric {
    private static final Logger log = LoggerFactory.getLogger(FigureFabric.class);

    static public Shape createFigure(FigureType type, ArrayList<Double> array) throws BadTriangleParams
    {
        Shape shape = null;
        switch (type)
        {
            case TRIANGLE->{
                double a = array.get(0);
                double b = array.get(1);
                double c = array.get(2);
                shape = createFigure(a,b,c);
                log.info("������ ����������� c �����������" + a + " " + b + " " + c );
                
            }
            case RECTANGLE ->{
                double a = array.get(0);
                double b = array.get(1);
                shape = createFigure(a,b);
                log.info("������ ������������� c �����������" + a + " " + b);
            }
            case CIRCLE -> {
                double r = array.get(0);
                shape = createFigure(r);
                log.info("������ ���� c �������� " + r);
            }
        }
        return shape;
         
    }
    static public Shape createFigure(double r){
        return (new Circle(r));
    }

    static public Shape createFigure(double a, double b){
        return (new Rectangle(a,b));
    }

    static public Shape createFigure(double a, double b, double c) throws BadTriangleParams {
        return (new Triangle(a,b,c));
    }
}
