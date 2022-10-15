package cft.shift;


import java.util.ArrayList;

public class ShapeFormatter {


    static public String PrintShape(Shape shape)
    {
        FigureType type = shape.type;

        return switch (type)
                {
                    case TRIANGLE -> PrintTriangle(shape);
                    case CIRCLE -> PrintCircle(shape);
                    case RECTANGLE -> PrintRectangle(shape);
                };
    }
    static public String PrintTriangle(Shape shape)
    {
        StringBuilder buf = new StringBuilder("��������: ");
        buf.append(shape.getName());

        buf.append("\n�������: ");
        buf.append(shape.calculateArea());
        buf.append(" ��. �.\n");


        buf.append("��������: ");
        buf.append(shape.calculatePerimeter());
        buf.append(" �.\n");


        ArrayList<Double> params = shape.getUniqueParams();

        buf.append("������� A: ");
        buf.append(params.get(0));
        buf.append(" �. ����: ");
        buf.append(params.get(1));
        buf.append("\n");

        buf.append(params.get(2));
        buf.append("������� B: ");
        buf.append(params.get(3));
        buf.append(" �. ����: ");
        buf.append("\n");

        buf.append("������� C: ");
        buf.append(params.get(4));
        buf.append(" �. ����: ");
        buf.append(params.get(5));
        buf.append("\n");

        return buf.toString();
    }

    static public String PrintCircle(Shape shape)
    {
        StringBuilder buf = new StringBuilder("��������: ");
        buf.append(shape.getName());

        buf.append("\n�������: ");
        buf.append(shape.calculateArea());
        buf.append(" ��. �.\n");


        buf.append("��������: ");
        buf.append(shape.calculatePerimeter());
        buf.append(" �.\n");


        ArrayList<Double> params = shape.getUniqueParams();
        buf.append("������: ");
        buf.append(params.get(0));
        buf.append(" �.\n�������: ");
        buf.append(params.get(1));
        buf.append(" �.\n");

        return buf.toString();

    }

    static public String PrintRectangle(Shape shape)
    {

        StringBuilder buf = new StringBuilder("��������: ");
        buf.append(shape.getName());

        buf.append("\n�������: ");
        buf.append(shape.calculateArea());
        buf.append(" ��. �.\n");


        buf.append("��������: ");
        buf.append(shape.calculatePerimeter());
        buf.append(" �.\n");


        ArrayList<Double> params = shape.getUniqueParams();
        buf.append("������: ");
        buf.append(params.get(0));
        buf.append(" �. \n");

        buf.append("������: ");
        buf.append(params.get(1));
        buf.append(" �. \n");

        buf.append("������ ���������: ");
        buf.append(params.get(2));
        buf.append(" �.\n");

        return buf.toString();

    }








}
