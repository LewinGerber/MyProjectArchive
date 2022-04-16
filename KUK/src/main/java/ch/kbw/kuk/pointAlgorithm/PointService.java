package ch.kbw.kuk.pointAlgorithm;

import ch.kbw.kuk.entities.Circle;
import ch.kbw.kuk.entities.Point;
import ch.kbw.kuk.entities.Position;
import ch.kbw.kuk.sequentialAlgorithm.SequentialService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Lewin Gerber
 * @version 26.10.2020
 * KUK
 */

@Service
public class PointService {

    private PointAlgorithm pa;

    /*public void resolve(Session session) {
        session.startSession();
        session.closeSession();
    }

    public static void main(String[] args) {
        PointService sqs = new PointService();

        Instant start = Instant.now();
        Circle circle = sqs.pa.makeCircle(sqs.bigNummbersEdge);
        System.out.println(circle.getRadius());
        Instant end = Instant.now();

        System.out.println(start.toString());
        String[] startParts = start.toString().split("\\.");
        String s = startParts[startParts.length-1];

        System.out.println(end.toString());
        String[] endParts = end.toString().split("\\.");
        String e = endParts[endParts.length-1];

        int startTime = Integer.parseInt(s.substring(0, s.length()-1));
        int endTime = Integer.parseInt(e.substring(0, e.length()-1));
        System.out.println("Recorded Time: " + (endTime - startTime) + "ms [" + endTime + " - " + startTime + "]");
        //PointService ps = new PointService();
        //Circle solution = ps.start();
        //System.out.println("xPos: "+solution.getPosX() + " - yPos: " + solution.getPosY());
        //System.out.println("Radius: "+solution.getRadius());
    }
     */


    public PointService(){
        this.pa=new PointAlgorithm();
    }

    public static void main(String[] args) {
        SequentialService sqs = new SequentialService();
        PointService ps=new PointService();
        Circle circle = ps.pa.makeCircle(sqs.amountExtreme);
        System.out.println(circle.getRadius() + " - X:" + circle.getPosX() + " - Y:" + circle.getPosY());
    }
}
