package cft.shift;


public class FigureFabric {
    static Shape createFigure(double r){
        return (new Circle(r));
    }
    static Shape createFigure(double a, double b){
        return (new Rectangle(a,b));
    }
    static Shape createFigure(double a, double b, double c){
        return (new Triangle(a,b,c));
    }
}
