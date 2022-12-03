package cft.shift;


import cft.shift.model.FigureType;
import cft.shift.model.Shape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class ShapeFormatter extends AbstractFormatter {

    private static final Logger log = LoggerFactory.getLogger(ShapeFormatter.class);

    static public String PrintShape(Shape shape) {
        FigureType type = shape.type;

        return switch (type) {
            case TRIANGLE -> PrintTriangle(shape);
            case CIRCLE -> PrintCircle(shape);
            case RECTANGLE -> PrintRectangle(shape);
        };
    }

    static public String PrintTriangle(Shape shape) {
        log.info("Печатается Треугольник");


        StringBuilder buf = format(shape);
        ArrayList<Double> params = shape.getUniqueParams();

        buf.append("Сторона A: ");
        buf.append(String.format("%.2f", params.get(0)));
        buf.append(" м. Угол: ");
        buf.append(String.format("%.2f", params.get(1)));
        buf.append("\n");

        buf.append("Сторона B: ");
        buf.append(String.format("%.2f", params.get(2)));
        buf.append(" м. Угол: ");
        buf.append(String.format("%.2f", params.get(3)));
        buf.append("\n");

        buf.append("Сторона C: ");
        buf.append(String.format("%.2f", params.get(4)));
        buf.append(" м. Угол: ");
        buf.append(String.format("%.2f", params.get(5)));
        buf.append("\n");

        return buf.toString();
    }

    static public String PrintCircle(Shape shape) {
        log.info("Печатается Круг");

        StringBuilder buf = format(shape);
        ArrayList<Double> params = shape.getUniqueParams();
        buf.append("Радиус: ");
        buf.append(String.format("%.2f", params.get(0)));
        buf.append(" м.\nДиаметр: ");
        buf.append(String.format("%.2f", params.get(1)));
        buf.append(" м.\n");

        return buf.toString();
    }

    static public String PrintRectangle(Shape shape) {
        log.info("Печатается Прямоугольник");

        StringBuilder buf = format(shape);
        ArrayList<Double> params = shape.getUniqueParams();
        buf.append("Длинна: ");
        buf.append(String.format("%.2f", params.get(0)));
        buf.append(" м. \n");

        buf.append("Ширина: ");
        buf.append(String.format("%.2f", params.get(1)));
        buf.append(" м. \n");

        buf.append("Длинна диагонали: ");
        buf.append(String.format("%.2f", params.get(2)));
        buf.append(" м.\n");

        return buf.toString();
    }
}
