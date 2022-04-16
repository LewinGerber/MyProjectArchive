package ch.kbw.kuk.pointAlgorithm;

import ch.kbw.kuk.entities.Circle;
import ch.kbw.kuk.entities.Point;
import ch.kbw.kuk.entities.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * @author Lewin Gerber, Mischa Maurer
 * @version 26.10.2020
 * KUK
 */

public class PointAlgorithm {
    private ArrayList<Point> points;
    private ArrayList<Point> thirteenPoints;
    private Circle circle;
    private Boolean bigCircle;
    private Random r;

    public Circle makeCircle(ArrayList<Point> points) {
        this.points = points;
        if(this.points.size() < 13 && points.size() > 0) {
            for(int i = 0; i < 13- points.size(); i++ )
                this.points.add(this.points.get(0));
        }
        while (!bigCircle) {
            creat13Points();
            if (exceptionTwo()) break;
            get3of13();
            pointsEvaluation();
        }
        return circle;
    }

    public PointAlgorithm() {

        this.thirteenPoints = new ArrayList<Point>();
        this.circle = new Circle(new Position(0.0, 0.0), 0.0);
        this.bigCircle = false;
        this.r = new Random();
    }

    public void pointsEvaluation() {
        for (Point p : points
        ) {
            //geht
            if (circle.contains(p)) {
                bigCircle = true;
            } else {
                bigCircle = false;
                break;
            }
        }

        for (Point p : points) {
            if (!circle.contains(p)) {
                p.setWeight(p.getWeight() + 1);
            }
        }
    }

    //geht
    public Point sortPoints(){
        Collections.sort(points, new Comparator<Point>() {
            public int compare(Point s1, Point s2) {
                if (s1.getWeight() < s2.getWeight()) {
                    return 1;
                }else {
                    return -1;
                }
            }
        });
        return null;
    }
    //geht
    public void creat13Points(){
        this.sortPoints();
        for (int i = 0; i < 13 ; i++) {
            thirteenPoints.add(points.get(i));
        }
    }

    public Boolean exceptionTwo(){
        Point middle = new Point(0,0);
        double diameter=0;
        for (int i =0;i<points.size();i++) {
            for (int j = i+1; j < points.size() ; j++) {
                double x2=points.get(j).getPosX();
                double x1=points.get(i).getPosX();
                double y2=points.get(j).getPosY();
                double y1=points.get(i).getPosY();
                double temp = 0;
                temp = points.get(i).distance(points.get(j).getPosition());
                if (diameter < temp) {
                    diameter = temp;
                    circle.setPosX((x1+x2)/2);
                    circle.setPosY((y1+y2)/2);
                    circle.setRadius(diameter/2);

                }
            }
        }

        for (Point p : thirteenPoints
        ) {
            if (circle.contains(p)) {
                bigCircle = true;
            } else {
                bigCircle = false;
                break;
            }
        }
        return bigCircle;
    }


    public void get3of13(){
        for (int a=1;a<=11;a++) {
            for (int b = a + 1; b <= 12; b++) {
                for (int c = b + 1; c <= 13; c++) {
                    double y1 = thirteenPoints.get(a - 1).getPosY();
                    double y2 = thirteenPoints.get(b - 1).getPosY();
                    double y3 = thirteenPoints.get(c - 1).getPosY();
                    double x1 = thirteenPoints.get(a - 1).getPosX();
                    double x2 = thirteenPoints.get(b - 1).getPosX();
                    double x3 = thirteenPoints.get(c - 1).getPosX();



                    // sonderfall wenn y2=y1 ist muss ich namen vertauschen weil
                    // man sonst / 0 teilt siehe handy foto (von p1,p2,p3) zu (p2,p3,p1)=(p1,p2,p3)
                    if (y3 != y2 && y1 != y2) {


                        if (y1 == y2) {
                            y2 = thirteenPoints.get(c - 1).getPosY();
                            y3 = thirteenPoints.get(b - 1).getPosY();
                        }

                        //todo: Mathe nachschauen also schritt für schritt mitrechnen

                        double upper1 = Math.pow(y2, 2) - Math.pow(y3, 2) + Math.pow(x2, 2) - Math.pow(x3, 2);
                        double upper2 = 2 * y3 - 2 * y2;
                        double upper3 = Math.pow(y1, 2) - Math.pow(y2, 2) + Math.pow(x1, 2) - Math.pow(x2, 2);
                        double upper4 = 2 * y2 - 2 * y1;


                        double under1 = (x2 - x1) / (y2 - y1);
                        double under2 = (x3 - x2) / (y3 - y2);

                        double upper = upper1 / upper2 - upper3 / upper4;
                        double under = under1 - under2;

                        circle.setPosX(upper / under);
                        circle.setPosY(-under2 * circle.getPosX() - upper1 / upper2);
                        circle.setRadius(Math.sqrt(Math.pow(circle.getPosX() - x1, 2) + Math.pow(circle.getPosY() - y1, 2)));

                        //geht
                        for (Point p : thirteenPoints
                        ) {
                            if (circle.contains(p)) {
                                bigCircle = true;
                            } else {
                                bigCircle = false;
                                break;
                            }
                        }
                    }

                    if (bigCircle) {
                        break;
                    }


                }
                if (bigCircle) {
                    break;
                }
            }
            if (bigCircle) {
                break;
            }
        }

    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
}
