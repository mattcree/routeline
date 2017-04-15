package routefindertest;

import models.routefinder.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Cree on 25/03/2017.
 */
public class StopTest {

    @Test
    public void newStationShouldCreateValidStation() {
        Stop stop = createValidStation();
        Assert.assertNotNull(stop);
    }

    @Test
    public void getNameShouldReturnName() {
        Stop stop = createValidStation();
        Assert.assertTrue(stop.getName().equals(name1));
    }

    @Test
    public void getLineShouldReturnLine() {
        Stop stop = createValidStation();
        Assert.assertTrue(stop.getLine().equals(line1));
    }

    @Test
    public void toStringShouldReturnLineToString() {
        Stop stop = createValidStation();
        Assert.assertTrue(stop.toString().equals(lineToString));
    }

    @Test
    public void equalsShouldReturnTrueWhenSameNameAndLine() {
        Stop stop = createValidStation();
        Stop sameStop = new Stop(name1, line1);
        Assert.assertTrue(stop.equals(sameStop));
    }

    @Test
    public void equalsShouldReturnFalseWhenSameNameDifferentLine() {
        Stop stop = createValidStation();
        Stop sameStop = new Stop(name1, line2);
        Assert.assertFalse(stop.equals(sameStop));
    }

    @Test
    public void equalsShouldReturnFalseWhenDifferentNameSameLine() {
        Stop stop = createValidStation();
        Stop sameStop = new Stop(name2, line1);
        Assert.assertFalse(stop.equals(sameStop));
    }

    @Test
    public void equalsShouldReturnFalseWhenDifferentNameDifferentLine() {
        Stop stop = createValidStation();
        Stop sameStop = new Stop(name2, line2);
        Assert.assertFalse(stop.equals(sameStop));
    }

    //helper for station creation

    private String name1 = "Newcastle";
    private String name2 = "Morpeth";
    private String line1 = "A";
    private String line2 = "B";
    private String lineToString = "Newcastle on Line A";

    private Stop createValidStation() {
        return new Stop(name1, line1);
    }

}
