package cft.shift.model;

public enum FigureType {
    CIRCLE("Круг"),
    RECTANGLE("Прямоугольник"),
    TRIANGLE("Треугольник");


    private String name;

    FigureType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
