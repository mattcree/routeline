import models.StationStop;
import models.StopConnection;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Cree on 25/03/2017.
 */
public class StopConnectionTest {

    @Test
    public void createStopConnectionShouldCreateValidStopConnection() {
        StopConnection connection = createValidStopConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void getFromShouldReturnStopConnectionOrigin() {
        StopConnection connection = createValidStopConnection();
        Assert.assertTrue(connection.from().equals(from));
    }

    @Test
    public void getToShouldReturnStopConnectionDestination() {
        StopConnection connection = createValidStopConnection();
        Assert.assertTrue(connection.to().equals(to));
    }

    @Test
    public void getDistanceShouldReturnStopConnectionDistance() {
        StopConnection connection = createValidStopConnection();
        Assert.assertTrue(connection.time() == time);
    }

    @Test
    public void toStringShouldReturnValidRepresentationOfStopConnectionAsString() {
        StopConnection connection = createValidStopConnection();
        StationStop  stop1 = connection.stopA;
        StationStop  stop2 = connection.stopB;
        Assert.assertTrue(connection.toString().equals(stop1.toString() + " to " + stop2.toString()));
    }

    @Test
    public void isLineChangeShouldReturnTrueIfChangeOfLines() {
        StationStop stop1 = createValidStationFromString("Barry", "A");
        StationStop stop2 = createValidStationFromString("Barry2", "B");

        StopConnection connection = createValidStopConnectionFromStops(stop1, stop2, 15);
        Assert.assertTrue(connection.isLineChange());
    }

    @Test
    public void isLineChangeShouldReturnFalseIfNotLineChange() {
        StationStop stop1 = createValidStationFromString("Barry", "A");
        StationStop stop2 = createValidStationFromString("Barry2", "A");

        StopConnection connection = createValidStopConnectionFromStops(stop1, stop2, 15);
        Assert.assertFalse(connection.isLineChange());
    }

    //helpers for tests
    private StopConnection createValidStopConnection() {
        StationStop stop1 = new StationStop();
        stop1.name = name1;
        stop1.line = line;

        StationStop stop2 = new StationStop();

        stop2.name = name2;
        stop2.line = line;

        StopConnection stopConnection = new StopConnection();
        stopConnection.stopA = stop1;
        stopConnection.stopB = stop2;
        stopConnection.time = time;

        return stopConnection;
    }

    //helpers for tests
    private StopConnection createValidStopConnectionFromStops(StationStop stop1, StationStop stop2, int time) {
        StopConnection stopConnection = new StopConnection();
        stopConnection.stopA = stop1;
        stopConnection.stopB = stop2;
        stopConnection.time = time;

        return stopConnection;
    }

    private int time = 5;
    private String line = "A";
    private String name1 = "Newcastle";
    private String name2 = "Morpeth";

    private StationStop from = createValidStationFromString(name1, line);
    private StationStop to = createValidStationFromString(name2, line);

    private StationStop createValidStationFromString(String name, String line) {
        StationStop stop = new StationStop();
        stop.name = name;
        stop.line = line;
        return stop;
    }

}
