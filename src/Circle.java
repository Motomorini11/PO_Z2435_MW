public class Circle {
    private Point center;
    private double diameter;

    public Circle(Point center, double diameter) {
        this.center = center;
        this.diameter = diameter;
    }


    public double area() {
        double radius = diameter / 2;
        return Math.PI * radius * radius;
    }


    public double circumference() {
        return Math.PI * diameter;
    }
}
