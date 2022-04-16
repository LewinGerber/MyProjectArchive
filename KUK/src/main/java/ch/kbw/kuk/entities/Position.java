package ch.kbw.kuk.entities;

/**
 * @author Lewin Gerber
 * @version 26.10.2020
 * KUK
 */

public class Position {
    private double posX;
    private double posY;

    public Position(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Position subtract(Position p) {
        return new Position(posX - p.getPosX(), posY - p.getPosY());
    }

    public double distance(Position p) {
        return Math.hypot(posX - p.getPosX(), posY - p.getPosY());
    }


    // Signed area / determinant thing
    public double cross(Position p) {
        return posX * p.y() - posY * p.x();
    }

    @Override
    public String toString() {
        return "x:" + posX + " - y:" + posY;
    }

    //GETTER & SETTER
    public double getPosX() {
        return posX;
    }

    public double x() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void x(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public double y() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void y(double posY) {
        this.posY = posY;
    }
}
