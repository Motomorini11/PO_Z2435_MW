public class Calculator {


    public double distance(Point a, Point b) {
        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }


    public double distanceX(Point a, Point b) {
        return Math.abs(b.getX() - a.getX());
    }


    public double distanceY(Point a, Point b) {
        return Math.abs(b.getY() - a.getY());
    }
}
