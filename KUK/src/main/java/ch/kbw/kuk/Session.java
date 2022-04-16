package ch.kbw.kuk;

import ch.kbw.kuk.entities.Circle;
import ch.kbw.kuk.entities.Point;
import ch.kbw.kuk.entities.Position;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * @author Lewin Gerber
 * @version 12.11.2020
 * kuk
 */

public class Session {
    private int id;
    private ArrayList<Point> points;
    // todo: create interface for the algorithm services
    private String solvingMethod;
    private Instant time;
    private long solveTime;
    private Circle solution;
    private boolean pending;
    private boolean solved;

    public Session(int id) {
        this.points = new ArrayList<>();
        this.id = id;
    }

    public void generateDataPoints(int size) {
        Random r = new Random();
        for(int i = 0; i < size; i++) {
            points.add(new Point(
                    new Position(r.nextInt(15), r.nextInt(15))
            ));
        }
    }

    public void startSession() {
        setPending(true);
        setSolved(false);
        time = Instant.now();
    }

    public void closeSession() {
        setPending(false);
        solveTime = Duration.between(time, Instant.now()).toMillis();
        setSolved(true);
    }

    //GETTER & SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public Circle getSolution() {
        return solution;
    }

    public void setSolution(Circle solution) {
        this.solution = solution;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public String getSolvingMethod() {
        return solvingMethod;
    }

    public void setSolvingMethod(String solvingMethod) {
        this.solvingMethod = solvingMethod;
    }
}
