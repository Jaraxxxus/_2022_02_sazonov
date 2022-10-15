package cft.shift;

public class Main {
    public static void main(String[] args){
        Circle circle = new Circle(3);
        Triangle triangle = new Triangle(3, 4, 5);

        Rectangle rec = new Rectangle(4,5);
        System.out.println(ShapeFormatter.PrintShape(rec));
        System.out.println(ShapeFormatter.PrintShape(circle));
        System.out.println(ShapeFormatter.PrintShape(triangle));

    }
}
