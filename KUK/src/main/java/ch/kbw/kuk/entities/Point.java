package ch.kbw.kuk.entities;

/**
 * @author Lewin Gerber
 * @version 26.10.2020
 * KUK
 */
public class Point {
    private Position position;
    private int weight;

    public Point(Position position) {
        this.position = position;
        this.weight = 1;
    }

    public Point(double posX, double posY) {
        this.position = new Position(posX, posY);
        this.weight = 1;
    }
    public Point(double posX, double posY,int w) {
        this.position = new Position(posX, posY);
        this.weight = w;
    }

    public double distance(Position p) {
        return Math.hypot(position.getPosX() - p.getPosX(), position.getPosY() - p.getPosY());
    }

    @Override
    public String toString() {
        return position.toString() + " - w:" + weight;
    }

    //GETTER & SETTER
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
