package cft.shift;

import cft.shift.model.Shape;

public class AbstractFormatter {
    static public StringBuilder format(Shape shape) {
        StringBuilder buf = new StringBuilder("Название: ");
        buf.append(shape.getName());

        buf.append("\nПлощадь: ");
        buf.append(String.format("%.2f", shape.getArea()));
        buf.append(" кв. м.\n");

        buf.append("Периметр: ");
        buf.append(String.format("%.2f", shape.getPerimeter()));
        buf.append(" м.\n");
        return buf;
    }
}



