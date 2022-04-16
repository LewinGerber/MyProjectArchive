package ch.kbw.kuk.sequentialAlgorithm;

import ch.kbw.kuk.entities.Point;
import ch.kbw.kuk.entities.Circle;
import ch.kbw.kuk.entities.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nayuki, Lewin Gerber
 * @version 30.10.2020
 * KUK
 */

public class SequentialAlgorithm {

    /*
    -> Main Method to create Algorithm and start the ITERATION PROCESS
    */
    public Circle makeCircle(ArrayList<Point> points) {
        //main circle object that will be returned
        Circle circle = null;

        //iterated through all the points
        for(int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            if(circle == null || !circle.contains(p)) {
                //outofbounds error at the end for last?
                circle = makeOneCenterPoint(points.subList(0,i+1), p);
            }
        }
        return circle;
    }

    /*
    -> effectively determines first center point but starts method for two center points
    => important when there is only one
    List (SubList): All given points so far
    Point p: point that isn't contained
    */
    public Circle makeOneCenterPoint(List<Point> points, Point p) {
        Circle circle = new Circle(p.getPosition(), 0);
        for(int i = 0; i < points.size(); i++) {
            Point q = points.get(i);
            if(!circle.contains(q)){
                if(circle.getRadius() == 0) {
                    circle = calculateDiameter(p.getPosition(), q.getPosition());
                } else {
                    circle = makeTwoCenterPoint(points.subList(0,i+1), p, q);
                }
            }
        }
        return circle;
    }

    public Circle makeTwoCenterPoint(List<Point> points, Point p, Point q){
        //circle if there are two points
        Circle circle = calculateDiameter(p.getPosition(), q.getPosition());

        Circle left = null;
        Circle right = null;

        Point pq = new Point(q.getPosition().subtract(p.getPosition()));

        for(Point r : points) {
            if(!circle.contains(r)) {
                double cross = pq.getPosition().cross(r.getPosition().subtract(p.getPosition()));
                Circle c = makeCircumcircle(p, q, r);
                //if there are only 2 points that form the circle
                if (c == null) continue;
                
                //analyzes if the smaller circle is on left or on the right
                else if (
                        cross > 0 &&
                                (left == null || pq.getPosition().cross(c.getPosition().subtract(p.getPosition()))
                                        > pq.getPosition().cross(left.getPosition().subtract(p.getPosition()))))
                    left = c;
                else if (cross < 0 && (right == null || pq.getPosition().cross(c.getPosition().subtract(p.getPosition())) < pq.getPosition().cross(right.getPosition().subtract(p.getPosition()))))
                    right = c;
            }
        }

        // Select which circle to return
        if (left == null && right == null)
            return circle;
        else if (left == null)
            return right;
        else if (right == null)
            return left;
        else
            return left.getRadius() <= right.getRadius() ? left : right;
    }

    public Circle makeCircumcircle(Point a, Point b, Point c) {
        // WIKIPEDIA FORMULA FOR CIRCUMCIRCLE: cartesian coordinates
        double ox = (Math.min(Math.min(a.getPosX(), b.getPosX()), c.getPosX()) + Math.max(Math.max(a.getPosX(), b.getPosX()), c.getPosX())) / 2;
        double oy = (Math.min(Math.min(a.getPosY(), b.getPosY()), c.getPosY()) + Math.max(Math.max(a.getPosY(), b.getPosY()), c.getPosY())) / 2;
        double ax = a.getPosX() - ox,  ay = a.getPosY() - oy;
        double bx = b.getPosX() - ox,  by = b.getPosY() - oy;
        double cx = c.getPosX() - ox,  cy = c.getPosY() - oy;
        double d = (ax * (by - cy) + bx * (cy - ay) + cx * (ay - by)) * 2;
        if (d == 0)
            return null;
        double x = ((ax*ax + ay*ay) * (by - cy) + (bx*bx + by*by) * (cy - ay) + (cx*cx + cy*cy) * (ay - by)) / d;
        double y = ((ax*ax + ay*ay) * (cx - bx) + (bx*bx + by*by) * (ax - cx) + (cx*cx + cy*cy) * (bx - ax)) / d;
        Point p = new Point(ox + x, oy + y);
        double r = Math.max(Math.max(p.distance(a.getPosition()), p.distance(b.getPosition())), p.distance(c.getPosition()));
        return new Circle(p.getPosition(), r);
    }

    //CALCULATION Methods
    public Circle calculateDiameter(Position p1, Position p2) {
        Point c = new Point((p1.getPosX() + p2.getPosX()) / 2, (p1.getPosY() + p2.getPosY()) / 2);
        return new Circle(c.getPosition(), Math.max(c.distance(p1), c.distance(p2)));
    }

}
