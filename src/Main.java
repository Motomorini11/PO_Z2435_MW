public class Main {
    public static void main(String[] args) {

        Point p1 = new Point(2, 3);
        Point p2 = new Point(5, 7);


        Calculator calc = new Calculator();


        System.out.println("Odległość między p1 a p2: " + calc.distance(p1, p2));
        System.out.println("Odległość wzdłuż osi X: " + calc.distanceX(p1, p2));
        System.out.println("Odległość wzdłuż osi Y: " + calc.distanceY(p1, p2));


        Circle circle = new Circle(p1, 10);


        System.out.println("Pole koła: " + circle.area());
        System.out.println("Obwód koła: " + circle.circumference());
    }
}
