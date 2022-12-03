package cft.shift;


import cft.shift.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class FigureFabric {
    private static final Logger log = LoggerFactory.getLogger(FigureFabric.class);

    static public Shape createFigure(FigureType type, ArrayList<Double> array) throws Exception {
        Shape shape = null;
        switch (type) {
            case TRIANGLE -> {
                double a = array.get(0);
                double b = array.get(1);
                double c = array.get(2);
                shape = createFigure(a, b, c);
                log.info("создан треугольник c параметрами" + a + " " + b + " " + c);

            }
            case RECTANGLE -> {
                double a = array.get(0);
                double b = array.get(1);
                shape = createFigure(a, b);
                log.info("создан прямоугольник c параметрами" + a + " " + b);
            }
            case CIRCLE -> {
                double r = array.get(0);
                shape = createFigure(r);
                log.info("создан круг c радиусом " + r);
            }
        }
        return shape;

    }

    static public Shape createFigure(double r) {
        return (new Circle(r));
    }

    static public Shape createFigure(double a, double b) {
        return (new Rectangle(a, b));
    }

    static public Shape createFigure(double a, double b, double c) throws Exception {
        return (new Triangle(a, b, c));
    }
}
