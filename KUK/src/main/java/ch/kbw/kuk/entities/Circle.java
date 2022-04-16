package ch.kbw.kuk.entities;

/**
 * @author Lewin Gerber
 * @version 26.10.2020
 * KUK
 */

public class Circle {
    private static final double MULTIPLICATIVE_EPSILON = 1 + 1e-14;

    private Position position;
    private double radius;

    public Circle(){

    }
    public Circle(Position position, double radius) {
        this.position = position;
        this.radius = radius;
    }

    public Circle(double posX, double posY, double radius) {
        this.setPosX(posX);
        this.setPosY(posY);
        this.radius = 1;
    }

    //true if p is in the circle - false if p isn't in the circle
    public boolean contains(Point p) {
        return position.distance(p.getPosition()) <= radius * MULTIPLICATIVE_EPSILON;
    }

    //GETTER & SETTER
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    //EXTENDED GETTER & SETTER
    public void setPosX(double posX){
        this.position.setPosX(posX);
    }

    public double getPosX(){
        return this.position.getPosX();
    }

    public void setPosY(double posY){
        this.position.setPosY(posY);
    }

    public double getPosY(){
        return this.position.getPosY();
    }
}
