import models.StationStop;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Cree on 25/03/2017.
 */
public class StationStopTest {

    @Test
    public void newStationShouldCreateValidStation() {
        StationStop stop = createValidStation();
        Assert.assertNotNull(stop);
    }

    @Test
    public void getNameShouldReturnName() {
        StationStop stop = createValidStation();
        Assert.assertTrue(stop.getName().equals(name1));
    }

    @Test
    public void getLineShouldReturnLine() {
        StationStop stop = createValidStation();
        Assert.assertTrue(stop.getLine().equals(line1));
    }

    @Test
    public void toStringShouldReturnLineToString() {
        StationStop stop = createValidStation();
        Assert.assertTrue(stop.toString().equals(lineToString));
    }

    @Test
    public void equalsShouldReturnTrueWhenSameNameAndLine() {
        StationStop stop = createValidStation();
        StationStop sameStationStop = createValidStationFromString(name1, line1);
        Assert.assertTrue(stop.equals(sameStationStop));
    }

    @Test
    public void equalsShouldReturnFalseWhenSameNameDifferentLine() {
        StationStop stop = createValidStation();
        StationStop sameStationStop = createValidStationFromString(name1, line2);
        Assert.assertFalse(stop.equals(sameStationStop));
    }

    @Test
    public void equalsShouldReturnFalseWhenDifferentNameSameLine() {
        StationStop stop = createValidStation();
        StationStop sameStationStop = createValidStationFromString(name2, line1);
        Assert.assertFalse(stop.equals(sameStationStop));
    }

    @Test
    public void equalsShouldReturnFalseWhenDifferentNameDifferentLine() {
        StationStop stop = createValidStation();
        StationStop sameStationStop = createValidStationFromString(name2, line2);
        Assert.assertFalse(stop.equals(sameStationStop));
    }

    //helper for station creation

    private String name1 = "Newcastle";
    private String name2 = "Morpeth";
    private String line1 = "A";
    private String line2 = "B";
    private String lineToString = "Newcastle on A line";

    private StationStop createValidStation() {
        StationStop stop = new StationStop(name1, line1);
        return stop;
    }

    private StationStop createValidStationFromString(String name, String line) {
        StationStop stop = new StationStop();
        stop.name = name;
        stop.line = line;
        return stop;
    }

}
