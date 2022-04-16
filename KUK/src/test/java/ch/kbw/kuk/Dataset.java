package ch.kbw.kuk;

import ch.kbw.kuk.entities.Circle;
import ch.kbw.kuk.entities.Point;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Lewin Gerber
 * @version 30.10.2020
 * KUK
 */

public class Dataset {

    // FLOATS - 15 SMALL NUMBERS UNDER 100.00, < 15 DEC PLACES, EQUALLY DISTRIBUTED
    private Dataset dataset1 = new Dataset(new ArrayList<Point>(Arrays.asList(
            new Point(-2.680150262960178, 1.892156273478585),
            new Point(12.544858422804173, -80.94267650367611),
            new Point(47.90926882538714, -85.34428384985986),
            new Point(31.267193341818686, 20.69443858093038),
            new Point(-12.071544896640832, -33.32528794041559),
            new Point(-86.96088457269887, 23.095315315212424),
            new Point(-36.34123831017816, 17.493269601887657),
            new Point(-44.66227605196239, -59.33478589513773),
            new Point(99.10829368794154, 82.31204989749898),
            new Point(-63.3846109709769, -29.323826716612185),
            new Point(-7.217606213933366, 46.303790413272175),
            new Point(40.97507070723362, -16.91929692282163),
            new Point(17.052087199603964, 45.50349816851149),
            new Point(-99.544858422804173, 80.94267650367611),
            new Point(0.00000000000, 99.9999999999999)
    )));

    // FLOATS - 15 SMALL NUMBERS UNDER 100.00, < 15 DEC PLACES, GATHERED IN 2 PLACES
    private Dataset dataset2 = new Dataset(new ArrayList<Point>(Arrays.asList(
            new Point(-80.62502915424304, -0.068894844466086),
            new Point(-80.50320393915791, -0.114584450898519),
            new Point(-80.5364289978175, -0.058351089135525),
            new Point(-80.5364289978175, -0.058351089135525),
            new Point(-80.43675382183876, -0.012661482703092),
            new Point(-80.40906627295578, -0.07592401468646),
            new Point(-80.49766642938132, -0.128642791339267),
            new Point(96.60811378441646, -2.956540282053341),
            new Point(96.63536560337735, -2.817185614350055),
            new Point(96.42871673921894, -2.78439628077281),
            new Point(96.5690902967541, -2.797534846793158),
            new Point(96.58671148331247, -2.826892681144892),
            new Point(96.60310296975007, -2.806668963215512),
            new Point(96.58005720164912, -2.806668963215512),
            new Point(96.58005720164913, -2.806668963215513)
    )));

    // FLOATS - 15 SMALL NUMBERS UNDER 100.00, < 15 DEC PLACES, BORDERING THE CIRCLE
    private Dataset dataset3 = new Dataset(new ArrayList<Point>(Arrays.asList(
            new Point(-1.23773, 97.39975),
            new Point(-80.96847616396863, -85.00576393480317),
            new Point(95.85373487966738, 9.3203751210688),
            new Point(-48.1759933886034, 78.9717956821928),
            new Point(-67.14419420964799, 67.41528195680452),
            new Point(-109.90823453456234, 21.600235435783),
            new Point(79.77898842115502, 6.821669450714576),
            new Point(83.95842250036824, -37.53035619807291),
            new Point(40.8781019915551, -97.18695407778001),
            new Point(20.94541638299977, -105.30774750643124),
            new Point(-74.86007250973392, -86.25511676998027),
            new Point(-76.43795567768978, -82.1344314782516),
            new Point(-109.22891573257875, 17.680215275270473),
            new Point(12.06818943420165, 2.020469065971968),
            new Point(45.98840983437136, 71.5536518072184)
    )));

    private ArrayList<Point> points;
    private Circle solution;

    public Dataset(ArrayList<Point> points, Circle solution) {
        this.points = points;
        this.solution = solution;
    }

    public Dataset(ArrayList<Point> points) {
        this.points = points;
        this.solution = null;
    }

    //GETTER & SETTER
    public Dataset getDataset1() {
        return dataset1;
    }

    public void setDataset1(Dataset dataset1) {
        this.dataset1 = dataset1;
    }

    public Dataset getDataset2() {
        return dataset2;
    }

    public void setDataset2(Dataset dataset2) {
        this.dataset2 = dataset2;
    }

    public Dataset getDataset3() {
        return dataset3;
    }

    public void setDataset3(Dataset dataset3) {
        this.dataset3 = dataset3;
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
}
